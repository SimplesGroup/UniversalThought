package universal.universalthought.fundraiser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import universal.universalthought.R;
import universal.universalthought.activity.HomeFragment;
import universal.universalthought.activity.MainActivity;

/**
 * Created by Sandhiya on 12/11/2017.
 */

public class BasicInformation extends AppCompatActivity {
    EditText fullname,amount,title,beneficaryname;
    RadioButton fundyes,fundno;
    Button one,two,three;

    Button save;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fundraiser_basicinfo);

        fullname = (EditText) findViewById(R.id.edt_name);
        amount = (EditText) findViewById(R.id.edt_amnt);
        title = (EditText) findViewById(R.id.edt_title);
        beneficaryname = (EditText) findViewById(R.id.edt_benificary);
        save = (Button)findViewById(R.id.button_save);
        fundyes = (RadioButton)findViewById(R.id.radiobtnexclnt);
        fundno = (RadioButton)findViewById(R.id.radiobtngood);
        one = (Button)findViewById(R.id.one_button);
        two = (Button)findViewById(R.id.two_button);
        three = (Button)findViewById(R.id.three_button);
         save.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         validation();
               String name = fullname.getText().toString();
                                         String tit = title.getText().toString();
                                         String amnt = amount.getText().toString();
                                         String benif = beneficaryname.getText().toString();
                                         if (!name.equals("")&&!tit.equals("")&&!amnt.equals("")&&!benif.equals(""))
                                         {

                                     Intent i = new Intent(BasicInformation.this, FundraiserDetails.class);
                 if(fundyes.isChecked())

                                     {
                                         i.putExtra("activity", "OrganizationDetails");
                                     }
                 else

                                     {
                                         i.putExtra("activity", "OtherDetails");
                                     }


                 startActivity(i);
                                         }
                                         else
                                         {
                                             Toast.makeText(BasicInformation.this, "Enter all fields ", Toast.LENGTH_SHORT).show();
                                         }
             }
         });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BasicInformation.this,FundraiserDetails.class);
                if(fundyes.isChecked()) {
                    i.putExtra("activity", "OrganizationDetails");
                }
                else
                {
                    i.putExtra("activity", "OtherDetails");
                }

                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i =  new Intent(BasicInformation.this,MainActivity.class);
        startActivity(i);
        finish();
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

        String name = fullname.getText().toString();
        String tit = title.getText().toString();

        String amnt = amount.getText().toString();
        String benif = beneficaryname.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            fullname.setError("Name can't be blank.");
            valid = false;
        } else {
            fullname.setError(null);
        }

        if (amnt.isEmpty() || amnt.length() < 3) {
            amount.setError("Target amount must be greater than Rs.10000.");
            valid = false;
        } else {
            amount.setError(null);
        }

        if (tit.isEmpty() || tit.length() < 3) {
            title.setError("Title can't be blank.");
            valid = false;
        } else {
            title.setError(null);
        }

        if (benif.isEmpty() || benif.length() < 3) {
            beneficaryname.setError("Beneficiary name can't be blank.");
            valid = false;
        } else {
            beneficaryname.setError(null);
        }




        return valid;
    }
}
