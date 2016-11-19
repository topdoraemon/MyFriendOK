package com.aoyama.atc.myfriend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SignUpActivity extends AppCompatActivity {

    //Explicit
    private EditText nameEditText,userEditText, passwordEditText;
    private ImageView avataImageView,takePhotoImageView;
    private Button button;
    private String nameString,userString,passwordString;


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


                } //if

            }// Onclick
        });

        //TakePhoto controller
        takePhotoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            } //onclick takePhotoImage
        });

    } //Main Method

} // Main Class
