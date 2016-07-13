package in.junctiontech.searchb4kharch;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class FlightsActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private Calendar calendar;
    private int year;
    private int month;
    private int day;
    private static Button depart_flight;
    private static Button return_flight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_flights);
        depart_flight = (Button) findViewById(R.id.btn_flights_depart);
        return_flight = (Button)
                findViewById(R.id.btn_flights_return);
/*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        //  mViewPager.endFakeDrag();

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);*/
        //   tabLayout.setupWithViewPager(mViewPager);



        /*final int PAGE = 2;
        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mViewPager.getCurrentItem() == PAGE) {
                    mViewPager.setCurrentItem(PAGE - 1, false);
                    mViewPager.setCurrentItem(PAGE, false);
                    return true;
                }
                return false;
            }
        });*/


        final TabHost tb = (TabHost) findViewById(R.id.tabHost);
        tb.setup();
        TabHost.TabSpec spec;

        spec = tb.newTabSpec("one_way");
        spec.setContent(R.id.tab1);
        spec.setIndicator("ONE-WAY");
        tb.addTab(spec);

       // tb.setIn

        spec = tb.newTabSpec("round_trip");
        spec.setContent(R.id.tab1);
        spec.setIndicator("ROUND-TRIP");
        tb.addTab(spec);


        for(int i=0;i<tb.getTabWidget().getChildCount();i++)
        {
            TextView tv = (TextView) tb.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#FFFFFF"));
            tb.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#b97dbc"));
        }
        tb.getTabWidget().setCurrentTab(1);
        tb.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#3c103e"));

     //   tb.setCurrentTab();


        //tb.setCurrentTab(0);

        tb.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
               Toast.makeText(FlightsActivity.this,tabId,Toast.LENGTH_LONG).show();
               // setSatatus(tb.getCurrentTab());
                for(int i=0;i<tb.getTabWidget().getChildCount();i++)
                {
                    tb.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#3c103e"));
                }

                tb.getTabWidget().getChildAt(tb.getCurrentTab()).setBackgroundColor(Color.parseColor("#b97dbc"));



            }
        });

/*
        // Inbox Tab
        TabSpec inboxSpec = tabHost.newTabSpec(INBOX_SPEC);
        // Tab Icon
        inboxSpec.setIndicator(INBOX_SPEC, getResources().getDrawable(R.drawable.arrow));
        Intent inboxIntent = new Intent(this, InboxActivity.class);
        // Tab Content
        inboxSpec.setContent(inboxIntent);

        // Outbox Tab
        TabSpec outboxSpec = tabHost.newTabSpec(OUTBOX_SPEC);
        outboxSpec.setIndicator(OUTBOX_SPEC, getResources().getDrawable(R.drawable.arrow));
        Intent outboxIntent = new Intent(this, ProfileActivity.class);
        outboxSpec.setContent(outboxIntent);

        // Profile Tab
        TabSpec profileSpec = tabHost.newTabSpec(PROFILE_SPEC);
        profileSpec.setIndicator(PROFILE_SPEC, getResources().getDrawable(R.drawable.arrow));
        Intent profileIntent = new Intent(this, LazyAdapter.class);
        profileSpec.setContent(profileIntent);

        // Adding all TabSpec to TabHost
        tabHost.addTab(inboxSpec); // Adding Inbox tab
        tabHost.addTab(outboxSpec); // Adding Outbox tab
        tabHost.addTab(profileSpec); // Adding Profile tab



        TabLayout.Tab t1 = tabLayout.newTab();
        t1.setText("One-Way");
        //t1.setIcon(R.drawable.arrow);

        TabLayout.Tab t2 = tabLayout.newTab();
        t2.setText("Round-Trip");
        //t2.setIcon(R.drawable.arrow);

        tabLayout.addTab(t1);
        tabLayout.addTab(t2);*//*


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener()

        {
                                               @Override
                                               public void onTabSelected(TabLayout.Tab tab) {
                                                   Toast.makeText(FlightsActivity.this, tab.getText() + "", Toast.LENGTH_LONG).show();

                                               }

                                               @Override
                                               public void onTabUnselected(TabLayout.Tab tab) {

                                               }

                                               @Override
                                               public void onTabReselected(TabLayout.Tab tab) {

                                               }
        }

        );*/



       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()

                               {
                                   @Override
                                   public void onClick(View view) {
                                       Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                                               .setAction("Action", null).show();
                                   }
                               }

        );*/

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


        String new_month = month+ "";
        if ((month+1) < 10)
            new_month = "0" + (month+1);

        String new_day = day + "";
