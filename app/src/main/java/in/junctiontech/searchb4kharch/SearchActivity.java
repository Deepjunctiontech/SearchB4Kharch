package in.junctiontech.searchb4kharch;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.junctiontech.searchb4kharch.util.Helper;
import model.ProductDataList;

public class SearchActivity extends AppCompatActivity {

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!= null)
            actionBar.setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bestdeal_search, menu);
        final SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();
        search.setIconified(false);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(SearchActivity.this, s, Toast.LENGTH_LONG).show();
                Helper.hideKeyboard(SearchActivity.this);
                categoryDataInitialization(s);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Toast.makeText(SearchActivity.this, s, Toast.LENGTH_LONG).show();
                //  mayAdap.getFilter().filter(s);
                //  mayAdap.notifyDataSetChanged();
                //categoryDataInitialization(s);
                return false;
            }
        });


        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }



    private void categoryDataInitialization(String text) {

        final CategoryDataAdapter categoryDataAdapter;
        LinearLayoutManager layoutManager_deals_data_recyclerview
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        RecyclerView deals_data_recyclerview = (RecyclerView) findViewById(R.id.search_recyclerview);
        deals_data_recyclerview.setHasFixedSize(true);
        deals_data_recyclerview.setLayoutManager(layoutManager_deals_data_recyclerview);


        final List<ProductDataList> productDataList_list = new ArrayList<>(0);
        categoryDataAdapter = new CategoryDataAdapter(this, productDataList_list);
        deals_data_recyclerview.setAdapter(categoryDataAdapter);


        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Wait While Searching Data...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setCancelable(false);
        pDialog.show();



        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest strReq = new StringRequest(Request.Method.POST,
                "http://www.searchb4kharch.com/Landingpage/Product/search?app=true&q="+text+"&c=all"
                , new Response.Listener<String>() {

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


}
