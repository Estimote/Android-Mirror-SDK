<p align="center">
    <a href="http://blog.estimote.com/post/150398268230/launching-estimote-mirror-the-worlds-first"><b>Mirror</b></a> Display for Android
</p>
<p align="center">
    <a href="https://estimote.canny.io/mirror-display">
        <img src="https://img.shields.io/badge/feature%20request-canny.io-blue.svg"
             alt="feature">
    </a>
    <a href="https://opensource.org/licenses/MIT">
        <img src="https://img.shields.io/badge/License-MIT-yellow.svg" alt="licence">
    </a>
    <a>
        <img src="https://www.bitrise.io/app/55cd8edc4f83f32e/status.svg?token=KN5gaUHiK2opILjxV8tcBg&branch=master"
             alt="build status">
    </a>
    <a href="https://discord.gg/HjJCwm5">
        <img src="https://img.shields.io/discord/308323056592486420.svg"
             alt="chat on Discord">
    </a>
</p>


# Mirror Context SDK for Android
Mirror Context is library for Android that enables to build your Mirror oriented apps and integrating with existing ones. Allows to display content on the screen based on physical context.
The SDK system works on Android 4.3 or above and requires device with Bluetooth Low Energy (SDK's min Android SDK version is 9).

## Installing

Mirror Core is not publicly available yet. However you can get its latest version from our internal repository. 
First - configure Estimote Maven repository:
```
repositories {
    maven {
        url  "http://estimote.bintray.com/android"
    }
}
```

Then - simply add compile dependency to the latest sdk version.
In you are using **Maven**, please use following dependency:

```
<dependency>
  <groupId>com.estimote</groupId>
  <artifactId>context</artifactId>
  <version>0.1.0</version>
  <type>pom</type>
</dependency>
```

For **Gradle**  - add compile dependency as follows:

```
compile 'com.estimote:context:0.1.0'
```

## 1. Create `MirrorContextManager` instance
```Java
MirrorContextManager ctxMgr = MirrorContextManager.createMirrorContextManager(context);
```

## 2. Define dictionary 
Dictionary is an object encapsulating data you’d like the Mirror to display on the screen. 
You can use it to specify the name of the template Mirror should use to construct it's view (note that the appropriate template needs to be pre-loaded to the Mirror device)
```Java
Dictionary dictionary = new Dictionary();
dictionary.setTemplate("table");
```
However - Setting template is probably not enough. If your template requires some input data, then you have to provide them:
```Java
dict.setTemplate("table");
dict.put("Name", "Luke");
dict.put("Surname", "Skywalker");
dict.put("Age", 20);
```
Templates are just HTML pages that are switched by Mirror and fed with data using JavaScript API:
```html
<html>
<head>
    <script language="JavaScript">
        if (mirror) {
            mirror.init();
            mirror.listen(Mirror.Events.USER_DATA, {
                ondata: function(ev) {
                    if (ev.data.title) {
                        document.getElementById("text").innerText = ev.data.title;
                    }
                },
                ondisconnect: function(ev) {
                    // Return to default template.
                    mirror.selectTemplate(null)
                }
            })
        }
    </script>
</head>
<body>
    <div id="center_frame">
        <div id="text">Default text</div>
    </div>
</body>
</html>
```


### 3. Let the magic happen - display stuff on your Mirror
Call display method to send data to Mirror device. You can specify predicate indicating when 
given dictionary should be displayed:
```java
ctxMgr.display(dict, Zone.NEAR, new DisplayCallback() {
    @Override public void onDataDisplayed() {
        // Called when sending data to the Mirror succeeded
    }

    @Override public void onFinish() {
        // Called when sending data display finished
    }

    @Override public void onFailure(MirrorException exception) {
        // Called when sending data to the Mirror failed
    }
});
```

## Opening issues
Delivering best developer tools is our goal. Hence, if you struggle with some issue during development process, please [let us know](https://github.com/Estimote/Android-Mirror-SDK/issues) about it.

Also don't forget to check current issues - maybe we've already aware of that trying to solve it!

## Getting help
If you need any further help, please visit our [forum](https://forums.estimote.com/).