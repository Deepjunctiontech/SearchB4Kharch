package in.junctiontech.searchb4kharch;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import model.ProductDataList;
import model.TopDealsData;

public class ProductDetailsActivity extends AppCompatActivity {
    private String URL;
    private NetworkImageView networkImageView;
    private TextView tv_productdetails_productname, tv_productdetails_productdescription,
            tv_productdetails_productprice;
    private String name;
    private Button btn_productdetails_productbuynow;
    private String productShopUrl;
    private JSONArray featureJSONArray;
    private ProductAdapter similarProductsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);












       /* Resources r = this.getResources();

        List<TopDealsData> obj = new ArrayList();

        List<String> wordList = Arrays.asList(r.getStringArray(R.array.sample));

        for (String e : wordList) {
            obj.add(new TopDealsData(e));
        }

        TopDealsAdapter mAdapter = new TopDealsAdapter(this, obj);
        myList.setAdapter(mAdapter);*/

        referenceInitialization();
        getProductDetails();


    }

    private void referenceInitialization() {
        networkImageView = (NetworkImageView) this.findViewById(R.id.img_productdetails_productimage);
        tv_productdetails_productname = (TextView) this.findViewById(R.id.tv_productdetails_productname);
        tv_productdetails_productdescription = (TextView) this.findViewById(R.id.tv_productdetails_productdescription);
       // tv_productdetails_productprice = (TextView) this.findViewById(R.id.tv_productdetails_productprice);
       // btn_productdetails_productbuynow = (Button) this.findViewById(R.id.btn_productdetails_productbuynow);
        Intent intent = getIntent();
        String categoriesUrlKey = intent.getStringExtra("categoriesUrlKey");
        String productUrlKey = intent.getStringExtra("productUrlKey");
        String productId = intent.getStringExtra("productId");
        URL = "http://www.searchb4kharch.com/Landingpage/product/" + categoriesUrlKey + "/" + productId + "/" + productUrlKey + "?app=true";
        // URL = "http://www.searchb4kharch.com/Landingpage/product/Mobiles/1me_m8?app=true";
        Log.d("SB4K", URL);
        name = intent.getStringExtra("name");
        actionBarTitleGravity();


    }



    private void getProductDetails() {

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView myList = (RecyclerView) findViewById(R.id.my_recycler_view_product);
        // myList.setNestedScrollingEnabled(false);
        myList.setHasFixedSize(true);
        myList.setLayoutManager(layoutManager);

        final List<ProductDataList> popularProductsList = new ArrayList<>(0);
        similarProductsAdapter = new ProductAdapter(this, popularProductsList);
        myList.setAdapter(similarProductsAdapter);

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest strReq = new StringRequest(Request.Method.GET,
                URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                pDialog.hide();
                ImageLoader imageLoader = AppController.getInstance().getImageLoader();
                try {
                    Log.d("SB4K", response);
                    JSONObject object = new JSONObject(response);

                    JSONArray newJArray = object.getJSONArray("products");
                    for (int i = 0; newJArray.length() > i; i++) {
                        Log.d("length", newJArray.length() + "");
                        JSONObject jsonObject = newJArray.getJSONObject(i);
                        tv_productdetails_productname.setText(jsonObject.getString("productName"));  //  use opt
                        tv_productdetails_productdescription.setText(jsonObject.optString("productDescription"));
               //         tv_productdetails_productprice.setText(jsonObject.getString("productPrice"));
                        networkImageView.setImageUrl(jsonObject.getString("imageName"), imageLoader);
                        productShopUrl = jsonObject.getString("productShopUrl");
                        // btn_productdetails_productbuynow.setVisibility(View.VISIBLE);

                        JSONArray jsonArray = jsonObject.optJSONArray("morePrices");

                        if(jsonArray!=null&&jsonArray.length()>0)
                        setPriceComaprisonLayout(jsonArray);
                        else
                        Toast.makeText(ProductDetailsActivity.this,"Not Available",Toast.LENGTH_LONG).show();

                        featureJSONArray = jsonObject.optJSONArray("productFeatures");

                        if(featureJSONArray!=null&&featureJSONArray.length()>0)
                        for(int j=0;i<featureJSONArray.length();j++) {

                           JSONObject jsonObject1 = featureJSONArray.getJSONObject(j);
                        //    Log.d("Featured",jsonObject.getString("lable"));

                        //    addFeaturesHeaderDynamicView(jsonObject1.getString("lable"));

                            JSONArray jsonArray1 = jsonObject1.getJSONArray("value");

                            for (int k = 0; k < jsonArray1.length()&& k<3; k++) {

                                JSONObject jsonObject2 = jsonArray1.getJSONObject(k);
                                addNewViewDynamic(jsonObject2.getString("lable"),jsonObject2.getString("value"));
                            }
                            break;
                        }
                        else {
                            LinearLayout featureLayout = (LinearLayout)findViewById(R.id.featureLayout);
                            featureLayout.setVisibility(View.GONE);
                            Toast.makeText(ProductDetailsActivity.this, "Features Not Available", Toast.LENGTH_LONG).show();

                        }


                        break; // only for one product display
                    }

                    JSONArray fpArray = object.optJSONArray("similarproduct");

                    if(fpArray!=null&&fpArray.length()>0) {
                        for (int i = 0; fpArray.length() > i; i++) {

                            try {
                                JSONObject jsonObject = fpArray.getJSONObject(i);
                                ProductDataList similarProduct = new ProductDataList();
                                similarProduct.setDescription(jsonObject.getString("productName"));  //  use opt
                                similarProduct.setImageUrl(jsonObject.optString("imageName",
                                        "http://www.searchb4kharch.com/frontend/images/mobile.png"));
                                similarProduct.setPrice(jsonObject.getString("productPrice"));
                                similarProduct.setProductUrlKey(jsonObject.getString("productsUrlKey"));
                                similarProduct.setCategoriesUrlKey(jsonObject.getString("categoriesUrlKey"));

                                in.junctiontech.searchb4kharch.util.Helper.longInfo(jsonObject.getString("sb4kProductID"));
                                similarProduct.setSb4kProductID(jsonObject.optString("sb4kProductID", ""));

                                popularProductsList.add(similarProduct);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        similarProductsAdapter.notifyDataSetChanged();

                    }
                    else
                    {
                        LinearLayout featureLayout = (LinearLayout)findViewById(R.id.recycler_similar_product_id);
                        featureLayout.setVisibility(View.GONE);
                        Toast.makeText(ProductDetailsActivity.this, "Similar Products Not Available", Toast.LENGTH_LONG).show();

                    }



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
                pDialog.hide();
                Log.e("TAG", "Registration Error: " + error.getMessage());
                ;

            }

        }

        );
        strReq.setRetryPolicy(new DefaultRetryPolicy(3000, 2, 2));
        queue.add(strReq);
    }

    private void setPriceComaprisonLayout(JSONArray jsonArray) {

        // for sorting array according to price
       // jsonArray = in.junctiontech.searchb4kharch.util.Helper.sortJsonArray(jsonArray,"productPrice");

        LinearLayout features_linearlayout = (LinearLayout) this.findViewById(R.id.layout_price_compare);
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();


        int length = jsonArray.length();

        for (int i = 0; length > i; i++) {

            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);

            LinearLayout title1 = new LinearLayout(this);
            title1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            title1.setOrientation(LinearLayout.HORIZONTAL);
            title1.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100);
            lp.weight = 1;
            lp.setMargins(2, 2, 2, 2);


            com.android.volley.toolbox.NetworkImageView merchantImage = new com.android.volley.toolbox.NetworkImageView(this);
            merchantImage.setLayoutParams(lp);
            merchantImage.setImageResource(R.mipmap.ic_launcher);
            merchantImage.setAdjustViewBounds(true);
                merchantImage.setImageUrl("http://www.searchb4kharch.com/frontend/images/"+jsonObject.getString("shop_image"), imageLoader);
            title1.addView(merchantImage);


            TextView tv3 = new TextView(this);
            tv3.setTextColor(Color.RED);

            tv3.setText("Rs "+jsonObject.getString("productPrice"));
            tv3.setLayoutParams(lp);
           // tv3.setPadding(20, 20, 20, 20);
            tv3.setBackgroundColor(Color.WHITE);
            tv3.setTextSize(16);
            tv3.setGravity(Gravity.CENTER);
            title1.addView(tv3);

            Button buyNow = new Button(this);
                buyNow.setTag(jsonObject.getString("productShopUrl"));
            buyNow.setLayoutParams(lp);
                buyNow.setBackgroundResource(R.drawable.buynow);

           // buyNow.setGravity(Gravity.CENTER);
       //         buyNow.setAdjustViewBounds(true);
      //          buyNow.setImageResource(R.drawable.buynow);  // for image view
           //     buyNow.setX(0.1f);
           //     buyNow.setY(0.1f);
          //  buyNow.setBackgroundResource(0); // android:background="?android:attr/selectableItemBackground"
                buyNow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(v.getTag().toString()));
                        startActivity(browserIntent);
                    }
                });

                int[] attrs = new int[] { android.R.attr.selectableItemBackground /* index 0 */};

               // Resources r = ProductDetailsActivity.this.getResources();
                //Drawable b=r.getDrawable(R.drawable.bg_button);

                TypedArray ta = obtainStyledAttributes(attrs);

                Drawable drawableFromTheme = ta.getDrawable(0 /* index */);
                ta.recycle();

                // setBackground(Drawable) requires API LEVEL 16,
                // otherwise you have to use deprecated setBackgroundDrawable(Drawable) method.
               // buyNow.setBackgroundDrawable(drawableFromTheme);

                /*Collections.sort(categories, new Comparator<AnyClass>() {
                    @Override
                    public int compare(AnyClasslhs, AnyClassrhs) {
                        return lhs.label.compareTo(rhs.label);
                    }
                });*/


            title1.addView(buyNow);




            features_linearlayout.addView(title1);
            View v = new View(this);
            lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 10);
            v.setLayoutParams(lp);
            features_linearlayout.addView(v);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
       // features_linearlayout.add



    }

    private void addNewViewDynamic(String lable,String value) {
        LinearLayout vishal_yadav = (LinearLayout) this.findViewById(R.id.vishal_yadav);

        LinearLayout nw = new LinearLayout(this);
    //    nw.setGravity(Gravity.CENTER);
        nw.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        nw.setOrientation(LinearLayout.HORIZONTAL);
        nw.setBackgroundResource(R.drawable.mybox);

        TextView tv1 = new TextView(this);
        tv1.setGravity(Gravity.END);
        tv1.setTextSize(14);
        tv1.setTypeface(null, Typeface.BOLD);
        tv1.setText("*");
        tv1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        nw.addView(tv1);

        TextView tv2 = new TextView(this);
        tv2.setGravity(Gravity.CENTER);
        tv2.setTextSize(12);
        tv2.setTypeface(null, Typeface.BOLD);
        tv2.setText(" "+lable+": ");
        tv2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        nw.addView(tv2);

        TextView tv3 = new TextView(this);
        tv2.setGravity(Gravity.CENTER);
        tv3.setTextSize(12);
        tv3.setText(""+value);
        tv3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        nw.addView(tv3);
        View v = new View(this);
        v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                15));
        vishal_yadav.addView(v);
        vishal_yadav.addView(nw);
        View v1 = new View(this);

        v1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                15));
        vishal_yadav.addView(v1);
    }

    private void actionBarTitleGravity() {
// TODO Auto-generated method stub

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {

            TextView textview = new TextView(getApplicationContext());
            RelativeLayout.LayoutParams layoutparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            textview.setLayoutParams(layoutparams);
            textview.setText("" + name);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            String shareBody = "https://play.google.com/store/apps/details?id=************************";
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "APP NAME (Open it in Google Play Store to Download the Application)");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            // sharingIntent.putExtra(android.content.Intent.Extra_)
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    public void onClickProductDetailFeatureViewMore(View view) {

        if (featureJSONArray != null)
            startActivity(new Intent(this, FeaturesActivity.class).putExtra("FeatureJSONArray", featureJSONArray.toString()));
        else
            Toast.makeText(this, "Features Not Available", Toast.LENGTH_LONG).show();
    }

    /*public void onClickProductDetailBuyNow(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(productShopUrl));
        startActivity(browserIntent);

    }*/
}
