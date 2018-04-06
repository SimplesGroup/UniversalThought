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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.internal.http.multipart.MultipartEntity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
/*import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;*/
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import universal.universalthought.R;

/**
 * Created by Sandhiya on 1/25/2018.
 */

public class OrganizationDetails extends AppCompatActivity {

    EditText name,email,cntname,number,website,pan,certficate,organisationlogo,regsec,regno,regaddress,cntmail,cntphone,fcraregno,regnoentity,ein,regadrus,authrep,uploadfund;
    Button save,previous,one,two,four;
    String URL_FORMONE="http://www.simples.in/universalthought/universalthought.php";
    String userid,category_data;
    RadioGroup radioGroup,rg80,raisefunds,tax,foreginfunds;
    Spinner organisationtype;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fundraiser_organization);

        name = (EditText)findViewById(R.id.edt_orgname);
        email = (EditText)findViewById(R.id.edt_email);
        cntname = (EditText)findViewById(R.id.edt_cntname);
        number = (EditText)findViewById(R.id.edt_cntno);
        website = (EditText)findViewById(R.id.edt_website);
        pan = (EditText)findViewById(R.id.edt_pan);
        certficate = (EditText)findViewById(R.id.certficate_name);
        organisationlogo = (EditText)findViewById(R.id.organization_logo);
        regsec = (EditText)findViewById(R.id.edt_regsec);
        regno = (EditText)findViewById(R.id.edt_regno);
        regaddress = (EditText)findViewById(R.id.edt_regaddress);
        cntmail = (EditText)findViewById(R.id.edt_cntctemail);
        cntphone = (EditText)findViewById(R.id.edt_cntctno);
        fcraregno = (EditText)findViewById(R.id.edt_fcraregno);
        uploadfund = (EditText)findViewById(R.id.edt_uploadfund);
        regnoentity = (EditText)findViewById(R.id.edt_501C);
        ein = (EditText)findViewById(R.id.edt_ein);
        regadrus = (EditText)findViewById(R.id.edt_us);
        authrep = (EditText)findViewById(R.id.edt_auth);
        save = (Button)findViewById(R.id.button_save);
        previous = (Button)findViewById(R.id.button_previous);
        one = (Button)findViewById(R.id.one_button);
        two = (Button)findViewById(R.id.two_button);
        four = (Button)findViewById(R.id.four_button);
        organisationtype = (Spinner)findViewById(R.id.orgtype);
        radioGroup=(RadioGroup)findViewById(R.id.rgOpinion) ;
        rg80=(RadioGroup)findViewById(R.id.rg80g) ;
        raisefunds=(RadioGroup)findViewById(R.id.frgn1) ;
        tax=(RadioGroup)findViewById(R.id.taxreceptis) ;
        foreginfunds=(RadioGroup)findViewById(R.id.fcra1) ;

        save.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Submit();
                //Post();
                String fullname = name.getText().toString();
                String noemail = email.getText().toString();
                String noname = cntname.getText().toString();
                String nonumber = number.getText().toString();
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
                if (!fullname.equals("") && !web.equals("") && !panno.equals("") && !regsection.equals("") && !regnumber.equals("") && !regnadd.equals("") &&
                        !contactmail.equals("") &&
                        !contactno.equals("") &&
                        !fcraregnumber.equals("") &&
                        !regnumberentity.equals("") &&
                        !einno.equals("") &&
                        !us.equals("")&&!author.equals("") ) {
                    validation();
                    Intent i = new Intent(OrganizationDetails.this, OtherDetails.class);
                    startActivity(i);
                }

            else{
                    Toast.makeText(OrganizationDetails.this, "Enter all fields ", Toast.LENGTH_SHORT).show();
                    }
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
            name.setError("Organization name can't be blank.");
            valid = false;
        } else {
            name.setError(null);
        }

        if (panno.isEmpty() || panno.length() < 3) {
            pan.setError("Please enter a value.");
            valid = false;
        } else{
            pan.setError(null);
        }

        if (web.isEmpty() || web.length() < 3) {
            website.setError("Please enter a valid URL");
            valid = false;
        } else {
            website.setError(null);
        }

        if (regsection.isEmpty() || regsection.length() < 3) {
            regsec.setError("Please enter a value.");
            valid = false;
        } else {
            regsec.setError(null);
        }

        if (regnumber.isEmpty() || regnumber.length() < 3) {
            regno.setError("Please enter a value.");
            valid = false;
        } else {
            regno.setError(null);
        }

        if (regnadd.isEmpty() || regnadd.length() < 3) {
            regaddress.setError("Please enter a value.");
            valid = false;
        } else {
            regaddress.setError(null);
        }

        if (contactmail.isEmpty() || contactmail.length() < 3) {
            cntmail.setError("Email can't be blank.");
            valid = false;
        } else {
            cntmail.setError(null);
        }

        if (contactno.isEmpty() || contactno.length() < 3) {
            cntphone.setError("Phone can't be blank.");
            valid = false;
        } else {
            cntphone.setError(null);
        }

        if (fcraregnumber.isEmpty() || fcraregnumber.length() < 3) {
            fcraregno.setError("Please enter a value.");
            valid = false;
        } else {
            fcraregno.setError(null);
        }

        if (regnumberentity.isEmpty() || regnumberentity.length() < 3) {
            regnoentity.setError("Please enter a value.");
            valid = false;
        } else {
            regnoentity.setError(null);
        }

        if (einno.isEmpty() || einno.length() < 3) {
            ein.setError("Please enter a value.");
            valid = false;
        } else {
            ein.setError(null);
        }

        if (us.isEmpty() || us.length() < 3) {
            regadrus.setError("Please enter a value.");
            valid = false;
        } else {
            regadrus.setError(null);
        }

        if (author.isEmpty() || author.length() < 3) {
            authrep.setError("Please enter a value.");
            valid = false;
        } else {
            authrep.setError(null);
        }

        return valid;
    }
    private void Submit(){
        StringRequest request=new StringRequest(Request.Method.POST, URL_FORMONE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Checking","HI"+response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                userid="3";
                String organisationname=name.getText().toString();
                String cntemail=email.getText().toString();
                String cntpersonname=cntname.getText().toString();
                String cntnumber=number.getText().toString();
                int selected=radioGroup.getCheckedRadioButtonId();
                RadioButton new_radiobutton=(RadioButton)findViewById(selected);
                String fundraisercontactperson =new_radiobutton.getText().toString();
              //  String benificeryname=beneficaryname.getText().toString();
                String web=website.getText().toString();
                String panno=pan.getText().toString();
                int select=rg80.getCheckedRadioButtonId();
                RadioButton rg=(RadioButton)findViewById(select);
                String rg80g =rg.getText().toString();
                int funds=raisefunds.getCheckedRadioButtonId();
                RadioButton rf=(RadioButton)findViewById(funds);
                String raisefund =rg.getText().toString();
                String certficatename=certficate.getText().toString();
                String orglogo=organisationlogo.getText().toString();
                String regsection=regsec.getText().toString();
                String regnumber=regno.getText().toString();
                String registrationaddress=regaddress.getText().toString();
                String cntctmail=cntmail.getText().toString();
                String orgtype = organisationtype.getSelectedItem().toString();
                int taxrec=tax.getCheckedRadioButtonId();
                RadioButton taxres=(RadioButton)findViewById(taxrec);
                String taxrecept =taxres.getText().toString();
                String phone =cntphone.getText().toString();
                int frgn=foreginfunds.getCheckedRadioButtonId();
                RadioButton frfunds=(RadioButton)findViewById(frgn);
                String foreignfund =frfunds.getText().toString();
                String reg =regnoentity.getText().toString();
                String empno =ein.getText().toString();
                String us =regadrus.getText().toString();
                String auth =authrep.getText().toString();
                String fcregno =fcraregno.getText().toString();
                String upfund =uploadfund.getText().toString();

                Map<String,String>params=new HashMap<>();
                params.put("Key","UniversalThought");
                params.put("rType","page3");
                params.put("user_id","1");
                params.put("name_of_organization", organisationname);
                params.put("fundraiser_contact_person_of_organization", fundraisercontactperson);

                Log.e("Checking",fundraisercontactperson);

                if(fundraisercontactperson.equals("No")) {

                params.put("contact_email", cntemail);
                params.put("name_of_contact_person", cntpersonname);
                params.put("contact_number", cntnumber);
                }else {

                    params.put("website", web);
                    params.put("pan_number_of_organization", panno);
                 params.put("tax_exemption_to_donors ", rg80g);
                 params.put("raise_foreign_funds  ", raisefund);
                    Log.e("Checking",rg80g);
                if(rg80g.equals("Yes")){
                    Log.e("Checking","1");
                    params.put("tax_certificate",certficatename);
                    params.put("organization_logo",orglogo);
                    params.put("organization_type",orgtype);
                    params.put("registration_section",regsection);
                    params.put("registration_number",regnumber);
                    params.put("registration_address",registrationaddress);
                    params.put("contact_email_for_finance_department",cntctmail);
                    params.put("contact_phone_for_finance_department",phone);
                    params.put("issue_tax_receipts_to_donors",taxrecept);

    }
    else{
                    Log.e("Checking","2");
    }
                    Log.e("Checking",raisefund);
                    if(raisefund.equals("Yes")){
                        Log.e("Checking","3");
                        params.put("foreign_funds_raised_to_transferred",foreignfund);
                        if(foreignfund.equals(0)){
                            params.put("fcra_registration_number",fcregno);
                            params.put("fcra_certificate",upfund);
                        }else if(foreignfund.equals(1)){
                            params.put("registered_name_of_c3_entity",reg);
                            params.put("employer_identification_number",empno);
                            params.put("registered_address_united_states",us);
                        }else{

                        }
                    }
                    else{
                        Log.e("Checking","4");
                    }

}
                return params;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

   /* private void Post(){
        try {


            String url="http://www.simples.in/universalthought/universalthought.php";

            String organisationname=name.getText().toString();
            String cntemail=email.getText().toString();
            String cntpersonname=cntname.getText().toString();
            String cntnumber=number.getText().toString();
            int selected=radioGroup.getCheckedRadioButtonId();
            RadioButton new_radiobutton=(RadioButton)findViewById(selected);
            String fundraisercontactperson =new_radiobutton.getText().toString();
            //  String benificeryname=beneficaryname.getText().toString();
            String web=website.getText().toString();
            String panno=pan.getText().toString();
            int select=rg80.getCheckedRadioButtonId();
            RadioButton rg=(RadioButton)findViewById(select);
            String rg80g =rg.getText().toString();
            int funds=raisefunds.getCheckedRadioButtonId();
            RadioButton rf=(RadioButton)findViewById(funds);
            String raisefund =rg.getText().toString();
            String certficatename=certficate.getText().toString();
            String orglogo=organisationlogo.getText().toString();
            String regsection=regsec.getText().toString();
            String regnumber=regno.getText().toString();
            String registrationaddress=regaddress.getText().toString();
            String cntctmail=cntmail.getText().toString();
            String orgtype = organisationtype.getSelectedItem().toString();
            int taxrec=tax.getCheckedRadioButtonId();
            RadioButton taxres=(RadioButton)findViewById(taxrec);
            String taxrecept =taxres.getText().toString();
            String phone =cntphone.getText().toString();
            int frgn=foreginfunds.getCheckedRadioButtonId();
            RadioButton frfunds=(RadioButton)findViewById(frgn);
            String foreignfund =frfunds.getText().toString();
            String reg =regnoentity.getText().toString();
            String empno =ein.getText().toString();
            String us =regadrus.getText().toString();
            String auth =authrep.getText().toString();
            String fcregno =fcraregno.getText().toString();
            String upfund =uploadfund.getText().toString();



            HttpClient client=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost(url);
            File file=new File("videpath");
            FileBody fileBody=new FileBody(file);
            org.apache.http.entity.mime.MultipartEntity entity=new org.apache.http.entity.mime.MultipartEntity();
            entity.addPart("stringone",new StringBody("sbgsbdsb"));
            entity.addPart("image",fileBody);


            entity.addPart("Key",new StringBody("UniversalThought"));
            entity.addPart("rType",new StringBody("page3"));
            entity.addPart("user_id",new StringBody("1"));
            entity.addPart("name_of_organization", new StringBody(organisationname));
            entity.addPart("fundraiser_contact_person_of_organization", new StringBody(fundraisercontactperson));

            Log.e("Checking",fundraisercontactperson);

            if(fundraisercontactperson.equals("No")) {

                entity.addPart("contact_email", new StringBody(cntemail));
                entity.addPart("name_of_contact_person", new StringBody(cntpersonname));
                entity.addPart("contact_number", new StringBody(cntnumber));
            }else {

                entity.addPart("website", new StringBody(web));
                entity.addPart("pan_number_of_organization", new StringBody(panno));
                entity.addPart("tax_exemption_to_donors ", new StringBody(rg80g));
                entity.addPart("raise_foreign_funds  ", new StringBody(raisefund));
                Log.e("Checking",rg80g);
                if(rg80g.equals("Yes")){
                    Log.e("Checking","1");
                    entity.addPart("tax_certificate",new StringBody(certficatename));
                    entity.addPart("organization_logo",new StringBody(orglogo));
                    entity.addPart("organization_type",new StringBody(orgtype));
                    entity.addPart("registration_section",new StringBody(regsection));
                    entity.addPart("registration_number",new StringBody(regnumber));
                    entity.addPart("registration_address",new StringBody(registrationaddress));
                    entity.addPart("contact_email_for_finance_department",new StringBody(cntctmail));
                    entity.addPart("contact_phone_for_finance_department",new StringBody(phone));
                    entity.addPart("issue_tax_receipts_to_donors",new StringBody(taxrecept));

                }
                else{
                    Log.e("Checking","2");
                }
                Log.e("Checking",raisefund);
                if(raisefund.equals("Yes")){
                    Log.e("Checking","3");
                    entity.addPart("foreign_funds_raised_to_transferred",new StringBody(foreignfund));
                    if(foreignfund.equals(0)){
                        entity.addPart("fcra_registration_number",new StringBody(fcregno));
                        entity.addPart("fcra_certificate",new StringBody(upfund));
                    }else if(foreignfund.equals(1)){
                        entity.addPart("registered_name_of_c3_entity",new StringBody(reg));
                        entity.addPart("employer_identification_number",new StringBody(empno));
                        entity.addPart("registered_address_united_states",new StringBody(us));
                    }else{

                    }
                }
                else{
                    Log.e("Checking","4");
                }

            }


            httpPost.setEntity(entity);
            HttpResponse response=client.execute(httpPost);
            HttpEntity entity1=response.getEntity();
            String Response= EntityUtils.toString(entity1);
            Log.d("Response:", Response);
        }catch (UnsupportedEncodingException e){

        }catch (IOException e){

        }
    }*/
}
