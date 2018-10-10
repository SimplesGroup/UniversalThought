package universal.universalthought.adapter.StoriesPageAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;

import java.util.List;

import universal.universalthought.CustomVolleyRequest;
import universal.universalthought.Detailpage;
import universal.universalthought.R;
import universal.universalthought.model.CategoryItemmodel;

public class EducationStoriesAdapter extends RecyclerView.Adapter<EducationStoriesAdapter.MyViewHolder> {
    private Context mContext;
    private List<CategoryItemmodel> productEnglishList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView quantity,username,createdate,likecount,commentcount;
        public NetworkImageView thumbnail;
        public ImageView userimage;
        Button overflow;
        ProgressBar progressBar;
        TextView total_amount_textview;
        RelativeLayout pb;
        public MyViewHolder(View view) {
            super(view);
            quantity = (TextView) view.findViewById(R.id.kg);
            username = (TextView) view.findViewById(R.id.name);
            createdate = (TextView) view.findViewById(R.id.date);
            likecount = (TextView) view.findViewById(R.id.alltab_likescount);
            commentcount = (TextView) view.findViewById(R.id.alltab_commentscount);
            thumbnail = (NetworkImageView) view.findViewById(R.id.thumbnail);
            userimage = (ImageView) view.findViewById(R.id.thum);
        }
    }
    public EducationStoriesAdapter(Context mContext, List<CategoryItemmodel> productEnglishList) {
        this.mContext = mContext;
        this.productEnglishList = productEnglishList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.storiesadapter, parent, false);

        return new MyViewHolder(itemView);
    }


    public void onBindViewHolder(final MyViewHolder holder, int position) {
        String splash = "fonts/LATO-MEDIUM.TTF";
////        final Typeface tf = Typeface.createFromAsset(mContext.getAssets(), splash);
        final CategoryItemmodel productEnglish = productEnglishList.get(position);
        ImageLoader imageLoader= CustomVolleyRequest.getInstance(mContext).getImageLoader();
        Log.e("SIZE", productEnglish.getTitleoffundraising());
        holder.quantity.setText(productEnglish.getTitleoffundraising());
        holder.username.setText(productEnglish.getName());
        //  holder.likecount.setText(productEnglish.getLikecount());
        //holder.commentcount.setText(productEnglish.getCommentcount());
        //  holder.createdate.setText(productEnglish.getDate());
        holder.thumbnail.setImageUrl(productEnglish.getPhoto(),imageLoader);
        //      holder.userimage.setImageURI(productEnglish.getUimage(),imageLoader);

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext,Detailpage.class);

                i.putExtra("ID", productEnglish.getId());

                // Log.e("TITLEEEE", productEnglish.getPname());
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
        // holder.title.setTypeface(tf);
        // holder.count.setTypeface(tf);
        // holder.quantity.setTypeface(tf);
    }


    /* @Override
     public int getItemViewType(int position) {
         return super.getItemViewType(position);
     }*/
    public int getItemCount() {
        return productEnglishList.size();
    }

    public void data(List<CategoryItemmodel> lists){
       this.productEnglishList.addAll(lists);
       notifyDataSetChanged();
    }
}
