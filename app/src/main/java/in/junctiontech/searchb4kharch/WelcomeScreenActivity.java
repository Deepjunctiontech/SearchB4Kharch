package in.junctiontech.searchb4kharch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


public class WelcomeScreenActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private CallbackManager callbackManager;
    //Signin button
    private SignInButton signInButton;

    //Signing Options
    private GoogleSignInOptions gso;

    //google api client
    private static GoogleApiClient mGoogleApiClient;

    //Signin constant to check the activity result
    private int RC_SIGN_IN = 100;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = this.getSharedPreferences("login_info", this.MODE_PRIVATE);
        check();
        FacebookSdk.sdkInitialize(getApplicationContext());

        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_welcome_screen);



        //Initializing google signin option
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

       /* // Initializing google plus api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).addApi(Plus.API, null)
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();*/

        //Initializing signinbutton
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setScopes(gso.getScopeArray());

        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        //Setting onclick listener to signing button
        signInButton.setOnClickListener(this);

        TextView imgLogo = (TextView) findViewById(R.id.imgLogo);

        String fontPath = "fonts/CircleD_Font_by_CrazyForMusic.ttf";
        fontPath = "ExternalFonts/chopinscript.ttf";
        // text view label

        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

        // Applying font
        imgLogo.setTypeface(tf);


        LoginButton loginButton = (LoginButton) findViewById(R.id.btn_welcomeScreen_loginWithFacebook);
        loginButton.setReadPermissions(Arrays.asList("public_profile","email", "user_birthday"));
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(WelcomeScreenActivity.this, "onSuccess", Toast.LENGTH_LONG).show();
                Toast.makeText(WelcomeScreenActivity.this, "" + "User ID: "
                        + loginResult.getAccessToken().getUserId()
                        + "\n" +
                        "Auth Token: "
                        + loginResult.getAccessToken().getToken(), Toast.LENGTH_LONG).show();

               /* AccessToken accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();
*/
                // Facebook Email address
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                Log.v("LoginActivity Response ", response.toString());

                                try {
                                    Toast.makeText(getApplicationContext(), "Name " + object.getString("name")
                                            + "\nEmail " + object.getString("email"), Toast.LENGTH_LONG).show();
                                    SharedPreferences.Editor editor = sp.edit();
                                    //editor.putString("user_id", jsonObject.getString("user_id"));
                                    editor.putString("user_name", object.getString("name"));
                                    editor.putString("user_email", object.getString("email"));
                                    editor.commit();
                                    startActivity(new Intent(WelcomeScreenActivity.this, HomeActivity.class));
                                    finish();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();


            }

            @Override
            public void onCancel() {
                Toast.makeText(WelcomeScreenActivity.this, "onCancel", Toast.LENGTH_LONG).show();

                SharedPreferences.Editor editor = sp.edit();
                editor.remove("user_email");
                editor.remove("user_name");
                editor.commit();
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(WelcomeScreenActivity.this, "onError", Toast.LENGTH_LONG).show();
                SharedPreferences.Editor editor = sp.edit();
                editor.remove("user_email");
                editor.remove("user_name");
                editor.commit();
            }
        });
        //    callbackManager.onActivityResult(requestCode, resultCode, data);


        /*callbackManager = CallbackManager.Factory.create();

        accessTokenTracker= new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {

            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {

            }
        };

        accessTokenTracker.startTracking();
        profileTracker.startTracking();
*/

    }

    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }


    private void check() {
        if (!sp.getString("user_name", "Not Found").equals("Not Found") &&
                !sp.getString("user_email", "Not Found").equals("Not Found")
                ) {
            startActivity(new Intent(WelcomeScreenActivity.this, FrontActivity.class));
            finish();
        }
    }

    public void onClickWelcome(View view) {
        Intent i = this.getIntent();
        switch (view.getId()) {

            default:
                i = i.setClass(this, WelcomeScreenActivity.class);
                break;

            case R.id.btn_welcomeScreen_loginWithFacebook:
              //  LoginManager.getInstance().logOut();
                // i = i.setClass(this, SignupActivity.class);
                return;


            case R.id.btn_welcomeScreen_signup:
                i = i.setClass(this, SignupActivity.class);
                break;

            case R.id.btn_welcomeScreen_login:
                i = i.setClass(this, LoginActivity.class);
                break;

            /*case R.id.btn_welcomeScreen_skipThisStep:
                i = i.setClass(this, FrontActivity.class);
                break;*/

        }

        startActivity(i);

    }

    //This function will option signing intent
    private void signIn() {
        //Creating an intent
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);

        //Starting intent for result
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //If signin
        if (requestCode == RC_SIGN_IN) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Calling a new function to handle signin
            handleSignInResult(result);
        } else
            callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    //After the signing we are calling this function
    private void handleSignInResult(GoogleSignInResult result) {
        //If the login succeed
        if (result.isSuccess()) {
            //Getting google account
            GoogleSignInAccount acct = result.getSignInAccount();
            Toast.makeText(this, acct.getDisplayName() + "\n" + acct.getEmail(), Toast.LENGTH_LONG).show();
            SharedPreferences.Editor editor = sp.edit();
            //editor.putString("user_id", jsonObject.getString("user_id"));
            editor.putString("user_name", acct.getDisplayName());
            editor.putString("user_email", acct.getEmail());
            editor.commit();
            startActivity(new Intent(WelcomeScreenActivity.this, HomeActivity.class));
            finish();
          /*  //Displaying name and email
            textViewName.setText(acct.getDisplayName());
            textViewEmail.setText(acct.getEmail());

            //Initializing image loader
            imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext())
                    .getImageLoader();

            imageLoader.get(acct.getPhotoUrl().toString(),
                    ImageLoader.getImageListener(profilePhoto,
                            R.mipmap.ic_launcher,
                            R.mipmap.ic_launcher));

            //Loading image
            profilePhoto.setImageUrl(acct.getPhotoUrl().toString(), imageLoader);*/

        } else {
            //If login fails
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
            SharedPreferences.Editor editor = sp.edit();
            editor.remove("user_email");
            editor.remove("user_name");
            editor.commit();
        }
    }
   /* boolean demo;*/

    @Override

    public void onClick(View v) {

        if (v == signInButton /*&& (demo=!demo)*/)
            signIn();
       /* else {
           // mGoogleApiClient.disconnect();
            logout();
        }*/
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (!connectionResult.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), this,
                    0).show();
            return;
        }

    }

    public static void logout() {
        LoginManager.getInstance().logOut();
       // Auth.GoogleSignInApi.signOut(mGoogleApiClient);
      //  mGoogleApiClient.disconnect();
       /* if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            mGoogleApiClient.disconnect();
            mGoogleApiClient.connect();
            updateUI(false);
        }*/
     //   mGoogleApiClient.connect();
       if (mGoogleApiClient.isConnected()) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            // ...
                            //Toast.makeText(WelcomeScreenActivity.this,status.toString()+"\nLogout",Toast.LENGTH_LONG).show();
                        }
                    });

        }
    }
}
