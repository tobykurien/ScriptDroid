package com.tobykurien.scriptdroid.components;

import android.content.Context;
import bsh.Interpreter;

public class BaseComponent {
   private Context context;
   
   public BaseComponent(Context context) {
      this.context = context;
   }
   
   public Context getContext() {
      return context;
   }
}
