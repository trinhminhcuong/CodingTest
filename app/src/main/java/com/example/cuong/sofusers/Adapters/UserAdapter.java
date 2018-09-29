package com.example.cuong.sofusers.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuong.sofusers.Asset.AsyncLoadImage;
import com.example.cuong.sofusers.DataModels.UserInformation;
import com.example.cuong.sofusers.R;



import java.util.List;

import static com.example.cuong.sofusers.Main2Activity.myDB;

public class UserAdapter  extends ArrayAdapter<UserInformation> {

    private Context context;
    private int resource;
    private List<UserInformation> objects;
    private CheckBox cbA01;


    public UserAdapter(@NonNull Context context, int resource, @NonNull List<UserInformation> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            convertView=layoutInflater.inflate(resource,null);
        }

        ImageView imageViewA01=convertView.findViewById(R.id.imageViewA011);
        TextView tvNameA01=convertView.findViewById(R.id.tvNameA011);
        TextView tvALA01=convertView.findViewById(R.id.tvALA011);
        TextView tvReputationA01=convertView.findViewById(R.id.tvReputationA011);
        cbA01=convertView.findViewById(R.id.cbA011);

        if(objects.get(position).isiChecked()==true){
            cbA01.setChecked(true);
        }
        else {
            cbA01.setChecked(false);
        }

        tvNameA01.setText(objects.get(position).getUserName());
        tvALA01.setText(objects.get(position).getLocation());
        tvReputationA01.setText(objects.get(position).getReputaion());

        new AsyncLoadImage(imageViewA01).execute(objects.get(position).getUserAvatar());



        cbA01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cbA01.isChecked()==true){
                    myDB.addUserId(objects.get(position).getUserId());
                    objects.get(position).setiChecked(true);
                }
                else {
                    myDB.deleteUserId(objects.get(position).getUserId());
                    objects.get(position).setiChecked(false);
                }
            }
        });
        return convertView;
    }
}
