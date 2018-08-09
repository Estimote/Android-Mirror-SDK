# Estimote Mirror Android SDK

[Estimote Mirror]: http://blog.estimote.com/post/150398268230/launching-estimote-mirror-the-worlds-first

[![Download](https://api.bintray.com/packages/estimote/android/mirror-display-sdk/images/download.svg)](https://bintray.com/estimote/android/mirror-display-sdk/_latestVersion)
![Apache License 2.0](https://img.shields.io/badge/license-Apache%202.0-blue.svg)

*This SDK allows you to take control of the big screen from your Android app with [Estimote Mirror][]*.

**Main features:**

* *Building Mirror experience based on mobile SDKs* - you can start prototyping your first Mirror application, using only mobile Display SDK. There is no need to upload any code or resources upfront to the Mirror.

* *Pre-defined views* - No need to design your first Mirror app view. Android Mirror SDK lets you define customized screens based on pre-defined views; All you need to do is to declare basic styling and data content.

* *Feedback from Mirror screen to mobile* - Whenever any display action has been triggered, your mobile app is being notified about it. You can handle successful screen change and perform further actions with your mobile app.

Please check the rest of README to get further details.

We really appreciate your [feedback about our SDKs](#your-feedback-and-questions), thank you!

# Table of Contents

* [Installation](#installation)
* [Quick start](#quick-start)
* [Your feedback and questions](#your-feedback-and-questions)
* [Changelog](#changelog)
* [License](#license)

# Installation

## Prerequisities
* 1 [Estimote Mirror][] w/ **1.0.15+** firmware version.
* An account in [Estimote Cloud](https://cloud.estimote.com/).
* An Android device with Bluetooth Low Energy support. We suggest using Android 5.0+ (Lollipop or newer).

## Maven/Gradle dependency

Add Estimote's Maven repo to your **project's** build.gradle:

~~~ java
repositories {
    maven {
        url  "http://estimote.bintray.com/android"
    }
}
~~~

Then, add the **Display SDK** as well as **Proximity SDK** dependency to your **module's** build.gradle:

~~~ java
implementation 'com.estimote:display-sdk:0.1.7'
implementation 'com.estimote:proximity-sdk:0.6.2'
~~~

## Obtain app credentials from Estimote Cloud

To obtain Estimote Cloud credentials for your mobile application:

1. Log in to your [Estimote Cloud](https://cloud.estimote.com/) account.
2. Go to *Apps* section and click `Add new app` option.
3. Select `Your own app` option.
4. Save your *App Id/App Token* credentials.

## Check Bluetooth scanning requirements

Making sure that everything needed for Bluetooth scanning to work is set up - [the user has Bluetooth enabled, location permissions were granted, etc.](https://developer.android.com/training/permissions/requesting) Displaying default popup dialogs to enable Bluetooth and give needed permissions. You can find more details in [Proximity SDK readme](https://github.com/Estimote/Android-Proximity-SDK#checking-requirements-for-bluetooth-scanning-with-requirementswizard).

1. Add Estimote's support library `Mustard` to your module's `build.gradle` file:

~~~ java
implementation 'com.estimote:mustard:0.2.1'
~~~

2. Use `RequirementsWizard` before performing any Bluetooth action:

~~~Kotlin
RequirementsWizardFactory.createEstimoteRequirementsWizard().fulfillRequirements(
            YOUR_ACTIVITY_CONTEXT_HERE,
            onRequirementsFulfilled : { /* start the Bluetooth operations here! */ },
            onRequirementsMissing: { /* scanning won't work, handle this case in your app */ },
            onError: { /* Oops, some error occurred, handle it here! */ })
~~~

# Quick start

The following is simple example for showing Poster View on the screen, when user appears in Mirror nearby range.

```Kotlin
//KOTLIN

//Initialize your Estimote Cloud credentials
val cloudCredentials = EstimoteCloudCredentials(YOUR_APP_ID_HERE, YOUR_APP_TOKEN_HERE)
EstimoteSDK.initialize(applicationContext, cloudCredentials.appId, cloudCredentials.appToken)

//Define MirrorClient
val mirrorClient = MirrorClient.Builder(this).build()

//Declare your customized Poster View
val defaultPosterViewStyle = PosterViewStyle.Builder().create()
val defaultPosterViewData = PosterViewData.Builder()
        .setHeader("Congratulations!")
        .setBody("You've just created a Poster View! \n Let's tweak it a little bit!")
        .setImage("poster.jpg")
        .create()
val posterView = PosterView(defaultPosterViewData, defaultPosterViewStyle)

//Build ProximityObserver with Cloud credentials
proximityObserver = ProximityObserverBuilder(applicationContext, cloudCredentials)
        .withLowLatencyPowerMode()
        .withTelemetryReportingDisabled()
        .withEstimoteSecureMonitoringDisabled()
        .withOnErrorAction { /* Handle an error here! */}
        .build()

//Define near proximity zone
val nearZone = proximityObserver.zoneBuilder()
        .forTag("mirror")
        .inNearRange()
        .withOnEnterAction {
          //Display a Poster View when you are in Mirror near range
          MirrorClient
          .forDevice(it.getInfo().getDeviceId())
          .take(posterView)
          .display() }
        .create()

//Start proximity observation
observationHandler = proximityObserver.addProximityZone(nearZone).start()
```

Zone monitoring is based on Estimote Proximity SDK - most reliable signal-processing technology.

To get more details, you can find [setting up ProximityObserver](https://github.com/Estimote/Android-Proximity-SDK#2-build-proximity-observer) or [defining Proximity Zones](https://github.com/Estimote/Android-Proximity-SDK#3-define-proximity-zones) at Github readme.

# Your feedback and questions

At Estimote we're massive believers in feedback! Here are some common ways to share your thoughts with us:

* Posting issue/question/enhancement on [our issues page](../../../issues).
* Asking our community managers on our [Estimote SDK for Android forum](https://forums.estimote.com).

# Changelog

To see what has changed in recent versions of our SDK, please visit [our releases page](../../../releases).

# License

[Apache 2.0](../license.txt)
