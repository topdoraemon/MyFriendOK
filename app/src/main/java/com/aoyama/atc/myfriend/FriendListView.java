package com.aoyama.atc.myfriend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

public class FriendListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list_view);

        ListView listView = (ListView) findViewById(R.id.livFriend);

        try {

            GetUser getUser = new GetUser(FriendListView.this);
            getUser.execute();

            String strJSoN = getUser.get();
            Log.d("21novV1", "JSoN ==> " + strJSoN);

            JSONArray jsonArray = new JSONArray(strJSoN);

            String[] nameStrings = new String[jsonArray.length()];
            String[] iconStrings = new String[jsonArray.length()];

            for (int i=0;i<jsonArray.length();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameStrings[i] = jsonObject.getString("Name");
                iconStrings[i] = jsonObject.getString("Image");
            }

            MyAdapter myAdapter = new MyAdapter(FriendListView.this, nameStrings, iconStrings);
            listView.setAdapter(myAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }   // Main Method

}   // Main Class