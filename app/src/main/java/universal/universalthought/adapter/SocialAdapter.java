package universal.universalthought.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import universal.universalthought.CustomVolleyRequest;
import universal.universalthought.Detailpage;
import universal.universalthought.R;
import universal.universalthought.model.CategoryItemmodel;

public class SocialAdapter extends RecyclerView.Adapter<SocialAdapter.MyViewHolder> {
    private Context mContext;
    private List<CategoryItemmodel> productEnglishList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,likecount,commentcount,bname;
        public NetworkImageView thumbnail;
        Button overflow;
        ProgressBar progressBar;
        TextView total_amount_textview;
        LinearLayout comment_layout;
        EditText comment_edit;
        ImageButton comment_post;
        ImageView like_button,comment_button,share_button;
        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (NetworkImageView) view.findViewById(R.id.thumbnail);
            likecount = (TextView)view.findViewById(R.id.alltab_likescount);
            commentcount = (TextView)view.findViewById(R.id.alltab_commentscount);
            bname = (TextView)view.findViewById(R.id.name);
       //     overflow = (Button) view.findViewById(R.id.overflow);
            progressBar=(ProgressBar)view.findViewById(R.id.circularProgressBar);
            total_amount_textview=(TextView)view.findViewById(R.id.totalamount);
            comment_layout=(LinearLayout)view.findViewById(R.id.comment_layout);
            comment_edit=(EditText)view.findViewById(R.id.edit_comment);
            comment_post=(ImageButton)view.findViewById(R.id.button_post);
            like_button=(ImageView)view.findViewById(R.id.button_likes);
            comment_button=(ImageView)view.findViewById(R.id.button_comment);
            share_button=(ImageView)view.findViewById(R.id.button_share);
        }
    }
    public SocialAdapter(Context mContext, List<CategoryItemmodel> productEnglishList) {
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

        final CategoryItemmodel productEnglish = productEnglishList.get(position);
        ImageLoader imageLoader= CustomVolleyRequest.getInstance(mContext).getImageLoader();
        Log.e("SIZE", productEnglish.getTitleoffundraising());

        holder.title.setText(Html.fromHtml(productEnglish.getTitleoffundraising()));
        if(productEnglish.getLikecount().equals("1")){
            holder.likecount.setText(Html.fromHtml(productEnglish.getLikecount()+"&nbsp;"+"Like"));
        }else  if(productEnglish.getLikecount().equals("0")){

        }else {
            holder.likecount.setText(Html.fromHtml(productEnglish.getLikecount()+"&nbsp;"+"Likes"));
        }
        if(productEnglish.getCommentcount().equals("1")){
            holder.commentcount.setText(Html.fromHtml(productEnglish.getCommentcount()+"&nbsp;"+"Comment"));
        }else  if(productEnglish.getCommentcount().equals("0")){

        }else {
            holder.commentcount.setText(Html.fromHtml(productEnglish.getCommentcount()+"&nbsp;"+"Comments"));
        }
        holder.bname.setText(Html.fromHtml(productEnglish.getBeneficiaryname()));
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
        holder.comment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.comment_layout.getVisibility()==View.VISIBLE){
                    holder.comment_layout.setVisibility(View.GONE);
                }else if(holder.comment_layout.getVisibility()==View.GONE){
                    holder.comment_layout.setVisibility(View.VISIBLE);

                }else {
                    holder.comment_layout.setVisibility(View.VISIBLE);
                }

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
    public void data(List<CategoryItemmodel> lists){
        this.productEnglishList.addAll(lists);
        notifyDataSetChanged();
    }
}
