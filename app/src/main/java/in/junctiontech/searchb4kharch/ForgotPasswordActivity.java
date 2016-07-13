package in.junctiontech.searchb4kharch;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.junctiontech.searchb4kharch.util.Helper;

public class ForgotPasswordActivity extends AppCompatActivity {

    private ScrollView forget_layout_id;
    private EditText edit_forget_emailid;
  //  private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        forget_layout_id = (ScrollView) this.findViewById(R.id.forget_layout_id);
        edit_forget_emailid = (EditText) this.findViewById(R.id.edit_forget_emailid);
     //   sp = this.getSharedPreferences("login_info", this.MODE_PRIVATE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void forgotpasswordOnClick(View view) {
        submit();
    }

    private boolean isEmptyEmail() {
        return edit_forget_emailid.getText() == null || edit_forget_emailid.getText().toString() == null
                || edit_forget_emailid.getText().toString().equals("") || edit_forget_emailid.getText().toString().isEmpty();
    }


    private void submit() {
        Helper.hideKeyboard(this);

        boolean b1 = isEmptyEmail();


        edit_forget_emailid.setError(null);


        if (b1) {
            Snackbar.make(forget_layout_id, "Field Cannot Be Blank", Snackbar.LENGTH_LONG).setAction("Dismiss", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //sp = getSharedPreferences("Login", MODE_PRIVATE);
                    /*String get_user = sp.getString("user_name", "Couldn't loaded Username...");
                    String get_pass = sp.getString("user_pass", "Couldn't loaded Password...");

                    Toast.makeText(LoginScreen.this, get_user + "\n" + get_pass, Toast.LENGTH_LONG).show();*/
                }
            }).show();

//        } else {
//            if (b2 || b1) {
//
//                if (b1) {
//                    edit_login_emailid.setError("Email Id cannot be blank");
//                }
//                if (b2) {
//                    edit_login_password.setError("Password cannot be blank");
//                }
//            }
        } else {
            final ProgressDialog pDialog = new ProgressDialog(this);
            pDialog.setMessage("Connecting...");
            pDialog.setCancelable(false);
            pDialog.show();

            //   startActivity(new Intent(LoginScreen.this, MainScreen.class));
            //  finish();

            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest strReq = new StringRequest(Request.Method.POST,
                    "http://www.searchb4kharch.com/Login/app_forgetpassword", new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.d("TAG", "Login Response: " + response.toString());
                    // Helper.showToast(LoginActivity.this, response);


                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        String data = jsonObject.optString("code", "key not found");

                        if ("200".equalsIgnoreCase(data)) {
                            // Helper.showToast(LoginActivity.this, jsonObject.optString("message", "message key not found"));
                        //    SharedPreferences.Editor editor = sp.edit();
                            //editor.putString("user_id", jsonObject.getString("user_id"));
                       //     editor.putString("user_name", edit_login_emailid.getText().toString());
                       //     editor.putString("user_email", edit_login_password.getText().toString());
                       //     editor.commit();

                    //        startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                            finish();
                        } else if ("key not found".equalsIgnoreCase(data)) {
                            // Helper.showToast(LoginActivity.this, "code key not found");
                        }
                        Helper.showToast(ForgotPasswordActivity.this, jsonObject.optString("message", "message key not found"));

                        edit_forget_emailid.setText("");
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
                    Snackbar.make(forget_layout_id, err + "\n" + error.toString(), Snackbar.LENGTH_LONG).setAction("Dismiss", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();

                   /* SharedPreferences.Editor editor = sp.edit();
                    editor.remove("user_email");
                    editor.remove("user_name");
                    editor.commit();*/

                    pDialog.dismiss();

                }

            }

            )

            {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> param = new HashMap<>();
                    param.put("emailId", edit_forget_emailid.getText().toString());
                        /*param.put("client_id", "pooja@junctiontech.in");
                        param.put("password", "junction2016");
                        param.put("client_id", "test@abc.com");
                        param.put("token", "f5316a5e35a4");
                        param.put("accountno", "9999888877770001");
                        param.put("device", "androide");*/
                    Map<String, String> params = new HashMap<>();
                    params.put("sb4k_forgot_info", new JSONObject(param).toString());
                    Log.d("sb4k_forgot_info", params.toString());
                    return params;
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
    }

}

