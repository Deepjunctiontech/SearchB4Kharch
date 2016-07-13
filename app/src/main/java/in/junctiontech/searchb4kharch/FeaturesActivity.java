package in.junctiontech.searchb4kharch;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FeaturesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_features);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        actionBarTitleGravity();

       String s = getIntent().getStringExtra("FeatureJSONArray");

        try {
           // JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = new JSONArray(s);

            for(int i=0;i<jsonArray.length();i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Log.d("Featured",jsonObject.getString("lable"));

                addFeaturesHeaderDynamicView(jsonObject.getString("lable"));

                JSONArray jsonArray1 = jsonObject.getJSONArray("value");

                for (int j = 0; j < jsonArray1.length(); j++) {

                    JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                    addFeaturesDataDynamicView(jsonObject1.getString("lable"),jsonObject1.getString("value"));
                }
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.d("FeatureJSONArray", s);



    }

    private void addFeaturesDataDynamicView(String data, String value) {
        LinearLayout features_linearlayout = (LinearLayout) this.findViewById(R.id.features_linearlayout);
        LinearLayout title1 = new LinearLayout(this);
        title1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        title1.setOrientation(LinearLayout.HORIZONTAL);
        title1.setGravity(Gravity.CENTER);


        TextView tv2 = new TextView(this);
        tv2.setText(data);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp.weight=2;
        lp.setMargins(2, 2, 2, 2);

        tv2.setLayoutParams(lp);
        tv2.setPadding(20, 20, 20, 20);
        tv2.setBackgroundColor(Color.WHITE);
        if(data.length()!=0)
        title1.addView(tv2);

        TextView tv3 = new TextView(this);
        tv3.setText( Html.fromHtml(value));
        tv3.setLayoutParams(lp);
        tv3.setPadding(20, 20, 20, 20);
        tv3.setBackgroundColor(Color.WHITE);

        if(value.length()!=0)
            title1.addView(tv3);


        features_linearlayout.addView(title1);
    }

    private void addFeaturesHeaderDynamicView(String header) {
        LinearLayout features_linearlayout = (LinearLayout) this.findViewById(R.id.features_linearlayout);


        LinearLayout heading = new LinearLayout(this);
        heading.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        heading.setOrientation(LinearLayout.HORIZONTAL);
        heading.setPadding(10, 10, 10, 10);

        TextView tv1 = new TextView(this);
        tv1.setTextSize(16);
        tv1.setTypeface(null, Typeface.BOLD);
        tv1.setText(header);
        tv1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        heading.addView(tv1);
        features_linearlayout.addView(heading);



    }

    private void actionBarTitleGravity() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // handleIntent(getIntent());
        setSupportActionBar(toolbar);
        /*TextView tv=new TextView(this);
        tv.setText("Hello");
        toolbar.addView(tv);*/
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
// TODO Auto-generated method stub

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        if (actionBar != null) {

            TextView textview; /*= new TextView(getApplicationContext());
            RelativeLayout.LayoutParams layoutparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            textview.setLayoutParams(layoutparams);
            textview.setText(actionBar.getTitle() + "");
            actionBar.setDisplayShowTitleEnabled(false);
            textview.setTextColor(Color.WHITE);
            // textview.setTypeface (null, Typeface.BOLD);
            textview.setGravity(Gravity.CENTER);
            textview.setTextSize(25);*/

            textview = (TextView) this.findViewById(R.id.toolbar_title_feature);
            textview.setText("Features");
            String fontPath = "fonts/CircleD_Font_by_CrazyForMusic.ttf";
            fontPath = "ExternalFonts/chopinscript.ttf";
            // text view label

            // Loading Font Face
            Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

            // Applying font
            textview.setTypeface(tf);

            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            // actionBar.setCustomView(textview);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


}
