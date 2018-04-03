package universal.universalthought.activity.Gallery;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import universal.universalthought.R;

/**
 * Created by Kuppusamy on 3/29/2018.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {
    private ArrayList<String> mImagesList;
    private Context mContext;
    public ArrayList<String> selected_usersList=new ArrayList<>();
    public ImageAdapter(Context context,ArrayList<String>imagelist) {
        mContext = context;
        mImagesList=new ArrayList<>();
        this.mImagesList=imagelist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_multiphoto_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mImagesList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String imageUrl = mImagesList.get(position);
        Glide.with(mContext)
                .load("file://"+imageUrl)
                .centerCrop()
                .placeholder(R.mipmap.menulogo)
                .error(R.mipmap.menulogo)
                .into(holder.imageView);
        if(selected_usersList.contains(mImagesList.get(position)))
            //  holder.imageView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.list_item_selected_state));
            holder.imageView.setBackgroundColor(Color.parseColor("#3F51B5"));
        else
            holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
    public ImageView imageView;
    public MyViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imageView1);
    }
}
}