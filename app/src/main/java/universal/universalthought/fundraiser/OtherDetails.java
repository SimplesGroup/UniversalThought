package universal.universalthought.fundraiser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

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
 * Created by Sandhiya on 12/12/2017.
 */

public class OtherDetails extends AppCompatActivity {

    Button previous,submit;
    EditText city,funvideolink;
    String URL_FORMONE="http://www.simples.in/universalthought/universalthought.php";
    String userid,category_data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fundraiser_otherdetails);

        previous = (Button)findViewById(R.id.button_previous);
        submit =(Button)findViewById(R.id.button_submit);
        city = (EditText)findViewById(R.id.edt_city);
        funvideolink = (EditText)findViewById(R.id.edt_video);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(OtherDetails.this,FundraiserDetails.class);
                startActivity(i);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(OtherDetails.this,FundraiserDetails.class);
        startActivity(i);
    }

    public void validation() {
        Log.d("SignupEnglish", "SignupEnglish");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        submit.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
//        progressDialog.show();



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
        submit.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        // Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        submit.setEnabled(true);
    }
    public boolean validate() {
        boolean valid = true;

        String cityname =city.getText().toString();
        String video = funvideolink.getText().toString();



        if (cityname.isEmpty() || cityname.length() < 3) {
            city.setError("Fundraiser city can't be blank.");
            valid = false;
        } else {
            city.setError(null);
        }

      if (video.isEmpty() || video.length() < 3) {
            funvideolink.setError("This field can't be empty.");
            valid = false;
        } else {
            funvideolink.setError(null);

        }

        return valid;
    }

    private void Submit(){
        StringRequest request=new StringRequest(Request.Method.POST, URL_FORMONE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                userid="3";
                /*String fundraisername=fullname.getText().toString();
                String fund_raising_amount=amount.getText().toString();
                String title_of_fundraising=title.getText().toString();
                int selected=radioGroup.getCheckedRadioButtonId();
                RadioButton new_radiobutton=(RadioButton)findViewById(selected);
                String who_fundraising=new_radiobutton.getText().toString();
                String benificeryname=beneficaryname.getText().toString();*/

                Map<String,String>params=new HashMap<>();
                params.put("Key","UniversalThought");
                params.put("rType","page3");
                params.put("user_id",userid);

                params.put("name_of_organization",userid);
                params.put("fundraiser_contact_person_of_organization",userid);
                params.put("contact_email",userid);
                params.put("name_of_contact_person",userid);
                params.put("contact_number",userid);

                params.put("website",userid);
                params.put("pan_number_of_organization",userid);
                params.put("pan_number_of_organization",userid);



                /*params.put("fund_raiser_name",fundraisername);
                params.put("raising_amount",fund_raising_amount);
                params.put("title_of_fundraising",title_of_fundraising);
                params.put("category",category_data);
                params.put("who_fundraising",who_fundraising);
                params.put("beneficiary_name",benificeryname);
                Log.e("RES",fund_raising_amount+fundraisername+title_of_fundraising+category_data+who_fundraising+benificeryname);
               */ return params;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

}
