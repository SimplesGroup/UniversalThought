package universal.universalthought.fundraiser;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import universal.universalthought.R;
import universal.universalthought.activity.Gallery.Customgallery;

/**
 * Created by Sandhiya on 12/12/2017.
 */

public class FundraiserDetails extends AppCompatActivity {
    EditText fundraisername;
    Button save,previous,upload;
    ImageView image;
    public Bitmap mBitmap;
    Uri picUri;
    String name;
    Button one,two,three;
    LinearLayout linearLayout1;
    private Uri fileUri;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;

    private static final String IMAGE_DIRECTORY_NAME = "Unversal Thought";
    String mCurrentPhotoPath,camera_image_convertedstring;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fundraiser_details);
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        fundraisername = (EditText)findViewById(R.id.edt_fundname);
        image = (ImageView)findViewById(R.id.fund_image);
        save = (Button)findViewById(R.id.button_save);
        previous = (Button)findViewById(R.id.button_previous);
        upload = (Button)findViewById(R.id.button_upload);
        one = (Button)findViewById(R.id.one_button);
        two = (Button)findViewById(R.id.two_button);
        three = (Button)findViewById(R.id.three_button);
linearLayout1=(LinearLayout)findViewById(R.id.fundraiser_layout);

          name = b.getString("activity");
        Log.e("ACTIVITY",name);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent i = new Intent(FundraiserDetails.this, OrganizationDetails.class);
                startActivity(i);*/
                validation();
                String name = fundraisername.getText().toString();
                String tit = image.getDrawable().toString();
if(!name.equals("")&&tit.equals("")) {
    if (name.equals("OrganizationDetails")) {
        Intent i = new Intent(FundraiserDetails.this, OrganizationDetails.class);
        startActivity(i);
    } else {
        Intent i = new Intent(FundraiserDetails.this, OtherDetails.class);
        startActivity(i);
    }
}
else
{
    Toast.makeText(FundraiserDetails.this, "Enter all fields ", Toast.LENGTH_SHORT).show();

}
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FundraiserDetails.this,BasicInformation.class);
                startActivity(i);
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.equals("OrganizationDetails"))
                {
                    Intent i = new Intent(FundraiserDetails.this, OrganizationDetails.class);
                    startActivity(i);
                }
                else
                {
                    Intent i = new Intent(FundraiserDetails.this, OtherDetails.class);
                    startActivity(i);
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(FundraiserDetails.this,BasicInformation.class);
                startActivity(i);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setVisibility(View.VISIBLE);
                LayoutInflater layoutInflater = (LayoutInflater) FundraiserDetails.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.imageselectionlayout,null);



                //instantiate popup window
       //  final PopupWindow popupWindow = new PopupWindow(customView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

              //  popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);
                final PopupWindow  window = new PopupWindow(customView, 450, 310, true);

                window.setBackgroundDrawable(new ColorDrawable(Color.BLUE));
                window.setOutsideTouchable(true);
                window.showAtLocation(customView, Gravity.CENTER, 40, 60);
                ImageButton camera=(ImageButton)customView.findViewById(R.id.camera_imagebutton);
                ImageButton gallery=(ImageButton)customView.findViewById(R.id.gallery_imagebutton);
                camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       window.dismiss();
                        if(ActivityCompat.checkSelfPermission(FundraiserDetails.this, android.Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){

                            ActivityCompat.requestPermissions(FundraiserDetails.this,
                                    new String[]{android.Manifest.permission
                                            .CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE},
                                    200);
                        }else {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            //fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

                           // intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

                            // start the image capture Intent

                            File photoFile = null;
                            try {
                                photoFile = createImageFile();
                            } catch (IOException ex) {
                                // Error occurred while creating the File

                            }
                            // Continue only if the File was successfully created
                            if (photoFile != null) {
                                Uri photoURI = FileProvider.getUriForFile(FundraiserDetails.this,
                                        "com.example.android.fileprovider",
                                        photoFile);
                               intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                                startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
                            }
                        }

                    }
                });



                gallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent gallery=new Intent(getApplicationContext(), Customgallery.class);
                        startActivity(gallery);
                    }
                });
                //close the popup window on button click


                //startActivityForResult(getPickImageChooserIntent(), 200);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // successfully captured the image
                // display it in image view
               // Bundle extras = data.getExtras();
                //Log.e("DATA","hi"+extras.toString());
               /* Bitmap imageBitmap = (Bitmap) extras.get("data");
                image.setImageBitmap(imageBitmap);*/
               // previewCapturedImage();
                setPic();
            }
        }

    }
    private void setPic() {
        // Get the dimensions of the View
        int targetW = image.getWidth();
        int targetH = image.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        image.setImageBitmap(bitmap);
        Uri uri= Uri.parse(mCurrentPhotoPath);
        try{
            Bitmap bm=BitmapFactory.decodeFile(uri.getPath());
            ByteArrayOutputStream bao=new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG,50,bao);
            byte[] imagebytes=bao.toByteArray();
            camera_image_convertedstring= Base64.encodeToString(imagebytes,Base64.DEFAULT);
            Log.e("ENCODE", camera_image_convertedstring);
        }catch (Exception e){

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(FundraiserDetails.this,
                        "com.example.android.fileprovider",
                        photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
            }
        }
    }



    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_DCIM);
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        /*File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
*/
        File image;

        image = new File(storageDir + File.separator
                + "IMG_" + timeStamp + ".jpg");

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        Log.e("IMAGE",mCurrentPhotoPath.toString());
      //  galleryAddPic();
        return image;
    }







    @Override
    public void onBackPressed() {
        Intent i = new Intent(FundraiserDetails.this,BasicInformation.class);
        startActivity(i);
           // super.onBackPressed();

    }



    public void validation() {
        Log.d("SignupEnglish", "SignupEnglish");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        save.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();



        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        save.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        // Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        save.setEnabled(true);
    }
    public boolean validate() {
        boolean valid = true;

        String name = fundraisername.getText().toString();
        String tit = image.getDrawable().toString();



        if (name.isEmpty() || name.length() < 3) {
            fundraisername.setError("Fundraiser name can't be blank.");
            valid = false;
        } else {
            fundraisername.setError(null);
        }

       /* if (tit.isEmpty() || tit.length() < 3) {
            image.setError("This field can't be empty.");
            valid = false;
        } else {
            image.setError(null);

        }*/

        return valid;
    }
}
