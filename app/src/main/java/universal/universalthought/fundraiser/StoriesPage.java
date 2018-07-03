package universal.universalthought.fundraiser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import universal.universalthought.CustomVolleyRequest;
import universal.universalthought.Detailpage;
import universal.universalthought.R;
import universal.universalthought.activity.FundraiserActivity;
import universal.universalthought.activity.HomeFragment;
import universal.universalthought.activity.TabsFragment;
import universal.universalthought.adapter.ProductsAdapterEnglish;
import universal.universalthought.model.CategoryItemmodel;
import universal.universalthought.model.ProductEnglish;

/**
 * Created by Sandhiya on 12/15/2017.
 */

public class StoriesPage extends Fragment {

    private ProductsAdapterEnglish adapter;
    private List<ProductEnglish> productEnglishList;
    private RecyclerView recyclerView;
    Context context;
    ImageButton help,stories,verify;
    ImageView fundraiser;

    SharedPreferences sharedpreferences;

    public static final String mypreference = "mypref";
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;

    private int dotscount;
    private List<Model>image=new ArrayList<>();

String sliderurl="http://www.simples.in/universalthought/universalthought.php";


    CoordinatorLayout mCoordinatorLayout;



    public StoriesPage() {
        // Required empty public constructor
    }
    public static StoriesPage newInstance() {
        StoriesPage fragment = new StoriesPage();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_stories,container,false);

        recyclerView = (RecyclerView)rootView. findViewById(R.id.drawerList);
        productEnglishList = new ArrayList<>();
        adapter = new ProductsAdapterEnglish(getActivity(), productEnglishList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) rootView.findViewById(R.id.layoutDots);


        changeStatusBarColor();
        getdataslide();

        prepareAlbums();



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
addBottomDots(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return rootView;
    }


private  void getdataslide(){
    StringRequest request=new StringRequest(Request.Method.POST, sliderurl, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {


            try {
                JSONObject jsondata=new JSONObject(response.toString());
                JSONArray jsonArray=jsondata.getJSONArray("result");

                for(int i=0;i<jsonArray.length();i++){
                    JSONObject explrObject = jsonArray.getJSONObject(i);


                    String photo=explrObject.getString("fundraiser_photo");
                    Log.e("IMG",photo);

Model model=new Model();
model.setImageurl(explrObject.getString("fundraiser_photo"));
image.add(model);
                }

            }catch (JSONException e){

            }
           myViewPagerAdapter = new MyViewPagerAdapter(getActivity(),image);

            viewPager.setAdapter(myViewPagerAdapter);

            dotscount = myViewPagerAdapter.getCount();
            dots = new TextView[dotscount];

            addBottomDots(0);

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
            param.put("rType","homeslider");
            param.put("homeslider","homeslider");
            return param;
        }
    };
  RequestQueue queue= Volley.newRequestQueue(getActivity());
  queue.add(request);
}
    private void prepareAlbums() {

        StringRequest request=new StringRequest(Request.Method.POST, sliderurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Home",response.toString());

                try {
                    JSONObject jsondata = new JSONObject(response.toString());
                    JSONArray jsonArray = jsondata.getJSONArray("result");

                    Log.e("DE", "data" + jsonArray.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject explrObject = jsonArray.getJSONObject(i);

                        ProductEnglish model = new ProductEnglish();
                        model.setAmount(explrObject.getString("raising_amount"));

                        model.setCategory(explrObject.getString("category"));
                        model.setId(explrObject.getString("id"));
                        model.setTitleoffundraising(explrObject.getString("title_of_fundraising"));

                        model.setPhoto(explrObject.getString("fundraiser_photo"));

                      productEnglishList.add(model);
                    }
                }catch (JSONException e){

                }
              adapter.notifyDataSetChanged();
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
                param.put("rType","HomePage1");
                param.put("home_page1","home_page1");


                return param;
            }
        };

        RequestQueue queue= Volley.newRequestQueue(getActivity());
        queue.add(request);



        /*int[] covers = new int[]{
                R.drawable.imageone,
                R.drawable.imagetwo,
                R.drawable.imagethree,
                R.drawable.imagefour,
                R.drawable.imageone,
                R.drawable.imagetwo};



        ProductEnglish a = new ProductEnglish("Fennel Seeds", "250", covers[0],"Help India's rural graduates achieve their dreams");
        productEnglishList.add(a);

        a = new ProductEnglish("Asafoetida", "250", covers[1],"Help India's rural graduates achieve their dreams");
        productEnglishList.add(a);

        a = new ProductEnglish("Red Chilli Powder", "150", covers[2],"Help India's rural graduates achieve their dreams");
        productEnglishList.add(a);

        a = new ProductEnglish("Black Cardamon", "540", covers[3],"Help India's rural graduates achieve their dreams");
        productEnglishList.add(a);

        a = new ProductEnglish("White Pepper", "14", covers[4],"Help India's rural graduates achieve their dreams");
        productEnglishList.add(a);

        a = new ProductEnglish("Black Pepper", "1", covers[5],"Help India's rural graduates achieve their dreams");
        productEnglishList.add(a);
*/

    }


class Model{
        String imageurl,title,id;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
   private void addBottomDots(int currentPage) {

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(getActivity());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }



    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }


    public class MyViewPagerAdapter extends PagerAdapter {
        Context context;
        private LayoutInflater layoutInflater;
       List<Model>images;
        public MyViewPagerAdapter(Context context, List<Model> images) {
            this.context = context;
            this.images = images;
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = layoutInflater.inflate(R.layout.sliderlayout, container, false);
            Log.e("IMG","imgs");
            Model model=images.get(position);
            ImageView imageView = (ImageView)itemView.findViewById(R.id.imageView);
            ImageLoader imageLoader= CustomVolleyRequest.getInstance(context).getImageLoader();

            imageLoader.get(model.getImageurl(), ImageLoader.getImageListener(imageView,R.drawable.logo,R.drawable.logo));


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(),Detailpage.class);
                    startActivity(i);
                }
            });

            ViewPager vp = (ViewPager) container;
            vp.addView(itemView, 0);


            return itemView;
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ViewPager vp = (ViewPager) container;
            View view = (View) object;
            vp.removeView(view);
        }
    }




}
