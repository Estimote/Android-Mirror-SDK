# Estimote Mirror SDK for Android

The Mirror SDK for Android are libraries that allow interaction with [Estimote Mirror](http://estimote.com/#jump-to-products). The SDK system works on Android 4.3 or above and requires device with Bluetooth Low Energy (SDK's min Android SDK version is 9).

Mirror SDK consists of:

- Context SDK - for building your Mirror oriented apps and integrating with existing ones. Allows to display content on the screen based on physical context.
- Management SDK - for changing configuration of your Mirror device and performing maintenance tasks. Can be used to build your own configuration application.

To display already prepared template on Mirror screen you can use code similar to this snippet:

```java
   MirrorContextManager ctxMgr = MirrorContextManager.createMirrorContextManager(context);
   Dictionary dict = new Dictionary();
   dict.setTemplate("table");
   dict.put("Name", "Luke");
   dict.put("Surname", "Skywalker");
   dict.put("Age", 20);

  ctxMgr.display(dict, Zone.NEAR, new DisplayCallback() {
      @Override
      public void onDataDisplayed() {
        Log.i(TAG, "Data displayed");
      }

      @Override
      public void onFinish() {
        Log.i(TAG, "Data display finished");
      }

      @Override
      public void onFailure(MirrorException exception) {
        Log.e(TAG, "Data sending error", exception);
      }
  });
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
