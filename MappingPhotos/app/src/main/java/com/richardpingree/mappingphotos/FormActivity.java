package com.richardpingree.mappingphotos;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.richardpingree.mappingphotos.fragments.FormFragment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Richard Pingree MDF3 1503 Week 4 on 3/25/15.
 */
public class FormActivity extends Activity implements FormFragment.FormListener {

    private static final int REQUEST_PHOTO_TAKEN = 0x00001;

    ImageView mImageView;
    EditText text1, text2;
    Button addBtn;

    Uri mImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

//        FormFragment frag = new FormFragment();
//        getFragmentManager().beginTransaction().replace(R.id.container, frag).commit();
        mImageView = (ImageView)findViewById(R.id.formImage);
        text1 = (EditText)findViewById(R.id.edit1);
        text2 = (EditText)findViewById(R.id.edit2);
        addBtn = (Button)findViewById(R.id.button);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_PHOTO_TAKEN && resultCode != RESULT_CANCELED){
            if(mImageUri != null){
                mImageView.setImageBitmap(BitmapFactory.decodeFile(mImageUri.getPath()));

                addToGallery(mImageUri);
            }else{
                mImageView.setImageBitmap((Bitmap)data.getParcelableExtra("data"));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_take_picture){
            Intent picIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            mImageUri = getImageUri();
            if(mImageUri != null){
                picIntent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
            }
            startActivityForResult(picIntent, REQUEST_PHOTO_TAKEN);
        }
        return true;
    }

    private Uri getImageUri(){
        String photoName = new SimpleDateFormat("MMddyyyy_HHmmss").format(new Date(System.currentTimeMillis()));

        File photeDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File appDir = new File(photeDir, "App Photos");
        appDir.mkdirs();

        File image = new File(appDir, photoName + ".jpg");
        try{
            image.createNewFile();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return Uri.fromFile(image);
    }

    private void addToGallery(Uri imageUri){
        Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        scanIntent.setData(imageUri);
        sendBroadcast(scanIntent);
    }
}
