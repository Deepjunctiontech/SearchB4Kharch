package in.junctiontech.searchb4kharch;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ContestPointsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest_points);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!= null)
            actionBar.setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
