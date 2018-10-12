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

public class AnimalsAdapter extends RecyclerView.Adapter<AnimalsAdapter.MyViewHolder> {
    private Context mContext;
    private List<CategoryItemmodel> productEnglishList;
    int totalcost_value,obtainedcost_value;
    int percentage_value;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,likecount,commentcount,bname;
        public NetworkImageView thumbnail;
        Button overflow;
        ProgressBar progressBar;
        TextView total_amount_textview;
        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (NetworkImageView) view.findViewById(R.id.thumbnail);
          likecount = (TextView)view.findViewById(R.id.alltab_likescount);
          commentcount = (TextView)view.findViewById(R.id.alltab_commentscount);
          bname = (TextView)view.findViewById(R.id.name);
            progressBar=(ProgressBar)view.findViewById(R.id.circularProgressBar);
            total_amount_textview=(TextView)view.findViewById(R.id.totalamount);
        }
    }
    public AnimalsAdapter(Context mContext, List<CategoryItemmodel> productEnglishList) {
        this.mContext = mContext;
        this.productEnglishList = productEnglishList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }


    public void onBindViewHolder(final MyViewHolder holder, int position) {
        String splash = "fonts/LATO-MEDIUM.TTF";

        final CategoryItemmodel model = productEnglishList.get(position);
        ImageLoader imageLoader= CustomVolleyRequest.getInstance(mContext).getImageLoader();
        Log.e("SIZE", model.getTitleoffundraising());

        holder.title.setText(model.getTitleoffundraising());
        holder.likecount.setText(model.getLikecount());
        holder.commentcount.setText(model.getCommentcount());
        holder.bname.setText(model.getBeneficiaryname());
        holder.thumbnail.setImageUrl(model.getPhoto(),imageLoader);
    int    totalcost_value = Integer.parseInt(model.getRaisingamount());
     int   obtainedcost_value= Integer.parseInt(model.getAmountraised());
    int    percentage_value=(int) ((obtainedcost_value*100)/totalcost_value);
       holder.progressBar.setProgress(percentage_value);
       // Main Progress
        //percentage_circularbar.setSecondaryProgress(50); // Secondary Progress
        holder.progressBar.setMax(100);
        holder.total_amount_textview.setText(model.getRaisingamount());

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext,Detailpage.class);
                i.putExtra("ID", model.getId());
                mContext  .startActivity(i);
            }
        });

       /* holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext,Detailpage.class);
                i.putExtra("ID", model.getId());
                mContext.startActivity(i);
            }
        });*/
    }

    public int getItemCount() {
        return productEnglishList.size();
    }
    public void data(List<CategoryItemmodel> lists){
        this.productEnglishList.addAll(lists);
        notifyDataSetChanged();
    }
}
