package in.junctiontech.searchb4kharch;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SplashScreenActivity extends Activity {

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sp = this.getSharedPreferences("login_info", this.MODE_PRIVATE);

        int time = 5000;
        TextView imgLogo = (TextView) findViewById(R.id.imgLogo);

        String fontPath = "fonts/CircleD_Font_by_CrazyForMusic.ttf";
        fontPath = "ExternalFonts/chopinscript.ttf";
        // text view label

        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

        // Applying font
        imgLogo.setTypeface(tf);

        /*SharedPreferences sharedPreferences = getSharedPreferences("myPreference", MODE_PRIVATE);
        String org=    sharedPreferences.getString("organization_name", null);
        if (org != null)
        {
            String  usert_type =    sharedPreferences.getString("user_type","Not Found");
            startActivity(new Intent(this, MainActivity.class).putExtra("user_type", usert_type));
        }else {*/
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity

                    //startActivity(new Intent(SplashScreen.this, SignUpActivity.class));
                    // close this activity
                    check();

                }
            }, time);
        }

    private void check() {
        if (!sp.getBoolean("SKIP", false)==false) {
            startActivity(new Intent(SplashScreenActivity.this, WelcomeScreenActivity.class));
            finish();
            return;
        }
        startActivity(new Intent(SplashScreenActivity.this, WelcomeActivity.class));
        finish();


    }


   /* }*/


    public void onPause() {
        super.onPause();
        finish();
    }

    private void startAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alhpa);
        anim.reset();
        RelativeLayout l=(RelativeLayout) findViewById(R.id.rel);
        l.clearAnimation();
        l.startAnimation(anim);

    }


    @Override
    protected void onStart() {
        super.onStart();
        startAnimations();
    }

}
