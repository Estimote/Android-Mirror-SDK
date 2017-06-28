# Estimote Mirror Android SDK

**Take control of the big screen from your Android app with [Estimote Mirror][]**

[Estimote Mirror]: http://blog.estimote.com/post/150398268230/launching-estimote-mirror-the-worlds-first

[![Feature requests](https://img.shields.io/badge/feature%20request-canny.io-blue.svg)](https://estimote.canny.io/mirror-display)
[![Apache License 2.0](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://tldrlegal.com/license/apache-license-2.0-(apache-2.0))
[![Chat on Gitter](https://img.shields.io/gitter/room/nwjs/nw.js.svg)](https://gitter.im/EstimoteMirror/Mirror-SDK-Android)

**Got a question?** Join us on [Gitter][], or head over to our [community forums][].

[Gitter]: https://gitter.im/EstimoteMirror
[community forums]: https://forums.estimote.com

**Table of Contents:**

* [Getting started](#getting-started)
  + [Hello, world!](#hello-world)
* [Documentation](#documentation)
* ["Work in progress" disclaimer](#work-in-progress-disclaimer)

## Getting started

Add Estimote's Maven repo to your **project's** build.gradle:

~~~ java
repositories {
    maven {
        url  "http://estimote.bintray.com/android"
    }
}
~~~

Then add the Display SDK dependency to your **module's** build.gradle:

~~~ java
dependencies {
    // ...
    compile 'com.estimote:display-sdk:0.1.0'
}
~~~

### Hello, world!

```java
public class MainActivity extends AppCompatActivity {

    private MirrorClient mirrorClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PosterViewData posterData = new PosterViewData.Builder()
            .setHeader("Hello, world!")
            .setBody("Programmable screen is here.")
            .setImage("assets/shoe_big.jpg")
            .create();

        PosterViewStyle posterStyle = new PosterViewStyle.Builder()
            .setTextAlign("center")
            .setTextPosition(new Position(Horizontal.center(), Vertical.bottom(80)))
            .setImagePosition(new Position(Horizontal.center(), Vertical.top(80)))
            .create();

        PosterView poster = new PosterView(posterData, posterStyle);

        this.mirrorClient = new MirrorClient.Builder()
            // replace with your own Mirror's ID
            .useMirrorWithIds("9f1a787ad3764057311e043f63921917")
            .setDebugModeEnabled(true)
            .setRepeatableDisplayRequests(true)
            .build();

        this.mirrorClient.when(MirrorZone.NEAR).thenShow(poster, new DisplayCallback() {
            @Override
            public void onViewOperationDone(ViewOperation viewOperation,
                    com.estimote.display.view.View view) {
                Log.d("Mirror", "Yay!")
            }

            @Override
            public void onViewOperationFailed(ViewOperation show,
                    com.estimote.display.view.View view, String message) {
                Log.d("Mirror", "Oh no!")
            }
        });
    }
}
```

## Documentation

We have extensive documentation available on [Estimote Developer Portal](http://developer.estimote.com).

The best place to get started is with … [Intro to Estimote Mirror](http://developer.estimote.com/mirror/)!

## "Work in progress" disclaimer

The APIs are not yet considered stable and will likely change … for better, and more powerful, naturally 😇 We'll be providing detailed changelogs and migration advice as we go.
