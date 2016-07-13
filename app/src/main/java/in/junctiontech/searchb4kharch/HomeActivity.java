package in.junctiontech.searchb4kharch;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.junctiontech.searchb4kharch.anim.AnimationUtils;
import model.ChildObject;
import model.ExpandedMenu;
import model.NavigationPopularCategoryData;
import model.OtherNavigationData;
import model.ParentObject;
import model.ProductDataList;
import model.Productdata;
import model.TopDealsData;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // private ExpandableListView elv_homeactivity_allcategory;
    private ArrayList<ExpandedMenu> listDataHeader;
    private HashMap<ExpandedMenu, List<String>> listDataChild;
    private ProgressDialog pDialog;
    private ExpandableListView elv;
    private MyExpandableAdapter adapter;
    private ArrayList<Object> productdataList;
    private DrawerLayout drawer;
    private LinearLayout main_layout;


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        handleIntent(getIntent());
        setSupportActionBar(toolbar);
        prepareListData();
        main_layout = (LinearLayout) findViewById(R.id.main_layout_id);

      //  expandableListViewInitialization();
        /*TextView tv=new TextView(this);
        tv.setText("Hello");
        toolbar.addView(tv);*/
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        /*getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/


       /* expandableList=new ExpandableListView(this);
        expandableList.setGroupIndicator(null);
       // expandableList.setSelector(getResources().getDrawable(R.drawable.junction));
        expandableList.setVerticalScrollBarEnabled(true);
        expandableList.setScrollbarFadingEnabled(false);
        expandableList.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

        list.setLayoutParams(new RelativeLayout.LayoutParams(155, 115));

        LayoutParams.fill_parent and LayoutParams.wrap_content

        LinearLayout imp= (LinearLayout) this.findViewById(R.id.imp);
        imp.addView(expandableList);*/

       /* ExpandableListView expandableList1 = (ExpandableListView) findViewById(R.id.navigationmenu1);
        ExpandableListView expandableList2 = (ExpandableListView) findViewById(R.id.navigationmenu2);
        ExpandableListView expandableList3 = (ExpandableListView) findViewById(R.id.navigationmenu3);*/

        /*LinearLayout imp= (LinearLayout) this.findViewById(R.id.imp);
        imp.addView(tv);*/

        //  elv_homeactivity_allcategory = new ExpandableListView(this);//(ExpandableListView) findViewById(R.id.navigationmenu);
        //   expandableList.setVisibility(View.VISIBLE);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

       // drawer.setVisibility(View.INVISIBLE);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //** NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //drawer.addView(expandableList);
        // navigationView.addView(expandableList);
       /* navigationView.setNavigationItemSelectedListener(this);*//**//*

        final Menu menu = navigationView.getMenu();
        //menu.
        for (int i = 1; i <= 3; i++) {
            menu.add("Runtime item " + i);


        }*/





       /* myList.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                int action = e.getAction();
                switch (action) {
                    case MotionEvent.ACTION_MOVE:
                        rv.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });*/

       /* if (getSupportActionBar()!= null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
        /*CustomAdapter ojb=new CustomAdapter(this);
        ListView lv= (ListView) this.findViewById(R.id.lv11111);
        lv.setAdapter(ojb);*/

     /*   elv_homeactivity_allcategory = (ExpandableListView) findViewById(R.id.elv_homeactivity_allcategory);
        TextView tv1 = new TextView(this);
        tv1.setText("All Category");
        tv1.setTextSize(20);
        tv1.setPadding(40, 40, 40, 40);
        elv_homeactivity_allcategory.addHeaderView(tv1, null, false);
*/

       /* prepareListData();
        final ExpandableListAdapter mMenuAdapter = new ExpandableListAdapter(HomeActivity.this, listDataHeader, listDataChild, elv_homeactivity_allcategory);

        // setting list adapter
        elv_homeactivity_allcategory.setAdapter(mMenuAdapter);

        elv_homeactivity_allcategory.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                ExpandedMenu expandedMenu = (ExpandedMenu) mMenuAdapter.getGroup(groupPosition);

                Toast.makeText(getApplicationContext(), expandedMenu.getIconName(), Toast.LENGTH_SHORT)
                        .show();

                setListViewHeights(parent, groupPosition);

                return false;
            }
        });

        elv_homeactivity_allcategory.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String child = (String) mMenuAdapter.getChild(groupPosition, childPosition);

                Toast
                        .makeText(getApplicationContext(), child, Toast.LENGTH_SHORT)
                        .show();

                return false;
            }
        });

        // Listview Group collasped listener
        elv_homeactivity_allcategory.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();
                setListViewHeight(elv_homeactivity_allcategory);

            }
        });

        elv_homeactivity_allcategory.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });*/

      /*  ListViewPopularCategoryAdapter listViewPopularCategoryAdapter = new ListViewPopularCategoryAdapter(this, listDataHeader);
        lv_homeactivity_popularcategory.setAdapter(listViewPopularCategoryAdapter);

        ListViewPopularCategoryAdapter listViewPopularCategoryAdapter1 = new ListViewPopularCategoryAdapter(this, listDataHeader);
        lv_homeactivity_others.setAdapter(listViewPopularCategoryAdapter1);
*/




    /*    setListViewHeight(lv_homeactivity_popularcategory);
        setListViewHeight(lv_homeactivity_others);*/

        //       setListViewHeight(elv_homeactivity_allcategory);
        //referenceInitialization();
     //   getTopDealData();
        getAllCategoryData();
        getOthersData();
        //  getHashKey();
      //  final ScrollView scrollView= (ScrollView) this.findViewById(R.id.home_scroll_id);
       // scrollView.scrollTo(0, scrollView.getBottom());
        //scrollView.setOn
     //   ObjectAnimator.ofInt(scrollView, "scrollY", scrollView.getScrollY()).setDuration(1000).start();

      //  OverScrollDecoratorHelper.setUpOverScroll(scrollView);


    }

    ExpandableListView.OnGroupExpandListener onGroupExpandListenser = new ExpandableListView.OnGroupExpandListener() {
        int previousGroup = -1;

        @Override
        public void onGroupExpand(int groupPosition) {
            if (groupPosition != previousGroup)
                elv.collapseGroup(previousGroup);
           /* Toast.makeText(MainActivity.this,"previurs="+previousGroup+"",Toast.LENGTH_LONG).show();
            Toast.makeText(MainActivity.this,"current="+groupPosition+"",Toast.LENGTH_LONG).show();*/
            previousGroup = groupPosition;
            // setListViewHeights(elv, groupPosition);
            //adapter.notifyDataSetChanged();
        }
    };

    private void expandableListViewInitialization() {

        elv = (ExpandableListView) findViewById(R.id.elv1);
        elv.setOnGroupExpandListener(onGroupExpandListenser);
        elv.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                // Toast.makeText(MainActivity.this, "collapse=" + groupPosition + "", Toast.LENGTH_LONG).show();
                setListViewHeight(elv);

            }
        });
        adapter = new MyExpandableAdapter(this, getData());
        elv.setAdapter(adapter);

        elv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            int pos = -1;

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                setListViewHeights(parent, groupPosition);
                Toast.makeText(HomeActivity.this, "ongroupclick=" + groupPosition + "", Toast.LENGTH_LONG).show();
               /* ParentObject obj = adapter.getGroup(groupPosition);
                obj.setPlusMinus(!obj.isPlusMinus());
                adapter.notifyDataSetChanged();*/


                ParentObject current = adapter.getGroup(groupPosition);

                if (pos == groupPosition)
                    current.setPlusMinus(!current.isPlusMinus());
                else if (pos != -1) {
                    ParentObject previous = adapter.getGroup(pos);
                    previous.setPlusMinus(false);
                    // current.setPlusMinus(!current.isPlusMinus());
                    current.setPlusMinus(true);
                } else if (pos == -1)
                    current.setPlusMinus(true);

                pos = groupPosition;
                return false;


            }
        });


        init();
