package com.aoyama.atc.myfriend;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.jibble.simpleftp.SimpleFTP;

import java.io.File;

public class SignUpActivity extends AppCompatActivity {

    //Explicit
    private EditText nameEditText,userEditText, passwordEditText;
    private ImageView avataImageView,takePhotoImageView;
    private Button button;
    private String nameString,userString,passwordString,
    imagePathString,imageNameString;
    private Uri uri;
    private boolean aBoolean = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //blind widget ผูกตัวแปร
        nameEditText = (EditText) findViewById(R.id.editText);
        userEditText = (EditText) findViewById(R.id.editText2);
        passwordEditText = (EditText) findViewById(R.id.editText3);
        avataImageView = (ImageView) findViewById(R.id.imageView);
        takePhotoImageView = (ImageView) findViewById(R.id.imageView2);
        button = (Button) findViewById(R.id.button3);

        //button controller
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get value from Edit Text รับค่ามาแปลงเป็น String แล้วใส่ในตัวแปร
                nameString = nameEditText.getText().toString().trim();
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                //Check Space
                if (nameString.equals("") || userString.equals("") || passwordString.equals("")) {
                    //Have Space
                    MyAlert myAlert = new MyAlert(SignUpActivity.this,R.drawable.doremon48,
                            "ข้อมูลไม่สมบูรณ์","กรุณากรอกข้อมูลสำคัญ !!!");
                    myAlert.myDialog();
                } else if (aBoolean) {
                    //NO Image
                    MyAlert myAlert = new MyAlert(SignUpActivity.this,R.drawable.bird48,
                            "ไม่มีรูปภาพ","กรุณาถ่ายรูป หรือ เลือกรูปภาพ !!!");
                    myAlert.myDialog();
                } else {
                    //Have Image
                    imagePathString = myFindPathImage();
                    Log.d("20NovV1", "imagePath==>" + imagePathString);
                    imageNameString = imagePathString.substring(imagePathString.lastIndexOf("/"));
                    Log.d("20NovV1", "imageName==>" + imageNameString);

                    uploadImageToServer();


                }

            }// Onclick
        });

        //TakePhoto controller

        takePhotoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            } //onclick takePhotoImage
        });


        //avata Controller
        avataImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*"); //เปิดโปรแกรมที่มีรูป
                startActivityForResult(Intent.createChooser(intent, "โปรดเลือก App รูปภาพ"), 1);

            }
        });


    } //Main Method

// Class Method
    private void uploadImageToServer() {
        //Change Policy
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build(); /// สิทธิ์ทั้งหมด
        StrictMode.setThreadPolicy(threadPolicy);

        try {

            SimpleFTP simpleFTP = new SimpleFTP();
            simpleFTP.connect("ftp.swiftcodingthai.com",21,"20nov@swiftcodingthai.com","Abc12345");
            simpleFTP.bin();
            simpleFTP.cwd("Image");  //ชื่อโฟลเดอร์ ใน FTP
            simpleFTP.stor(new File(imagePathString));
            simpleFTP.disconnect();

            Toast.makeText(SignUpActivity.this, "Upload Success", Toast.LENGTH_SHORT).show();


        } catch (Exception e) {
            Log.d("20NovV1", "e SimpleFTP ==>" + e.toString());
        }

    }//Upload Image

    // Class เรียก Path รูปภพา
    private String myFindPathImage() {

        String result = null;
        String[] strings = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, strings, null, null, null);

        if (cursor!=null) {
            cursor.moveToFirst();
            int i = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            result = cursor.getString(i);

        } else {
            result = uri.getPath();
        }//if

        return result;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //ถ้า ส่งค่ามา สถานะเป็น 0 และมีค่ามา
        if ((requestCode == 0) && (resultCode == RESULT_OK)) {

            //Take Photo OK
            Log.d("19NovV1", "Take Photo OK");
            uri = data.getData();
            aBoolean = false;
            try {

                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                avataImageView.setImageBitmap(bitmap);

            } catch (Exception e) {
                Log.d("19NovV1", "e==>" + e.toString());
            }//try
            //Setup Image Take to ImageView
            //ถ้ามีเคสสำเร็จ และมีการเลือก
        } else if ((requestCode==1)&&(resultCode==RESULT_OK)) {
            uri = data.getData();
            aBoolean = false;
            try {

                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                avataImageView.setImageBitmap(bitmap);

            } catch (Exception e) {
                Log.d("20NovV1", "e==>" + e.toString());
            }//try




        } //if else





    } //onActivityResult
} // Main Class
