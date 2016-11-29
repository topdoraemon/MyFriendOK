package com.aoyama.atc.myfriend;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    // Explicit
    private Button signInButton, signUpButton; //ประกาศตัวแปร
    private EditText userEditText, passwordEditText;
    private String userString,passwordString;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //blind widget

        signInButton = (Button) findViewById(R.id.button);
        signUpButton = (Button) findViewById(R.id.button2);
        userEditText = (EditText) findViewById(R.id.editText4);
        passwordEditText = (EditText) findViewById(R.id.editText5);

        //SignIn Controller
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get Value
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                //check space
                if ((userString.length()==0)||(passwordString.length()==0)) {
                    MyAlert myAlert = new MyAlert(MainActivity.this,
                            R.drawable.kon48, "ข้อมูลว่าง", "กรุณากรอก Username และ Password");
                    myAlert.myDialog();

                } else {
                    //authen
                    chekUserPassword();
                }


            }//On Click
        });



        //signup control
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SignUpActivity.class));
            }
        });



        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }// Main Method

    private void chekUserPassword() {
        String strResult = null, strTruePassword = null;
        boolean bolStatus = true;


        try {

            GetUser getUser = new GetUser(MainActivity.this);
            getUser.execute();
            strResult = getUser.get();
            Log.d("20NovV3", "strResult ==>" + strResult);

            JSONArray jsonArray = new JSONArray(strResult);


            for (int i=0;i<jsonArray.length();i++ ) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (userString.equals(jsonObject.getString("User"))) {
                    bolStatus = false;
                    strTruePassword = jsonObject.getString("Password");
                }



            }//for
            if (bolStatus) {
                MyAlert myAlert = new MyAlert(MainActivity.this,
                        R.drawable.rat48, "User False", "No this User in my Database");
                myAlert.myDialog();

            } else if (passwordString.equals(strTruePassword)) {
                startActivity(new Intent(MainActivity.this, FriendListView.class));


            } else {
                MyAlert myAlert = new MyAlert(MainActivity.this,
                        R.drawable.rat48, "Password False", "Please Typ Again");
                myAlert.myDialog();

            }

        } catch (Exception e) {
            Log.d("20NovV3","e CheckUser==>" + e.toString());
        }
    }



} //Main Class

