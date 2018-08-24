package universal.universalthought.fundraiser;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.File;
import java.io.UnsupportedEncodingException;

import universal.universalthought.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by user on 8/20/2018.
 */

public class SignUpOrganization extends Fragment {

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    EditText name,email,cntname,number,website,pan,certficate,organisationlogo,regsec,regno,regaddress,cntmail,cntphone,fcraregno,regnoentity,ein,regadrus,authrep,uploadfund;
    Button save,save1,previous,previous1,one,two,four,buttonuploadfund;
    String URL_FORMONE="http://www.simples.in/universalthought/universalthought.php";
    String userid,category_data;
    RadioGroup radioGroup,rg80,raisefunds,tax,foreginfunds;
    Spinner organisationtype;
    private int SELECT_PICTURE = 1;
    private Uri picUri;
    Button choose,logo;
    String filePath;
    String filePathpdf;
    MultipartEntity entity;
    File finalFile;
    TextView txtcntctperson,txtweb,txtwebsite,txtstatus,txtpan,txtfundname,txt80g,txtfrngdont,txtfrng,txtrdo,txtemail,txtdescemail,txtcntname,txtcntno;
    TextView txt80gupload,txtsprt,txtorglogo,txtrecmd,txtorgtype,txtregsec,txtregno,txtregadrress,txtcntctemail,txtallqueries,txtcntctno;
    TextView txtissues,txttaxrecepit,txtinr,txtfcra,txtfcraregno,txtuploadfund,txt501C,txtein,txtus,txtauth,txtfcraupload;
    View viewstatus,viewfrg,viewissues;
    public View view;

    @Nullable
    public static SignUpOrganization newInstance() {
        SignUpOrganization fragment = new SignUpOrganization();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fundraiser_organization, container, false);
        sharedpreferences = getActivity().getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        name = (EditText)view.findViewById(R.id.edt_orgname);
        txtcntctperson = (TextView)view.findViewById(R.id.txt_cntctperson);
        txtwebsite = (TextView)view.findViewById(R.id.txt_email);
        txtweb = (TextView)view.findViewById(R.id.txt_website);
        txtstatus = (TextView)view.findViewById(R.id.txt_status);
        txtpan = (TextView)view.findViewById(R.id.txt_pan);
        txtfundname = (TextView)view.findViewById(R.id.txt_fundname);
        txt80g = (TextView)view.findViewById(R.id.txt_80g);
        txtfrngdont = (TextView)view.findViewById(R.id.txt_frngdont);
        txtfrng = (TextView)view.findViewById(R.id.txt_frng);
        txtrdo = (TextView)view.findViewById(R.id.txt_rdiofme);
        txtemail = (TextView)view.findViewById(R.id.txt_email);
        txtdescemail = (TextView)view.findViewById(R.id.txt_descemail);
        txtcntname = (TextView)view.findViewById(R.id.txt_cntname);
        txtcntno = (TextView)view.findViewById(R.id.txt_cntno);
        txt80gupload = (TextView)view.findViewById(R.id.txt_80gupload);
        txtsprt = (TextView)view.findViewById(R.id.txt_sprtfrmt);
        txtorglogo = (TextView)view.findViewById(R.id.txt_orglogo);
        txtrecmd = (TextView)view.findViewById(R.id.txt_recmd);
        txtorgtype = (TextView)view.findViewById(R.id.txt_orgtype);
        txtregsec = (TextView)view.findViewById(R.id.txt_regsec);
        txtregno = (TextView)view.findViewById(R.id.txt_regno);
        txtregadrress = (TextView)view.findViewById(R.id.txt_regadrress);
        txtcntctemail = (TextView)view.findViewById(R.id.txt_cntctemail);
        txtallqueries = (TextView)view.findViewById(R.id.txt_allqueries);
        txtcntctno = (TextView)view.findViewById(R.id.txt_cntctno);
        txtissues = (TextView)view.findViewById(R.id.txt_issues);
        txtinr = (TextView)view.findViewById(R.id.txt_inr);
        txttaxrecepit = (TextView)view.findViewById(R.id.txt_taxrecepit);
        txtfcra = (TextView)view.findViewById(R.id.txt_fcra);
        txtfcraregno = (TextView)view.findViewById(R.id.txt_fcraregno);
        txtuploadfund = (TextView)view.findViewById(R.id.txt_uploadfund);
        txt501C = (TextView)view.findViewById(R.id.txt_501C);
        txtein = (TextView)view.findViewById(R.id.txt_ein);
        txtus = (TextView)view.findViewById(R.id.txt_us);
        txtauth = (TextView)view.findViewById(R.id.txt_auth);
        txtfcraupload = (TextView)view.findViewById(R.id.txt_fcraupload);



