package universal.universalthought.Fragments;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import universal.universalthought.Listinterface;
import universal.universalthought.R;
import universal.universalthought.activity.CheckClass;
import universal.universalthought.adapter.EmergenciesAdapter;
import universal.universalthought.model.CategoryItemmodel;


public class EmergenciesFragment extends Fragment implements Listinterface {

    private RecyclerView recyclerView;
    private static EmergenciesAdapter adapter;
    private static List<CategoryItemmodel> productList;
    ImageView fundraiser;
    RequestQueue requestqueue;
    int requestcount=1;
    String url="http://www.simples.in/universalthought/universalthought.php";
    public EmergenciesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_messages, container, false);
        productList = new ArrayList<CategoryItemmodel>();
        adapter = new EmergenciesAdapter(getActivity(), productList);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        requestqueue= Volley.newRequestQueue(getActivity());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
       // recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        getData();

        // Inflate the layout for this fragment
        return rootView;
    }

    private void getData(){
        CheckClass cls=new CheckClass();
        cls.jsonmethod(getContext(),"emergenicies",requestcount);
        productList = cls.jsonmethod(getContext(),"emergenicies",requestcount);
        requestcount++;
        //adapter.notifyDataSetChanged();
    }
StringRequest getDatafromserver(final int reqcount){
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Response",response.toString());
                try {
                    JSONObject jsondata=new JSONObject(response.toString());
                    JSONArray jsonArray=jsondata.getJSONArray("result");

                    Log.e("Response","data"+jsonArray.toString());
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject explrObject = jsonArray.getJSONObject(i);
                        CategoryItemmodel model=new CategoryItemmodel();
                        model.setAmountraised(explrObject.getString("amount"));
                        model.setCity(explrObject.getString("city"));
                        model.setId(explrObject.getString("id"));
                        model.setTitleoffundraising(explrObject.getString("title_of_fundraising"));
                        Log.e("Response",explrObject.getString("title_of_fundraising"));
                        model.setPhoto(explrObject.getString("fundraiser_photo"));
                        productList.add(model);
                    }
                    adapter.notifyDataSetChanged();
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
                param.put("rType","IndividualCategory");
                param.put("category","emergenicies");
                param.put("page",String.valueOf(reqcount));


                return param;


            }
        };
    request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 3, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    requestqueue.add(request);
        return request;
}


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
    @Override
    public void List(List<CategoryItemmodel> list) {
        productList=list;

        Log.e("Response","listnews"+productList.toString());
        adapter.data(productList);

    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {

                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
