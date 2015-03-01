package com.catglo.fdtake;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import com.afollestad.materialdialogs.MaterialDialog;
import com.catglo.fdtake.crop.CropImageActivity;

/**
 * Created by goblets on 2/27/15.
 */
public class Photo {
    private final Activity activity;
    public static int RESULT_PICK_IMAGE = 1001;
    private final Intent intent;

    static interface Extra {
        String ASPECT_X = "aspect_x";
        String ASPECT_Y = "aspect_y";
        String MAX_X = "max_x";
        String MAX_Y = "max_y";
        String ERROR = "error";
    }


    public Photo(Activity activity) {
        this.activity = activity;
        this.intent = new Intent(activity, CropImageActivity.class);
    }

    public Photo asSquare() {
        intent.putExtra(Extra.ASPECT_X, 1);
        intent.putExtra(Extra.ASPECT_Y, 1);
        return this;
    }

    public Photo withMaxSize(int width, int height) {
        intent.putExtra(Extra.MAX_X, width);
        intent.putExtra(Extra.MAX_Y, height);
        return this;
    }

    /**
     * Set output URI where the cropped image will be saved
     *
     * @param output Output image URI
     */
    public Photo output(Uri output) {
        intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
        return this;
    }

    public void takePhotoOrChooseFromLibrary(){
        new MaterialDialog.Builder(activity)
                .positiveText("Take Photo")
                .negativeText("Pick From Gallery")
                .forceStacking(true)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        //  startCameraIntent();
                        intent.putExtra(CropImageActivity.EXTRA_USE_GALLERY, false);
                        activity.startActivityForResult(intent, RESULT_PICK_IMAGE);
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {

                        intent.putExtra(CropImageActivity.EXTRA_USE_GALLERY, true);
                        activity.startActivityForResult(intent, RESULT_PICK_IMAGE);

                        //   Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        //           android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        //   startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
                    }
                })
                .show();
    }




}
//takePhotoOrChooseFromLibrary