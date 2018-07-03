package universal.universalthought.activity;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Hashtable;
import java.util.Map;


import universal.universalthought.R;

public class GoogleSignin extends AppCompatActivity implements  GoogleApiClient.OnConnectionFailedListener {




    //Signing Options
    private GoogleSignInOptions gso;

    //google api client
    private GoogleApiClient mGoogleApiClient;

    //Signin constant to check the activity result
    private int RC_SIGN_IN = 100;
    SharedPreferences sharedpreferences;
    private String EMAILID_CHECK="email";

    public static final String GcmId = "gcmid";
    public static final String mypreference = "mypref";
    public static final String Activity = "activity";
    public static final String CONTENTID = "contentid";
    String UPLOAD_URL="http://www.simples.in/universalthought/universalthought.php";
    private String KEY_GCM = "gcm_id";
    private String KEY_NAME = "name";
    private String KEY_EMAIL= "email";
    private String KEY_GENDER = "gender";
    public static final String Language = "lamguage";
    private String KEY_PROFILEIMAGE = "image";
    String contentid;
String UPLOAD_CHECK_USER="http://www.simples.in/universalthought/universalthought.php";
String activity,gcmids;
    public static final String MYUSERID= "myprofileid";
    public static final String USERNAME= "myprofilename";
    public static final String USERIMAGE= "myprofileimage";
    public static final String USERMAILID= "myprofileemail";
    public static final String USEREMAIL= "myprofileemail";
ProgressDialog pdialog;
    String username,userimage,user_email,language_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.googlesinintw);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        gcmids=sharedpreferences.getString(GcmId,"");
        language_data=sharedpreferences.getString(Language,"");
        Log.e("GCMID:",gcmids);
        if (sharedpreferences.contains(Activity)) {

            activity=sharedpreferences.getString(Activity,"");
            gcmids=sharedpreferences.getString(GcmId,"");
            Log.e("GCMID:",activity);
            // Log.e("IMAGEURL:",profilepicturefromfacebook);

        }

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);


    }




    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }


    //After the signing we are calling this function
    private void handleSignInResult(GoogleSignInResult result) {
        //If the login succeed
        if (result.isSuccess()) {

       final GoogleSignInAccount acct = result.getSignInAccount();

            pdialog = new ProgressDialog(this);
            pdialog.show();
            pdialog.setContentView(R.layout.custom_progressdialog);
            pdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            /*final StringRequest signintwo=new StringRequest(Request.Method.POST, UPLOAD_CHECK_USER, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
Log.e("UserID",response.toString());
                if(response.toString().trim().equalsIgnoreCase("no")){
                    pdialog.dismiss();*/
                    StringRequest upload=new StringRequest(Request.Method.POST, UPLOAD_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String res) {
                            if(res.toString().trim().equalsIgnoreCase("no")){
                                Log.e("UserID","no cancel");
                            }else {
                                Log.e("UserIDnew", res);
                                if(res==""||res==null||res.equalsIgnoreCase("no")){
                                    Log.e("UserID","empty"+res);
                                }else {
                                    Log.e("UserIDnew", res);
                                    String[] array = res.split(",");

                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.putString(MYUSERID,array[0]);
                                    editor.putString(USERNAME,array[1]);
                                    editor.putString(USERIMAGE,array[2]);
                                    editor.putString(USERMAILID,array[3].toString().trim());
                                    Log.e("EMAIL,",array[3].toString());
                                    editor.commit();
                                    if (sharedpreferences.contains(USERNAME)) {
                                        username=sharedpreferences.getString(USERNAME,"");
                                    }
                                    if (sharedpreferences.contains(USERIMAGE)) {
                                        userimage=sharedpreferences.getString(USERIMAGE,"");
                                        }
                                    if (sharedpreferences.contains(USERMAILID)) {
                                        user_email=sharedpreferences.getString(USERMAILID,"");
                                        }
                                    Log.e("EMAIL,","data"+user_email+userimage+username);
                                    android.support.v4.app.FragmentTransaction ft =getSupportFragmentManager().beginTransaction();
                                    MobileDialogFragment dialog = new MobileDialogFragment();
                                    dialog.show(ft, "Tag");
                                }

                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        protected Map<String ,String> getParams()throws AuthFailureError{
                          Map<String,String> params=new Hashtable<String, String>();

                            params.put("MailId",acct.getEmail());
                            params.put("Mobile ",acct.getDisplayName());
                            params.put("Key","UniversalThought");
                            params.put("rType","UserLogin");
                            //params.put(KEY_PROFILEIMAGE,acct.getPhotoUrl().toString());
                         //   params.put(KEY_GENDER,"null");
                            params.put(KEY_GCM,gcmids);
                            return  params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(GoogleSignin.this);


                    //Adding request to the queue
                    requestQueue.add(upload);

                /*}else {
                    pdialog.dismiss();
                    if(response==""||response==null){
                        Log.e("UserID","emptycheck"+response);
                    }else {
                        Log.e("UserIDcheck","no cancel");
                        Log.e("UserIDcheck", response);
                        String[] array = response.split(",");

                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(MYUSERID,array[0]);
                        editor.putString(USERNAME,array[1]);
                        editor.putString(USERIMAGE,array[2]);
                        String emails=array[3].toString();
                        editor.putString(USERMAILID,array[3].toString().trim());
                        editor.commit();

                        android.support.v4.app.FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                        MobileDialogFragment mobile=new MobileDialogFragment();
                        mobile.show(transaction, "Tag");
                    }



                }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                protected Map<String,String> getParams() throws AuthFailureError{
                    Map<String ,String> params=new Hashtable<String, String>();
Log.e("Email",gcmids);
                    params.put(EMAILID_CHECK,acct.getEmail());
                    params.put(KEY_GCM,gcmids);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(GoogleSignin.this);


            //Adding request to the queue
            requestQueue.add(signintwo);

            //Displaying name and email

*/



        } else {
            //If login fails
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }


}