//            Toast.makeText(AttendenceEntry.this,new_month ,Toast.LENGTH_LONG).show();
        if ((day + 1) < 10)
            new_day = "0" + (day);



       depart_flight.setText(year + "-" + new_month + "-" + new_day);
       //depart_flight.setText(year + "-" + new_month + "-" + new_day);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_flights, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String lastSelected = null;

    public void onClickSelectClass(final View view) {

        final AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setCancelable(false);

        final RadioGroup rg = new RadioGroup(this);
        //     final RadioButton rb_admin = newtext RadioButton(SignUpActivity.this);
        final RadioButton economy = new RadioButton(this);
        final RadioButton premium_economy = new RadioButton(this);
        final RadioButton business = new RadioButton(this);
        final RadioButton first = new RadioButton(this);

        if(lastSelected==null)
            economy.setChecked(true);

        else if (lastSelected.equalsIgnoreCase("Economy"))
            economy.setChecked(true);
        else if (lastSelected.equalsIgnoreCase("Premium Economy"))
            premium_economy.setChecked(true);
        else if (lastSelected.equalsIgnoreCase("Business"))
            business.setChecked(true);
        else if (lastSelected.equalsIgnoreCase("First"))
            first.setChecked(true);


        economy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    ((Button) view).setText(buttonView.getText().toString());
                    lastSelected = "Economy";
                    alert.dismiss();
                }

            }
        });
        premium_economy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    ((Button) view).setText(buttonView.getText().toString());
                    lastSelected = "Premium Economy";
                    alert.dismiss();
                }

            }
        });
        business.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    ((Button) view).setText(buttonView.getText().toString());
                    lastSelected = "Business";
                    alert.dismiss();
                }

            }
        });

        first.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    ((Button) view).setText(buttonView.getText().toString());
                    lastSelected = "First";
                    alert.dismiss();
                }

            }
        });






        economy.setText("Economy");
        premium_economy.setText("Premium Economy");
        business.setText("Business");
        first.setText("First");


        rg.addView(economy);
        rg.addView(premium_economy);
        rg.addView(business);
        rg.addView(first);
        rg.setPadding(50, 50, 50, 50);

        alert.setView(rg);
        alert.setTitle("Select a Class");


        alert.show();





    }




    public void onClickFlightDate(View view) {

        if(view.getId()==R.id.btn_flights_depart)
        {
            showDialog(999);
        }else if(view.getId()==R.id.btn_flights_return)
        {
            showDialog(888);
        }


    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999)

            return new DatePickerDialog(this, myDateListenerDepart, year, month, day);
//            return new DatePickerDialog(this,R.style.DialogTheme, myDateListener, year, month, day);
        else if (id == 888)
            return new DatePickerDialog(this, myDateListenerReturn, year, month, day);

        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListenerDepart = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String new_day = dayOfMonth + "";
            if (dayOfMonth < 10)
                new_day = "0" + dayOfMonth;

            String new_month = monthOfYear + 1 + "";
//            Toast.makeText(AttendenceEntry.this,new_month ,Toast.LENGTH_LONG).show();
            if ((monthOfYear + 1) < 10)
                new_month = "0" + (monthOfYear + 1);

//            currentdate.setText("" + new_day + "-" + new_month + "-" + year);
            depart_flight.setText(year + "-" + new_month + "-" + new_day);

        }
    };

    private DatePickerDialog.OnDateSetListener myDateListenerReturn = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String new_day = dayOfMonth + "";
            if (dayOfMonth < 10)
                new_day = "0" + dayOfMonth;

            String new_month = monthOfYear + 1 + "";
//            Toast.makeText(AttendenceEntry.this,new_month ,Toast.LENGTH_LONG).show();
            if ((monthOfYear + 1) < 10)
                new_month = "0" + (monthOfYear + 1);

//            currentdate.setText("" + new_day + "-" + new_month + "-" + year);
            return_flight.setText(year + "-" + new_month + "-" + new_day);

        }
    };




    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_flights, container, false);
            /*TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            depart_flight = (Button) rootView.findViewById(R.id.btn_flights_depart);
            return_flight = (Button) rootView.findViewById(R.id.btn_flights_return);

            depart_flight.setText("Hello");*/

            return rootView;
        }
    }
}
