package universal.universalthought.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

/**
 * Created by Sandhiya on 12/11/2017.
 */

public class SignUpActivity extends Fragment {

    public static final String mypreference = "mypref";
    SharedPreferences sharedpreferences;
    public  static String USERNAME="username";
    public  static String USERIMAGE="userimage";
    public  static String USERID="userid";

    Button signin_button;
    EditText name,mailid,password,mobileno;
    RequestQueue queue;
    String URL_SIGNUP="http://www.simples.in/universalthought/universalthought.php";

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
        signin_button=(Button)view.findViewById(R.id.button_signin);

        name = (EditText) view.findViewById(R.id.edt_username);
        mailid = (EditText)view.findViewById(R.id.edt_email);
        password = (EditText)view.findViewById(R.id.edt_password);
        mobileno = (EditText)view.findViewById(R.id.edt_mobileno);

         
        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registration();
            }
        });

        return view;
    }

    private void Registration(){
        StringRequest request=new StringRequest(Request.Method.POST, URL_SIGNUP, new Response.Listener<String>() {
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
}
