package universal.universalthought.activity.Gallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Kuppusamy on 3/29/2018.
 */

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {


    public static interface OnItemClickListener{
    public void onItemClick(View view, int position);
    public void onItemLongClick(View view, int position);
}
private OnItemClickListener mListener;
GestureDetector mGestureDetector;
public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, final OnItemClickListener listener){
mListener=listener;
mGestureDetector =new GestureDetector(new GestureDetector.SimpleOnGestureListener(){
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
View childview=recyclerView.findChildViewUnder(e.getX(),e.getY());
if(childview!=null&&mListener!=null){
mListener.onItemLongClick(childview,recyclerView.getChildPosition(childview));
}
    }
});


}
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
    View childview=rv.findChildViewUnder(e.getX(),e.getY());
    if(childview!=null&&mListener!=null&&mGestureDetector.onTouchEvent(e)){
        mListener.onItemClick(childview, rv.getChildPosition(childview));
    }
        return false;
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }
}
