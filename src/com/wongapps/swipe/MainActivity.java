/*****************************************************************************
wongapps
Binocular Project
Created by: Brandon Wong
Email: b.lh.wong@utexas.edu
Last Modified: April 14, 2015
Description: Application that swipes frames of given images from drawable
******************************************************************************/


package com.wongapps.swipe;

import android.app.Notification.Action;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
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
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;



public class MainActivity extends ActionBarActivity implements OnClickListener{
	

	private final Integer[] mImage = {R.drawable.frame1, R.drawable.frame2, R.drawable.frame3,
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
	
	private final Integer[] cache = {R.drawable.frame1_100x100, R.drawable.frame2_100x100, R.drawable.frame3_100x100,
			R.drawable.frame4_100x100, R.drawable.frame5_100x100, R.drawable.frame6_100x100, 
			R.drawable.frame7_100x100, R.drawable.frame8_100x100, R.drawable.frame9_100x100, 
			R.drawable.frame10_100x100, R.drawable.frame11_100x100, R.drawable.frame12_100x100, 
			R.drawable.frame13_100x100, R.drawable.frame14_100x100, R.drawable.frame15_100x100, 
			R.drawable.frame16_100x100, R.drawable.frame17_100x100, R.drawable.frame18_100x100, 
			R.drawable.frame19_100x100, R.drawable.frame20_100x100, R.drawable.frame21_100x100, 
			R.drawable.frame22_100x100, R.drawable.frame23_100x100, R.drawable.frame24_100x100, 
			R.drawable.frame25_100x100, R.drawable.frame26_100x100, R.drawable.frame27_100x100, 
			R.drawable.frame28_100x100, R.drawable.frame29_100x100, R.drawable.frame30_100x100, 
			R.drawable.frame31_100x100, R.drawable.frame32_100x100, R.drawable.frame33_100x100, 
			R.drawable.frame34_100x100, R.drawable.frame35_100x100, R.drawable.frame36_100x100, 
			R.drawable.frame37_100x100, R.drawable.frame38_100x100, R.drawable.frame39_100x100, 
			R.drawable.frame40_100x100, R.drawable.frame41_100x100, R.drawable.frame42_100x100, 
			R.drawable.frame43_100x100, R.drawable.frame44_100x100
	};
	
	ImageView imageView;
	private GestureDetector swipeDetector;
	View.OnTouchListener swipeListener;
	private static final int start = 0;							//first frame
	//private static final int SWIPE_MIN_DISTANCE = 150;			//min distance of swipe
	private static final int SWIPE_MIN_DISTANCE = 0;
	private static float thresholdWidth;						//60% of screen width which is initalized onCreate
	private static int pointer;									//pointer of frame
	private static int end;
	


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
                
        

        final ImageView iView = new ImageView(this);
        showView(start, iView);
        //showView(start);
       
        
        
        
        Display display = getWindowManager().getDefaultDisplay();
        thresholdWidth = (float) (display.getWidth() * 0.6);			//set 60% of screen width
        pointer = start;
        end = mImage.length;
        

        
        
        
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
	//private void showView(int pointer)
	{				
		// TODO Auto-generated method stub
		//ImageView view = (ImageView)findViewById(R.id.test);
		final RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        final RelativeLayout mlayout = (RelativeLayout)findViewById(R.id.layout);
        //mlayout.removeAllViews();
        //mlayout.removeView(findViewById(R.id.test));
        //ImageView view = new ImageView(getApplicationContext());
        view.setLayoutParams(param);
        ((ImageView) view).setScaleType(ScaleType.FIT_CENTER);
        final Drawable d = ResourcesCompat.getDrawable(getResources(), mImage[pointer], null);
        view.setImageDrawable(d);
        mlayout.addView(view);
		
	}
	
	private void runAnimation(final AnimationDrawable animation) 				//animation method
	{
		// TODO Auto-generated method stub
		final RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        final RelativeLayout mlayout = (RelativeLayout)findViewById(R.id.layout);
        //mlayout.removeAllViews();
        //mlayout.removeAllViewsInLayout();
        //mlayout.removeView(findViewById(R.id.test));
        final ImageView imageAnim = new ImageView(getApplicationContext());
        imageAnim.setLayoutParams(param);
        imageAnim.setScaleType(ScaleType.FIT_CENTER);
        imageAnim.setImageDrawable(animation);
        mlayout.addView(imageAnim);
        //animation.start();
        
        Handler handler = new Handler();
        Runnable r = new Runnable(){
        	
        	public void run(){

                animation.start();

                }
                };
        
        handler.postDelayed(r, 0);
        
        //handler.removeCallbacks(r);
	}
	

	class MySwipeDetector extends SimpleOnGestureListener				//swipe detector
	{
		@SuppressWarnings("deprecation")
		@Override
		
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
		{
			try
			{
				final Handler handler = new Handler();
				//float e2save = e2.getX();
				//float e1x = e1.getX();
				//float e2x = e2.getX();
				final float leftswipe = e1.getX() - e2.getX();
				//Log.d("e1x e2x", Float.toString(e1x) + " " + Float.toString(e2x) + " " + Float.toString(e2save));
				Runnable r = new Runnable()
				{
					public void run()
					{
						
						
						
						
						final AnimationDrawable animation = new AnimationDrawable();
						final ImageView iView = new ImageView(getApplicationContext());
						
						
						if(leftswipe > SWIPE_MIN_DISTANCE)		//if left swipe is greater than minimum swipe distance
						{
							
							if(leftswipe < thresholdWidth)		//if left swipe is greater than 60% of screen
							{
								if(pointer < end - 1)					//move to next frame if not at the end
								{
									pointer++;									
									
									showView(pointer, iView);	
									//showView(pointer);
									//final Toast toast = Toast.makeText(getApplicationContext(), "Left Swipe, Increment", Toast.LENGTH_SHORT);
					    			//toast.show();
								}
							//e2x = e2save;
								//else											//already at the end of frames		
								//{
									//final Toast toast = Toast.makeText(getApplicationContext(), "No more to increment", Toast.LENGTH_SHORT);
					    			//toast.show();
								//}
								
							}
							else												//if left swipe is less than 60% of screen
							{
								
								if(pointer < end - 1)					//animate to the end if not already at end
								{
									//final AnimationDrawable animation = new AnimationDrawable();
									for(int begin = pointer; begin < end; begin++)		//adding frames
									{
										
										animation.addFrame(getResources().getDrawable(mImage[begin]), 0);
									}
							        pointer = end- 1;
							        runAnimation(animation);
							        
							        //animation.setCallback(null);
									//final Toast toast = Toast.makeText(getApplicationContext(), "Left Swipe, Animate Right", Toast.LENGTH_SHORT);
					    			//toast.show();
								}
								//else											//already at the end of animation
								//{
									//final Toast toast = Toast.makeText(getApplicationContext(), "End of Animation", Toast.LENGTH_SHORT);
					    			//toast.show();
								//}
								
								
							}
							
							
						}
						else if(-leftswipe > SWIPE_MIN_DISTANCE)	//if right swipe is greater than minimum swipe distance
						{
							if(-leftswipe < thresholdWidth)		//if right swipe is greater than 60% of the screen
							{
								if(pointer > start)								//move to previous frame if not at beginning
								{
									pointer--;
									//ImageView iView = new ImageView(getApplicationContext());
									showView(pointer, iView);
									//showView(pointer);
									//final Toast toast = Toast.makeText(getApplicationContext(), "Right Swipe, Decrement", Toast.LENGTH_SHORT);
					    			//toast.show();
								}
								//else											//already at beginning of frames
								//{
									//final Toast toast = Toast.makeText(getApplicationContext(), "No more to decrement", Toast.LENGTH_SHORT);
					    			//toast.show();
								//}
							}
							else
							{
								
								if(pointer > start)								//animate to beginning if not already
								{
									//final AnimationDrawable animation = new AnimationDrawable();
									for(int begin = pointer; begin >= start; begin--)		//adding frames
									{
										animation.addFrame(getResources().getDrawable(mImage[begin]), 0);
									}
							        pointer = start; 
							        runAnimation(animation);
							        //animation.setCallback(null);
									//final Toast toast = Toast.makeText(getApplicationContext(), "Right Swipe, Animate Left", Toast.LENGTH_SHORT);
					    			//toast.show();
								}
								
								//else											//already at beginning of animation
								//{
									//final Toast toast = Toast.makeText(getApplicationContext(), "Beginning of Animation", Toast.LENGTH_SHORT);
					    			//toast.show();
								//}
								
							}
						}
					}
				};
				
				handler.postDelayed(r, 0);
				
				
		
				
			}
			catch(Exception e)
			{
				
			}
			return false;
			
			
		}
		
		
		
		
	}
	


	@Override
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
