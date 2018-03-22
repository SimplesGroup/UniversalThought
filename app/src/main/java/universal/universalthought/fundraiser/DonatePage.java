package universal.universalthought.fundraiser;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import universal.universalthought.R;

/**
 * Created by Sandhiya on 12/15/2017.
 */

public class DonatePage extends AppCompatActivity {

    EditText donateamnt,donatename,donatemail,donorname,comment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.donatepage);

        donateamnt = (EditText)findViewById(R.id.edt_amnt);
        donatename = (EditText)findViewById(R.id.edt_name);
        donatemail = (EditText)findViewById(R.id.edt_email);
        donorname = (EditText)findViewById(R.id.edt_donor);
        comment = (EditText)findViewById(R.id.edt_cmnt);



    }
}