/*
       ListView lv1= (ListView) findViewById(R.id.listView1);
        ListView lv2= (ListView) findViewById(R.id.listView2);
        Resources r=this.getResources();
        ;

        ArrayAdapter<String> ob1=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,r.getStringArray(R.array.sample));

        ArrayAdapter<String> ob2=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,r.getStringArray(R.array.sample));

        lv1.setAdapter(ob1);
        lv2.setAdapter(ob2);*/

       // setListViewHeight((ListView) findViewById(R.id.listView1));
      //  setListViewHeight((ListView) findViewById(R.id.listView2));
        setListViewHeight(elv);
    }

    private void init() {
        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(HomeActivity.this, "ongroupclick=" + groupPosition + "\n+onchildclick=" + childPosition + "", Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    public List<ParentObject> getData() {
        List<ParentObject> parentObjects = new ArrayList<ParentObject>();
        for (int i = 0; i < 20; i++) {
            parentObjects.add(new ParentObject("Hello World" , getChildren(i), false));

        }
        return parentObjects;
    }

    private List<ChildObject> getChildren(int childCount) {
        List<ChildObject> childObjects = new ArrayList<ChildObject>();
        for (int i = 0; i <= childCount; i++) {
            childObjects.add(new ChildObject("Child"));
        }
        return childObjects;
    }

    private void getHashKey() {
        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("in.junctiontech.searchb4kharch", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("111hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("111name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("111no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("111exception", e.toString());
        }
    }


    private boolean check() {
        SharedPreferences sp=this.getSharedPreferences("login_info",MODE_PRIVATE);
        if (!sp.getString("user_name", "Not Found").equals("Not Found") &&
                !sp.getString("user_email", "Not Found").equals("Not Found")
                ) {
            return true;
        }
        return false;
    }

    private void getOthersData() {
        TextView tv2 = new TextView(this);
        tv2.setText("Others");
        tv2.setTextSize(20);
        tv2.setPadding(40, 40, 40, 40);
        final ListView lv_homeactivity_others = (ListView) findViewById(R.id.lv_homeactivity_others);
        lv_homeactivity_others.addHeaderView(tv2, "hello", false);
        final List<OtherNavigationData> otherNavigationDataList = new ArrayList<>(0);
        final ListViewOthersAdapter listViewOthersAdapter = new ListViewOthersAdapter(this, otherNavigationDataList);
        lv_homeactivity_others.setAdapter(listViewOthersAdapter);

        int[] id = {R.drawable.login, R.drawable.login,R.drawable.login, R.drawable.coupon,R.drawable.login, R.drawable.points
                , R.drawable.rateourapp, R.drawable.contactus, R.drawable.recentlyviewed};
        String[] name = {/*"Login",*/ "Logout", "Profile",/*"Coupon of the day","Live Support", "SearchB4Kharch Points",*/ "Rate our app", "Contact us"/*, "Recently viewed"*/};

        for (int i = 0; i < name.length; i++)
            otherNavigationDataList.add(new OtherNavigationData(id[i], name[i]));

       /* boolean ck=check();
        if(ck==true)
            otherNavigationDataList.remove(0);
        else
            otherNavigationDataList.remove(1);*/

        listViewOthersAdapter.notifyDataSetChanged();

        setListViewHeight(lv_homeactivity_others);
        final SharedPreferences sp=this.getSharedPreferences("login_info",MODE_PRIVATE);
        lv_homeactivity_others.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Animation animation1 = new AlphaAnimation(0.3f, 1.0f);
                animation1.setDuration(4000);
                view.startAnimation(animation1);*/

              /*  ObjectAnimator objectanimator =ObjectAnimator.ofFloat(view, "rotation", 180);
                objectanimator.setDuration(3000);
                objectanimator.setStartDelay(3000);
                objectanimator.start();*/  //   NOT WORKING FOR THIS CASE

             //   ((WelcomeScreenActivity)getActivity()).signOutFromGplus();
                if (position != 0) {
                    OtherNavigationData otherNavigation = listViewOthersAdapter.getItem(position - 1);
                    // Toast.makeText(HomeActivity.this, otherNavigation.getName(), Toast.LENGTH_LONG).show();
                    switch (otherNavigation.getName() + "") {
                        case "Login":
                        case "Logout":
                            finish();
                       //     WelcomeScreenActivity.logout();

                            SharedPreferences.Editor editor = sp.edit();
                            editor.remove("user_email");
                            editor.remove("user_name");
                            editor.remove("user_id");
                            editor.commit();
                            startActivity(new Intent(getBaseContext(), WelcomeScreenActivity.class));
                            break;

                        case "Coupon of the day":
                            startActivity(new Intent(getBaseContext(), CouponActivity.class));
                            break;

                        case "Rate our app":
                            //  startActivity(new Intent(getBaseContext(),CouponActivity.class));
                            // browser coding
                            Toast.makeText(HomeActivity.this, otherNavigation.getName(), Toast.LENGTH_LONG).show();
                            break;

                        case "Contact us":
                            startActivity(new Intent(getBaseContext(), ContactUs.class));
                            break;

                        case "Recently viewed":
                            Toast.makeText(HomeActivity.this, otherNavigation.getName(), Toast.LENGTH_LONG).show();
                            break;

                        case "SearchB4Kharch Points":
                            startActivity(new Intent(getBaseContext(), ContestPointsActivity.class));
                            break;

                        case "Profile":
                            startActivity(new Intent(getBaseContext(), SignupActivity.class).putExtra("PROFILE",true));
                            break;

                        case "Live Support":
                            startActivity(new Intent(getBaseContext(), LiveSupportActivity.class));
                            break;

                    }

                }

            }
        });

    }


    private void getAllCategoryData() {

        /*      For Top Deals     */


        final TopDealsAdapter topDealsAdapter;  // live example of final keyword not initialized when declare at method at a time
        LinearLayoutManager layoutManager_topdeals_recyclerview
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        final RecyclerView topdeals_recycler = (RecyclerView) findViewById(R.id.topdeals_recyclerview);
/*
        FlipInTopXAnimator animator = new FlipInTopXAnimator();
        animator.setAddDuration(2000);
        animator.setRemoveDuration(2000);
        topdeals_recycler.setItemAnimator(animator);*/
        //  mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        topdeals_recycler.setHasFixedSize(true);
        topdeals_recycler.setLayoutManager(layoutManager_topdeals_recyclerview);
        final List<TopDealsData> topDealsDataList = new ArrayList<>(0);
        topDealsAdapter = new TopDealsAdapter(this, topDealsDataList);
        topdeals_recycler.setAdapter(topDealsAdapter);



        /*      For Popular Products Recycler Data*/

        final ProductAdapter popularProductsAdapter;
        LinearLayoutManager layoutManager_popularproducts_recyclerview
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView popularproducts_recyclerview = (RecyclerView) findViewById(R.id.popularproducts_recyclerview);
        popularproducts_recyclerview.setHasFixedSize(true);
        popularproducts_recyclerview.setLayoutManager(layoutManager_popularproducts_recyclerview);
        final List<ProductDataList> popularProductsList = new ArrayList<>(0);
        popularProductsAdapter = new ProductAdapter(this, popularProductsList);
        popularproducts_recyclerview.setAdapter(popularProductsAdapter);


         /*      For Popular Mobiles Recycler Data*/

        final ProductAdapter popularMobilesAdapter;
        LinearLayoutManager layoutManager_popularmobiles_recyclerview
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView popularmobiles_recyclerview = (RecyclerView) findViewById(R.id.popularmobiles_recyclerview);
        popularmobiles_recyclerview.setHasFixedSize(true);
        popularmobiles_recyclerview.setLayoutManager(layoutManager_popularmobiles_recyclerview);
        final List<ProductDataList> popularMobilesList = new ArrayList<>(0);
        popularMobilesAdapter = new ProductAdapter(this, popularMobilesList);
        popularmobiles_recyclerview.setAdapter(popularMobilesAdapter);


        /*  For Other Products Recycler Data */


        final ProductAdapter recentlyViewedAdapter;
        LinearLayoutManager layoutManager_recentlyviewed_recyclerview
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recentlyviewed_recyclerview = (RecyclerView) findViewById(R.id.recentlyviewed_recyclerview);
        recentlyviewed_recyclerview.setHasFixedSize(true);
        recentlyviewed_recyclerview.setLayoutManager(layoutManager_recentlyviewed_recyclerview);
        final List<ProductDataList> recentlyViewedList = new ArrayList<>(0);
        recentlyViewedAdapter = new ProductAdapter(this, recentlyViewedList);
        recentlyviewed_recyclerview.setAdapter(recentlyViewedAdapter);


        TextView tv2 = new TextView(this);
        tv2.setText("All Categories");
        tv2.setTextSize(20);
        tv2.setPadding(40, 40, 40, 40);
        final ListView lv_homeactivity_popularcategory = (ListView) findViewById(R.id.lv_homeactivity_popularcategory);
        lv_homeactivity_popularcategory.addHeaderView(tv2, null, false);
        //      lv_homeactivity_popularcategory.setAdapter(null);  if entries atribute set at xml
        final List<NavigationPopularCategoryData> navigationPopularCategoryDataList = new ArrayList<>(0);
        final ListViewPopularCategoryAdapter listViewPopularCategoryAdapter = new ListViewPopularCategoryAdapter(this, navigationPopularCategoryDataList);
        lv_homeactivity_popularcategory.setAdapter(listViewPopularCategoryAdapter);

        lv_homeactivity_popularcategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    NavigationPopularCategoryData navigationPopularCategoryData = listViewPopularCategoryAdapter.getItem(position - 1);
                    //Toast.makeText(HomeActivity.this,categoriesUrlKey,Toast.LENGTH_LONG).show();

                    startActivity(new Intent(HomeActivity.this, ProductDataListActivity.class).
                            putExtra("categoriesUrlKey", navigationPopularCategoryData.getCategoriesUrlKey())
                            .putExtra("name", navigationPopularCategoryData.getName()));
                }
            }
        });

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setCancelable(false);
       // pDialog.show();


     final   AlertDialog builder = new AlertDialog.Builder(this).create();

