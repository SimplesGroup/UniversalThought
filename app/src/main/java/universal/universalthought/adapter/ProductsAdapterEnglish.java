package universal.universalthought.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import universal.universalthought.Detailpage;
import universal.universalthought.R;
import universal.universalthought.model.ProductEnglish;


public class ProductsAdapterEnglish extends RecyclerView.Adapter<ProductsAdapterEnglish.MyViewHolder> {

    private Context mContext;
    private List<ProductEnglish> productEnglishList;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView quantity;
        public ImageView thumbnail;
        Button overflow;

        public MyViewHolder(View view) {
            super(view);
         //   title = (TextView) view.findViewById(R.id.profile);
           // count = (TextView) view.findViewById(R.id.count);
            quantity = (TextView) view.findViewById(R.id.kg);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (Button) view.findViewById(R.id.overflow);
        }
    }


    public ProductsAdapterEnglish(Context mContext, List<ProductEnglish> productEnglishList) {
        this.mContext = mContext;
        this.productEnglishList = productEnglishList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        String splash = "fonts/LATO-MEDIUM.TTF";
////        final Typeface tf = Typeface.createFromAsset(mContext.getAssets(), splash);
        final ProductEnglish productEnglish = productEnglishList.get(position);
       // holder.title.setText(productEnglish.getPname());
       // holder.count.setText("Rs." + productEnglish.getPprice());
        holder.quantity.setText(productEnglish.getPquantity());
        // loading album cover using Glide library
        Glide.with(mContext).load(productEnglish.getPimage()).into(holder.thumbnail);

       /* holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });*/
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext,Detailpage.class);

               // i.putExtra("TITLE", productEnglish.getPname());

               // Log.e("TITLEEEE", productEnglish.getPname());
                mContext.startActivity(i);
            }
        });

       // holder.title.setTypeface(tf);
       // holder.count.setTypeface(tf);
       // holder.quantity.setTypeface(tf);
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    /*private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }
*/
    /**
     * Click listener for popup menu items
     */
 /*   class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }
*/
    @Override
    public int getItemCount() {
        return productEnglishList.size();
    }
}
