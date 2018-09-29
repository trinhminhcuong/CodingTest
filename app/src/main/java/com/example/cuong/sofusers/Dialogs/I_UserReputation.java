package com.example.cuong.sofusers.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cuong.sofusers.Adapters.ReputationAdapter;
import com.example.cuong.sofusers.Asset.AsyncGetReputation;
import com.example.cuong.sofusers.DataModels.UserReputation;
import com.example.cuong.sofusers.R;

import java.util.List;

public class I_UserReputation extends Dialog {

    private Context context;
    private ListView lvD011;
    private ArrayAdapter<UserReputation> reputationAdapter;


    public I_UserReputation(@NonNull Context context) {
        super(context);
        this.context=context;
        this.setContentView(R.layout.dialog_i_reputation);
        lvD011=this.findViewById(R.id.lvD011);
    }

    public void setUserReputations(List<UserReputation> userReputations){
        reputationAdapter=new ReputationAdapter(this.context,R.layout.adapter_ii_user_reputation,userReputations);
        lvD011.setAdapter(reputationAdapter);
    }
}
