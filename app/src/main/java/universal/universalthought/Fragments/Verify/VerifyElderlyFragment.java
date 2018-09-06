package universal.universalthought.Fragments.Verify;

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

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import universal.universalthought.Listinterface;
import universal.universalthought.R;
import universal.universalthought.activity.CheckClass;
import universal.universalthought.activity.VerifyJsonParser;
import universal.universalthought.adapter.ElderlyAdapter;
import universal.universalthought.adapter.VerifyAdapters.VerifyElderlyAdapter;
import universal.universalthought.model.CategoryItemmodel;


public class VerifyElderlyFragment extends Fragment implements Listinterface {

    private RecyclerView recyclerView;
    private static VerifyElderlyAdapter adapter;
    private static List<CategoryItemmodel> productList;
    ImageView fundraiser;
    RequestQueue requestqueue;
    int requestcount=1;
    String url="http://www.simples.in/universalthought/universalthought.php";
    public VerifyElderlyFragment() {
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
        adapter = new VerifyElderlyAdapter(getActivity(), productList);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        requestqueue= Volley.newRequestQueue(getActivity());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        getData();
        adapter.notifyDataSetChanged();
        // Inflate the layout for this fragment
        return rootView;
    }

    private void getData(){
        VerifyJsonParser cls=new VerifyJsonParser();
        productList = cls.jsonmethodverify(getContext(),"elderly",requestcount);
        requestcount++;
        adapter.notifyDataSetChanged();
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
        adapter .data(productList);
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
