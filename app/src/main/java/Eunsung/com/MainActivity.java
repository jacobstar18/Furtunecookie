package Eunsung.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    EditText EditTextEmail,EditPassword ;
    Button TextButton, LottoButton, LinkButton;
    private TextView textView;
    private InterstitialAd mInterstitialAd;
    private Button scaleBtn ;
    ImageView iv, cookiePic;
    private int onOff = 1;
    private Timer timer;
    ArrayList WinNumber = new ArrayList();
    private int buttoncount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AdBanner();
        /*Animaion Effect*/
        cookiePic = (ImageView) findViewById(R.id.cookiePic);
        Animation animationEatrh=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_animation);
        buttoncount = 0 ;
        animationEatrh.setRepeatCount(Animation.INFINITE);
        cookiePic.startAnimation(animationEatrh);
        /*Animaion Effect*/

        //Interstitial Ad
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1420948259689687/5213683523");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        //Interstitial Ad


        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                update();
            }
        };

        timer = new Timer();
        timer.schedule(timerTask, 0, 2000);


        iv = (ImageView)findViewById(R.id.cookiePic);
        iv.setOnClickListener(new MyListener());

    }


    class MyListener implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            Log.d("TAG", "Button clicked");
            buttoncount ++;
            ImageView imageview = null;
            imageview = (ImageView)findViewById(R.id.cookiePic);

            if (buttoncount==5){
                imageview.setImageResource(R.drawable.cookiecrush1);
            }
            if (buttoncount==10){
                imageview.setImageResource(R.drawable.cookiecrush2);
            }
            if (buttoncount==15){
                imageview.setImageResource(R.drawable.cookiecrush3);


            }
            if (buttoncount==20){
                imageview.setImageResource(R.drawable.cookiecrush4);

            }
            if (buttoncount==25){
                imageview.setImageResource(R.drawable.cookiecrush5);
            }
            if (buttoncount > 30){
                //Interstitial Ad
                imageview.setImageResource(R.drawable.originalcookie);
                buttoncount =  0;
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
                //Interstitial Ad

                Intent intent = new Intent(MainActivity.this,  fortuneActivity.class);

                // 1개 추출하고

                Collections.shuffle(WinNumber);

                String get_string = (String) WinNumber.get(0);
                intent.putExtra("getString", get_string);
                startActivity(intent);
            }

        } // end onClick


    } // end MyListener()


    private void AdBanner(){

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-1420948259689687/1657581892");

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

    }
    private void update(){

        ImageView cookieimageview = null;
        cookieimageview = (ImageView)findViewById(R.id.bottonbanner);
        if (onOff==1){
            cookieimageview.setImageResource(R.drawable.greenclick);
            onOff = 2;
        }
        if(onOff==2){
            cookieimageview.setImageResource(R.drawable.pinkclick);
            onOff = 3;
        }
        if(onOff==3){
            cookieimageview.setImageResource(R.drawable.blueclick);
            onOff = 1;
        }
  /*      Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d("TAG", "timererere clicked");


            }
        };*/
    }
}
