package com.example.cuong.sofusers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;

import com.example.cuong.sofusers.Adapters.UserAdapter;
import com.example.cuong.sofusers.Asset.AsyncGetReputation;
import com.example.cuong.sofusers.Asset.AsyncGetUsers;
import com.example.cuong.sofusers.Asset.MyDB;
import com.example.cuong.sofusers.DataModels.UserInformation;
import com.example.cuong.sofusers.Dialogs.I_UserReputation;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private ListView lv021;
    private RadioButton rbAll201, rbMarked201;
    private ArrayAdapter<UserInformation> userAdapter;
    private List<UserInformation> users;
    private List<String> markedUser;

    public static MyDB myDB;

    private String api="https://api.stackexchange.com/2.2/users?page=1&pagesize=30&site=stackoverflow";
    private String url1="https://api.stackexchange.com/2.2/users/";
    private String url2="/reputation-history?page=1&pagesize=30&site=stackoverflow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
        myDB=new MyDB(Main2Activity.this);
        markedUser=new ArrayList<>();
        myDB.getUserIdList(markedUser);
        loadUsers();

        lv021.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                I_UserReputation userReputation=new I_UserReputation(Main2Activity.this);
                new AsyncGetReputation(Main2Activity.this,userReputation).execute(url1+users.get(i).getUserId()+url2);
                userReputation.show();
            }
        });

        rbAll201.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rbAll201.isChecked()==true) {
                    loadUsers();
                }
            }
        });

        rbMarked201.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rbMarked201.isChecked()==true){
                    for(int i=users.size()-1;i>=0;i--){
                        if(users.get(i).isiChecked()==false){
                            userAdapter.remove(users.get(i));
                        }
                    }
                    userAdapter.notifyDataSetChanged();
                }
            }
        });
    }
    public void loadUsers(){
        new AsyncGetUsers(Main2Activity.this,users,lv021,userAdapter,markedUser).execute(api);
    }

    public void init(){
        lv021=findViewById(R.id.lv021);
        rbAll201=findViewById(R.id.rbAll021);
        rbMarked201=findViewById(R.id.rbMarked021);
        users=new ArrayList<>();
        userAdapter=new UserAdapter(Main2Activity.this,R.layout.adapter_i_user_object,users);

    }


}
