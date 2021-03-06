package universal.universalthought;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import universal.universalthought.Util.Config;
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
    RequestQueue requestQueue;
    String post_id;
    String text;
    /** declaration start of campaign organizer **/
    TextView campaign_organizer_textview_label,campaign_organizer_textview_name,campaign_organizer_textview_date,campaign_organizer_textview_centerdivider,campaign_organizer_textview_location;
    TextView benificiary_name_textview;
    ImageView campaign_logo_imageview;
    ImageButton conatct_campaign_imgbutton;

LinearLayout benificiarylayout,campain_layout;
    /** declaration end of campaign organizer **/

    LinearLayout comment_layouts;
    EditText comment_edit;
    ImageButton comment_post;
    ImageView like_button,comment_button,share_button;


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


        campaign_logo_imageview=(ImageView)findViewById(R.id.campainorganizerlogo);
        conatct_campaign_imgbutton=(ImageButton)findViewById(R.id.contact_organizer);
        campaign_organizer_textview_label=(TextView)findViewById(R.id.organizer_campainname) ;
        campaign_organizer_textview_name=(TextView)findViewById(R.id.campain_organaizername);
        campaign_organizer_textview_date=(TextView)findViewById(R.id.campain_date) ;
        campaign_organizer_textview_centerdivider=(TextView)findViewById(R.id.campain_centerdivider) ;
        campaign_organizer_textview_location=(TextView)findViewById(R.id.campain_location) ;
        benificiary_name_textview=(TextView)findViewById(R.id.benificiary_name) ;
        benificiarylayout=(LinearLayout)findViewById(R.id.benificiary_layout);
        campain_layout=(LinearLayout)findViewById(R.id.campain_layout) ;
        //campaign_organizer_textview_name.setText("universal thought");

       /* campaign_organizer_textview_date.setText("16 Aug 2018");
        campaign_organizer_textview_location.setText("Coimbatore,TamilNadu");
        benificiary_name_textview.setText("Beneficiary: universal");*/

        comment_layouts=(LinearLayout)findViewById(R.id.comment_layout);
        comment_edit=(EditText)findViewById(R.id.edit_comment);
        comment_post=(ImageButton)findViewById(R.id.button_post);
        like_button=(ImageView)findViewById(R.id.button_likes);
        comment_button=(ImageView)findViewById(R.id.button_comment);
        share_button=(ImageView)findViewById(R.id.button_share);

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

        comment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comment_layouts.getVisibility()==View.VISIBLE){
                    comment_layouts.setVisibility(View.GONE);
                }else if(comment_layouts.getVisibility()==View.GONE){
                    comment_layouts.setVisibility(View.VISIBLE);
                }
            }
        });

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
        StringRequest request=new StringRequest(Request.Method.POST, Config.url, new Response.Listener<String>() {
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
                            model.setAmountraised(explrObject.getString("raising_amount"));
                           model.setCity(explrObject.getString("city"));
                            model.setId(explrObject.getString("id"));
                        model.setTitleoffundraising(explrObject.getString("title"));

                        model.setPhoto(explrObject.getString("fundraiser_photo"));

                        text=explrObject.getString("description");
                        total_cost.setText(explrObject.getString("raising_amount"));
                        currentdonated_cost.setText("Raised of Rs,"+explrObject.getString("amount_raised")+"goal");
                        title.setText(explrObject.getString("title"));
                            networkImageView_image_post.setImageUrl(explrObject.getString("fundraiser_photo"),imageLoader);
                            totalcost_value=Integer.valueOf(explrObject.getString("raising_amount"));
                            obtainedcost_value=Integer.valueOf(explrObject.getString("amount_raised"));
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



                            campaign_organizer_textview_name.setText(explrObject.getString("name"));
                            campaign_organizer_textview_location.setText(explrObject.getString("city"));
                            campaign_organizer_textview_centerdivider.setText(Html.fromHtml("|"));

                            if(explrObject.getString("beneficiary_name")!=null) {
                                benificiary_name_textview.setText("Beneficiary: "+explrObject.getString("beneficiary_name"));
                            }else {
                                benificiary_name_textview.setVisibility(View.GONE);
                                benificiarylayout.setVisibility(View.GONE);
                            }

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
