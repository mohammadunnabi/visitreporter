package com.prangroup.VisitReporter.Camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.prangroup.VisitReporter.Model.ImageModel;
import com.prangroup.VisitReporter.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CameraActivity extends AppCompatActivity {
    private Camera mCamera;
    private CameraPreview mCameraPreview;
    FrameLayout preview;
    File pictureFile;
    private boolean safeToTakePicture = true;
    private boolean safeToResetPicture = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        setTitle("Take Image");
        mCamera = getCameraInstance();
        mCameraPreview = new CameraPreview(this, mCamera);
        preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mCameraPreview);
        ImageView captureButton = (ImageView) findViewById(R.id.button_capture);
        Button button_reset = (Button) findViewById(R.id.button_reset);
        Button button_save = (Button) findViewById(R.id.button_save);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (safeToTakePicture) {
                    mCamera.takePicture(null, null, mPicture);
                    safeToTakePicture = false;
                    safeToResetPicture = true;

                }
            }
        });
        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (safeToResetPicture) {
                    safeToResetPicture = false;
                    safeToTakePicture = true;
                    mCameraPreview.previewCamera();

                }
            }
        });
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pictureFile != null) {
                    Bundle conData = new Bundle();
                    Intent intent = new Intent();
                    String path = pictureFile.getAbsolutePath();
                    if (!path.equalsIgnoreCase("")) {
                        new ImageModel(getContentResolver()).saveImage(path);
                        conData.putString("imageName", path);
                        intent.putExtras(conData);
                        setResult(RESULT_OK, intent);
                    } else {
                        setResult(RESULT_CANCELED, intent);
                    }
                    finish();
                }
            }
        });
    }


    private Camera getCameraInstance() {
        Camera camera = null;
        try {
            camera = Camera.open();
        } catch (Exception e) {
        }
        return camera;
    }

    byte[] resizeImage(byte[] input) {
        Bitmap original = BitmapFactory.decodeByteArray(input, 0, input.length);
        Bitmap resized = Bitmap.createScaledBitmap(original, 300, 250, true);
        ByteArrayOutputStream blob = new ByteArrayOutputStream();
        resized.compress(Bitmap.CompressFormat.JPEG, 100, blob);
        return blob.toByteArray();
    }

    Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            pictureFile = getOutputMediaFile();
            if (pictureFile == null) {
                return;
            }
            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(resizeImage(data));
                fos.close();
            } catch (FileNotFoundException e) {

            } catch (IOException e) {
            }
        }
    };

    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "rfl_image");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        String timeStamp = System.currentTimeMillis() + "";
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator  + "img_" + timeStamp + ".jpg");
        return mediaFile;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // getMenuInflater().inflate(R.menu.add_back, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
           /* case R.id.back:
                finish();
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