//        LinearLayout.LayoutParams webViewParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT);
//        webViewParam.gravity = Gravity.CENTER_HORIZONTAL
//                | Gravity.CENTER_VERTICAL;

//        LinearLayout layout = new LinearLayout(this);
//        layout.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
//        layout.setLayoutParams(webViewParam);

        WebView view = new WebView(this);
        view.setVerticalScrollBarEnabled(false);
        view.setHorizontalScrollBarEnabled(false);
  //      view.setBackgroundColor(android.graphics.Color.TRANSPARENT);
       // view.setBackgroundDrawable(null);

     //   view.setLayoutParams(webViewParam);
       // view.setBackgroundColor(Color.BLUE);
     //   view.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
       // view.loadUrl("file:///android_asset/sbkloader_slow.gif");
        view.setBackgroundColor(android.graphics.Color.TRANSPARENT);
        view.loadDataWithBaseURL("file:///android_asset/","<html><center><img src=\"sbkloader_slow.gif\"></html>","text/html","utf-8","");
       // view.setBackgroundColor(android.graphics.Color.TRANSPARENT);
        //layout.addView(view);
       // view.setBackgroundColor(Color.BLUE);
        view.setBackgroundColor(android.graphics.Color.TRANSPARENT);
       // view.setBackgroundDrawable(null);
       // webView.loadData(webData, "text/html", "UTF-8");
//        view.setLayoutParams(new LinearLayout.LayoutParams(400,
//                             400));

     //    view.setBackgroundColor(Color.TRANSPARENT);
//        view.getSettings().setLoadWithOverviewMode(true);
//        view.getSettings().setUseWideViewPort(true);
//        view.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);



      //  builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//    builder.getWindow().setLayout(220,
//                220);
//       // builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

