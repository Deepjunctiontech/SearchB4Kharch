package in.junctiontech.searchb4kharch;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
import java.util.List;

import in.junctiontech.searchb4kharch.anim.AnimationUtils;
import model.DealsData;
import model.ProductDataList;

public class ProductDataListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView navigationItemClicked_listView;
    private String categoriesUrlKey;
    private String URL;
    private String name;
    private CustomAdapter customAdapter;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deals_data);

        Intent intent=getIntent();
        categoriesUrlKey=intent.getStringExtra("categoriesUrlKey");
        URL = "http://www.searchb4kharch.com/Landingpage/product/"+categoriesUrlKey+"?app=true";

        name=intent.getStringExtra("name");
    //    referenceInitialization();
        categoryDataInitialization();

        actionBarTitleGravity();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
        public boolean onSupportNavigateUp() {
        finish();
        return true;
        }

    private void categoryDataInitialization() {

        final CategoryDataAdapter categoryDataAdapter;
        LinearLayoutManager layoutManager_deals_data_recyclerview
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        RecyclerView deals_data_recyclerview = (RecyclerView) findViewById(R.id.deals_data_recyclerview);
        deals_data_recyclerview.setHasFixedSize(true);
        deals_data_recyclerview.setLayoutManager(layoutManager_deals_data_recyclerview);


        final List<ProductDataList> productDataList_list = new ArrayList<>(0);
        categoryDataAdapter = new CategoryDataAdapter(this, productDataList_list);
        deals_data_recyclerview.setAdapter(categoryDataAdapter);


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
                Log.d("DEBUG", response);
               // mSwipeRefreshLayout.setRefreshing(false);
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray newJArray = object.getJSONArray("products");
                    int j=newJArray.length();
                  //  if(newJArray.length()>10)
                  //      j=10;
                    for (int i = 0; j > i; i++) {

                        JSONObject jsonObject = newJArray.getJSONObject(i);
                        ProductDataList productDataList = new ProductDataList();
                        productDataList.setName(jsonObject.getString("productName"));  //  use opt
                        productDataList.setImageUrl(jsonObject.optString("imageName",
                                ""));
                        productDataList.setDescription(jsonObject.optString("productDescription","hello"));
                        productDataList.setPrice(jsonObject.getString("productPrice"));
                        productDataList.setProductUrlKey(jsonObject.getString("productsUrlKey"));
                        productDataList.setCategoriesUrlKey(jsonObject.getString("categoriesUrlKey"));
                        productDataList.setSb4kProductID(jsonObject.getString("sb4kProductID"));

                        Log.d("Check",jsonObject.getString("sb4kProductID"));

                        productDataList_list.add(productDataList);
                    }
                    categoryDataAdapter.notifyDataSetChanged();
                    // setListViewHeight(lv_homeactivity_popularcategory);

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







    private void actionBarTitleGravity() {
// TODO Auto-generated method stub

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {

            TextView textview = new TextView(getApplicationContext());
            RelativeLayout.LayoutParams layoutparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            textview.setLayoutParams(layoutparams);
            textview.setText(""+name);
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

    private void referenceInitialization() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout_navigationItemClicked);
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.BLACK,
                Color.CYAN, Color.GRAY, Color.YELLOW, Color.MAGENTA, Color.LTGRAY);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //   onRefresh();
        navigationItemClicked_listView = (ListView) findViewById(R.id.navigationItemClicked_listView);

       /*loanAppDatabase=LoanAppDatabase.getInstance(this);

        SharedPreferences sp = this.getSharedPreferences("loanApp_info", this.MODE_PRIVATE);
        emailId=sp.getString("user_id", "Not Found");*/


        navigationItemClicked_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Helper.showToast(LoanStatusActivity.this, position + "");
                /*if (position == 0)
                    startActivity(new Intent(LoanStatusActivity.this, LoanApplicationActivity.class));*/
                // confirmation();

                ProductDataList productDataList = customAdapter.getItem(position);
                //Toast.makeText(HomeActivity.this,categoriesUrlKey,Toast.LENGTH_LONG).show();
                startActivity(new Intent(ProductDataListActivity.this, ProductDetailsActivity.class).
                        putExtra("categoriesUrlKey", categoriesUrlKey)
                        .putExtra("name", productDataList.getName())
                        .putExtra("productId", productDataList.getSb4kProductID())
                        .putExtra("productUrlKey", productDataList.getProductUrlKey()));

            }
        });
        navigationItemClicked_listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {
                // Helper.showToast(LoanStatusActivity.this, position + "");
               /* if(position!=0)
               startActivity(new Intent(LoanStatusActivity.this, SeekReferenceActivity.class));
        */
                navigationItemClicked_listView.getItemAtPosition(position);


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
        //   registerForContextMenu(bestdeal_listView);


    }

    @Override
    public void onRefresh() {
        getProductData();
    }


    @Override
    protected void onResume() {
        super.onResume();
        // update();
       // getProductData();
    }

    private void getProductData() {
        customAdapter=null;
        //bestdeal_listView.addHeaderView(tv2, null, false);
        //      lv_homeactivity_popularcategory.setAdapter(null);  if entries atribute set at xml
        final List<ProductDataList> productDataList_list = new ArrayList<>(0);
        customAdapter = new CustomAdapter(this, productDataList_list);
        navigationItemClicked_listView.setAdapter(customAdapter);

        /*pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();*/

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest strReq = new StringRequest(Request.Method.GET,
                URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("DEBUG", response);
                mSwipeRefreshLayout.setRefreshing(false);
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray newJArray = object.getJSONArray("products");
                    int j=newJArray.length();
                    if(newJArray.length()>10)
                        j=10;
                    for (int i = 0; j > i; i++) {
                        JSONObject jsonObject = newJArray.getJSONObject(i);
                        ProductDataList productDataList = new ProductDataList();
                        productDataList.setName(jsonObject.getString("productName"));  //  use opt
                        productDataList.setImageUrl(jsonObject.optString("imageName",
                                "http://api.androidhive.info/json/movies/1.jpg"));
                        productDataList.setDescription(jsonObject.optString("productDescription","hello"));
                        productDataList.setPrice(jsonObject.getString("productPrice"));
                        productDataList.setProductUrlKey(jsonObject.getString("productsUrlKey"));
                        productDataList_list.add(productDataList);
                    }
                    customAdapter.notifyDataSetChanged();
                   // setListViewHeight(lv_homeactivity_popularcategory);

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
                Log.e("TAG", "onErrorResponse: " + error.getMessage());
                mSwipeRefreshLayout.setRefreshing(false);


            }

        }

        );
        strReq.setRetryPolicy(new DefaultRetryPolicy(3000, 2, 2));
       // AppController.getInstance().addToRequestQueue(strReq);
        queue.add(strReq);

    }

    private class CustomAdapter extends ArrayAdapter<ProductDataList> { // TODO see <String> and think
        private final Context context;
        private List<ProductDataList> productDataList_list;
        private ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        public CustomAdapter(Context context, List<ProductDataList> productDataList_list) {
            super(context, R.layout.productdatalist_listview, productDataList_list);
            this.context = context;
            this.productDataList_list = productDataList_list;
        }

        @Override
        public ProductDataList getItem(int position) {
            return super.getItem(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // super.getView(position, convertView, parent);

            ViewHolder holder;
            if (convertView == null) {
                LayoutInflater myLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = myLayoutInflater.inflate(R.layout.productdatalist_listview, null);
                holder = new ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.tv_productdatalist_name);
                holder.description = (TextView) convertView.findViewById(R.id.tv_productdatalist_description);
                holder.price = (TextView) convertView.findViewById(R.id.tv_productdatalist_price);
                if (imageLoader == null)
                    imageLoader = AppController.getInstance().getImageLoader();
                holder.img_productdatalist_img = (NetworkImageView) convertView.findViewById(R.id.img_productdatalist_img);
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


            ProductDataList productdatalist = productDataList_list.get(position);


            holder.name.setText(productdatalist.getName());
            holder.description.setText(productdatalist.getDescription());
            holder.price.setText(productdatalist.getPrice());
            holder.img_productdatalist_img.setImageUrl(productdatalist.getImageUrl(), imageLoader);


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
            private TextView name, description, price;
            private NetworkImageView img_productdatalist_img;
            // private LinearLayout linearLayout,addLoan;
            // private things are access easily in method of same class
        }
    }
}


