package com.example.cuong.sofusers.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cuong.sofusers.DataModels.UserReputation;
import com.example.cuong.sofusers.R;

import java.util.List;

public class ReputationAdapter extends ArrayAdapter<UserReputation> {

    private Context context;
    private int resource;
    private List<UserReputation> objects;

    public ReputationAdapter(@NonNull Context context, int resource, @NonNull List<UserReputation> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            convertView=layoutInflater.inflate(resource,null);
        }

        TextView tvUserReputationA021=convertView.findViewById(R.id.tvUserReputationA021);

        tvUserReputationA021.setText(objects.get(position).getReputationType()+" - "+objects.get(position).getReputationChange()
                +" - "+objects.get(position).getCreatedAt()+" - "+objects.get(position).getPostId());

        return convertView;
    }
}
