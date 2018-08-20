package universal.universalthought.fundraiser;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import universal.universalthought.activity.FaceBooklogin;
import universal.universalthought.activity.GoogleSignin;
import universal.universalthought.model.ResponseDataModel;

import static android.app.Activity.RESULT_OK;

/**
 * Created by user on 8/17/2018.
 */

public class SignupUserActivity extends Fragment {

    public static final String mypreference = "mypref";
    SharedPreferences sharedpreferences;
    public  static String USEREMAIL="useremail";
    public  static String USERPHONE="userphone";
    public  static String USERID="userid";
    public ViewPager viewPager;
    private TabLayout tabLayout;
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
        View view = inflater.inflate(R.layout.signup_user, container, false);
        sharedpreferences = getActivity(). getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
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
                // Toast.makeText(getActivity(),"clicked",Toast.LENGTH_LONG).show();
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
        Log.e("RES","START");
        StringRequest request=new StringRequest(Request.Method.POST, URL_SIGNUP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("RES","hi"+response);
                JSONObject jsonObj = null;
                String mailid ="",userid="",phonenum="";
                try {
                    jsonObj = new JSONObject(response);
                    JSONArray contacts = jsonObj.getJSONArray("result");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        userid = c.getString("id");
                        mailid = c.getString("mail_id");
                        phonenum = c.getString("mobile");

                        ResponseDataModel a = new ResponseDataModel(mailid,phonenum,userid);
                        a.setId(userid);
                        a.setMail(mailid);
                        a.setMobileno(phonenum);
                        Log.e("RES",userid);

                        Bundle bundle = new Bundle();
                        bundle.putString("id",userid);
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
                editor.putString(USEREMAIL,mailid);
                editor.putString(USERID,userid);
                editor.putString(USERPHONE,phonenum);
                editor.commit();
                Log.e("Response",response);
                Toast.makeText(getActivity(),"clicked"+userid,Toast.LENGTH_LONG).show();
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
                String name=username.getText().toString();

                params.put("Key","UniversalThought");
                params.put("rType","UserSignUp");
                params.put("Name",name);
                params.put("MailId",mail);
                params.put("Password",pswd);
                params.put("Mobile",mobile);


                return params;
            }

        };

        queue.add(request);
        Log.e("RES","SEND");
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
    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new LoginActivity(), "User");
        adapter.addFragment(new SignUpActivity(), "Organization");

        viewPager.setAdapter(adapter);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            //    pos = position;
            //  Log.e("POSITIONNNNN", String.valueOf(pos));
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);


        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