class CategoryDataAdapter extends RecyclerView.Adapter<CategoryDataAdapter.ViewHolder> {
    private static Context context;
    private List<ProductDataList> productDataList_list;
    private static ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private int mPreviousPosition = 0;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        private TextView name, description, price;
        private NetworkImageView img_productdatalist_img;
        // private AQuery aq;

        public ViewHolder(View v) {
            super(v);


            name = (TextView) v.findViewById(R.id.tv_productdatalist_name);
            description = (TextView) v.findViewById(R.id.tv_productdatalist_description);
            price = (TextView) v.findViewById(R.id.tv_productdatalist_price);


            if (imageLoader == null)
                imageLoader = AppController.getInstance().getImageLoader();
            img_productdatalist_img = (NetworkImageView) v.findViewById(R.id.img_productdatalist_img);
            //mTextView = v;
            itemView.setOnClickListener(this);
        }

        // Handles the row being being clicked
        @Override
        public void onClick(View view) {
            int position = getLayoutPosition(); // gets item position

            ProductDataList productDataList = productDataList_list.get(position);

            context.startActivity(new Intent(context, ProductDetailsActivity.class).
                    putExtra("categoriesUrlKey", productDataList.getCategoriesUrlKey())
                    .putExtra("name", productDataList.getName())
                    .putExtra("productId", productDataList.getSb4kProductID())
                    .putExtra("productUrlKey", productDataList.getProductUrlKey()));
        }


    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CategoryDataAdapter(Context context, List<ProductDataList> productDataList_list) {
        this.productDataList_list = productDataList_list;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CategoryDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.productdatalist_listview, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        ProductDataList productdatalist = productDataList_list.get(position);


        holder.name.setText(productdatalist.getName());
        holder.description.setText(productdatalist.getDescription());
        holder.price.setText(productdatalist.getPrice());
        holder.img_productdatalist_img.setImageUrl(productdatalist.getImageUrl(), imageLoader);


        if (position > mPreviousPosition) {
            AnimationUtils.animateSunblind(holder, true);
//            AnimationUtils.animateSunblind(holder, true);
            //  AnimationUtils.animate1(holder, true);
            //     AnimationUtils.animate(holder, true);
            //   AnimationUtils.animateScatter(holder, true);
            AnimationUtils.scaleXYVishal(holder.img_productdatalist_img);

            // AnimationUtils.animateToolbarDroppingDown(holder.product_thumbnail);
        } else {
            AnimationUtils.animateSunblind(holder, false);
//            AnimationUtils.animateSunblind(holder, false);
            //     AnimationUtils.animate1(holder, false);
            //   AnimationUtils.animate(holder, false);
            //     AnimationUtils.animateScatter(holder, false);
            AnimationUtils.scaleXYVishal(holder.img_productdatalist_img);
            //AnimationUtils.animateToolbarDroppingDown(holder.product_thumbnail);
        }
        mPreviousPosition = position;


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return productDataList_list.size();
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
