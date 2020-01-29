package com.example.petping;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UserHisLikeAdapter extends BaseAdapter {
    private Context context;
    ArrayList<PetSearch> historyList;
    public UserHisLikeAdapter(Context context, ArrayList<PetSearch> historyList) {
        this.context = context;
        this.historyList = historyList;
    }

    @Override
    public int getCount() {
        return historyList.size();
    }

    @Override
    public Object getItem(int position) {
        return historyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.user_hist_like_adapter, null);
        TextView textViewName, textViewAge, textViewBreed;
        ImageView imgView = (ImageView) view.findViewById(R.id.adapter_img);

        Glide.with(context)
                .load(historyList.get(position).getUrl())
                .into((ImageView) imgView);

        textViewName = view.findViewById(R.id.adapter_name);
        textViewAge = view.findViewById(R.id.adapter_age);
        textViewBreed = view.findViewById(R.id.adapter_breed);

        String sexMale = "ผู้";
        ImageView ImageViewSex = view.findViewById(R.id.adapter_sex);
        if(historyList.get(position).getSex().equals(sexMale)){
            ImageViewSex.setImageResource(R.drawable.sex_male);
        }
        else {
            ImageViewSex.setImageResource(R.drawable.sex_female);
        }

        textViewName.setText(historyList.get(position).getName());
        textViewAge.setText(historyList.get(position).getAge());
        textViewBreed.setText(historyList.get(position).getBreed());

        return view;
    }
}