//        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                                        ViewGroup.LayoutParams.MATCH_PARENT));
        builder.setView(view);
   //
    //    final AlertDialog abb =builder.create();

        builder.getWindow().setLayout(180,180);
     //   builder.setView(layout);


      //  builder.setContentView(view,webViewParam);
       // builder.getWindow().setGravity(Gravity.CENTER);
       // builder.setView(view,100,100,100,100);
       // builder.getWindow().setBackgroundDrawable(null);

        builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        builder.setCancelable(false);
        builder.show();

        WindowManager.LayoutParams lp = builder.getWindow().getAttributes();
        lp.dimAmount = 0.2f;
        builder.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);




        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest strReq = new StringRequest(Request.Method.POST,
                "http://www.searchb4kharch.com/?app=true", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

            //    drawer.setVisibility(View.VISIBLE);

                //builder.cancel();
                hidePDialog();

                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray newJArray = object.getJSONArray("categories");
                    for (int i = 0; newJArray.length() > i; i++) {
                        JSONObject jsonObject = newJArray.getJSONObject(i);
                        NavigationPopularCategoryData navigationPopularCategoryData = new NavigationPopularCategoryData();
                        navigationPopularCategoryData.setName(jsonObject.getString("categoryName"));  //  use opt
                        navigationPopularCategoryData.setThumbnailUrl("http://www.searchb4kharch.com/frontend/images/" +
                                jsonObject.optString("categoryImage","mobile.png"));
                        navigationPopularCategoryData.setCategoriesUrlKey(jsonObject.getString("categoriesUrlKey"));

                        navigationPopularCategoryDataList.add(navigationPopularCategoryData);
//     Navigation Categories list

                    }
                    listViewPopularCategoryAdapter.notifyDataSetChanged();
                    setListViewHeight(lv_homeactivity_popularcategory);

                    /*
                     * for Featured Products
                      * */

                    JSONArray fpArray = object.getJSONArray("featureproduct");

                    for (int i = 0; fpArray.length() > i; i++) {

                        try {
                        JSONObject jsonObject = fpArray.getJSONObject(i);
                        ProductDataList featuredProducts = new ProductDataList();
                            featuredProducts.setDescription(jsonObject.getString("productName"));  //  use opt
                            featuredProducts.setImageUrl(jsonObject.optString("imageName",
                                "http://www.searchb4kharch.com/frontend/images/mobile.png"));
                            featuredProducts.setPrice(jsonObject.getString("productPrice"));
                            featuredProducts.setProductUrlKey(jsonObject.getString("productsUrlKey"));
                            featuredProducts.setCategoriesUrlKey(jsonObject.getString("categoriesUrlKey"));

                            in.junctiontech.searchb4kharch.util.Helper.longInfo(jsonObject.getString("sb4kProductID"));
                            featuredProducts.setSb4kProductID(jsonObject.optString("sb4kProductID",""));

                        popularProductsList.add(featuredProducts);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    popularProductsAdapter.notifyDataSetChanged();



                    JSONArray npArray = object.getJSONArray("newproduct");

                    for (int i = 0; npArray.length() > i; i++) {

                        try {
                            JSONObject jsonObject = npArray.getJSONObject(i);
                            ProductDataList newProducts = new ProductDataList();
                            newProducts.setDescription(jsonObject.getString("productName"));  //  use opt
                            newProducts.setImageUrl(jsonObject.optString("imageName",
                                    "http://www.searchb4kharch.com/frontend/images/mobile.png"));
                            newProducts.setPrice(jsonObject.getString("productPrice"));
                            newProducts.setProductUrlKey(jsonObject.getString("productsUrlKey"));
                            newProducts.setCategoriesUrlKey(jsonObject.getString("categoriesUrlKey"));
                            newProducts.setSb4kProductID(jsonObject.optString("sb4kProductID",""));
                            popularMobilesList.add(newProducts);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    popularMobilesAdapter.notifyDataSetChanged();


                    JSONArray lshpArray = object.getJSONArray("lshproduct");

                    for (int i = 0; lshpArray.length() > i; i++) {

                        try {
                            JSONObject jsonObject = lshpArray.getJSONObject(i);
                            ProductDataList lshProducts = new ProductDataList();
                            lshProducts.setDescription(jsonObject.getString("productName"));  //  use opt
                            lshProducts.setImageUrl(jsonObject.optString("imageName",
                                    "http://www.searchb4kharch.com/frontend/images/mobile.png"));
                            lshProducts.setPrice(jsonObject.getString("productPrice"));
                            lshProducts.setProductUrlKey(jsonObject.getString("productsUrlKey"));
                            lshProducts.setCategoriesUrlKey(jsonObject.getString("categoriesUrlKey"));
                            lshProducts.setSb4kProductID(jsonObject.optString("sb4kProductID",""));
                            recentlyViewedList.add(lshProducts);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                recentlyViewedAdapter.notifyDataSetChanged();


                    JSONArray dealsgategorys = object.getJSONArray("dealsgategorys");
                    productdataList = new ArrayList<>(dealsgategorys.length());
                    for (int i = 0; dealsgategorys.length() > i; i++) {

                        try {
                            JSONObject jsonObject = dealsgategorys.getJSONObject(i);
                            Productdata productdata = new Productdata();
                            String categoryData = jsonObject.optString("category");
                            productdata.setDescription(categoryData);
                            String replacedData=categoryData.replace(" ","_");
                            String URL = "http://www.searchb4kharch.com/Landingpage/Deals/"+replacedData+"?app=true";
                            productdata.setPrice(URL);

                            productdataList.add(productdata);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }




                    }

                    JSONArray topDeals = object.getJSONArray("deals");

                    for (int i = 0; i < topDeals.length(); i++) {
                        try {

                            JSONObject jsonObject = topDeals.getJSONObject(i);
                            TopDealsData topDealsData = new TopDealsData();
                            topDealsData.setDescription(jsonObject.getString("offer_name"));  //  use opt
                            topDealsData.setProduct_thumbnailUrl(jsonObject.getString("url"));
                            topDealsData.setPrice(jsonObject.getString("coupon_title"));
                            topDealsData.setMerchant_thumbnailUrl(jsonObject.optString("link", "vishal"));
                            topDealsData.setPrice_caption(jsonObject.optString("coupon_code" , ""));
                            topDealsDataList.add(topDealsData);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    topDealsAdapter.notifyDataSetChanged();






                } catch (JSONException e) {
                    e.printStackTrace();
                    e.getMessage();
                }

                builder.cancel();
                main_layout.setVisibility(View.VISIBLE);

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


    private void referenceInitialization() {


    }

    private void getTopDealData() {

        /*  For Top Deals Recycler Data*/

        final TopDealsAdapter topDealsAdapter;  // live example of final keyword not initialized when declare at method at a time
        LinearLayoutManager layoutManager_topdeals_recyclerview
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        final RecyclerView topdeals_recycler = (RecyclerView) findViewById(R.id.topdeals_recyclerview);
/*
        FlipInTopXAnimator animator = new FlipInTopXAnimator();
        animator.setAddDuration(2000);
        animator.setRemoveDuration(2000);
        topdeals_recycler.setItemAnimator(animator);*/
        //  mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        topdeals_recycler.setHasFixedSize(true);
        topdeals_recycler.setLayoutManager(layoutManager_topdeals_recyclerview);
        final List<TopDealsData> topDealsDataList = new ArrayList<>(0);
        topDealsAdapter = new TopDealsAdapter(this, topDealsDataList);
        topdeals_recycler.setAdapter(topDealsAdapter);
        //    topDealsAdapter.setHasStableIds(true) ;


      /*    For Popular Products Recycler Data*/

//        final ProductAdapter popularProductsAdapter;
//        LinearLayoutManager layoutManager_popularproducts_recyclerview
//                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        RecyclerView popularproducts_recyclerview = (RecyclerView) findViewById(R.id.popularproducts_recyclerview);
//        popularproducts_recyclerview.setHasFixedSize(true);
//        popularproducts_recyclerview.setLayoutManager(layoutManager_popularproducts_recyclerview);
//        final List<Productdata> popularProductsList = new ArrayList<>(0);
//        popularProductsAdapter = new ProductAdapter(this, popularProductsList);
//        popularproducts_recyclerview.setAdapter(popularProductsAdapter);


    /*      For Popular Mobiles Recycler Data*/

//        final ProductAdapter popularMobilesAdapter;
//        LinearLayoutManager layoutManager_popularmobiles_recyclerview
//                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        RecyclerView popularmobiles_recyclerview = (RecyclerView) findViewById(R.id.popularmobiles_recyclerview);
//        popularmobiles_recyclerview.setHasFixedSize(true);
//        popularmobiles_recyclerview.setLayoutManager(layoutManager_popularmobiles_recyclerview);
//        final List<Productdata> popularMobilesList = new ArrayList<>(0);
//        popularMobilesAdapter = new ProductAdapter(this, popularMobilesList);
//        popularmobiles_recyclerview.setAdapter(popularMobilesAdapter);


       /*   For Recently Viewed Recycler Data*/
//
//        final ProductAdapter recentlyViewedAdapter;
//        LinearLayoutManager layoutManager_recentlyviewed_recyclerview
//                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        RecyclerView recentlyviewed_recyclerview = (RecyclerView) findViewById(R.id.recentlyviewed_recyclerview);
//        recentlyviewed_recyclerview.setHasFixedSize(true);
//        recentlyviewed_recyclerview.setLayoutManager(layoutManager_recentlyviewed_recyclerview);
//        final List<Productdata> recentlyViewedList = new ArrayList<>(0);
//        recentlyViewedAdapter = new ProductAdapter(this, recentlyViewedList);
//        recentlyviewed_recyclerview.setAdapter(recentlyViewedAdapter);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setCancelable(false);

        JsonArrayRequest movieReq = new JsonArrayRequest("http://api.androidhive.info/json/movies.json",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("TAG", response.toString());
                        hidePDialog();
                        Toast.makeText(HomeActivity.this, "onResponse", Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject jsonObject = response.getJSONObject(i);
                                TopDealsData topDealsData = new TopDealsData();
                                topDealsData.setDescription(jsonObject.getString("title"));  //  use opt
                                topDealsData.setProduct_thumbnailUrl(jsonObject.getString("image"));
                                topDealsData.setPrice(jsonObject.getString("releaseYear"));
                                topDealsData.setMerchant_thumbnailUrl(jsonObject.getString("image"));
                                topDealsData.setPrice_caption(jsonObject.getString("rating"));
                                topDealsDataList.add(topDealsData);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }


//                        for (int i = 0; i < response.length(); i++) {
//                            try {
//
//                                JSONObject jsonObject = response.getJSONObject(i);
//                                Productdata popularProductData = new Productdata();
//                                popularProductData.setDescription(jsonObject.getString("title"));  //  use opt
//                                popularProductData.setProduct_thumbnailUrl(jsonObject.getString("image"));
//                                popularProductData.setPrice(jsonObject.getString("releaseYear"));
//                                popularProductsList.add(popularProductData);
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }

//                        for (int i = 0; i < response.length(); i++) {
//                            try {
//
//                                JSONObject jsonObject = response.getJSONObject(i);
//                                Productdata popularMobilesData = new Productdata();
//                                popularMobilesData.setDescription(jsonObject.getString("title"));  //  use opt
//                                popularMobilesData.setProduct_thumbnailUrl(jsonObject.getString("image"));
//                                popularMobilesData.setPrice(jsonObject.getString("releaseYear"));
//                                popularMobilesList.add(popularMobilesData);
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }

//                        for (int i = 0; i < response.length(); i++) {
//                            try {
//
//                                JSONObject jsonObject = response.getJSONObject(i);
//                                Productdata recentlyVieweddata = new Productdata();
//                                recentlyVieweddata.setDescription(jsonObject.getString("title"));  //  use opt
//                                recentlyVieweddata.setProduct_thumbnailUrl(jsonObject.getString("image"));
//                                recentlyVieweddata.setPrice(jsonObject.getString("releaseYear"));
//                                recentlyViewedList.add(recentlyVieweddata);
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
                        topDealsAdapter.notifyDataSetChanged();
                      //  popularProductsAdapter.notifyDataSetChanged();
                      //  popularMobilesAdapter.notifyDataSetChanged();
                     //   recentlyViewedAdapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                hidePDialog();
                Toast.makeText(HomeActivity.this, "onErrorResponse", Toast.LENGTH_SHORT).show();

            }
        });

        movieReq.setRetryPolicy(new DefaultRetryPolicy(3000, 2, 2));
        AppController.getInstance().addToRequestQueue(movieReq);
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

    private void setListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


    class ListViewPopularCategoryAdapter extends ArrayAdapter<NavigationPopularCategoryData> {
        private List<NavigationPopularCategoryData> navigationPopularCategoryDataList;
        private final Context context;
        private ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        public ListViewPopularCategoryAdapter(Context context, List<NavigationPopularCategoryData> navigationPopularCategoryDataList) {
            super(context, R.layout.listview_popularcategory_layout, navigationPopularCategoryDataList);
            this.context = context;
            this.navigationPopularCategoryDataList = navigationPopularCategoryDataList;
        }


        // private List<ReferenceSought> referenceSoughtList;


        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public int getCount() {
            return super.getCount();
        }


        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            //super.getView(position, convertView, parent);

            final ViewHolder holder;
            if (convertView == null) {
                LayoutInflater myLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = myLayoutInflater.inflate(R.layout.listview_popularcategory_layout, null);
                holder = new ViewHolder();
                holder.tv_listview_populatcategory = (TextView) convertView.findViewById(R.id.tv_listview_populatcategory);
                if (imageLoader == null)
                    imageLoader = AppController.getInstance().getImageLoader();
                holder.popularcategory_thumbnail = (NetworkImageView) convertView.findViewById(R.id.popularcategory_thumbnail);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            NavigationPopularCategoryData navigationPopularCategoryData = navigationPopularCategoryDataList.get(position);

            //  holder.btn_listview_populatcategory.setCompoundDrawablesWithIntrinsicBounds(R.drawable.home1, 0, 0, 0);
            holder.tv_listview_populatcategory.setText(navigationPopularCategoryData.getName());
            holder.popularcategory_thumbnail.setImageUrl(navigationPopularCategoryData.getThumbnailUrl(), imageLoader);

            //   AnimationUtils.scaleXYV(convertView);

            return convertView;
        }

        private class ViewHolder {
            private TextView tv_listview_populatcategory;
            private NetworkImageView popularcategory_thumbnail;
        }
    }


    class ListViewOthersAdapter extends ArrayAdapter<OtherNavigationData> {
        private List<OtherNavigationData> otherNavigationDataList;
        private final Context context;

        public ListViewOthersAdapter(Context context, List<OtherNavigationData> otherNavigationDataList) {
            super(context, R.layout.listview_navigation_other_layout, otherNavigationDataList);
            this.context = context;
            this.otherNavigationDataList = otherNavigationDataList;
        }


        // private List<ReferenceSought> referenceSoughtList;


        @Override
        public OtherNavigationData getItem(int position) {
            return super.getItem(position);
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public int getCount() {
            return super.getCount();
        }


        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            //super.getView(position, convertView, parent);

            final ViewHolder holder;
            if (convertView == null) {
                LayoutInflater myLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = myLayoutInflater.inflate(R.layout.listview_navigation_other_layout, null);
                holder = new ViewHolder();
                holder.tv_listview_navigation_other = (TextView) convertView.findViewById(R.id.tv_listview_navigation_other);
                holder.iv_navigation_other = (ImageView) convertView.findViewById(R.id.iv_navigation_other);
                //   holder.tv_listview_navigation_other.setShadowLayer(1.5f,-2,5, Color.GRAY);  // for TextView Shadow
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            OtherNavigationData otherNavigationData = otherNavigationDataList.get(position);

            //  holder.btn_listview_populatcategory.setCompoundDrawablesWithIntrinsicBounds(R.drawable.home1, 0, 0, 0);
            holder.tv_listview_navigation_other.setText(otherNavigationData.getName());
            holder.iv_navigation_other.setImageResource(otherNavigationData.getImage_resource_id());


            return convertView;
        }

        private class ViewHolder {
            private TextView tv_listview_navigation_other;
            private ImageView iv_navigation_other;
        }
    }


    private void setListViewHeights(ExpandableListView listView,
                                    int group) {
        android.widget.ExpandableListAdapter listAdapter = (android.widget.ExpandableListAdapter) listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group))
                    || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null,
                            listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                    totalHeight += listItem.getMeasuredHeight();
                }
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 200;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<ExpandedMenu>();
        listDataChild = new HashMap<ExpandedMenu, List<String>>();

        ExpandedMenu item1 = new ExpandedMenu();
        item1.setIconName("heading1");
        item1.setIconImg(R.mipmap.ic_launcher);
        // Adding data header
        listDataHeader.add(item1);

        ExpandedMenu item2 = new ExpandedMenu();
        item2.setIconName("heading2");
        item2.setIconImg(R.mipmap.ic_launcher);
        listDataHeader.add(item2);

        ExpandedMenu item3 = new ExpandedMenu();
        item3.setIconName("heading3");
        item3.setIconImg(R.mipmap.ic_launcher);
        listDataHeader.add(item3);

        ExpandedMenu item4 = new ExpandedMenu();
        item4.setIconName("heading4");
        item4.setIconImg(R.mipmap.ic_launcher);
        // Adding data header
        listDataHeader.add(item4);

        ExpandedMenu item5 = new ExpandedMenu();
        item5.setIconName("heading5");
        item5.setIconImg(R.mipmap.ic_launcher);
        // Adding data header
        listDataHeader.add(item5);

        ExpandedMenu item6 = new ExpandedMenu();
        item6.setIconName("heading6");
        item6.setIconImg(R.mipmap.ic_launcher);
        // Adding data header
        listDataHeader.add(item6);


        // Adding child data
        List<String> heading1 = new ArrayList<String>();
        heading1.add("Submenu of item 1");

        List<String> heading2 = new ArrayList<String>();
        heading2.add("Submenu of item 2");
        heading2.add("Submenu of item 2");
        heading2.add("Submenu of item 2");

        List<String> heading3 = new ArrayList<String>();
        heading3.add("Submenu of item 3");
        heading3.add("Submenu of item 3");
        heading3.add("Submenu of item 3");

        List<String> heading4 = new ArrayList<String>();
        heading4.add("Submenu of item 4");
        heading4.add("Submenu of item 4");
        heading4.add("Submenu of item 4");

        List<String> heading5 = new ArrayList<String>();
        heading5.add("Submenu of item 5");
        heading5.add("Submenu of item 5");
        heading5.add("Submenu of item 5");

        List<String> heading6 = new ArrayList<String>();
        heading6.add("Submenu of item 6");
        heading6.add("Submenu of item 6");
        heading6.add("Submenu of item 6");

        listDataChild.put(listDataHeader.get(0), heading1);// Header, Child data
        listDataChild.put(listDataHeader.get(1), heading2);
        listDataChild.put(listDataHeader.get(2), heading3);
        listDataChild.put(listDataHeader.get(3), heading4);
        listDataChild.put(listDataHeader.get(4), heading5);
        listDataChild.put(listDataHeader.get(5), heading6);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
       /* final SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(HomeActivity.this, s, Toast.LENGTH_LONG).show();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                   Toast.makeText(HomeActivity.this, s, Toast.LENGTH_LONG).show();
              //  mayAdap.getFilter().filter(s);
              //  mayAdap.notifyDataSetChanged();
                return false;
            }
        });*/


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent i = this.getIntent();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_coupon) {
            i = i.setClass(this, CouponActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.enter, R.anim.exit);
            return true;
        } else if (id == R.id.action_search) {
            Intent intent = new Intent(this,SearchActivity.class);
            Toast.makeText(HomeActivity.this, "search clicked", Toast.LENGTH_LONG).show();
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Intent i = this.getIntent();
        switch (item.getItemId()) {

            case R.id.login:
                i = i.setClass(this, SignupActivity.class);
                break;

            case R.id.logout:
                i = i.setClass(this, SignupActivity.class);
                break;

            case R.id.contact_us:
                i = i.setClass(this, ContactUs.class);
                break;

            case R.id.coupon_of_the_day:
                i = i.setClass(this, CouponActivity.class);
                break;

            case R.id.share:
                String shareBody = "https://play.google.com/store/apps/details?id=************************";
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "APP NAME (Open it in Google Play Store to Download the Application)");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                overridePendingTransition(R.anim.enter, R.anim.exit);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;

            default:
                DrawerLayout drawer1 = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer1.closeDrawer(GravityCompat.START);
                return true;


        }

        startActivity(i);
        overridePendingTransition(R.anim.enter, R.anim.exit);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClickHomeCategory(View view) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.openDrawer(GravityCompat.START);
    }

    public void onClickHomeBestDeals(View view) {
        Intent i = this.getIntent();
        i.putExtra("DealsGategory", productdataList);
        i = i.setClass(this, BestDealsActivity.class);
        startActivity(i);
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

    public void onClickHome(View view) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    public class ExpandableListAdapter extends BaseExpandableListAdapter {
        private Context mContext;
        private List<ExpandedMenu> mListDataHeader; // header titles

        // child data in format of header title, child title
        private HashMap<ExpandedMenu, List<String>> mListDataChild;
        ExpandableListView expandList;

        public ExpandableListAdapter(Context context, List<ExpandedMenu> listDataHeader,
                                     HashMap<ExpandedMenu, List<String>> listChildData, ExpandableListView mView) {
            this.mContext = context;
            this.mListDataHeader = listDataHeader;
            this.mListDataChild = listChildData;
            this.expandList = mView;
        }

        @Override
        public int getGroupCount() {
            int i = mListDataHeader.size();
            Log.d("GROUPCOUNT", String.valueOf(i));
            return this.mListDataHeader.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            int childCount = 0;
            if (groupPosition != 6) {
                childCount = this.mListDataChild.get(this.mListDataHeader.get(groupPosition))
                        .size();
            }
            return childCount;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return this.mListDataHeader.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            Log.d("CHILD", mListDataChild.get(this.mListDataHeader.get(groupPosition))
                    .get(childPosition).toString());
            return this.mListDataChild.get(this.mListDataHeader.get(groupPosition))
                    .get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            ExpandedMenu headerTitle = (ExpandedMenu) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this.mContext
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.listheader, null);
            }
            TextView lblListHeader = (TextView) convertView
                    .findViewById(R.id.submenu);
            ImageView headerIcon = (ImageView) convertView.findViewById(R.id.iconimage);
            //  lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle.getIconName());
            headerIcon.setImageResource(headerTitle.getIconImg());
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            final String childText = (String) getChild(groupPosition, childPosition);

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this.mContext
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_submenu, null);
            }

            TextView txtListChild = (TextView) convertView
                    .findViewById(R.id.submenu);

            txtListChild.setText(childText);

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }


   /* private class CustomAdapter extends ArrayAdapter { // TODO see <String> and think
        private final Context context;

        public CustomAdapter(Context context) {
            super(context, R.layout.customexpandablelayout, listDataHeader);
            this.context = context;
        }
        // private List<ReferenceSought> referenceSoughtList;


        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public int getCount() {
            return super.getCount();
        }


        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            //super.getView(position, convertView, parent);

            final ViewHolder holder;
            if (convertView == null) {
                LayoutInflater myLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = myLayoutInflater.inflate(R.layout.customexpandablelayout, null);
                holder = new ViewHolder();
                holder.navigationmen5u = (ExpandableListView) convertView.findViewById(R.id.navigationmen5u);


                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            prepareListData();
            final ExpandableListAdapter mMenuAdapter = new ExpandableListAdapter(HomeActivity.this, listDataHeader, listDataChild, elv_homeactivity_allcategory);

            // setting list adapter
            holder.navigationmen5u.setAdapter(mMenuAdapter);

            holder.navigationmen5u.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                    ExpandedMenu expandedMenu = (ExpandedMenu) mMenuAdapter.getGroup(groupPosition);

                    Toast
                            .makeText(getApplicationContext(), expandedMenu.getIconName(), Toast.LENGTH_SHORT)
                            .show();

                    return false;
                }
            });

            holder.navigationmen5u.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    String child = (String) mMenuAdapter.getChild(groupPosition, childPosition);

                    Toast
                            .makeText(getApplicationContext(), child, Toast.LENGTH_SHORT)
                            .show();

                    return false;
                }
            });

            // Listview Group collasped listener
            holder.navigationmen5u.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

                @Override
                public void onGroupCollapse(int groupPosition) {
                    Toast.makeText(getApplicationContext(),
                            listDataHeader.get(groupPosition) + " Collapsed",
                            Toast.LENGTH_SHORT).show();

                }
            });

            holder.navigationmen5u.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                @Override
                public void onGroupExpand(int groupPosition) {
                    Toast.makeText(getApplicationContext(),
                            listDataHeader.get(groupPosition) + " Expanded",
                            Toast.LENGTH_SHORT).show();
                }
            });

            return convertView;
        }


        public class ViewHolder {
            private ExpandableListView navigationmen5u;

        }
    }*/


}

