package universal.universalthought.fundraiser;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import universal.universalthought.R;
import universal.universalthought.activity.HomeFragment;
import universal.universalthought.adapter.ProductsAdapterEnglish;
import universal.universalthought.model.ProductEnglish;

/**
 * Created by Sandhiya on 12/15/2017.
 */

public class StoriesPage extends AppCompatActivity {

    private ProductsAdapterEnglish adapter;
    private List<ProductEnglish> productEnglishList;
    private RecyclerView recyclerView;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_stories);
        recyclerView = (RecyclerView) findViewById(R.id.drawerList);
        productEnglishList = new ArrayList<>();
        adapter = new ProductsAdapterEnglish(getApplicationContext(), productEnglishList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(StoriesPage.this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareAlbums();
    }

    private void prepareAlbums() {
        int[] covers = new int[]{
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

        adapter.notifyDataSetChanged();
    }
}