        email = (EditText)view.findViewById(R.id.edt_email);
        cntname = (EditText)view.findViewById(R.id.edt_cntname);
        number = (EditText)view.findViewById(R.id.edt_cntno);
        website = (EditText)view.findViewById(R.id.edt_website);
        pan = (EditText)view.findViewById(R.id.edt_pan);
        certficate = (EditText)view.findViewById(R.id.certficate_name);
        organisationlogo = (EditText)view.findViewById(R.id.organization_logo);
        regsec = (EditText)view.findViewById(R.id.edt_regsec);


        regno = (EditText)view.findViewById(R.id.edt_regno);
        regaddress = (EditText)view.findViewById(R.id.edt_regaddress);
        cntmail = (EditText)view.findViewById(R.id.edt_cntctemail);
        cntphone = (EditText)view.findViewById(R.id.edt_cntctno);
        fcraregno = (EditText)view.findViewById(R.id.edt_fcraregno);
        uploadfund = (EditText)view.findViewById(R.id.edt_uploadfund);
        regnoentity = (EditText)view.findViewById(R.id.edt_501C);
        ein = (EditText)view.findViewById(R.id.edt_ein);
        regadrus = (EditText)view.findViewById(R.id.edt_us);
        authrep = (EditText)view.findViewById(R.id.edt_auth);
        save = (Button)view.findViewById(R.id.button_save);
        save1 = (Button)view.findViewById(R.id.button_save1);
        previous = (Button)view.findViewById(R.id.button_previous);
        previous1 = (Button)view.findViewById(R.id.button_previous1);

