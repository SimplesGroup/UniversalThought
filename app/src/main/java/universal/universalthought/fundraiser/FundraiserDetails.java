package universal.universalthought.fundraiser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import universal.universalthought.R;

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
                 startActivityForResult(getPickImageChooserIntent(), 200);
            }
        });
    }


    public Intent getPickImageChooserIntent() {

        // Determine Uri of camera image to save.
        Uri outputFileUri = getCaptureImageOutputUri();

        List<Intent> allIntents = new ArrayList<>();
        PackageManager packageManager = getPackageManager();

        // colle ct all camera intents
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (outputFileUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            }
            allIntents.add(intent);
        }

        // collect all gallery intents
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
        for (ResolveInfo res : listGallery) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            allIntents.add(intent);
        }

        // the main intent is the last in the list (fucking android) so pickup the useless one
        Intent mainIntent = allIntents.get(allIntents.size() - 1);
        for (Intent intent : allIntents) {
            if (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                mainIntent = intent;
                break;
            }
        }
        allIntents.remove(mainIntent);

        // Create a chooser from the main intent
        Intent chooserIntent = Intent.createChooser(mainIntent, "Select source");

        // Add all other intents
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));

        return chooserIntent;
    }

    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new File(getImage.getPath(), "profile.png"));
        }
        return outputFileUri;
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(FundraiserDetails.this,BasicInformation.class);
        startActivity(i);
           // super.onBackPressed();

    }
    public Uri getPickImageResultUri(Intent data) {
        boolean isCamera = true;
        if (data != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }


        return isCamera ? getCaptureImageOutputUri() : data.getData();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Bitmap bitmap;
        if (resultCode == Activity.RESULT_OK) {



            if (getPickImageResultUri(data) != null) {
                picUri = getPickImageResultUri(data);

                try {
                    mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), picUri);
                    mBitmap = rotateImageIfRequired(mBitmap, picUri);
                    mBitmap = getResizedBitmap(mBitmap, 500);

                    ImageView croppedImageView = (ImageView) findViewById(R.id.fund_image);
                    croppedImageView.setImageBitmap(mBitmap);
                    image.setImageBitmap(mBitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else {


                bitmap = (Bitmap) data.getExtras().get("data");

                mBitmap = bitmap;
                ImageView croppedImageView = (ImageView) findViewById(R.id.fund_image);
                if (croppedImageView != null) {
                    croppedImageView.setImageBitmap(mBitmap);
                }

                image.setImageBitmap(mBitmap);

            }

        }

    }

    private static Bitmap rotateImageIfRequired(Bitmap img, Uri selectedImage) throws IOException {

        ExifInterface ei = new ExifInterface(selectedImage.getPath());
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateImage(img, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateImage(img, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateImage(img, 270);
            default:
                return img;
        }
    }
    private static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 0) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
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
