
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

Mirror Display is library for Android that enables to build Mirror experience with customized screens based on **built-in views**.

<p align="center">
<a><b>Got a question?</b> Join us on <a href="https://gitter.im/Estimote-Mirror-Display/Lobby">Gitter</a>, or head over to <a href="http://stackoverflow.com/questions/tagged/estimote">stackoverflow</a> and our <a href="https://forums.estimote.com">community forums</a>.</a>
</p>

## Motivation
We believe that prototyping with Mirror **should not require any web development skills**, not even bringing web dev in!

Hence, your Android dev skills is the only requirement to make your plans come true!
## Installing

Right now .aars are available from public Maven repository.

Copy generated Gradle configuration:

```
repositories {
    maven {
        url  "http://estimote.bintray.com/android"
    }
}
```

In order to pull it via **Maven**, please use following dependency:

```
<dependency>
  <groupId>com.estimote</groupId>
  <artifactId>display-sdk</artifactId>
  <version>0.1.0</version>
  <type>pom</type>
</dependency>
```

or **Gradle** option:

```
compile 'com.estimote:display-sdk:0.1.0'
```

## 1. Create MirrorClient

Define general display rules for the client.
```java
MirrorClient mirrorClient = new MirrorClient.Builder()
.useMirrorWithIds("9f1a787ad3764057311e043f63921917")
.setDebugModeEnabled(true)
.build();
```


## 2. Pick & tune view

Select view that match your needs, fill it in with data and adjust its layout.

### Poster View
```java
//Define poster view style
PosterViewStyle posterViewStyle =
        new PosterViewStyle.Builder()
                .setImagePosition(new Position(Horizontal.right(190), Vertical.top(5)))
                .setBackgroundImage("assets/voronoi_bg.png")
                .create();

//Fill in with the data
PosterViewData posterViewData =
        new PosterViewData.Builder()
                .setHeader("Discover Mirror with\nthe Estimote App")
                .setBody("See the Mirror Demos to learn\nhow Mirror works and what you\ncan use it for.")
                .setImage("assets/estimote_app.png")
                .create();

//Finally, declare the view!
PosterView posterView = new PosterView(posterViewData, posterViewStyle);
```

### Table View

```java

//Define table view style
TableViewStyle tableViewStyle = new TableViewStyle.Builder()
        .setBackgroundColor("#2c2a3d")
        .setFontColor("#ffffff")
        .setRowSeparatorColor("#4c445e")
        .setHeaderFontColor("#ffffff")
        .setHeaderBackgroundColor("#6042a2")
        .setColumnHeadersFontColor("#8d6bd2")
        .create();

//Add columns and fill in the rows       
String[] columns = new String[]{"FLIGHT NO.", "DESTINATION", "TIME", "STATUS", "GATE"};
List<String[]> rows = new ArrayList<>();
rows.add(new String[]{"UA740", "NEW YORK", "6:00AM", "ON TIME", "7"});
rows.add(new String[]{"BA214", "SAN FRANCISCO", "8:20AM", "ON TIME", "22"});

//Put it into view data
TableViewData tableViewData = new TableViewData.Builder()
        .setHeader("Departures")
        .setColumns(columns)
        .setRows(rows)
        .create();

//Declare the view
TableView tableView = new TableView(tableViewData, tableViewStyle);
```

## 3. Display it!
Determine which proximity zone should be trigger for view change. Declare the action you would like to perform and handle it with `DisplayCallback`.

```java
mirrorClient.when(MirrorZone.ANY).thenShow(view, new com.estimote.display.client.DisplayCallback() {
    @Override
    public void onViewOperationDone(ViewOperation viewOperation, com.estimote.display.view.View view) {
        //Yay, it works!
    }

    @Override
    public void onViewOpetationFailed(ViewOperation show, com.estimote.display.view.View view, String message) {

    }
});

```

## Documentation
The documentation for Mirror Display API can be found [here](https://github.com/Estimote/Android-Mirror-SDK/blob/abiela/display_sdk/MirrorDisplaySDK/mirror-display-sdk-0.1.0-javadoc.jar).

## Opening issues
Delivering best developer tools is our goal. Hence, if you struggle with some issue during development process, please [let us know](https://github.com/Estimote/Android-Mirror-SDK/issues) about it.

Also don't forget to check current issues - maybe we've already aware of that trying to solve it!

## Getting help
If you need any further help, please visit our [forum](https://forums.estimote.com/).
