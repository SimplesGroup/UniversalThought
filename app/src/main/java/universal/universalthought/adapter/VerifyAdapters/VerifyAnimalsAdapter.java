package universal.universalthought.adapter.VerifyAdapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import universal.universalthought.R;
import universal.universalthought.activity.VerifyQuestionActivity;
import universal.universalthought.model.CategoryItemmodel;

public class VerifyAnimalsAdapter extends RecyclerView.Adapter<VerifyAnimalsAdapter.MyViewHolder> {
    private Context mContext;
    private List<CategoryItemmodel> productEnglishList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.album_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String splash = "fonts/LATO-MEDIUM.TTF";

        final CategoryItemmodel productEnglish = productEnglishList.get(position);
        ImageLoader imageLoader= CustomVolleyRequest.getInstance(mContext).getImageLoader();
        Log.e("SIZE", productEnglish.getTitleoffundraising());

        holder.quantity.setText(productEnglish.getTitleoffundraising());

        holder.thumbnail.setImageUrl(productEnglish.getPhoto(),imageLoader);
        int    totalcost_value = Integer.parseInt(productEnglish.getRaisingamount());
        int   obtainedcost_value= Integer.parseInt(productEnglish.getAmountraised());
        int    percentage_value=(int) ((obtainedcost_value*100)/totalcost_value);
        holder.progressBar.setProgress(percentage_value);
        // Main Progress
        //percentage_circularbar.setSecondaryProgress(50); // Secondary Progress
        holder.progressBar.setMax(100);
        holder.total_amount_textview.setText(productEnglish.getRaisingamount());
//        holder.overflow.setText("Verify");

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext,VerifyQuestionActivity.class);
                i.putExtra("ID", productEnglish.getId());
                mContext.startActivity(i);
            }
        });
      /*  holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialogs dialogs=new Dialogs();
                dialogs.dialog(mContext);


            }
        });*/
    }
    public VerifyAnimalsAdapter(Context mContext, List<CategoryItemmodel> productEnglishList) {
        this.mContext = mContext;
        this.productEnglishList = productEnglishList;

    }
    @Override
    public int getItemCount() {
        return productEnglishList.size();
    }
    public void data(List<CategoryItemmodel> lists){
        this.productEnglishList.addAll(lists);
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView quantity;
        public NetworkImageView thumbnail;
        Button overflow;
        ProgressBar progressBar;
        TextView total_amount_textview;
        public MyViewHolder(View itemView) {
            super(itemView);
            quantity = (TextView) itemView.findViewById(R.id.title);
            thumbnail = (NetworkImageView) itemView.findViewById(R.id.thumbnail);
         //   overflow = (Button) itemView.findViewById(R.id.overflow);
            progressBar=(ProgressBar)itemView.findViewById(R.id.circularProgressBar);
            total_amount_textview=(TextView)itemView.findViewById(R.id.totalamount);
        }
    }
}
