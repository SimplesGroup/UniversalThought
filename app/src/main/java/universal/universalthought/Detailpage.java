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

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import universal.universalthought.fundraiser.DonatePage;
import universal.universalthought.model.CategoryItemmodel;

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
String url="http://www.simples.in/universalthought/universalthought.php";
    RequestQueue requestQueue;
    String post_id;
    String text;
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

        Intent in=getIntent();
        post_id=in.getStringExtra("ID");
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        String imageurl="http://simpli-city.in/vdfdhfv78lmdsvmg5todlsh4jffgskjb2947qnt/images/food/1826888200besil%20pakoda.jpg";


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
        text="Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum." +
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";

        webView_details_about_page.getSettings().setLoadsImagesAutomatically(true);
        webView_details_about_page.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView_details_about_page.getSettings().setAllowFileAccess(true);
        webView_details_about_page.getSettings().setJavaScriptEnabled(true);




        if(post_id!=null){
            getdata();
        }

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



    private void getdata(){
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Response",response.toString());
                try {
                    JSONObject jsondata=new JSONObject(response.toString());
                    JSONArray jsonArray=jsondata.getJSONArray("result");

                    Log.e("DE","data"+jsonArray.toString());
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject explrObject = jsonArray.getJSONObject(i);

                            CategoryItemmodel model=new CategoryItemmodel();
                            model.setAmount(explrObject.getString("amount"));
                           model.setCity(explrObject.getString("city"));
                            model.setId(explrObject.getString("id"));
                        model.setTitleoffundraising(explrObject.getString("title"));

                        model.setPhoto(explrObject.getString("fundraiser_photo"));

                        title.setText(explrObject.getString("title"));
                            networkImageView_image_post.setImageUrl(explrObject.getString("fundraiser_photo"),imageLoader);
                            totalcost_value=56658;
                            obtainedcost_value=40000;
                            percentage_value=(int) ((obtainedcost_value*100)/totalcost_value);

                            percentage.setText(percentage_value+"%");
                            Resources res = getResources();
                            Drawable drawable = res.getDrawable(R.drawable.piechart);

                            percentage_circularbar.setProgress(percentage_value);   // Main Progress
                            //percentage_circularbar.setSecondaryProgress(50); // Secondary Progress
                            percentage_circularbar.setMax(100); // Maximum Progress
                            webView_details_about_page.loadDataWithBaseURL("", text+"</head>", "text/html", "utf-8", "");
                            // description.setBackgroundColor(0x0a000000);
                            webView_details_about_page.setBackgroundColor(Color.TRANSPARENT);
                            webView_details_about_page.setWebViewClient(new MyBrowser());

                    }

                }catch (JSONException e){

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String>param=new HashMap<>();
                param.put("Key","UniversalThought");
                param.put("rType","DetailPage");
                param.put("id",post_id);

                return param;

            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 3, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(request);

    }

}
