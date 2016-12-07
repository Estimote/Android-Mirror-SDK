package com.estimote.mirror.simpledemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.estimote.coresdk.common.SystemRequirementsChecker;
import com.estimote.coresdk.common.config.EstimoteSDK;
import com.estimote.sdk.mirror.context.Dictionary;
import com.estimote.sdk.mirror.context.DisplayCallback;
import com.estimote.sdk.mirror.context.MirrorContextManager;
import com.estimote.sdk.mirror.context.Zone;
import com.estimote.sdk.mirror.core.MirrorException;

public class MainActivity extends AppCompatActivity {

  private Button bigButton;
  private Button smallButton;
  private MirrorContextManager mirrorContext;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    bigButton = (Button) findViewById(R.id.big_button);
    bigButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onBigButton();
      }
    });
    smallButton = (Button) findViewById(R.id.small_button);
    smallButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onSmallButton();
      }
    });
    // Init Estimote SDK
    EstimoteSDK.initialize(getApplicationContext(), "", "");
    // Init Mirror Context SDK.
    mirrorContext = new MirrorContextManager(this);
    // Check for Bluetooth permissions.
    SystemRequirementsChecker.checkWithDefaultDialogs(this);
  }

  private void onBigButton() {
    Dictionary data = new Dictionary();
    data.put("template", "welcome");
    data.put("title", "Hello!!!");
    data.put("bgcolor", "yellow");
    data.put("subtitle", "Welcome to my app");

    mirrorContext.display(data, Zone.WHEREVER_YOU_ARE, new DisplayCallback() {
      @Override
      public void onDataDisplayed() {
        System.out.println("Message sent");
      }

      @Override
      public void onFailure(MirrorException exception) {
        System.out.println("Message not sent");
      }
    });
  }

  private void onSmallButton() {
    Dictionary data = new Dictionary();
    data.put("template", "welcome");
    data.put("title", "Hello again!!!");
    data.put("bgcolor", "green");
    data.put("subtitle", "This is TEMPLATE!!!!");
    data.put("font", "monospace");

    mirrorContext.display(data, Zone.WHEREVER_YOU_ARE, new DisplayCallback() {
      @Override
      public void onDataDisplayed() {
        System.out.println("Message sent");
      }

      @Override
      public void onFailure(MirrorException exception) {
        System.out.println("Message not sent");
      }
    });
  }
}
