package in.junctiontech.searchb4kharch;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.junctiontech.searchb4kharch.util.Helper;

public class LoginActivity extends AppCompatActivity {

    private EditText edit_login_emailid, edit_login_password;
    private ScrollView login_layout_id;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!= null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        edit_login_emailid = (EditText)this.findViewById(R.id.edit_login_emailid);
        edit_login_password = (EditText)this.findViewById(R.id.edit_login_password);
        login_layout_id = (ScrollView)this.findViewById(R.id.login_layout_id);
        sp = this.getSharedPreferences("login_info", this.MODE_PRIVATE);
      //  check();


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void loginOnClick(View view) {
        submit();
    }

    public void forgotpasswordOnClick(View view) {
        startActivity(this.getIntent().setClass(this,ForgotPasswordActivity.class));
    }

    private void createCustomActionBarTitle()
    {
        /*    First Approach    */

       /* ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.custom_action_bar, null);

        TextView frag1 = (TextView)v.findViewById(R.id.apptitle);
       // frag1.setTypeface(vivaldiiFont);

        actionBar.setCustomView(v);*/

        /*      Second approach     */

        /*

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.custom_action_bar_new);
        actionBar.setDisplayHomeAsUpEnabled(true);

        */


    }


    private boolean isEmptyEmail () {
        return edit_login_emailid.getText() == null || edit_login_emailid.getText().toString() == null
                || edit_login_emailid.getText().toString().equals("") || edit_login_emailid.getText().toString().isEmpty();
    }


    private boolean isEmptyPassword () {
        return edit_login_password.getText() == null || edit_login_password.getText().toString() == null
                || edit_login_password.getText().toString().equals("") || edit_login_password.getText().toString().isEmpty();
    }


    private void submit() {
        Helper.hideKeyboard(this);

        boolean b1 = isEmptyEmail();
        boolean b2 = isEmptyPassword();


        edit_login_emailid.setError(null);
        edit_login_password.setError(null);


        if (b1 && b2) {
            Snackbar.make(login_layout_id, "One Or More Field Are Blank", Snackbar.LENGTH_LONG).setAction("Dismiss", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //sp = getSharedPreferences("Login", MODE_PRIVATE);
                    /*String get_user = sp.getString("user_name", "Couldn't loaded Username...");
                    String get_pass = sp.getString("user_pass", "Couldn't loaded Password...");

                    Toast.makeText(LoginScreen.this, get_user + "\n" + get_pass, Toast.LENGTH_LONG).show();*/
                }
            }).show();

        } else {
            if (b2 || b1) {

                if (b1) {
                    edit_login_emailid.setError("Email Id cannot be blank");
                }
                if (b2) {
                    edit_login_password.setError("Password cannot be blank");
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
                        "http://www.searchb4kharch.com/Login/app_login", new Response.Listener<String>() {

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
                                editor.putString("user_name", edit_login_emailid.getText().toString());
                                editor.putString("user_email", edit_login_password.getText().toString());
                                editor.putString("user_id", jsonObject.optString("user_id", "id not found"));
                                editor.commit();
                                startActivity(new Intent(LoginActivity.this, FrontActivity.class));
                                finish();
                            }else if ("key not found".equalsIgnoreCase(data)) {
                                // Helper.showToast(LoginActivity.this, "code key not found");
                            }
                            Helper.showToast(LoginActivity.this, jsonObject.optString("message", "message key not found"));
                            edit_login_password.setText("");
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
                        Snackbar.make(login_layout_id, err + "\n" + error.toString(), Snackbar.LENGTH_LONG).setAction("Dismiss", new View.OnClickListener() {
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
                        param.put("emailId", edit_login_emailid.getText().toString());
                        param.put("password", edit_login_password.getText().toString());
                        /*param.put("client_id", "pooja@junctiontech.in");
                        param.put("password", "junction2016");
                        param.put("client_id", "test@abc.com");
                        param.put("token", "f5316a5e35a4");
                        param.put("accountno", "9999888877770001");
                        param.put("device", "androide");*/
                        Map<String, String> params = new HashMap<>();
                        params.put("sb4k_login_info", new JSONObject(param).toString());
                        Log.d("login_info", params.toString());
                        return params;
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
}
