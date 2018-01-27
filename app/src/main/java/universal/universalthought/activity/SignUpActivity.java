package universal.universalthought.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import universal.universalthought.R;
import universal.universalthought.fundraiser.BasicInformation;
import universal.universalthought.model.ProductEnglish;
import universal.universalthought.model.ResponseDataModel;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Sandhiya on 12/11/2017.
 */

public class SignUpActivity extends Fragment {

    public static final String mypreference = "mypref";
    SharedPreferences sharedpreferences;
    public  static String USERNAME="username";
    public  static String USERIMAGE="userimage";
    public  static String USERID="userid";

    Button signin_button,gmail,facebook;
    EditText username,mailid,password,mobileno;
    RequestQueue queue;
    String URL_SIGNUP="http://www.simples.in/universalthought/universalthought.php";
    private List<ResponseDataModel> datalist;

    @Nullable
    public static SignUpActivity newInstance() {
        SignUpActivity fragment = new SignUpActivity();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_signup, container, false);
        sharedpreferences = getActivity(). getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        queue = Volley.newRequestQueue(getActivity());
        datalist = new ArrayList<>();
        signin_button=(Button)view.findViewById(R.id.button_signin);
        gmail = (Button) view.findViewById(R.id.btngmail);
        facebook = (Button) view.findViewById(R.id.btnfb);

        username = (EditText) view.findViewById(R.id.edt_username);
        mailid = (EditText)view.findViewById(R.id.edt_email);
        password = (EditText)view.findViewById(R.id.edt_password);
        mobileno = (EditText)view.findViewById(R.id.edt_mobileno);

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebook=new Intent(getActivity(),FaceBooklogin.class);
                startActivity(facebook);
            }
        });
        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent google=new Intent(getActivity(),GoogleSignin.class);
                startActivity(google);
            }
        });

        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registration();
                validation();
                /*FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                OtpDialogFragment inputNameDialog = new OtpDialogFragment();
                inputNameDialog.setCancelable(false);
                inputNameDialog.setDialogTitle("Enter Name");
                inputNameDialog.show(fragmentManager, "Input Dialog");*/
                        /*Intent i = new Intent(getActivity(),BasicInformation.class);
                        startActivity(i);*/
            }
        });

        return view;
    }

    private void Registration(){
        StringRequest request=new StringRequest(Request.Method.POST, URL_SIGNUP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject jsonObj = null;
                try {
                    jsonObj = new JSONObject(response);
                    JSONArray contacts = jsonObj.getJSONArray("result");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString("id");
                        String mail = c.getString("mail_id");
                        String mobileno = c.getString("mobile");

                        ResponseDataModel a = new ResponseDataModel(mail,mobileno,id);
                        a.setId(id);
                        a.setMail(mail);
                        a.setMobileno(mobileno);
                        Log.e("Response",id);

                        Bundle bundle = new Bundle();
                        bundle.putString("id",id);
// set Fragmentclass Arguments
                        Fragment fragobj = new Fragment();
                        fragobj.setArguments(bundle);
                        Log.e("Response",a.getId());

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Getting JSON Array node


                    String[] array = response.split(",");
                SharedPreferences.Editor editor=sharedpreferences.edit();
                editor.putString(USERNAME,"name");
                editor.commit();
                Log.e("Response",response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String, String>();
                String mail = mailid.getText().toString();
                String pswd = password.getText().toString();
                String mobile = mobileno.getText().toString();

                params.put("Key","UniversalThought");
                params.put("rType","UserSignUp");
                params.put("MailId",mail);
                params.put("Password",pswd);
                params.put("Mobile",mobile);


return params;
            }

        };
        queue.add(request);
    }



    public void validation() {
        Log.d("SignupEnglish", "SignupEnglish");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        signin_button.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();



        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        signin_button.setEnabled(true);
        getActivity().setResult(RESULT_OK, null);
        getActivity().finish();
    }

    public void onSignupFailed() {
        // Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        signin_button.setEnabled(true);
    }
    public boolean validate() {
        boolean valid = true;

        String name = username.getText().toString();

        String mobile = mobileno.getText().toString();
        String emailup = mailid.getText().toString();
        String Passwordin = password.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            username.setError("at least 3 characters");
            valid = false;
        } else {
            username.setError(null);
        }

        if (mobile.isEmpty() || !Patterns.PHONE.matcher(mobile).matches()) {
            mobileno.setError("Enter Valid Login Code");
            valid = false;
        } else {
            mobileno.setError(null);
        }


        if (emailup.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailup).matches()) {
            mailid.setError("enter a valid email address");
            valid = false;
        } else {
            mailid.setError(null);
        }



        if (Passwordin.isEmpty() || password.length() < 4 || Passwordin.length() > 10) {
            password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }
}
