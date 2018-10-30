package universal.universalthought.adapter.StoriesPageAdapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import universal.universalthought.CustomVolleyRequest;
import universal.universalthought.Detailpage;
import universal.universalthought.Detailstorypage;
import universal.universalthought.R;
import universal.universalthought.Response.Comment;
import universal.universalthought.Response.Likeclass;
import universal.universalthought.model.CategoryItemmodel;

public class EmergenciesStoriesAdapter extends RecyclerView.Adapter<EmergenciesStoriesAdapter.MyViewHolder> {
    private Context mContext;
    private List<CategoryItemmodel> productEnglishList;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String MYUSERID= "myprofileid";
    public static final String USERNAME= "myprofilename";
    public static final String USERIMAGE= "myprofileimage";
    public static final String USERMAILID= "myprofileemail";
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView quantity,username,createdate,likecount,commentcount;
        public NetworkImageView thumbnail;
        public ImageView userimage;
        Button overflow;
        ProgressBar progressBar;
        TextView total_amount_textview;
        RelativeLayout pb;
        LinearLayout comment_layout;
        EditText comment_edit;
        ImageButton comment_post;
        ImageView like_button,comment_button,share_button;
        public MyViewHolder(View view) {
            super(view);
            quantity = (TextView) view.findViewById(R.id.title);
            username = (TextView) view.findViewById(R.id.name);
            createdate = (TextView) view.findViewById(R.id.date);
            likecount = (TextView) view.findViewById(R.id.alltab_likescount);
            commentcount = (TextView) view.findViewById(R.id.alltab_commentscount);
            thumbnail = (NetworkImageView) view.findViewById(R.id.thumbnail);
            userimage = (ImageView) view.findViewById(R.id.thum);
            comment_layout=(LinearLayout)view.findViewById(R.id.comment_layout);
            comment_edit=(EditText)view.findViewById(R.id.edit_comment);
            comment_post=(ImageButton)view.findViewById(R.id.button_post);
            like_button=(ImageView)view.findViewById(R.id.button_likes);
            comment_button=(ImageView)view.findViewById(R.id.button_comment);
            share_button=(ImageView)view.findViewById(R.id.button_share);

        }
    }
    public EmergenciesStoriesAdapter(Context mContext, List<CategoryItemmodel> productEnglishList) {
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

        sharedpreferences = mContext.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        final String userid=sharedpreferences.getString(MYUSERID,"");
        String username=sharedpreferences.getString(USERNAME,"");
        String userimage=sharedpreferences.getString(USERIMAGE,"");
        String useremail=sharedpreferences.getString(USERMAILID,"");


        final CategoryItemmodel productEnglish = productEnglishList.get(position);
        ImageLoader imageLoader= CustomVolleyRequest.getInstance(mContext).getImageLoader();
        Log.e("SIZE", productEnglish.getTitleoffundraising());
        holder.quantity.setText(Html.fromHtml(productEnglish.getTitleoffundraising()));
        holder.username.setText(Html.fromHtml(productEnglish.getName()));
        if(productEnglish.getLikecount().equals("1")){
            holder.likecount.setText(Html.fromHtml(productEnglish.getLikecount()+"&nbsp;"+"Like"));
        }else if(productEnglish.getLikecount().equals("0")){

        }else {
            holder.likecount.setText(Html.fromHtml(productEnglish.getLikecount()+"&nbsp;"+"Likes"));
        }
        if(productEnglish.getCommentcount().equals("1")){
            holder.commentcount.setText(Html.fromHtml(productEnglish.getCommentcount()+"&nbsp;"+"Comment"));
        }else if(productEnglish.getCommentcount().equals("0")){

        }else {
            holder.commentcount.setText(Html.fromHtml(productEnglish.getCommentcount()+"&nbsp;"+"Comments"));
        }
        if(productEnglish.getLiketype()==0){
            holder.like_button.setImageResource(R.mipmap.likeblack);
        }else if(productEnglish.getLiketype()==1) {
            holder.like_button.setImageResource(R.mipmap.heartfullred);
        }else {

        }
        holder.createdate.setText(Html.fromHtml(productEnglish.getDate()));
        holder.thumbnail.setImageUrl(productEnglish.getPhoto(),imageLoader);
        //      holder.userimage.setImageURI(productEnglish.getUimage(),imageLoader);
        Glide.with(mContext).load(productEnglish.getUimage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.userimage);
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext,Detailstorypage.class);

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
        holder.comment_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comment comment=new Comment();
                comment.UploadComment(userid,holder.comment_edit.getText().toString(),productEnglish.getId(),mContext);

                holder.comment_layout.setVisibility(View.GONE);
                int commentscount=Integer.parseInt(productEnglish.getCommentcount());
                int total=commentscount+1;
                if(total>1) {
                    holder.commentcount.setText(Html.fromHtml(total+"&nbsp;"+"Comments"));
                }else if(total==1){
                    holder.commentcount.setText(Html.fromHtml(total+"&nbsp;"+"Comment"));
                }
                else {

                }

            }
        });
        holder.share_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, productEnglish.getUrl());
                mContext.startActivity(sharingIntent);
            }
        });
        holder.like_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Likeclass likeclass=new Likeclass();
                String data=null;
                if(productEnglish.getLiketype()==0){
                    data  =     likeclass.StoryLike(mContext,userid,productEnglish.getId(),1);
                    holder.like_button.setImageResource(R.mipmap.heartfullred);
                    int val=Integer.parseInt(productEnglish.getLikecount());
                    int result=val+1;
                    if(result==1){
                        holder.likecount.setText(Html.fromHtml(result+"&nbsp;"+"Like"));
                    }else if(result==0){

                    }else {
                        holder.likecount.setText(Html.fromHtml(result+"&nbsp;"+"Likes"));
                    }

                }else if(productEnglish.getLiketype()==1){
                    data  =     likeclass.StoryLike(mContext,userid,productEnglish.getId(),0);
                    holder.like_button.setImageResource(R.mipmap.likeblack);
                    int val=Integer.parseInt(productEnglish.getLikecount());
                    int result=val-1;
                    if(result==1){
                        holder.likecount.setText(Html.fromHtml(result+"&nbsp;"+"Like"));
                    }else if(result==0){

                    }else {
                        holder.likecount.setText(Html.fromHtml(result+"&nbsp;"+"Likes"));
                    }
                }

            /*try {
                JSONArray array=new JSONArray(data.toString());
                for(int i=0;i<array.length();i++){
                    JSONObject object=array.getJSONObject(i);

                }
            }catch (JSONException e){

            }*/
            }
        });
       /* int    totalcost_value = Integer.parseInt(productEnglish.getRaisingamount());
        int   obtainedcost_value= Integer.parseInt(productEnglish.getAmountraised());
        int    percentage_value=(int) ((obtainedcost_value*100)/totalcost_value);
        holder.progressBar.setProgress(percentage_value);   // Main Progress
        //percentage_circularbar.setSecondaryProgress(50); // Secondary Progress
        holder.progressBar.setMax(100);
        holder.total_amount_textview.setText(productEnglish.getRaisingamount());*/
      /*  holder.overflow.setOnClickListener(new View.OnClickListener() {
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
