package in.junctiontech.searchb4kharch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void onClickWelcomeSkip(View view) {
        Intent i = this.getIntent();
        i = i.setClass(this, WelcomeScreenActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
        SharedPreferences sp = this.getSharedPreferences("login_info", this.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("SKIP", true);
        editor.commit();
        finish();
    }

    public void onClickWelcomeNext(View view) {
        Intent i = this.getIntent();
        i = i.setClass(this, SearchAllStoresActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.enter, R.anim.exit);

        finish();

    }
}
