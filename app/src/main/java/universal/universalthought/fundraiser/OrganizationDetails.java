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

import universal.universalthought.R;

/**
 * Created by Sandhiya on 1/25/2018.
 */

public class OrganizationDetails extends AppCompatActivity {

    EditText name,website,pan,regsec,regno,regaddress,cntmail,cntphone,fcraregno,regnoentity,ein,regadrus,authrep;
    Button save,previous,one,two,four;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fundraiser_organization);

        name = (EditText)findViewById(R.id.edt_orgname);
        website = (EditText)findViewById(R.id.edt_website);
        pan = (EditText)findViewById(R.id.edt_pan);
        regsec = (EditText)findViewById(R.id.edt_regsec);
        regno = (EditText)findViewById(R.id.edt_regno);
        regaddress = (EditText)findViewById(R.id.edt_regaddress);
        cntmail = (EditText)findViewById(R.id.edt_cntctemail);
        cntphone = (EditText)findViewById(R.id.edt_cntctno);
        fcraregno = (EditText)findViewById(R.id.edt_fcraregno);
        regnoentity = (EditText)findViewById(R.id.edt_501C);
        ein = (EditText)findViewById(R.id.edt_ein);
        regadrus = (EditText)findViewById(R.id.edt_us);
        authrep = (EditText)findViewById(R.id.edt_auth);
        save = (Button)findViewById(R.id.button_save);
        previous = (Button)findViewById(R.id.button_previous);
        one = (Button)findViewById(R.id.one_button);
        two = (Button)findViewById(R.id.two_button);
        four = (Button)findViewById(R.id.four_button);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
                Intent i = new Intent(OrganizationDetails.this, OtherDetails.class);
                startActivity(i);
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrganizationDetails.this, FundraiserDetails.class);
                startActivity(i);
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrganizationDetails.this, BasicInformation.class);
                startActivity(i);
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrganizationDetails.this, FundraiserDetails.class);
                startActivity(i);
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrganizationDetails.this, OtherDetails.class);
                startActivity(i);
            }
        });
    }
    public void validation() {
        Log.d("SignupEnglish", "SignupEnglish");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        save.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext(),
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
        save.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        // Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        save.setEnabled(true);
    }
    public boolean validate() {
        boolean valid = true;

        String fullname = name.getText().toString();
        String web = website.getText().toString();
        String panno = pan.getText().toString();
        String regsection = regsec.getText().toString();
        String regnumber = regno.getText().toString();
        String regnadd = regaddress.getText().toString();
        String contactmail = cntmail.getText().toString();
        String contactno = cntphone.getText().toString();
        String fcraregnumber = fcraregno.getText().toString();
        String regnumberentity = regnoentity.getText().toString();
        String einno = ein.getText().toString();
        String us = regadrus.getText().toString();
        String author = authrep.getText().toString();

        if (fullname.isEmpty() || fullname.length() < 3) {
            name.setError("Name can't be blank.");
            valid = false;
        } else {
            name.setError(null);
        }

        if (panno.isEmpty() || panno.length() < 3) {
            pan.setError("Target amount must be greater than Rs.10000.");
            valid = false;
        } else {
            pan.setError(null);
        }

        if (web.isEmpty() || web.length() < 3) {
            website.setError("Title can't be blank.");
            valid = false;
        } else {
            website.setError(null);
        }

        if (regsection.isEmpty() || regsection.length() < 3) {
            regsec.setError("Beneficiary name can't be blank.");
            valid = false;
        } else {
            regsec.setError(null);
        }
        return valid;
    }
}
