package in.junctiontech.searchb4kharch;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import in.junctiontech.searchb4kharch.util.Helper;

public class SignupActivity extends AppCompatActivity {

    private Button btn_signup_signup;
    private ScrollView signup_layout_id;
    private EditText edit_signup_password;
    private EditText edit_signup_fname;
    private EditText edit_signup_emailid;
    private EditText edit_signup_lname;
    private EditText edit_signup_dob;
    private SharedPreferences sp;
    private EditText edit_signup_mobileno;
    private String genderText = "Male";
    private Calendar calendar;
    private int year;
    private int month;
    private int day;
    private RadioGroup genderRadioGroup;
    private RadioButton genderMale,genderFemale;
    private Button btn_signup_dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        referenceInitialization();


    }

    private void sub() {
        Helper.hideKeyboard(this);

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Connecting...");
        pDialog.setCancelable(false);
        pDialog.show();

        //   startActivity(new Intent(LoginScreen.this, MainScreen.class));
        //  finish();

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest strReq = new StringRequest(Request.Method.POST,
                "http://www.searchb4kharch.com/user/PersonalInformation?app=true", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("TAG", "Login Response: " + response.toString());
                // Helper.showToast(LoginActivity.this, response);


                try {
                    JSONArray array = new JSONArray(response);
                    JSONObject jsonObject = array.getJSONObject(0);

                    edit_signup_fname.setText(jsonObject.optString("userFirstName", "key not found"));
                    edit_signup_lname.setText(jsonObject.optString("userLastName", "key not found")+"  ");
                    edit_signup_emailid.setText(jsonObject.optString("userEmail", "key not found"));
                   String gender= jsonObject.optString("userGender", "key not found");
                    edit_signup_mobileno.setText( jsonObject.optString("userMobileNo", "key not found"));
                    edit_signup_dob.setText(jsonObject.optString("userDOB", "key not found"));

                    edit_signup_fname.setTextColor(Color.BLACK);
                    edit_signup_lname.setTextColor(Color.BLACK);
                    edit_signup_emailid.setTextColor(Color.BLACK);
                    edit_signup_mobileno.setTextColor(Color.BLACK);
                    edit_signup_dob.setTextColor(Color.BLACK);

                    edit_signup_fname.setEnabled(false);
                    edit_signup_lname.setEnabled(false);
                    edit_signup_emailid.setEnabled(false);
                    edit_signup_mobileno.setEnabled(false);
                    edit_signup_dob.setEnabled(false);

                    if(gender.equalsIgnoreCase("Male"))
                        genderMale.setChecked(true);
                    else if(gender.equalsIgnoreCase("Female"))
                        genderFemale.setChecked(true);

                    genderRadioGroup.setEnabled(false);
                    genderMale.setEnabled(false);
                    genderFemale.setEnabled(false);
                    btn_signup_dob.setEnabled(false);


//                    Log.d("sb4k_response", response);
//                    if ("200".equalsIgnoreCase(data)) {
//                        // Helper.showToast(LoginActivity.this, jsonObject.optString("message", "message key not found"));
//
//                    } else if ("key not found".equalsIgnoreCase(data)) {
//                        // Helper.showToast(LoginActivity.this, "code key not found");
//                    }
                    Helper.showToast(SignupActivity.this, jsonObject.optString("message", "message key not found"));
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    pDialog.dismiss();
                }
            }
        }

                , new Response.ErrorListener()

        {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "Registration Error: " + error.getMessage());
                String err = error.getMessage();
                if (error instanceof NoConnectionError) {
                    err = "No Internet Access\nCheck Your Internet Connection.";
                }
                // TODO dispaly appropriate message ex 404- page not found..


                pDialog.dismiss();

            }

        }

        )

        {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> param = new HashMap<>();
                param.put("user_id", sp.getString("user_id", "not found"));
                Log.d("login_info", param.toString());
                return param;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                headers.put("abc", "value");
                return headers;
            }
        };
        strReq.setRetryPolicy(new DefaultRetryPolicy(3000, 2, 2));
        queue.add(strReq);
    }






    private void referenceInitialization() {

        sp = this.getSharedPreferences("login_info", this.MODE_PRIVATE);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
        boolean check = this.getIntent().getBooleanExtra("PROFILE", false);
        btn_signup_signup = (Button) this.findViewById(R.id.btn_signup_signup);
        edit_signup_password = (EditText) this.findViewById(R.id.edit_signup_password);
        edit_signup_fname = (EditText) this.findViewById(R.id.edit_signup_fname);
        edit_signup_emailid = (EditText) this.findViewById(R.id.edit_signup_emailid);
        edit_signup_lname = (EditText) this.findViewById(R.id.edit_signup_lname);
        edit_signup_dob = (EditText) this.findViewById(R.id.edit_signup_dob);
        edit_signup_mobileno = (EditText) this.findViewById(R.id.edit_signup_mobileno);
        genderMale = (RadioButton) this.findViewById(R.id.genderMale);
        genderFemale = (RadioButton) this.findViewById(R.id.genderFemale);
        genderRadioGroup = (RadioGroup) this.findViewById(R.id.genderRadioGroup);
        btn_signup_dob = (Button) this.findViewById(R.id.btn_signup_dob);

      //  edit_signup_dob.setText(year + "-" + (month+1) + "-" + day);



        signup_layout_id = (ScrollView) this.findViewById(R.id.signup_layout_id);

        sp = this.getSharedPreferences("login_info", MODE_PRIVATE);

        if (check != false) {

            edit_signup_fname.setText(sp.getString("user_name", ""));
            edit_signup_fname.setTextColor(Color.BLACK);
            edit_signup_emailid.setText(sp.getString("user_email", ""));
            edit_signup_emailid.setTextColor(Color.BLACK);
            btn_signup_signup.setVisibility(View.GONE);
            edit_signup_password.setVisibility(View.GONE);
            edit_signup_fname.setEnabled(false);
            edit_signup_emailid.setEnabled(false);
            actionBar.setTitle("Profile");
            sub();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void signupOnClick(View view) {
        submit();

    }

    public void getDOBOnClick(View view) {
        showDialog(999);
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
//            return new DatePickerDialog(this,R.style.DialogTheme, myDateListener, year, month, day);
        }
        return null;
    }

    public DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
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
            edit_signup_dob.setText(year + "-" + new_month + "-" + new_day);

        }
    };


    private boolean isEmptyEditTextField (EditText e) {
        return e.getText() == null || e.getText().toString() == null
                || e.getText().toString().equals("") || e.getText().toString().isEmpty();
    }


    private void submit() {
        Helper.hideKeyboard(this);
        boolean b1 = isEmptyEditTextField(edit_signup_fname);
        boolean b2 = isEmptyEditTextField(edit_signup_lname);
        boolean b3 = isEmptyEditTextField(edit_signup_dob);
        boolean b4 = isEmptyEditTextField(edit_signup_emailid);
        boolean b5 = isEmptyEditTextField(edit_signup_password);
        boolean b6 = isEmptyEditTextField(edit_signup_mobileno);

        edit_signup_fname.setError(null);
        edit_signup_lname.setError(null);
        edit_signup_dob.setError(null);
        edit_signup_emailid.setError(null);
        edit_signup_password.setError(null);
        edit_signup_mobileno.setError(null);



        if (b1 && b2) {
            Snackbar.make(signup_layout_id, "One Or More Fields Are Blank", Snackbar.LENGTH_LONG).setAction("Dismiss", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //sp = getSharedPreferences("Login", MODE_PRIVATE);
                    /*String get_user = sp.getString("user_name", "Couldn't loaded Username...");
                    String get_pass = sp.getString("user_pass", "Couldn't loaded Password...");

                    Toast.makeText(LoginScreen.this, get_user + "\n" + get_pass, Toast.LENGTH_LONG).show();*/
                }
            }).show();

        } else {
            if (b6 || b5 || b4 || b3 || b2 || b1 ) {

                if (b6) {
                    edit_signup_mobileno.setError("Mobile No cannot be blank");
                }

                if (b3) {
                    edit_signup_dob.setError("DOB cannot be blank");
                }

                if (b1) {
                    edit_signup_fname.setError("First name  cannot be blank");
                }
                if (b2) {
                    edit_signup_lname.setError("Last Name cannot be blank");
                }
                if (b4) {
                    edit_signup_emailid.setError("Email Id cannot be blank");
                }
                if (b5) {
                    edit_signup_password.setError("Password cannot be blank");
                }


            } else {
                final ProgressDialog pDialog = new ProgressDialog(this);
                pDialog.setMessage("Connecting...");
                pDialog.setCancelable(false);
                pDialog.show();

                //   startActivity(new Intent(LoginScreen.this, MainScreen.class));
                //  finish();

                RequestQueue queue = Volley.newRequestQueue(this);
                StringRequest strReq = new StringRequest(Request.Method.POST,
                        "http://www.searchb4kharch.com/Login/insert_user_info?app=true", new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", "Login Response: " + response.toString());
                        // Helper.showToast(LoginActivity.this, response);


                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            String data = jsonObject.optString("code", "key not found");
                            Log.d("sb4k_response", response);
                            if ("200".equalsIgnoreCase(data)) {
                                // Helper.showToast(LoginActivity.this, jsonObject.optString("message", "message key not found"));
                                SharedPreferences.Editor editor = sp.edit();
                                //editor.putString("user_id", jsonObject.getString("user_id"));
                                editor.putString("user_name", edit_signup_emailid.getText().toString());
                                editor.putString("user_email", edit_signup_password.getText().toString());
                                editor.putString("user_id", jsonObject.optString("user_id", "id not found"));
                                editor.commit();
                                startActivity(new Intent(SignupActivity.this, FrontActivity.class));
                                finish();
                            }else if ("key not found".equalsIgnoreCase(data)) {
                                // Helper.showToast(LoginActivity.this, "code key not found");
                            }
                            Helper.showToast(SignupActivity.this, jsonObject.optString("message", "message key not found"));
                            //edit_login_password.setText("");
                        }catch(JSONException e){
                            e.printStackTrace();
                        }

                        finally{
                            pDialog.dismiss();
                        }
                    }
                }

                        ,new Response.ErrorListener()

                {

                    @Override
                    public void onErrorResponse (VolleyError error){
                        Log.e("TAG", "Registration Error: " + error.getMessage());
                        String err = error.getMessage();
                        if (error instanceof NoConnectionError) {
                            err = "No Internet Access\nCheck Your Internet Connection.";
                        }
                        // TODO dispaly appropriate message ex 404- page not found..
                        Snackbar.make(signup_layout_id, err + "\n" + error.toString(), Snackbar.LENGTH_LONG).setAction("Dismiss", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();

                        SharedPreferences.Editor editor = sp.edit();
                        editor.remove("user_email");
                        editor.remove("user_name");
                        editor.remove("user_id");
                        editor.commit();
                        pDialog.dismiss();

                    }

                }

                )

                {
                    @Override
                    protected Map<String, String> getParams () {
                        Map<String, String> param = new HashMap<>();

                        param.put("userFirstName", edit_signup_fname.getText().toString());
                        param.put("userLastName", edit_signup_lname.getText().toString());
                        param.put("userEmail", edit_signup_emailid.getText().toString());
                        param.put("userPassword", edit_signup_password.getText().toString());
                        param.put("userGender", genderText);
                        param.put("userDOB", edit_signup_dob.getText().toString());
                        param.put("userMobileNo", edit_signup_mobileno.getText().toString());

                        /*param.put("client_id", "pooja@junctiontech.in");
                        param.put("password", "junction2016");
                        param.put("client_id", "test@abc.com");
                        param.put("token", "f5316a5e35a4");
                        param.put("accountno", "9999888877770001");
                        param.put("device", "androide");*/
                   //     Map<String, String> params = new HashMap<>();
                   //     params.put("sb4k_login_info", new JSONObject(param).toString());
                        Log.d("signup_info", param.toString());
                        return param;
                    }

                    @Override
                    public Map<String, String> getHeaders ()throws AuthFailureError {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("Content-Type", "application/x-www-form-urlencoded");
                        headers.put("abc", "value");
                        return headers;
                    }
                };
                strReq.setRetryPolicy(new DefaultRetryPolicy(3000, 2, 2));
                queue.add(strReq);
            }
        }

    }


    public void genderChecked(View view) {
        RadioButton radioButton = (RadioButton) view;
        genderText = radioButton.getText().toString();
    }
}
