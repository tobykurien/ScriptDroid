package com.tobykurien.scriptdroid;

import java.io.InputStreamReader;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;
import bsh.Interpreter;

public class MainActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      //setContentView(R.layout.activity_main);

      Interpreter i = new Interpreter();  // Construct an interpreter
      try {
         i.set("activity", this);
         i.set("layout", new R.layout());
         
         i.set("foo", 5);                    // Set variables
         i.set("date", new Date() ); 

         Date date = (Date)i.get("date");    // retrieve a variable

         // Eval a statement and get the result
         i.eval("bar = foo*10");             
         Log.d("bsh", String.valueOf(i.get("bar")) );

         // Source an external script file
         i.eval(new InputStreamReader(getAssets().open("scripts/test.js")));
         Toast.makeText(this, String.valueOf(i.get("output")), Toast.LENGTH_LONG).show();
      } catch (Exception e) {
         Log.e("bsh", "error", e);
      }      
      
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }

}
