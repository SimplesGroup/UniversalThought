package universal.universalthought.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

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
        ProgressBar progressBar;
        TextView total_amount_textview;

        public MyViewHolder(View view) {
            super(view);

            quantity = (TextView) view.findViewById(R.id.title);
            thumbnail = (NetworkImageView) view.findViewById(R.id.thumbnail);
         //   overflow = (Button) view.findViewById(R.id.overflow);
            progressBar=(ProgressBar)view.findViewById(R.id.circularProgressBar);
            total_amount_textview=(TextView)view.findViewById(R.id.totalamount);
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

        int    totalcost_value = Integer.parseInt(productEnglish.getRaisingamount());
        int   obtainedcost_value= Integer.parseInt(productEnglish.getAmountraised());
        int    percentage_value=(int) ((obtainedcost_value*100)/totalcost_value);
        holder.progressBar.setProgress(percentage_value);   // Main Progress
        //percentage_circularbar.setSecondaryProgress(50); // Secondary Progress
        holder.progressBar.setMax(100);
        holder.total_amount_textview.setText(productEnglish.getRaisingamount());

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext,Detailpage.class);

                i.putExtra("ID", productEnglish.getId());
                mContext.startActivity(i);
            }
        });

       /* holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext,Detailpage.class);
                i.putExtra("ID", productEnglish.getId());
                mContext.startActivity(i);
            }
        });*/
    }



    public int getItemCount() {
        return productEnglishList.size();
    }
}
