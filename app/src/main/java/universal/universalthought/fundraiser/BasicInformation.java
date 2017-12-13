package universal.universalthought.fundraiser;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import universal.universalthought.R;
import universal.universalthought.activity.HomeFragment;
import universal.universalthought.activity.MainActivity;

/**
 * Created by Sandhiya on 12/11/2017.
 */

public class BasicInformation extends AppCompatActivity {

    Button save;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fundraiser_basicinfo);

        save = (Button)findViewById(R.id.button_save);
         save.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Intent i = new Intent(BasicInformation.this,FundraiserDetails.class);
                 startActivity(i);
             }
         });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(BasicInformation.this,MainActivity.class);
        startActivity(i);
    }
}
