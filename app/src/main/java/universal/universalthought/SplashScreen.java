package universal.universalthought;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import universal.universalthought.activity.MainActivity;


public class SplashScreen extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    private PrefManager prefManager;
    public static final String mypreference = "mypref";
    private static int SPLASH_TIME_OUT = 3000;
    PackageInfo info;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try {
            info = getPackageManager().getPackageInfo("simplicity_an.simplicity_an", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
        setContentView(R.layout.splashscreen);
        sharedpreferences =  getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                prefManager = new PrefManager(getApplicationContext());
              //  if (!prefManager.isFirstTimeLaunch()) {
                    /*if(language_data!=null){
                        if(language_data.equals("English")) {
                            Intent i = new Intent(SplashScreen.this, MainPageEnglish.class);
                            startActivity(i);
                            finish();
                        }else {
                            Intent i = new Intent(SplashScreen.this, MainPageTamil.class);
                            startActivity(i);
                            finish();
                        }
                    }*/

                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);
                    finish();

               /* }else {
                    Intent i = new Intent(SplashScreen.this, IntroSlider.class);
                    startActivity(i);
                    finish();
                }*/

                }

        }, SPLASH_TIME_OUT);



    }
}
