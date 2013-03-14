package com.tobykurien.scriptdroid.components;

import android.content.Context;
import android.widget.Toast;

public class Alert extends BaseComponent {

   public Alert(Context context) {
      super(context);
   }

   public void toast(String message) {
      Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
   }
   
}
