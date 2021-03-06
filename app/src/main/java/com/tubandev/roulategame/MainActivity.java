package com.tubandev.roulategame;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {

    boolean blnButtonRotation = true;
    int intNumber = 6;;
    long lngDegrees = 0;
    SharedPreferences sharedPreferences;

    ImageView selected,imageRoulette;
    Button b_start,b_up,b_down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        b_start = (Button)findViewById(R.id.buttonStart);
        b_up = (Button)findViewById(R.id.buttonUp);
        b_down = (Button)findViewById(R.id.buttonDown);

        selected = (ImageView)findViewById(R.id.imageSelected);
        imageRoulette = (ImageView)findViewById(R.id.rouletteImage);


        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.intNumber = this.sharedPreferences.getInt("INT_NUMBER",6);
        setImageRoulette(this.intNumber);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        this.blnButtonRotation = false;
        b_start.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Toast toast = Toast.makeText(this, " " + String.valueOf((int)(((double)this.intNumber)
                - Math.floor(((double)this.lngDegrees) / (360.0d / ((double)this.intNumber))))) + " ",Toast.LENGTH_LONG);
        toast.setGravity(49,0,0);
        toast.show();
        this.blnButtonRotation = true;
        b_start.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public void onClickButtonRotation(View v)
    {
        if(this.blnButtonRotation)
        {
            int ran = new Random().nextInt(360) + 3600;
            RotateAnimation rotateAnimation = new RotateAnimation((float)this.lngDegrees, (float)
                    (this.lngDegrees + ((long)ran)),1,0.5f,1,0.5f);

            this.lngDegrees = (this.lngDegrees + ((long)ran)) % 360;
            rotateAnimation.setDuration((long)ran);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setInterpolator(new DecelerateInterpolator());
            rotateAnimation.setAnimationListener(this);
            imageRoulette.setAnimation(rotateAnimation);
            imageRoulette.startAnimation(rotateAnimation);

        }
    }

    public void buttonUp(View v)
    {
        if(this.intNumber < 10)
        {
            this.intNumber++;
            setImageRoulette(this.intNumber);
//            b_down.setVisibility(View.GONE);
            SharedPreferences.Editor editor = this.sharedPreferences.edit();
            editor.putInt("INT_NUMBER",this.intNumber);
            editor.commit();

        }

        if(this.intNumber ==10)
        {
            b_up.setVisibility(View.INVISIBLE);
        }
    }


    public void buttonDown(View v)
    {
        if(this.intNumber > 2)
        {
            intNumber--;
            setImageRoulette(this.intNumber);
            b_up.setVisibility(View.VISIBLE);

            SharedPreferences.Editor editor = this.sharedPreferences.edit();
            editor.putInt("INT_NUMBER",this.intNumber);
            editor.commit();

        }


        if(this.intNumber > 2)
        {
            b_down.setVisibility(View.INVISIBLE);
        }


    }

    private void setImageRoulette(int myNumber)
    {
        switch(myNumber)
        {
            case 1:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette));
                return;
            case 2:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_2));
                return;

            case 3:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_3));
                return;
            case 4:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_4));
                return;

            case 5:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_5));
                return;
            case 6:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_6));
                return;

            case 7:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_7));
                return;
            case 8:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_8));
                return;

            case 9:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_9));
                return;
            case 10:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_10));
                return;


        }
    }
}
