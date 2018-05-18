# Estimote Mirror Android SDK

[Estimote Mirror]: http://blog.estimote.com/post/150398268230/launching-estimote-mirror-the-worlds-first

[![Download](https://api.bintray.com/packages/estimote/android/mirror-display-sdk/images/download.svg)](https://bintray.com/estimote/android/mirror-display-sdk/_latestVersion)
![Feature requests](https://www.bitrise.io/app/55cd8edc4f83f32e/status.svg?token=KN5gaUHiK2opILjxV8tcBg&branch=master) 
![Apache License 2.0](https://img.shields.io/badge/license-Apache%202.0-blue.svg)

*This SDK allows you to take control of the big screen from your Android app with [Estimote Mirror][]*.

**Main features:**

* *Building Mirror experience based on mobile SDKs* - you can start prototyping your first Mirror application, using only mobile Display SDK. There is no need to upload any code or resources upfront to the Mirror.

* *Pre-defined views* - No need to design your first Mirror app view. Android Mirror SDK lets you define customized screens based on pre-defined views; All you need to do is to declare basic styling and data content.

* *Display rules based on proximity* - You can easily take control over Mirror-plugged screen with display rules. Building on top of screen proximity conditions i.e. when you're nearby the screen, you can define different screen behavior.

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

Then, add the Display SDK dependency to your **module's** build.gradle:

~~~ java
compile 'com.estimote:display-sdk:0.1.7'
~~~

## Obtain app credentials from Estimote Cloud

To obtain Estimote Cloud credentials for your mobile application:

1. Log in to your [Estimote Cloud](https://cloud.estimote.com/) account.
2. Go to *Apps* section and click **Add new app** option.
3. Select **Your own app** option.
4. Save your App Id/App Token credentials.

## Initialize Estimote SDK in your Android project

Initialize Estimote SDK in your Application class `onCreate()` method:

~~~ java
//  To get your AppId and AppToken you need to create a new application in Estimote Cloud.
EstimoteSDK.initialize(applicationContext, <YOUR_APP_ID>, <YOUR_APP_TOKEN>);
// Optional, debug logging.
EstimoteSDK.enableDebugLogging(true);

~~~

## Grant Android runtime permissions

Ask for runtime `ACCESS_COARSE_LOCATION` permission - [ it is required to enable Bluetooth scanning since Android 6.0](https://developer.android.com/training/permissions/requesting). You can use provided helper that will also check if Bluetooth adapter is enabled

~~~ java
@Override
void onStart() {
    super.onStart();
    SystemRequirementsChecker.checkWithDefaultDialogs(this);
}
~~~

# Quick start

The following is simple example for showing Poster View on the screen.

```java
//Java
MirrorClient mirrorClient=
        new MirrorClient.Builder(this).build();

PosterViewStyle defaultPosterViewStyle=
        new PosterViewStyle.Builder().create();

PosterViewData defaultPosterViewData=
        new PosterViewData.Builder()
        .setHeader("Congratulations!")
        .setBody("You've just created a Poster View! \n Let's tweak it a little bit!")
        .setImage("poster.jpg")
        .create();

PosterView posterView = new PosterView(defaultPosterViewData,defaultPosterViewStyle);

mirrorClient.when(MirrorZone.ANY).thenShow(posterView,new DisplayCallback(){
        @Override
        public void onViewOperationDone(ViewOperation viewOperation,com.estimote.display.view.View view){
            /* View has been displayed! You can refresh the UI and inform user about it. */
            }

        @Override
        public void onViewOperationFailed(ViewOperation show,com.estimote.display.view.View view,String message){
            /* Oops! Something went wrong - check the message and maybe try to display the view once again! */
            }
        });
```

# Your feedback and questions

At Estimote we're massive believers in feedback! Here are some common ways to share your thoughts with us:

* Posting issue/question/enhancement on [our issues page](../../../issues).
* Asking our community managers on our [Estimote SDK for Android forum](https://forums.estimote.com).

# Changelog

To see what has changed in recent versions of our SDK, please visit [our releases page](../../../releases).

# License

[Apache 2.0](../license.txt)
