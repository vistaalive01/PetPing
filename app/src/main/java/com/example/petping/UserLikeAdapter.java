package com.example.petping;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class UserLikeAdapter extends BaseAdapter {
    private ArrayList<PetSearch> petLike;
    private Context context;

    public UserLikeAdapter (Context context, List<PetSearch> petLike){
        this.context = context;
        this.petLike = (ArrayList<PetSearch>) petLike;
    }

    @Override
    public int getCount() {
        return petLike.size();
    }

    @Override
    public Object getItem(int i) {
        return petLike.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View temp = View.inflate(context, R.layout.pet_listview_adapter, null);
        TextView textViewName, textViewAge, textViewBreed;
        ImageView imgView = (ImageView) view.findViewById(R.id.adapter_img);

        Glide.with(context)
                .load(petLike.get(i).getUrl())
                .into((ImageView) imgView);

        textViewName = view.findViewById(R.id.adapter_name);
        textViewAge = view.findViewById(R.id.adapter_age);
        textViewBreed = view.findViewById(R.id.adapter_breed);

        String sexMale = "ผู้";
        ImageView ImageViewSex = view.findViewById(R.id.adapter_sex);
        if(petLike.get(i).getSex().equals(sexMale)){
            ImageViewSex.setImageResource(R.drawable.sex_male);
        }
        else {
            ImageViewSex.setImageResource(R.drawable.sex_female);
        }

        textViewName.setText(petLike.get(i).getName());
        textViewAge.setText(petLike.get(i).getAge());
        textViewBreed.setText(petLike.get(i).getBreed());

        return temp;
    }
}