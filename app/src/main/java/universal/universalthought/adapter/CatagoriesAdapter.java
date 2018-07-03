package universal.universalthought.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;

import java.util.List;

import universal.universalthought.CustomVolleyRequest;
import universal.universalthought.Detailpage;
import universal.universalthought.R;
import universal.universalthought.model.CategoryItemmodel;

/**
 * Created by Kuppusamy on 4/12/2018.
 */

public class CatagoriesAdapter extends RecyclerView.Adapter<CatagoriesAdapter.MyViewHolder> {
    private Context mContext;
    private List<CategoryItemmodel> productEnglishList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView quantity;
        public NetworkImageView thumbnail;
        Button overflow;

        public MyViewHolder(View view) {
            super(view);

            quantity = (TextView) view.findViewById(R.id.kg);
            thumbnail = (NetworkImageView) view.findViewById(R.id.thumbnail);
            overflow = (Button) view.findViewById(R.id.overflow);
        }
    }
    public CatagoriesAdapter(Context mContext, List<CategoryItemmodel> productEnglishList) {
        this.mContext = mContext;
        this.productEnglishList = productEnglishList;
    }
    @Override
    public CatagoriesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new CatagoriesAdapter.MyViewHolder(itemView);
    }


    public void onBindViewHolder(final CatagoriesAdapter.MyViewHolder holder, int position) {
        String splash = "fonts/LATO-MEDIUM.TTF";
////        final Typeface tf = Typeface.createFromAsset(mContext.getAssets(), splash);
        final CategoryItemmodel productEnglish = productEnglishList.get(position);
        ImageLoader imageLoader= CustomVolleyRequest.getInstance(mContext).getImageLoader();
        Log.e("SIZE", productEnglish.getTitleoffundraising());
        // holder.title.setText(productEnglish.getPname());
        // holder.count.setText("Rs." + productEnglish.getPprice());
        holder.quantity.setText(productEnglish.getTitleoffundraising());
       holder.thumbnail.setImageUrl(productEnglish.getPhoto(),imageLoader);
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext,Detailpage.class);

                i.putExtra("ID", productEnglish.getId());
                mContext.startActivity(i);
            }
        });


    }



    public int getItemCount() {
        return productEnglishList.size();
    }
}
