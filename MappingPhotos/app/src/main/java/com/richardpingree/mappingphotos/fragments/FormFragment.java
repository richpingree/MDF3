package com.richardpingree.mappingphotos.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.richardpingree.mappingphotos.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Richard Pingree MDF3 1503 Week 4 on 3/25/15.
 */
public class FormFragment extends Fragment {

    private static final int REQUEST_TAKE_PICTURE = 0x01001;

    public ImageView mImageView;
    public EditText text1, text2;
    public Button addBtn;
    Uri mImageUri;

    private FormListener mListener;

    public interface FormListener{

    }

    public FormFragment(){

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof FormListener){
            mListener = (FormListener) activity;
        }else{
            throw new IllegalArgumentException("Containing activity must implement FormListener interface");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_form, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mImageView = (ImageView)getView().findViewById(R.id.formImage);
        text1 = (EditText)getView().findViewById(R.id.edit1);
        text2 = (EditText)getView().findViewById(R.id.edit2);
        addBtn = (Button)getView().findViewById(R.id.button);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_TAKE_PICTURE && resultCode != Activity.RESULT_CANCELED){
            if(mImageUri != null){
                mImageView.setImageBitmap(BitmapFactory.decodeFile(mImageUri.getPath()));

                addToGallary(mImageUri);
            } else{
                mImageView.setImageBitmap((Bitmap)data.getParcelableExtra("data"));
            }

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_take_picture:
                Intent picIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mImageUri = getImageUri();
                if(mImageUri != null){
                    picIntent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
                }
                startActivityForResult(picIntent, REQUEST_TAKE_PICTURE);
        }
        return super.onOptionsItemSelected(item);

    }

    private Uri getImageUri(){
        String imageName = new SimpleDateFormat("MMddyyyy_HHmmss").format(new Date(System.currentTimeMillis()));

        File imageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File appDir = new File(imageDir, "Images");
        appDir.mkdirs();

        File image = new File(appDir, imageName + ".jpg");
        try{
            image.createNewFile();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

        return Uri.fromFile(image);
    }

    private void addToGallary(Uri imageUri){
        Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        scanIntent.setData(imageUri);
        getActivity().sendBroadcast(scanIntent);
    }
}
