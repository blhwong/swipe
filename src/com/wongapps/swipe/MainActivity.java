/*****************************************************************************
wongapps
Binocular Project
Created by: Brandon Wong
Email: b.lh.wong@utexas.edu
Last Modified: April 14, 2015
Description: Application that swipes frames of given images from drawable
******************************************************************************/


package com.wongapps.swipe;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.Toast;



public class MainActivity extends ActionBarActivity implements OnClickListener{
	

	private Integer[] mImage = {R.drawable.frame1, R.drawable.frame2, R.drawable.frame3,
			R.drawable.frame4, R.drawable.frame5, R.drawable.frame6, 
			R.drawable.frame7, R.drawable.frame8, R.drawable.frame9, 
			R.drawable.frame10, R.drawable.frame11, R.drawable.frame12, 
			R.drawable.frame13, R.drawable.frame14, R.drawable.frame15, 
			R.drawable.frame16, R.drawable.frame17, R.drawable.frame18, 
			R.drawable.frame19, R.drawable.frame20, R.drawable.frame21, 
			R.drawable.frame22, R.drawable.frame23, R.drawable.frame24, 
			R.drawable.frame25, R.drawable.frame26, R.drawable.frame27, 
			R.drawable.frame28, R.drawable.frame29, R.drawable.frame30, 
			R.drawable.frame31, R.drawable.frame32, R.drawable.frame33, 
			R.drawable.frame34, R.drawable.frame35, R.drawable.frame36, 
			R.drawable.frame37, R.drawable.frame38, R.drawable.frame39, 
			R.drawable.frame40, R.drawable.frame41, R.drawable.frame42, 
			R.drawable.frame43, R.drawable.frame44
	};
	
	ImageView imageView;
	private GestureDetector swipeDetector;
	View.OnTouchListener swipeListener;
	private static final int start = 0;							//first frame
	private static final int SWIPE_MIN_DISTANCE = 5;			//min distance of swipe
	private static float thresholdWidth;						//60% of screen width which is initalized onCreate
	private static int pointer;									//pointer of frame
	
	

	@SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
                
        

        ImageView iView = new ImageView(this);
        showView(start, iView);
        
        
        Display display = getWindowManager().getDefaultDisplay();
        thresholdWidth = (float) (display.getWidth() * 0.6);			//set 60% of screen width
        pointer = start;
        
        iView.setOnClickListener(this);
        swipeDetector = new GestureDetector(this, new MySwipeDetector());
        swipeListener = new View.OnTouchListener()
        {
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return swipeDetector.onTouchEvent(event);
			}
        };
        iView.setOnTouchListener(swipeListener);
        
    }
	
	private void showView(int pointer, ImageView view)					//show frame method
	{				
		// TODO Auto-generated method stub
		RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        RelativeLayout mlayout = (RelativeLayout)findViewById(R.id.layout);
        view.setLayoutParams(param);
        view.setScaleType(ScaleType.FIT_CENTER);
        Drawable d = getResources().getDrawable(mImage[pointer]);
        view.setImageDrawable(d);
        mlayout.addView(view);
		
	}

	class MySwipeDetector extends SimpleOnGestureListener				//swipe detector
	{
	
		@SuppressWarnings("deprecation")
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
		{
			try
			{
				if((e1.getX() - e2.getX()) > SWIPE_MIN_DISTANCE)		//if left swipe is greater than minimum swipe distance
				{
					
					if((e1.getX() - e2.getX()) > thresholdWidth)		//if left swipe is greater than 60% of screen
					{
						
						if(pointer < mImage.length - 1)					//animate to the end if not already at end
						{
							final AnimationDrawable animation = new AnimationDrawable();
							for(int begin = pointer; pointer < mImage.length; pointer++)		//adding frames
							{
								animation.addFrame(getResources().getDrawable(mImage[pointer]), 0);
							}
					        pointer = mImage.length - 1;
					        runAnimation(animation);
							//final Toast toast = Toast.makeText(getApplicationContext(), "Left Swipe, Animate Right", Toast.LENGTH_SHORT);
			    			//toast.show();
						}
						else											//already at the end of animation
						{
							//final Toast toast = Toast.makeText(getApplicationContext(), "End of Animation", Toast.LENGTH_SHORT);
			    			//toast.show();
						}
						
					}
					else												//if left swipe is less than 60% of screen
					{
						if(pointer < mImage.length - 1)					//move to next frame if not at the end
						{
							pointer++;									
							ImageView iView = new ImageView(getApplicationContext());
							showView(pointer, iView);					
							//final Toast toast = Toast.makeText(getApplicationContext(), "Left Swipe, Increment", Toast.LENGTH_SHORT);
			    			//toast.show();
						}
						else											//already at the end of frames		
						{
							//final Toast toast = Toast.makeText(getApplicationContext(), "No more to increment", Toast.LENGTH_SHORT);
			    			//toast.show();
						}
						
					}
					
				}
				else if((e2.getX() - e1.getX()) > SWIPE_MIN_DISTANCE)	//if right swipe is greater than minimum swipe distance
				{
					if((e2.getX() - e1.getX()) > thresholdWidth)		//if right swipe is greater than 60% of the screen
					{
						if(pointer > start)								//animate to beginning if not already
						{
							final AnimationDrawable animation = new AnimationDrawable();
							for(int begin = pointer; pointer >= start; pointer--)		//adding frames
							{
								animation.addFrame(getResources().getDrawable(mImage[pointer]), 0);
							}
					        pointer = start; 
					        runAnimation(animation);
							//final Toast toast = Toast.makeText(getApplicationContext(), "Right Swipe, Animate Left", Toast.LENGTH_SHORT);
			    			//toast.show();
						}
						else											//already at beginning of animation
						{
							//final Toast toast = Toast.makeText(getApplicationContext(), "Beginning of Animation", Toast.LENGTH_SHORT);
			    			//toast.show();
						}
						
					}
					else
					{
						if(pointer > start)								//move to previous frame if not at beginning
						{
							pointer--;
							ImageView iView = new ImageView(getApplicationContext());
							showView(pointer, iView);
							//final Toast toast = Toast.makeText(getApplicationContext(), "Right Swipe, Decrement", Toast.LENGTH_SHORT);
			    			//toast.show();
						}
						else											//already at beginning of frames
						{
							//final Toast toast = Toast.makeText(getApplicationContext(), "No more to decrement", Toast.LENGTH_SHORT);
			    			//toast.show();
						}
					}
				}

			}
			catch(Exception e)
			{
				
			}
			return false;
			
		}

		private void runAnimation(final AnimationDrawable animation) 				//animation method
		{
			// TODO Auto-generated method stub
			RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	        RelativeLayout mlayout = (RelativeLayout)findViewById(R.id.layout);
	        ImageView imageAnim = new ImageView(getApplicationContext());
	        imageAnim.setLayoutParams(param);
	        imageAnim.setScaleType(ScaleType.FIT_CENTER);
	        imageAnim.setImageDrawable(animation);
	        mlayout.addView(imageAnim);
	        Handler handler = new Handler();
	        handler.postDelayed(new Runnable(){

	        public void run(){

	        animation.start();

	        }
	        }, 0);
			
		}
		
		
	}


    public void onClick(View v)
    {
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
