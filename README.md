# FDTake-Android
Android library to take and crop photos similar to FDTake for iOS

This project is derived from 
https://github.com/ralfgehrer/AndroidCameraUtil and https://github.com/ralfgehrer/AndroidCameraUtil

The purpose is to provide a simple way of capturing a user photo from the camera or the gallery and cropping it. Inspired by the FDTake lib for iOS. 

Usage is as follows:

        cropped = new File(Environment.getExternalStorageDirectory(),"profile_image.png");
        new Photo(this)
                .asSquare()
                .withMaxSize(190,190)
                .output(Uri.fromFile(cropped))
                .takePhotoOrChooseFromLibrary();