class TopDealsAdapter extends RecyclerView.Adapter<TopDealsAdapter.ViewHolder> {
    private static Context context;
    private List<TopDealsData> topDealsDataList;
    private static ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private int mPreviousPosition = 0;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        private TextView tv_description, tv_price, tv_price_caption;
        private NetworkImageView product_thumbnail, merchant_thumnbail;
       // private AQuery aq;

        public ViewHolder(View v) {
            super(v);
          //  aq = new AQuery(v);
            tv_description = (TextView) v.findViewById(R.id.topdeals_description);
            tv_price = (TextView) v.findViewById(R.id.topdeals_price);
            tv_price_caption = (TextView) v.findViewById(R.id.topdeals_price_caption);
            if (imageLoader == null)
                imageLoader = AppController.getInstance().getImageLoader();
            product_thumbnail = (NetworkImageView) v.findViewById(R.id.topdeals_product_thumbnail);
            merchant_thumnbail = (NetworkImageView) v.findViewById(R.id.topdeals_merchant_thumbnail);
            //mTextView = v;
            itemView.setOnClickListener(this);
        }

        // Handles the row being being clicked
        @Override
        public void onClick(View view) {
            int position = getLayoutPosition(); // gets item position

           TopDealsData topDealsData =  topDealsDataList.get(position);
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(topDealsData.getMerchant_thumbnailUrl()));
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
    public TopDealsAdapter(Context context, List<TopDealsData> topDealsDataList) {
        this.topDealsDataList = topDealsDataList;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TopDealsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.topdeals_recycler_data, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        TopDealsData topDealsData = topDealsDataList.get(position);

        holder.tv_description.setText(topDealsData.getDescription());
        holder.tv_price.setText(topDealsData.getPrice());
        holder.tv_price_caption.setText(topDealsData.getPrice_caption());
       // holder.product_thumbnail.setImageUrl(topDealsData.getProduct_thumbnailUrl(), imageLoader);
      //  holder.merchant_thumnbail.setImageUrl(topDealsData.getMerchant_thumbnailUrl(), imageLoader);

      /*  imageLoader.get(topDealsData.getProduct_thumbnailUrl(),
                ImageLoader.getImageListener(holder.product_thumbnail,
                        R.mipmap.ic_launcher,
                        R.mipmap.ic_launcher));*/
        holder.product_thumbnail.setImageUrl(topDealsData.getProduct_thumbnailUrl(), imageLoader);
    //    holder.product_thumbnail.setImageResource(R.drawable.junction_logo);
          /*   ImageLoader.ImageListener im= imageLoader.getImageListener(holder.product_thumbnail, R.drawable.allcategory, R.drawable.allcategory);
        ImageLoader.ImageContainer imageContainer=imageLoader.get(topDealsData.getProduct_thumbnailUrl(), im);
        holder.product_thumbnail.setImageBitmap(imageContainer.getBitmap());*/
              /* Picasso.with(context).load(topDealsData.getProduct_thumbnailUrl()).rotate(90).into(holder.product_thumbnail);
       *//* Picasso.with(this)
                .load("YOUR IMAGE URL HERE")
                .placeholder(R.drawable.ic_placeholder)   // optional
                .error(R.drawable.ic_error_fallback)      // optional
                .resize(250, 200)                        // optional
                .rotate(90)                             // optional
                .into(imageView);*/

        /*holder.aq.id(holder.product_thumbnail).image(topDealsData.getProduct_thumbnailUrl(),
                true, true, 0, 0, new BitmapAjaxCallback() {
                    @Override
                    public void callback(String url, ImageView iv, Bitmap bm, AjaxStatus status) {
                      //  iv.setImageBitmap(bm);
                        holder.product_thumbnail.setImageBitmap(bm);
                        Toast.makeText(context, status.getMessage() + "", Toast.LENGTH_LONG).show();
                    }
                });*/


// Loading image with placeholder and error image
        /*imageLoader.get(topDealsData.getProduct_thumbnailUrl(), ImageLoader.getImageListener(
                holder.product_thumbnail, R.drawable.bestprice, R.drawable.gallery),600,600);
       */ // Toast.makeText(t, position + "", Toast.LENGTH_SHORT).show();

        /*holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
       /* boolean check=false;
        if (position > mPreviousPosition)
            check=true;

        int j = position % 5;
        switch (j) {
            case 0:
                AnimationUtils.animateSunblind(holder, check);
                break;
            case 1:
                AnimationUtils.animate1(holder, check);
                break;
            case 2:
                AnimationUtils.animate(holder, check);
                break;
            case 3:
                AnimationUtils.animateScatter(holder, check);
                break;
            case 4:
                AnimationUtils.scaleXY(holder);
                break;


        }*/

        if (position > mPreviousPosition) {
            AnimationUtils.animateSunblind(holder, true);
//            AnimationUtils.animateSunblind(holder, true);
            //  AnimationUtils.animate1(holder, true);
            //     AnimationUtils.animate(holder, true);
            //   AnimationUtils.animateScatter(holder, true);
            AnimationUtils.scaleXYVishal(holder.product_thumbnail);

            // AnimationUtils.animateToolbarDroppingDown(holder.product_thumbnail);
        } else {
            AnimationUtils.animateSunblind(holder, false);
//            AnimationUtils.animateSunblind(holder, false);
            //     AnimationUtils.animate1(holder, false);
            //   AnimationUtils.animate(holder, false);
            //     AnimationUtils.animateScatter(holder, false);
            AnimationUtils.scaleXYVishal(holder.product_thumbnail);
            //AnimationUtils.animateToolbarDroppingDown(holder.product_thumbnail);
        }
        mPreviousPosition = position;


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return topDealsDataList.size();
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

class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private static Context context;
    private List<ProductDataList> productdataList;
    private static ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private int mPreviousPosition = 0;

    public ProductAdapter(Context context, List<ProductDataList> productdataList) {
        this.productdataList = productdataList;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_recycler_data, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        ProductDataList productdata = productdataList.get(position);

        holder.tv_description.setText(productdata.getDescription());
        holder.tv_price.setText(productdata.getPrice());
        holder.product_thumbnail.setImageUrl(productdata.getImageUrl(), imageLoader);

        // Toast.makeText(t, position + "", Toast.LENGTH_SHORT).show();

        /*holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        if (position > mPreviousPosition) {
              AnimationUtils.animateSunblind(holder, true);
//            AnimationUtils.animateSunblind(holder, true);
            //  AnimationUtils.animate1(holder, true);
            //     AnimationUtils.animate(holder, true);
        //    AnimationUtils.animateScatter(holder, true);
            AnimationUtils.scaleXYVishal(holder.product_thumbnail);

            // AnimationUtils.animateToolbarDroppingDown(holder.product_thumbnail);
        } else {
             AnimationUtils.animateSunblind(holder, false);
//            AnimationUtils.animateSunblind(holder, false);
            //     AnimationUtils.animate1(holder, false);
            //   AnimationUtils.animate(holder, false);
         //   AnimationUtils.animateScatter(holder, false);
            AnimationUtils.scaleXYVishal(holder.product_thumbnail);
            //AnimationUtils.animateToolbarDroppingDown(holder.product_thumbnail);
        }
        mPreviousPosition = position;


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return productdataList.size();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        private TextView tv_description, tv_price;
        private NetworkImageView product_thumbnail;

        public ViewHolder(View v) {
            super(v);
            tv_description = (TextView) v.findViewById(R.id.product_description);
            tv_price = (TextView) v.findViewById(R.id.product_price);
            if (imageLoader == null)
                imageLoader = AppController.getInstance().getImageLoader();
            product_thumbnail = (NetworkImageView) v.findViewById(R.id.product_product_thumbnail);
            //mTextView = v;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    ProductDataList productdata = productdataList.get(position);
                    Log.d("SB4K", productdata.getCategoriesUrlKey()+productdata.getDescription()+productdata.getProductUrlKey());
                    context.startActivity(new Intent(context, ProductDetailsActivity.class).
                            putExtra("categoriesUrlKey", productdata.getCategoriesUrlKey())
                            .putExtra("name", productdata.getDescription())
                            .putExtra("productId", productdata.getSb4kProductID())
                            .putExtra("productUrlKey", productdata.getProductUrlKey()));


                }
            });
        }

        // Handles the row being being clicked
        @Override
        public void onClick(View view) {
            int position = getLayoutPosition(); // gets item position

            // We can access the data within the views
            Toast.makeText(context, position + "", Toast.LENGTH_SHORT).show();
            Intent i = new Intent();
            //noinspection SimplifiableIfStatement

            i = i.setClass(context, ProductDetailsActivity.class);
            context.startActivity(i);
        }


    }

    // Provide a suitable constructor (depends on the kind of dataset)


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

/*/* RecyclerView myList1 = (RecyclerView) findViewById(R.id.my_recycler_view1);
        SlideInUpAnimator slideInUpAnimator = new SlideInUpAnimator();
       *//* slideInUpAnimator.setAddDuration(3000);
        slideInUpAnimator.setRemoveDuration(5000);*//*
        myList1.setItemAnimator(slideInUpAnimator);
        //  myList1.setNestedScrollingEnabled(false);
        *//*RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(3000);
        itemAnimator.setRemoveDuration(5000);
        myList1.setItemAnimator(itemAnimator);*//*

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        myList1.setHasFixedSize(true);
        myList1.setLayoutManager(layoutManager1);
    //    myList1.setAdapter(mAdapter);


        RecyclerView myList2 = (RecyclerView) findViewById(R.id.my_recycler_view2);
        // myList2.setNestedScrollingEnabled(false);
       *//* RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        myList1.setItemAnimator(itemAnimator);*//*

        LinearLayoutManager layoutManager2
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        myList2.setHasFixedSize(true);
        myList2.setLayoutManager(layoutManager2);
      //  myList2.setAdapter(mAdapter);


        RecyclerView myList3 = (RecyclerView) findViewById(R.id.my_recycler_view3);
       *//* RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        myList1.setItemAnimator(itemAnimator);*//*
        //  myList3.setNestedScrollingEnabled(false);
        LinearLayoutManager layoutManager3
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        myList3.setHasFixedSize(true);
        myList3.setLayoutManager(layoutManager3);
   //     myList3.setAdapter(mAdapter);*/

class MyExpandableAdapter extends BaseExpandableListAdapter {
    Context context;
    List<ParentObject> parentObjects;

    public MyExpandableAdapter(Context context, List<ParentObject> parentObjects)
    {
        this.context = context;
        this.parentObjects = parentObjects;
    }
    @Override
    public int getGroupCount() {
        return parentObjects.size();
    }

    //Cộng thêm 2 vào childrenCount.Row đầu tiên và row cuối cùng dẽ được sử làm header và footer
    //Add 2 to childcount. The first row and the last row are used as header and footer to childview
    @Override
    public int getChildrenCount(int i) {
        return parentObjects.get(i).childObjects.size() +2;
    }

    @Override
    public ParentObject getGroup(int i) {
        return parentObjects.get(i);
    }

    @Override
    public ChildObject getChild(int i, int i2) {
        return parentObjects.get(i).childObjects.get(i2);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i2) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup)
    {
        ParentObject currentParent = parentObjects.get(i);
        if(view ==null)
        {
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.parent_row,null);
        }
        TextView txtMother = (TextView)view.findViewById(R.id.parentText);
        ImageView plus = (ImageView)view.findViewById(R.id.plus);
        ImageView minus = (ImageView)view.findViewById(R.id.minus);
        txtMother.setText(currentParent.getParentText());

        if(currentParent.isPlusMinus()) {
            plus.setVisibility(View.INVISIBLE);
            minus.setVisibility(View.VISIBLE);
        }
        else
        {
            plus.setVisibility(View.VISIBLE);
            minus.setVisibility(View.INVISIBLE);
        }


        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        ParentObject currentParent = getGroup(groupPosition);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //the first row is used as header
        if(childPosition ==0)
        {
            view = inflater.inflate(R.layout.child_header, null);
           /* TextView txtHeader = (TextView)view.findViewById(R.id.txtHeader);
            txtHeader.setText(currentParent.textToHeader);*/
        }

        //Here is the ListView of the ChildView
        if(childPosition>0 && childPosition<getChildrenCount(groupPosition)-1)
        {
            ChildObject currentChild = getChild(groupPosition,childPosition-1);
            view = inflater.inflate(R.layout.child_row,null);
            TextView txtChildName = (TextView)view.findViewById(R.id.txtChildName);
         //   TextView txtChildAge = (TextView)view.findViewById(R.id.txtChildAge);
            txtChildName.setText(currentChild.getChildText());
          //  txtChildAge.setText("Age: " + String.valueOf(currentChild.age));
        }
        //the last row is used as footer
        if(childPosition == getChildrenCount(groupPosition)-1)
        {
            view = inflater.inflate(R.layout.child_footer,null);
            /*TextView txtFooter = (TextView)view.findViewById(R.id.txtFooter);
            txtFooter.setText(currentParent.textToFooter);*/
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }
}


class GIFWebView extends WebView {

    public GIFWebView(Context context, String path) {
        super(context);
        loadUrl(path);
        // TODO Auto-generated constructor stub
    }


}