        choose = (Button)view.findViewById(R.id.button_choosefile);
        logo = (Button)view.findViewById(R.id.button_choosefileone);
        buttonuploadfund = (Button)view.findViewById(R.id.button_uploadfund);
        organisationtype = (Spinner)view.findViewById(R.id.orgtype);
        radioGroup=(RadioGroup)view.findViewById(R.id.rgOpinion) ;
        rg80=(RadioGroup)view.findViewById(R.id.rg80g) ;
        raisefunds=(RadioGroup)view.findViewById(R.id.frgn1) ;
        tax=(RadioGroup)view.findViewById(R.id.taxreceptis) ;
        foreginfunds=(RadioGroup)view.findViewById(R.id.fcra1) ;
        viewstatus = (View)view.findViewById(R.id.view_status);
        viewfrg = (View)view.findViewById(R.id.view_frng);
        viewissues = (View)view.findViewById(R.id.view_issues);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoBrowse();
            }
        });
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoBrowse();
            }
        });


        save1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                save.setVisibility(View.VISIBLE);
                previous.setVisibility(View.VISIBLE);
                previous1.setVisibility(View.GONE);
                txtcntctperson.setVisibility(View.VISIBLE);
                radioGroup.setVisibility(View.VISIBLE);
                save1.setVisibility(View.GONE);

                /*if(name.getText().equals("")){
                    save.setVisibility(View.VISIBLE);
                    previous.setVisibility(View.VISIBLE);
                    previous1.setVisibility(View.GONE);
                    txtcntctperson.setVisibility(View.VISIBLE);
                    radioGroup.setVisibility(View.VISIBLE);
                    save1.setVisibility(View.GONE);
                }
                else{
                    validation();
                }*/
              /* int selected=radioGroup.getCheckedRadioButtonId();
               RadioButton new_radiobutton=(RadioButton)findViewById(selected);
               final String fundraisercontactperson =new_radiobutton.getText().toString();
*/
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {

                        int selected=radioGroup.getCheckedRadioButtonId();
                        RadioButton new_radiobutton=(RadioButton)view.findViewById(selected);
                        final String fundraisercontactperson =new_radiobutton.getText().toString();

                        if(fundraisercontactperson.equals("Yes")){
                            txtweb.setVisibility(View.VISIBLE);
                            website.setVisibility(View.VISIBLE);
                            txtstatus.setVisibility(View.VISIBLE);
                            viewstatus.setVisibility(View.VISIBLE);
                            txtpan.setVisibility(View.VISIBLE);
                            pan.setVisibility(View.VISIBLE);
                            txtfundname.setVisibility(View.VISIBLE);
                            txt80g.setVisibility(View.VISIBLE);
                            rg80.setVisibility(View.VISIBLE);
                            raisefunds.setVisibility(View.VISIBLE);
                            viewfrg.setVisibility(View.VISIBLE);
                            txtfrngdont.setVisibility(View.VISIBLE);
                            txtfrng.setVisibility(View.VISIBLE);
                            txtrdo.setVisibility(View.VISIBLE);


                            txtemail.setVisibility(View.GONE);
                            email.setVisibility(View.GONE);
                            txtdescemail.setVisibility(View.GONE);
                            txtcntname.setVisibility(View.GONE);
                            cntname.setVisibility(View.GONE);
                            txtcntno.setVisibility(View.GONE);
                            number.setVisibility(View.GONE);
                        }else{
                            txtemail.setVisibility(View.VISIBLE);
                            email.setVisibility(View.VISIBLE);
                            txtdescemail.setVisibility(View.VISIBLE);
                            txtcntname.setVisibility(View.VISIBLE);
                            cntname.setVisibility(View.VISIBLE);
                            txtcntno.setVisibility(View.VISIBLE);
                            number.setVisibility(View.VISIBLE);
                            txtwebsite.setVisibility(View.VISIBLE);

                            website.setVisibility(View.GONE);
                            txtweb.setVisibility(View.GONE);
                            txtstatus.setVisibility(View.GONE);
                            viewstatus.setVisibility(View.GONE);
                            txtpan.setVisibility(View.GONE);
                            pan.setVisibility(View.GONE);
                            txtfundname.setVisibility(View.GONE);
                            txt80g.setVisibility(View.GONE);
                            rg80.setVisibility(View.GONE);
                            raisefunds.setVisibility(View.GONE);
                            viewfrg.setVisibility(View.GONE);
                            txtfrngdont.setVisibility(View.GONE);
                            txtfrng.setVisibility(View.GONE);
                            txtrdo.setVisibility(View.GONE);
                        }
                    }
                });


               /*int select=rg80.getCheckedRadioButtonId();
               RadioButton rg=(RadioButton)findViewById(select);
//               String rg80g =rg.getText().toString();*/

                rg80.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        int select=rg80.getCheckedRadioButtonId();
                        RadioButton rg=(RadioButton)view.findViewById(select);
                        String rg80g =rg.getText().toString();
                        if(rg80g.equals("Yes")){
                            txt80gupload.setVisibility(View.VISIBLE);
                            certficate.setVisibility(View.VISIBLE);
                            choose.setVisibility(View.VISIBLE);
                            txtsprt.setVisibility(View.VISIBLE);
                            txtorglogo.setVisibility(View.VISIBLE);
                            organisationlogo.setVisibility(View.VISIBLE);
                            logo.setVisibility(View.VISIBLE);
                            txtrecmd.setVisibility(View.VISIBLE);
                            txtorgtype.setVisibility(View.VISIBLE);
                            organisationtype.setVisibility(View.VISIBLE);
                            txtregsec.setVisibility(View.VISIBLE);
                            regsec.setVisibility(View.VISIBLE);
                            txtregno.setVisibility(View.VISIBLE);
                            regno.setVisibility(View.VISIBLE);
                            txtregadrress.setVisibility(View.VISIBLE);
                            regaddress.setVisibility(View.VISIBLE);
                            txtcntctemail.setVisibility(View.VISIBLE);
                            cntmail.setVisibility(View.VISIBLE);
                            txtallqueries.setVisibility(View.VISIBLE);
                            cntphone.setVisibility(View.VISIBLE);
                            txtcntctno.setVisibility(View.VISIBLE);
                            txtissues.setVisibility(View.VISIBLE);
                            viewissues.setVisibility(View.VISIBLE);
                            txtinr.setVisibility(View.VISIBLE);
                            txttaxrecepit.setVisibility(View.VISIBLE);
                            tax.setVisibility(View.VISIBLE);
                        }else{
                            txt80gupload.setVisibility(View.GONE);
                            certficate.setVisibility(View.GONE);
                            choose.setVisibility(View.GONE);
                            txtsprt.setVisibility(View.GONE);
                            txtorglogo.setVisibility(View.GONE);
                            organisationlogo.setVisibility(View.GONE);
                            logo.setVisibility(View.GONE);
                            txtrecmd.setVisibility(View.GONE);
                            txtorgtype.setVisibility(View.GONE);
                            organisationtype.setVisibility(View.GONE);
                            txtregsec.setVisibility(View.GONE);
                            regsec.setVisibility(View.GONE);
                            txtregno.setVisibility(View.GONE);
                            regno.setVisibility(View.GONE);
                            txtregadrress.setVisibility(View.GONE);
                            regaddress.setVisibility(View.GONE);
                            txtcntctemail.setVisibility(View.GONE);
                            cntmail.setVisibility(View.GONE);
                            txtallqueries.setVisibility(View.GONE);
                            cntphone.setVisibility(View.GONE);
                            txtcntctno.setVisibility(View.GONE);
                            txtissues.setVisibility(View.GONE);
                            viewissues.setVisibility(View.GONE);
                            txtinr.setVisibility(View.GONE);
                            txttaxrecepit.setVisibility(View.GONE);
                            tax.setVisibility(View.GONE);
                        }
                    }
                });




                raisefunds.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        int funds=raisefunds.getCheckedRadioButtonId();
                        RadioButton rf=(RadioButton)view.findViewById(funds);
                        String raisefund =rf.getText().toString();
                        if(raisefund.equals("Yes")){
                            txtfcra.setVisibility(View.VISIBLE);
                            foreginfunds.setVisibility(View.VISIBLE);
                        }else{
                            txtfcra.setVisibility(View.GONE);
                            foreginfunds.setVisibility(View.GONE);
                        }
                    }
                });




                foreginfunds.setOnCheckedChangeListener(
                        new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                int frgn=foreginfunds.getCheckedRadioButtonId();
                                RadioButton frfunds=(RadioButton)view.findViewById(frgn);
                                String foreignfund =frfunds.getText().toString();
                                if(foreignfund.equals("To our FCRA registered organization")){
                                    txtfcraregno.setVisibility(View.VISIBLE);
                                    fcraregno.setVisibility(View.VISIBLE);
                                    txtuploadfund.setVisibility(View.VISIBLE);
                                    uploadfund.setVisibility(View.VISIBLE);
                                    buttonuploadfund.setVisibility(View.VISIBLE);
                                    txtfcraupload.setVisibility(View.VISIBLE);

                                    txt501C.setVisibility(View.GONE);
                                    txtein.setVisibility(View.GONE);
                                    txtus.setVisibility(View.GONE);
                                    txtauth.setVisibility(View.GONE);
                                    regnoentity.setVisibility(View.GONE);
                                    ein.setVisibility(View.GONE);
                                    regadrus.setVisibility(View.GONE);
                                    authrep.setVisibility(View.GONE);
                                }else if(foreignfund.equals("To our 501(c)(3) registered organization in the US")){
                                    txt501C.setVisibility(View.VISIBLE);
                                    txtein.setVisibility(View.VISIBLE);
                                    txtus.setVisibility(View.VISIBLE);
                                    txtauth.setVisibility(View.VISIBLE);
                                    regnoentity.setVisibility(View.VISIBLE);
                                    ein.setVisibility(View.VISIBLE);
                                    regadrus.setVisibility(View.VISIBLE);
                                    authrep.setVisibility(View.VISIBLE);

                                    txtfcraregno.setVisibility(View.GONE);
                                    fcraregno.setVisibility(View.GONE);
                                    txtuploadfund.setVisibility(View.GONE);
                                    uploadfund.setVisibility(View.GONE);
                                    buttonuploadfund.setVisibility(View.GONE);
                                }else{
                                    txtfcraregno.setVisibility(View.GONE);
                                    fcraregno.setVisibility(View.GONE);
                                    txtuploadfund.setVisibility(View.GONE);
                                    uploadfund.setVisibility(View.GONE);
                                    buttonuploadfund.setVisibility(View.GONE);
                                    txt501C.setVisibility(View.GONE);
                                    txtein.setVisibility(View.GONE);
                                    txtus.setVisibility(View.GONE);
                                    txtauth.setVisibility(View.GONE);
                                    regnoentity.setVisibility(View.GONE);
                                    ein.setVisibility(View.GONE);
                                    regadrus.setVisibility(View.GONE);
                                    authrep.setVisibility(View.GONE);
                                }
                            }
                        });




            }
        });




        save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Submit();
                        Uploadpost();

                        Intent i = new Intent(getActivity(), OtherDetails.class);
                        startActivity(i);
                        //Post();
                /*String fullname = name.getText().toString();
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
                    }*/
                    }
                });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FundraiserDetails.class);
                startActivity(i);
            }
        });

      /*  one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), BasicInformation.class);
                startActivity(i);
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FundraiserDetails.class);
                startActivity(i);
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), OtherDetails.class);
                startActivity(i);

            }
        });*/
        return view;
    }
    @SuppressLint("NewApi")
    public void validation() {
        Log.d("SignupEnglish", "SignupEnglish");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        save.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
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
                      //  onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    /*public void onSignupSuccess() {
        save.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }*/

    public void onSignupFailed() {
        // Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        save.setEnabled(true);
    }
    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
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

    private void VideoBrowse() {
        if(ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},200);
        }else {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            // Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Files.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(intent, 100);
        }


    }

    private String getPath(Uri contentUri) {
        String[] proj = { MediaStore.Files.FileColumns.DATA};
        CursorLoader loader = new CursorLoader(getActivity(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            picUri = data.getData();
            filePath = getPath(picUri);
            Log.d("picUri", picUri.toString());
//            Log.d("filePath", filePath);


        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            // Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Files.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(intent, 100);

        }
    }
    private void Uploadpost() {
        if (filePath != null) {
            File final_file = new File(filePath);
            File final_file1 = new File(filePath);

            try {
                entity=new org.apache.http.entity.mime.MultipartEntity();
                String organisationname=name.getText().toString();
                String cntemail=email.getText().toString();
                String cntpersonname=cntname.getText().toString();
                String cntnumber=number.getText().toString();
                int selected=radioGroup.getCheckedRadioButtonId();
                RadioButton new_radiobutton=(RadioButton)view.findViewById(selected);
                String fundraisercontactperson =new_radiobutton.getText().toString();
                //  String benificeryname=beneficaryname.getText().toString();
                String web=website.getText().toString();
                String panno=pan.getText().toString();
                int select=rg80.getCheckedRadioButtonId();
                RadioButton rg=(RadioButton)view.findViewById(select);
                String rg80g =rg.getText().toString();
                int funds=raisefunds.getCheckedRadioButtonId();
                RadioButton rf=(RadioButton)view.findViewById(funds);
                String raisefund =rg.getText().toString();
                String certficatename=certficate.getText().toString();
                String orglogo=organisationlogo.getText().toString();
                String regsection=regsec.getText().toString();
                String regnumber=regno.getText().toString();
                String registrationaddress=regaddress.getText().toString();
                String cntctmail=cntmail.getText().toString();
                String orgtype = organisationtype.getSelectedItem().toString();
                int taxrec=tax.getCheckedRadioButtonId();
                RadioButton taxres=(RadioButton)view.findViewById(taxrec);
                String taxrecept =taxres.getText().toString();
                String phone =cntphone.getText().toString();
                int frgn=foreginfunds.getCheckedRadioButtonId();
                RadioButton frfunds=(RadioButton)view.findViewById(frgn);
                String foreignfund =frfunds.getText().toString();
                String reg =regnoentity.getText().toString();
                String empno =ein.getText().toString();
                String us =regadrus.getText().toString();
                String auth =authrep.getText().toString();
                String fcregno =fcraregno.getText().toString();
                String upfund =uploadfund.getText().toString();

                FileBody fileBody=new FileBody(final_file);
                FileBody fileBody1=new FileBody(final_file);
              /*  entity.addPart("Key",new StringBody("UniversalThought"));
                entity.addPart("rType",new StringBody("file"));
                entity.addPart("sathish",fileBody);
*/



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
                    entity.addPart("tax_exemption_to_donors", new StringBody(rg80g));
                    entity.addPart("raise_foreign_funds", new StringBody("Yes"));
                    Log.e("Checking",rg80g);
                    if(rg80g.equals("Yes")){
                        Log.e("Checking","1");
                        entity.addPart("tax_certificate",fileBody);
                        entity.addPart("organization_logo",fileBody);
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
                        Log.e("Checking",foreignfund);
                        entity.addPart("foreign_funds_raised_to_transferred",new StringBody("1"));
                        if(foreignfund.equals("1")){
                            entity.addPart("fcra_registration_number",new StringBody(fcregno));
                            entity.addPart("fcra_certificate",new StringBody(upfund));
                        }else if(foreignfund.equals("To our 501(c)(3) registered organization in the US")){
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


            }catch (UnsupportedEncodingException e){

            }catch (NoSuchMethodError e) {
                e.printStackTrace();
            }

            CustomMultipart request=new CustomMultipart(URL_FORMONE, new Response.Listener() {
                @Override
                public void onResponse(Object response) {
                    Log.e("Checking", "hi" + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            },entity);
            RequestQueue queue= Volley.newRequestQueue(getActivity());
            queue.add(request);

        }
    }
}
