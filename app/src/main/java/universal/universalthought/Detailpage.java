package universal.universalthought;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import universal.universalthought.fundraiser.DonatePage;

/**
 * Created by Kuppusamy on 12/5/2017.
 */

public class Detailpage extends AppCompatActivity {
    TextView title,total_cost,currentdonated_cost,percentage;
    ProgressBar percentage_circularbar;
    Button button_helpnow;
    ImageButton imagebutton_rupees,backbutton;
    EditText editText_enteramt;
    WebView    webView_details_about_page;
    NetworkImageView networkImageView_image_post;
    ImageLoader imageLoader;
    int totalcost_value,obtainedcost_value;
    int percentage_value;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail_page);
        title=(TextView)findViewById(R.id.title_item);
        total_cost=(TextView)findViewById(R.id.total_cost);
        currentdonated_cost=(TextView)findViewById(R.id.tonated_cost);
        percentage=(TextView)findViewById(R.id.textView_percentage);
        percentage_circularbar=(ProgressBar)findViewById(R.id.circularProgressBar);
        button_helpnow=(Button)findViewById(R.id.button_helpnow);
        imagebutton_rupees=(ImageButton)findViewById(R.id.imagebutton_rupees);
        editText_enteramt=(EditText)findViewById(R.id.editext_donation_cost);
        webView_details_about_page=(WebView)findViewById(R.id.wwebview_detail_page);
        networkImageView_image_post=(NetworkImageView)findViewById(R.id.detail_image);
        imageLoader=CustomVolleyRequest.getInstance(this).getImageLoader();
        backbutton = (ImageButton)findViewById(R.id.imagebutton_back);

        String imageurl="http://simpli-city.in/vdfdhfv78lmdsvmg5todlsh4jffgskjb2947qnt/images/food/1826888200besil%20pakoda.jpg";

        networkImageView_image_post.setImageUrl(imageurl,imageLoader);
        totalcost_value=56658;
        obtainedcost_value=40000;
        percentage_value=(int) ((obtainedcost_value*100)/totalcost_value);

        percentage.setText(percentage_value+"%");
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.piechart);

        percentage_circularbar.setProgress(percentage_value);   // Main Progress
       //percentage_circularbar.setSecondaryProgress(50); // Secondary Progress
        percentage_circularbar.setMax(100); // Maximum Progress
        // percentage_circularbar.setProgressDrawable(drawable);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        button_helpnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Detailpage.this, DonatePage.class);
                startActivity(i);
            }
        });
        String text="Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum." +
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";

        webView_details_about_page.getSettings().setLoadsImagesAutomatically(true);
        webView_details_about_page.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView_details_about_page.getSettings().setAllowFileAccess(true);
        webView_details_about_page.getSettings().setJavaScriptEnabled(true);

        webView_details_about_page.loadDataWithBaseURL("", text+"</head>", "text/html", "utf-8", "");
        // description.setBackgroundColor(0x0a000000);
        webView_details_about_page.setBackgroundColor(Color.TRANSPARENT);
        webView_details_about_page.setWebViewClient(new MyBrowser());
    }
    private class MyBrowser extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            // ((EditText) getActionBar().getCustomView().findViewById(R.id.editText)).setText(url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.e("URL",url.toString());



            return true;
        }
    }
}
