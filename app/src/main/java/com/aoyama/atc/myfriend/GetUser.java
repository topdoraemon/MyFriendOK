package com.aoyama.atc.myfriend;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Created by Administrator on 20/11/2559.
 */

public class GetUser extends AsyncTask<Void,Void,String>{
    //Explicit
    private static final String strJSON = "http://swiftcodingthai.com/20nov/get_user_naitop.php";
    private Context context;

    public GetUser(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... voids) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strJSON).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();


        } catch (Exception e) {
            Log.d("20NovV3", "e doIn ==>" + e.toString());
            return null;
        }


    }
}//MainClass
