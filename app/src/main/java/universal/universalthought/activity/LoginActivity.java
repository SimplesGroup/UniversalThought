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

import java.util.HashMap;
import java.util.Map;

import universal.universalthought.R;
import universal.universalthought.fundraiser.BasicInformation;

import static android.app.Activity.RESULT_OK;


public class LoginActivity extends Fragment {

    public static final String mypreference = "mypref";
    SharedPreferences sharedpreferences;
    public  static String USERNAME="username";
    public  static String USERIMAGE="userimage";
    public  static String USERID="userid";
    RequestQueue queue;

    Button login ,fb,googlelogin;
    EditText mailid,password;
    String URL_LOGIN="http://www.simples.in/universalthought/universalthought.php";

    @Nullable
    public static LoginActivity newInstance() {
        LoginActivity fragment = new LoginActivity();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, container, false);
        sharedpreferences = getActivity(). getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        queue = Volley.newRequestQueue(getActivity());
        login = (Button) view.findViewById(R.id.button_login);
        fb=(Button) view.findViewById(R.id.btnfb) ;
        googlelogin=(Button)view.findViewById(R.id.btngmail) ;
        mailid = (EditText)view.findViewById(R.id.edit_email);
        password = (EditText)view.findViewById(R.id.edit_pswd);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registration();
                validation();
               // showInputNameDialog();
                /*Intent i = new Intent(getActivity(),BasicInformation.class);
                startActivity(i);*/
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebook=new Intent(getActivity(),FaceBooklogin.class);
                startActivity(facebook);
            }
        });
        googlelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent google=new Intent(getActivity(),GoogleSignin.class);
                startActivity(google);
            }
        });
        return view;
    }

    private void showInputNameDialog() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        MobileDialogFragment inputNameDialog = new MobileDialogFragment();
        inputNameDialog.setCancelable(false);
        inputNameDialog.setDialogTitle("Enter Name");
        inputNameDialog.show(fragmentManager, "Input Dialog");
        getActivity().setFinishOnTouchOutside(true);
    }


    private void Registration(){
        StringRequest request=new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

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

                params.put("Key","UniversalThought");
                params.put("rType","UserLogin");
                params.put("MailId",mail);
                params.put("Password",pswd);

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

        login.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),R.style.AppTheme_Dark_Dialog);
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
        login.setEnabled(true);
        getActivity().setResult(RESULT_OK, null);
        getActivity().finish();
    }

    public void onSignupFailed() {
        // Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        login.setEnabled(true);
    }
    public boolean validate() {
        boolean valid = true;


        String mail = mailid.getText().toString();
        String pswrd = password.getText().toString();



        if (mail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            mailid.setError("enter a valid email address");
            valid = false;
        } else {
            mailid.setError(null);
        }



        if (pswrd.isEmpty() || password.length() < 4 || pswrd.length() > 10) {
            password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            password.setError(null);
        }


        return valid;
    }
}
