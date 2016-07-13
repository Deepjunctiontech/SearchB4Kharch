package in.junctiontech.searchb4kharch;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.BestDeals;
import model.Productdata;

public class BestDealsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

 //   private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView bestdeal_listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_deals);
        actionBarTitleGravity();
        handleIntent(getIntent());
        referenceInitialization();

    }

  /*  private void getBestDealsData() {
        TextView tv2 = new TextView(this);
        tv2.setText("All Categories");
        tv2.setTextSize(20);
        tv2.setPadding(40, 40, 40, 40);
        final ListView bestdeal_listView = (ListView) findViewById(R.id.bestdeal_listView);
        //bestdeal_listView.addHeaderView(tv2, null, false);
        //      lv_homeactivity_popularcategory.setAdapter(null);  if entries atribute set at xml
        final List<BestDeals> bestDealsList = new ArrayList<>(0);
        final CustomAdapter customAdapter = new CustomAdapter(this, bestDealsList);
        bestdeal_listView.setAdapter(customAdapter);

        *//*pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();*//*

        JsonArrayRequest movieReq = new JsonArrayRequest("http://api.androidhive.info/json/movies.json",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        Log.d("TAG", response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject jsonObject = response.getJSONObject(i);
                                BestDeals bestDeals = new BestDeals();
                                bestDeals.setDetail(jsonObject.getString("title"));  //  use opt
                                bestDeals.setThumbnailUrl(jsonObject.getString("image"));
                                bestDeals.setExtra(jsonObject.getString("releaseYear"));
                                bestDeals.setMerchantName(jsonObject.getString("releaseYear"));
                                bestDealsList.add(bestDeals);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        customAdapter.notifyDataSetChanged();
                        // setListViewHeight(lv_homeactivity_popularcategory);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mSwipeRefreshLayout.setRefreshing(false);
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                Toast.makeText(BestDealsActivity.this, "onErrorResponse", Toast.LENGTH_SHORT).show();

            }
        });

        movieReq.setRetryPolicy(new DefaultRetryPolicy(3000, 2, 2));
        AppController.getInstance().addToRequestQueue(movieReq);
    }*/

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void actionBarTitleGravity() {
// TODO Auto-generated method stub

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {

            TextView textview = new TextView(getApplicationContext());
            LayoutParams layoutparams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            textview.setLayoutParams(layoutparams);
        //    textview.setText(actionBar.getTitle() + "");
            textview.setText("Best Deals");
            actionBar.setDisplayShowTitleEnabled(false);
            textview.setTextColor(Color.WHITE);
            // textview.setTypeface (null, Typeface.BOLD);
            textview.setGravity(Gravity.CENTER);
            textview.setTextSize(20);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(textview);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            Toast.makeText(this, query, Toast.LENGTH_LONG).show();
            //txtQuery.setText("Search Query: " + query);

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bestdeal_search, menu);
        final SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //   Toast.makeText(ViewFeedback.this, s, Toast.LENGTH_LONG).show();
                /*mayAdap.getFilter().filter(s);
                mayAdap.notifyDataSetChanged();*/
                return false;
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void referenceInitialization() {
   /* //    mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout_best_deals);
    //    mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.BLACK,
                Color.CYAN, Color.GRAY, Color.YELLOW, Color.MAGENTA, Color.LTGRAY);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //   onRefresh();*/
        bestdeal_listView = (ListView) findViewById(R.id.bestdeal_listView);

       /*loanAppDatabase=LoanAppDatabase.getInstance(this);

        SharedPreferences sp = this.getSharedPreferences("loanApp_info", this.MODE_PRIVATE);
        emailId=sp.getString("user_id", "Not Found");*/



        bestdeal_listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {
                // Helper.showToast(LoanStatusActivity.this, position + "");
               /* if(position!=0)
               startActivity(new Intent(LoanStatusActivity.this, SeekReferenceActivity.class));
        */
                bestdeal_listView.getItemAtPosition(position);


                //check current item with your logic and show or don't show contextMenu
                if (position == 0) {
                    return true;  // capture long click hide context menu
                }
               /* LoanApplication loanApplication = customAdapter.getItem(position);
                dateTimeClick = loanApplication.getDateTime();
                //emailId= loanApplication.getEmailId();*/
                return false;
            }
        });
        registerForContextMenu(bestdeal_listView);


        Intent intent =this.getIntent();
        List<Productdata> productdataList = (List<Productdata>) intent.getSerializableExtra("DealsGategory");

        final ListView bestdeal_listView = (ListView) findViewById(R.id.bestdeal_listView);
        //bestdeal_listView.addHeaderView(tv2, null, false);
        //      lv_homeactivity_popularcategory.setAdapter(null);  if entries atribute set at xml
        final DealsCategory customAdapter = new DealsCategory(this, productdataList);
        bestdeal_listView.setAdapter(customAdapter);

        bestdeal_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Helper.showToast(LoanStatusActivity.this, position + "");
                /*if (position == 0)
                    startActivity(new Intent(LoanStatusActivity.this, LoanApplicationActivity.class));*/

                Productdata productdata = customAdapter.getItem(position);
                String clickData = productdata.getDescription();

                startActivity(new Intent(BestDealsActivity.this, DealsDataActivity.class)
                        .putExtra("URL",productdata.getPrice())
                        .putExtra("DATA",clickData));
               // confirmation();
            }
        });


    }

    private void confirmation() {

        startActivity(new Intent(BestDealsActivity.this, DealsDataActivity.class));
        return;

       /* AlertDialog.Builder alt = new AlertDialog.Builder(this);
        alt.setTitle("Confirmation");
        alt.setMessage("Do you Open this offer in Browser?");
        alt.setPositiveButton("OPEN", new MyClick());
        alt.setNegativeButton("CANCEL", new MyClick());
        alt.show();*/
    }

    private class MyClick implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {

        }
    }


    @Override
    public void onRefresh() {
        //getBestDealsData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // update();
        //getBestDealsData();
    }

   /* private void update() {
       // LoanAppDatabase loanAppDatabase = LoanAppDatabase.getInstance(this);


      // List<LoanApplication> loanApplicationList = loanAppDatabase.getLoanApplicationList();


        List<BestDeals> bestDealsList = new ArrayList<>(10);

        for (int i=0;i<10;i++) {
            bestDealsList.add(new BestDeals("Amazon Summer Appliance Store BHOPAL", "Great Deals", "Amazon.in"));
        }

       *//* if (bestDealsList != null) {
            bestDealsList.add(0, new BestDeals());
        }
        else {
            bestDealsList = new ArrayList<>(1);
            bestDealsList.add(0, new LoanApplication());
        }
*//*


        if (bestDealsList == null)
        {

            bestdeal_listView.setAdapter(new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, new String[]{"No Data Found In Server"}));
    }
        else {

            if (customAdapter == null) {
                Log.d("ADAPTER", "CREATED");

                customAdapter = new CustomAdapter(this, bestDealsList);
                bestdeal_listView.setAdapter(customAdapter);
            } else {
                Log.d("ADAPTER", "MODIFIED");
                customAdapter.clear();
                customAdapter.addAll(bestDealsList);
                customAdapter.notifyDataSetChanged();
            }
        }

    }*/

    private class CustomAdapter extends ArrayAdapter<BestDeals> { // TODO see <String> and think
        private final Context context;
        private List<BestDeals> bestDealsList;
        private ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        public CustomAdapter(Context context, List<BestDeals> bestDealsList) {
            super(context, R.layout.bestdeal_listview, bestDealsList);
            this.context = context;
            this.bestDealsList = bestDealsList;
        }

        @Override
        public BestDeals getItem(int position) {
            return super.getItem(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // super.getView(position, convertView, parent);

            ViewHolder holder;
            if (convertView == null) {
                LayoutInflater myLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = myLayoutInflater.inflate(R.layout.bestdeal_listview, null);
                holder = new ViewHolder();
                holder.detail = (TextView) convertView.findViewById(R.id.tv_bestdeal_detail);
                holder.extra = (TextView) convertView.findViewById(R.id.tv_bestdeal_extra);
                holder.merchant_name = (TextView) convertView.findViewById(R.id.tv_bestdeal_merchant_name);
                if (imageLoader == null)
                    imageLoader = AppController.getInstance().getImageLoader();
                holder.img_bestdeal_img = (NetworkImageView) convertView.findViewById(R.id.img_bestdeal_img);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
           /* Bitmap bitmap = ((BitmapDrawable) holder.img_bestdeal_img.getDrawable()).getBitmap();
            Palette palette =  Palette.GETPALETTE(bitmap);
            Palette palette = PaletteTransformation.getPalette(bitmap);

            convertView.setBackgroundColor(palette.getLightVibrantColor(0));

            Palette.generateAsync(bitmap,
                    new Palette.PaletteAsyncListener() {
                        @Override
                        public void onGenerated(Palette palette) {
                            Palette.Swatch vibrant =
                                    palette.getVibrantSwatch();
                            if (swatch != null) {
                                // If we have a vibrant color
                                // update the title TextView
                                titleView.setBackgroundColor(
                                        vibrant.getRgb());
                                titleView.setTextColor(
                                        vibrant.getTitleTextColor());
                            }
                        }
                    });*/


            BestDeals bestDeals = bestDealsList.get(position);


            holder.detail.setText(bestDeals.getDetail());
            holder.extra.setText(bestDeals.getExtra());
            holder.merchant_name.setText(bestDeals.getMerchantName());
            holder.img_bestdeal_img.setImageUrl(bestDeals.getThumbnailUrl(), imageLoader);


            /*} else {
                holder.addLoan.setVisibility(View.VISIBLE);
                holder.linearLayout.setVisibility(View.INVISIBLE);
            }*/


         /*  holder.status.setText(loanApplication.getStatus());
            holder.ammount.setText(loanApplication.getAmmount());
            holder.type.setText(loanApplication.getType());
          holder.id.setText(loanApplication.getEmailId());

           holder.likeText.setText(loanApplication.getLike());
            holder.dislikeText.setText(loanApplication.getDislike());
            holder.dateTime.setText(loanApplication.getDateTime());*/

            return convertView;
        }

        public class ViewHolder {
            private TextView detail, extra, merchant_name;
            private NetworkImageView img_bestdeal_img;
            // private LinearLayout linearLayout,addLoan;
            // private things are access easily in method of same class


        }
    }

    private class DealsCategory extends ArrayAdapter<Productdata> { // TODO see <String> and think
        private final Context context;
        private List<Productdata> productdataList;
        private ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        public DealsCategory(Context context, List<Productdata> productdataList) {
            super(context, R.layout.dealscategory_lauout, productdataList);
            this.context = context;
            this.productdataList = productdataList;
        }

        @Override
        public Productdata getItem(int position) {
            return super.getItem(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // super.getView(position, convertView, parent);

            ViewHolder holder;
            if (convertView == null) {
                LayoutInflater myLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = myLayoutInflater.inflate(R.layout.dealscategory_lauout, null);
                holder = new ViewHolder();
                holder.detail = (TextView) convertView.findViewById(R.id.tv_category_deals);
//                holder.extra = (TextView) convertView.findViewById(R.id.tv_bestdeal_extra);
//                holder.merchant_name = (TextView) convertView.findViewById(R.id.tv_bestdeal_merchant_name);
//                if (imageLoader == null)
//                    imageLoader = AppController.getInstance().getImageLoader();
//                holder.img_bestdeal_img = (NetworkImageView) convertView.findViewById(R.id.img_bestdeal_img);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
           /* Bitmap bitmap = ((BitmapDrawable) holder.img_bestdeal_img.getDrawable()).getBitmap();
            Palette palette =  Palette.GETPALETTE(bitmap);
            Palette palette = PaletteTransformation.getPalette(bitmap);

            convertView.setBackgroundColor(palette.getLightVibrantColor(0));

            Palette.generateAsync(bitmap,
                    new Palette.PaletteAsyncListener() {
                        @Override
                        public void onGenerated(Palette palette) {
                            Palette.Swatch vibrant =
                                    palette.getVibrantSwatch();
                            if (swatch != null) {
                                // If we have a vibrant color
                                // update the title TextView
                                titleView.setBackgroundColor(
                                        vibrant.getRgb());
                                titleView.setTextColor(
                                        vibrant.getTitleTextColor());
                            }
                        }
                    });*/


            Productdata productdata = productdataList.get(position);


            holder.detail.setText(productdata.getDescription());
         //   holder.extra.setText(bestDeals.getExtra());
         //   holder.merchant_name.setText(bestDeals.getMerchantName());
         //   holder.img_bestdeal_img.setImageUrl(bestDeals.getThumbnailUrl(), imageLoader);


            /*} else {
                holder.addLoan.setVisibility(View.VISIBLE);
                holder.linearLayout.setVisibility(View.INVISIBLE);
            }*/


         /*  holder.status.setText(loanApplication.getStatus());
            holder.ammount.setText(loanApplication.getAmmount());
            holder.type.setText(loanApplication.getType());
          holder.id.setText(loanApplication.getEmailId());

           holder.likeText.setText(loanApplication.getLike());
            holder.dislikeText.setText(loanApplication.getDislike());
            holder.dateTime.setText(loanApplication.getDateTime());*/

            return convertView;
        }

        public class ViewHolder {
            private TextView detail, extra, merchant_name;
            private NetworkImageView img_bestdeal_img;
            // private LinearLayout linearLayout,addLoan;
            // private things are access easily in method of same class


        }
    }




}
