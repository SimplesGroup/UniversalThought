package universal.universalthought.activity.Gallery;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import universal.universalthought.R;
import universal.universalthought.fundraiser.FundraiserDetails;

/**
 * Created by Kuppusamy on 3/29/2018.
 */

public class Customgallery extends AppCompatActivity {
    RecyclerView recyclerView_imageslist;
    private ImageAdapter imageAdapter;
    ArrayList<String>imageUrls=new ArrayList<>();
    ArrayList<String>multiselect_list=new ArrayList<>();
    boolean isMultiSelect = false;
    ActionMode mActionMode;
    Menu context_menu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customgallery);
        if(ContextCompat.checkSelfPermission(Customgallery.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Customgallery.this,new String []{Manifest.permission.READ_EXTERNAL_STORAGE},0);
        }else {
            populateImagesFromGallery();
        }
        recyclerView_imageslist=(RecyclerView)findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),4);
        recyclerView_imageslist.setLayoutManager(layoutManager);
        recyclerView_imageslist.setItemAnimator(new DefaultItemAnimator());
        recyclerView_imageslist.addItemDecoration(new ItemOffsetDecoration(this, R.dimen.item_offset));
        imageAdapter=new ImageAdapter(this,imageUrls);
        recyclerView_imageslist.setAdapter(imageAdapter);
        recyclerView_imageslist.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView_imageslist, new RecyclerItemClickListener.OnItemClickListener() {
    @Override
    public void onItemClick(View view, int position) {
        if (isMultiSelect)
            multi_select(position);
        else{
            if (mActionMode == null) {
                mActionMode = startActionMode(mActionModeCallback);
            }
            multi_select(position);
        }
            Log.e("CHEK","cccc");
       // Toast.makeText(getApplicationContext(), "Details Page", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int position) {
       if (!isMultiSelect) {
            multiselect_list = new ArrayList<String>();
            isMultiSelect = true;

            if (mActionMode == null) {
                mActionMode = startActionMode(mActionModeCallback);
            }
        }

        multi_select(position);

    }
}));
    }
private void multi_select(int position){
    if (mActionMode != null) {
        if(multiselect_list.contains(imageUrls.get(position))){
            multiselect_list.remove(imageUrls.get(position));
        }else {
            multiselect_list.add(imageUrls.get(position));
        }

        if(multiselect_list.size()>0){
mActionMode.setTitle("" + multiselect_list.size());
        }else {
            mActionMode.setTitle("");
        }
        refreshAdapter();
    }
}
    public void refreshAdapter()
    {
        imageAdapter.selected_usersList=multiselect_list;
        //imageAdapter.usersList=user_list;

        imageAdapter.notifyDataSetChanged();
    }
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateImagesFromGallery();
            } else {
                ActivityCompat.requestPermissions(Customgallery.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        0);
            }
        }
    }
private ActionMode.Callback mActionModeCallback=new ActionMode.Callback() {
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater=mode.getMenuInflater();
        inflater.inflate(R.menu.menu_multi_select,menu);
        context_menu = menu;
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                Log.e("SSSS","select");
                System.out.println("goi"+multiselect_list);
                Intent fund=new Intent(getApplicationContext(), FundraiserDetails.class);
                fund.putExtra("GALLERY",multiselect_list);
                startActivity(fund);
                finish();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        mActionMode = null;
        isMultiSelect = false;
        multiselect_list = new ArrayList<String>();
        refreshAdapter();
    }
};
    private void populateImagesFromGallery() {
       /* if (!mayRequestGalleryImages()) {
            return;
        }*/

        imageUrls = loadPhotosFromNativeGallery();
        //initializeRecyclerView(imageUrls);
    }
private ArrayList<String>loadPhotosFromNativeGallery(){
String [] columns={MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
String orderby= MediaStore.Images.Media.DATE_TAKEN;
    Cursor cursor = managedQuery(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
            null, orderby + " DESC");
for(int i=0;i<cursor.getCount();i++){
cursor.moveToPosition(i);
int columnindex=cursor.getColumnIndex(MediaStore.Images.Media.DATA);
imageUrls.add(cursor.getString(columnindex));
    System.out.println("=====> Array path => "+imageUrls.get(i));
}
return imageUrls;
}
}
