package in.junctiontech.searchb4kharch;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.junctiontech.searchb4kharch.anim.AnimationUtils;
import model.DealsData;
import model.NavigationPopularCategoryData;
import model.ProductDataList;
import model.Productdata;
import model.TopDealsData;

public class DealsDataActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deals_data);


        dealsDataInitialization();
        actionBarTitleGravity();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void actionBarTitleGravity() {
// TODO Auto-generated method stub

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {

            TextView textview = new TextView(getApplicationContext());
            RelativeLayout.LayoutParams layoutparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            textview.setLayoutParams(layoutparams);
            textview.setText(""+data);
            textview.setSingleLine();
            actionBar.setDisplayShowTitleEnabled(false);
            textview.setTextColor(Color.WHITE);
            // textview.setTypeface (null, Typeface.BOLD);
            textview.setGravity(Gravity.CENTER);
            textview.setTextSize(20);


           /* String fontPath = "fonts/CircleD_Font_by_CrazyForMusic.ttf";
           fontPath = "ExternalFonts/chopinscript.ttf";
            // text view label

            // Loading Font Face
            Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

            // Applying font
            textview.setTypeface(tf);*/

            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(textview);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void dealsDataInitialization() {
        Intent i = this.getIntent();
        String URL = i.getStringExtra("URL");
        data = i.getStringExtra("DATA");
        final DealsDataAdapter dealsDataAdapter;
        LinearLayoutManager layoutManager_deals_data_recyclerview
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        RecyclerView deals_data_recyclerview = (RecyclerView) findViewById(R.id.deals_data_recyclerview);
        deals_data_recyclerview.setHasFixedSize(true);
        deals_data_recyclerview.setLayoutManager(layoutManager_deals_data_recyclerview);
        final List<DealsData> dealsDataList = new ArrayList<>(0);
        dealsDataAdapter = new DealsDataAdapter(this, dealsDataList);
        deals_data_recyclerview.setAdapter(dealsDataAdapter);


        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Wait while downloading data...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setCancelable(false);
        pDialog.show();


        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                hidePDialog();
                Log.d("SB4K", response);
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; jsonArray.length() > i; i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        DealsData dealsData = new DealsData();


                        dealsData.setOffer_name(jsonObject.optString("offer_name"));
                        dealsData.setCoupon_title(jsonObject.optString("coupon_title"));
                        dealsData.setCoupon_code(jsonObject.optString("coupon_code"));
                        dealsData.setCoupon_description(jsonObject.optString("coupon_description"));
                        dealsData.setCoupon_expiry(jsonObject.optString("coupon_expiry"));
                        dealsData.setLink(jsonObject.optString("link"));
                        dealsData.setUrl(jsonObject.optString("url"));



                        dealsDataList.add(dealsData);
                    }

                    dealsDataAdapter.notifyDataSetChanged();


                } catch (JSONException e) {
                    e.printStackTrace();
                    e.getMessage();
                }

            }
        }

                , new Response.ErrorListener()

        {

            @Override
            public void onErrorResponse(VolleyError error) {
                hidePDialog();
                Log.e("TAG", " Error: " + error.getMessage());
                ;

            }

        }

        );
        strReq.setRetryPolicy(new DefaultRetryPolicy(3000, 2, 2));
        queue.add(strReq);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }


    public void onClickCouponCopy(View view) {
        Button b= (Button) view;
        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText("text to clip");
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("text label", b.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Copied To Clipboard",Toast.LENGTH_LONG).show();
        }

    }
}

class DealsDataAdapter extends RecyclerView.Adapter<DealsDataAdapter.ViewHolder> {
    private static Context context;
    private List<DealsData> dealsDataList;
    private static ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private int mPreviousPosition = 0;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder


   public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        private TextView deals_category_description, deals_category_couponexpiry,deals_category_offer_name,deals_category_coupon_title;
        private NetworkImageView deals_category_thumbnail;
        private Button deals_category_couponcode;
        // private AQuery aq;

        public ViewHolder(View v) {
            super(v);


            deals_category_offer_name = (TextView) v.findViewById(R.id.deals_category_offer_name);
            deals_category_coupon_title = (TextView) v.findViewById(R.id.deals_category_coupon_title);
            deals_category_description = (TextView) v.findViewById(R.id.deals_category_description);
            deals_category_couponexpiry = (TextView) v.findViewById(R.id.deals_category_couponexpiry);

            if (imageLoader == null)
                imageLoader = AppController.getInstance().getImageLoader();
            deals_category_thumbnail = (NetworkImageView) v.findViewById(R.id.deals_category_thumbnail);
            deals_category_couponcode = (Button)  v.findViewById(R.id.deals_category_couponcode);
            //mTextView = v;
            itemView.setOnClickListener(this);
        }

        // Handles the row being being clicked
        @Override
        public void onClick(View view) {
            int position = getLayoutPosition(); // gets item position

            DealsData dealsData = dealsDataList.get(position);

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(dealsData.getLink()));
            context.startActivity(browserIntent);

            // We can access the data within the views
            //    Toast.makeText(context, position + "", Toast.LENGTH_SHORT).show();
            //    Intent i = new Intent();
            //noinspection SimplifiableIfStatement

            //    i = i.setClass(context, ProductDetailsActivity.class);
            //    context.startActivity(i);
        }


    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public DealsDataAdapter(Context context, List<DealsData> dealsDataList) {
        this.dealsDataList = dealsDataList;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DealsDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.deals_category_data, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        DealsData dealsData = dealsDataList.get(position);

        holder.deals_category_coupon_title.setText(dealsData.getCoupon_title());
        holder.deals_category_offer_name.setText(dealsData.getOffer_name());
        holder.deals_category_description.setText(dealsData.getCoupon_description());
        holder.deals_category_couponexpiry.setText(dealsData.getCoupon_expiry());
        holder.deals_category_couponcode.setText(dealsData.getCoupon_code());
        // holder.product_thumbnail.setImageUrl(topDealsData.getProduct_thumbnailUrl(), imageLoader);
        //  holder.merchant_thumnbail.setImageUrl(topDealsData.getMerchant_thumbnailUrl(), imageLoader);

        holder.deals_category_thumbnail.setImageUrl(dealsData.getUrl(), imageLoader);


        if (position > mPreviousPosition) {
            AnimationUtils.animateSunblind(holder, true);
//            AnimationUtils.animateSunblind(holder, true);
            //  AnimationUtils.animate1(holder, true);
            //     AnimationUtils.animate(holder, true);
            //   AnimationUtils.animateScatter(holder, true);
            AnimationUtils.scaleXYVishal(holder.deals_category_thumbnail);

            // AnimationUtils.animateToolbarDroppingDown(holder.product_thumbnail);
        } else {
            AnimationUtils.animateSunblind(holder, false);
//            AnimationUtils.animateSunblind(holder, false);
            //     AnimationUtils.animate1(holder, false);
            //   AnimationUtils.animate(holder, false);
            //     AnimationUtils.animateScatter(holder, false);
            AnimationUtils.scaleXYVishal(holder.deals_category_thumbnail);
            //AnimationUtils.animateToolbarDroppingDown(holder.product_thumbnail);
        }
        mPreviousPosition = position;


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dealsDataList.size();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    /*// Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, Data data) {
        list.add(position, data);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(Data data) {
        int position = list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);
    }*/


}


