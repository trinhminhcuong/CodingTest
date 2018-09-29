package com.example.cuong.sofusers.Asset;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cuong.sofusers.DataModels.UserReputation;
import com.example.cuong.sofusers.Dialogs.I_UserReputation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AsyncGetReputation extends AsyncTask<String,Void,String> {

    private Context context;
    private I_UserReputation dialogUserreputation;

    public AsyncGetReputation(Context context, I_UserReputation dialogUserreputation) {
        this.context = context;
        this.dialogUserreputation = dialogUserreputation;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();}

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder stringBuilder=new StringBuilder();
        try {
            URL url=new URL(strings[0]);
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            InputStream inputStream=httpURLConnection.getInputStream();

            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);

            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

            String line="";

            while((line=bufferedReader.readLine())!=null){
                stringBuilder.append(line + "\n");
            }
            bufferedReader.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        List<UserReputation> userReputations=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(s);
            String jsitems=jsonObject.getString("items");
            JSONArray jsonArray=new JSONArray(jsitems);
            int test=jsonArray.length();
            for(int i=0;i<test;i++){
                JSONObject jsonUser=jsonArray.getJSONObject(i);
                String reputationType=jsonUser.getString("reputation_history_type");
                String reputationChange=jsonUser.getString("reputation_change");
                String createdAt=jsonUser.getString("creation_date");
                String postID=jsonUser.getString("post_id");
                userReputations.add(new UserReputation(reputationType,reputationChange,createdAt,postID));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        dialogUserreputation.setUserReputations(userReputations);

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
