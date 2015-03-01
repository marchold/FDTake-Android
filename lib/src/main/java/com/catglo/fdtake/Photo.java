package com.catglo.fdtake;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.afollestad.materialdialogs.MaterialDialogCompat;
import com.catglo.fdtake.crop.CropImageActivity;

/**
 * Public interface to take photo library.
 */
public class Photo {
    private final Activity activity;
    public static int RESULT_PICK_IMAGE = 1001;
    private final Intent intent;
    String takePhotoButtonLabel = "Take Photo";
    String message = "Get Photo How?";
    String pickPhotoButtonLabel = "Pick From Gallery";

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


    /**
     *
     * @param message
     * @return
     */
    public Photo message(String message) {
        this.message = message;
        return this;
    }

    /**
     *
     * @param takePhotoButtonLabel
     * @return
     */
    public Photo takePhotoButtonLabel(String takePhotoButtonLabel) {
        this.takePhotoButtonLabel = takePhotoButtonLabel;
        return this;
    }


    /**
     *
     * @param pickPhotoButtonLabel
     * @return
     */
    public Photo pickPhotoButtonLabel(String pickPhotoButtonLabel) {
        this.pickPhotoButtonLabel = pickPhotoButtonLabel;
        return this;
    }

    /**
     * Launch the camera to capture a photo then crop the image
     */
    public void takePhoto() {
        intent.putExtra(CropImageActivity.EXTRA_USE_GALLERY, false);
        activity.startActivityForResult(intent, RESULT_PICK_IMAGE);
    }

    /**
     * Launch the gallery to get a photo then crop the image
     */
    public void chooseFromLibrary() {
        intent.putExtra(CropImageActivity.EXTRA_USE_GALLERY, true);
        activity.startActivityForResult(intent, RESULT_PICK_IMAGE);
    }

    /**
     * Shows a dialog to pick from gallery or take a photo with the camera
     */
    public void takePhotoOrChooseFromLibrary() {
        DialogFragment fragment = new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                return new MaterialDialogCompat
                        .Builder(activity)
                        .setTitle(message)
                        .setPositiveButton(takePhotoButtonLabel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                takePhoto();
                            }
                        })
                        .setNeutralButton(pickPhotoButtonLabel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                chooseFromLibrary();
                            }
                        })
                        .create();
            }
        };
        fragment.show(activity.getFragmentManager(), "takePhotoOrChooseFromLibraryDialog");
    }
}