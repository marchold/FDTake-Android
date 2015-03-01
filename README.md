# FDTake-Android
Android library to take and crop photos similar to FDTake for iOS

This project is derived from 
https://github.com/ralfgehrer/AndroidCameraUtil and https://github.com/ralfgehrer/AndroidCameraUtil

The purpose is to provide a simple way of capturing a user photo from the camera or the gallery and cropping it. 

Usage is as follows:

        cropped = new File(Environment.getExternalStorageDirectory(),"profile_image.png");
        new Photo(this)
                .asSquare()
                .withMaxSize(190,190)
                .output(Uri.fromFile(cropped))
                .takePhotoOrChooseFromLibrary();

And then to ge the results you use onActivityResult               
                
        protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        super.onActivityResult(requestCode,resultCode,result);
        try {
            if (requestCode == Photo.RESULT_PICK_IMAGE) {
                Bitmap bmp = BitmapHelper.readBitmap(this, Uri.fromFile(cropped));
        

