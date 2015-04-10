package com.nullcognition.reboundexamples;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;


public class MainActivity extends ActionBarActivity {

   Spring spring;

   @Override
   protected void onCreate(Bundle savedInstanceState){

	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_main);

	  final ImageButton myView = (ImageButton)findViewById(R.id.imageButton);

	  createReboundSpring(myView);
	  setOnclick(myView);

   }

   private void createReboundSpring(final ImageButton inMyView){

// Create a system to run the physics loop for a set of springs.
	  SpringSystem springSystem = SpringSystem.create();

// Add a spring to the system.
	  spring = springSystem.createSpring();

// Add a listener to observe the motion of the spring.
	  spring.addListener(new SimpleSpringListener() {

		 @Override
		 public void onSpringUpdate(Spring spring){
			// You can observe the updates in the spring
			// state by asking its current value in onSpringUpdate.
			float value = (float)spring.getCurrentValue();
			float scale = 1f - (value * 0.5f);
			inMyView.setScaleX(scale);
			inMyView.setScaleY(scale);
		 }
	  });
   }

   private void setOnclick(ImageButton inImageButton){

	  inImageButton.setOnTouchListener(new View.OnTouchListener() {

		 @Override
		 public boolean onTouch(View v, MotionEvent event){

			int me = event.getAction();

			if(me == MotionEvent.ACTION_DOWN){
			   spring.setEndValue(1);
			   return true;
			}
			// Set the spring in motion; moving from 0 to 1
			else if(me == MotionEvent.ACTION_UP){
			   spring.setEndValue(0);
			   return true;
			}

			return false;
		 }
	  });

   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu){

	  getMenuInflater().inflate(R.menu.menu_main, menu);
	  return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item){

	  int id = item.getItemId();

	  if(id == R.id.action_settings){
		 return true;
	  }

	  return super.onOptionsItemSelected(item);
   }
}
