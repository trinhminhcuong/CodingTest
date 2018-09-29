package com.example.cuong.sofusers.Asset;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cuong.sofusers.DataModels.UserInformation;

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

import static com.example.cuong.sofusers.Main2Activity.myDB;

public class AsyncGetUsers extends AsyncTask<String,Void,String> {
    private Context context;
    private List<UserInformation> users;
    private ListView listView;
    private ArrayAdapter<UserInformation> userInfomationAdapter;
    private List<String> markedUsers;


    public AsyncGetUsers(Context context, List<UserInformation> users, ListView listView, ArrayAdapter<UserInformation> userInfomationAdapter, List<String> markedUsers) {
        this.context = context;
        this.users = users;
        this.listView = listView;
        this.userInfomationAdapter = userInfomationAdapter;
        this.markedUsers = markedUsers;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

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
        users.clear();
        try {
            JSONObject jsonObject=new JSONObject(s);
            String items=jsonObject.getString("items");
            JSONArray jsonArray=new JSONArray(items);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonUser=jsonArray.getJSONObject(i);
                String userName=jsonUser.getString("display_name");
                String userAvatar=jsonUser.getString("profile_image");
                String reputation=jsonUser.getString("reputation");
                String userLocation="";
                if(jsonUser.has("location")==true){
                    userLocation=jsonUser.getString("location");
                }
                String userId=jsonUser.getString("user_id");
                users.add(new UserInformation(userName,userAvatar,reputation,userLocation,userId));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for(int i=0;i<users.size();i++){
            for(int j=0;j<markedUsers.size();j++){
                if(users.get(i).getUserId().equals(markedUsers.get(j))==true){
                    users.get(i).setiChecked(true);
                    Log.d("Marked",users.get(i).getUserName());
                }
            }
        }
        userInfomationAdapter.notifyDataSetChanged();
        listView.setAdapter(userInfomationAdapter);



    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}

