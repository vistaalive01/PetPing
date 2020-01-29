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

public class HomeAdapter extends BaseAdapter {
    private Context context;
    private List<PetSearch> petList;
    public HomeAdapter(Context context, ArrayList<PetSearch> petList) {
        this.context = context;
        this.petList = petList;
    }

    @Override
    public int getCount() {
        return petList.size();
    }

    @Override
    public Object getItem(int position) {
        return petList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.home_adapter, null);
        ImageView imgView = (ImageView) view.findViewById(R.id.home_img);
        TextView textViewName, textViewBreed, textViewAge;
        Glide.with(context)
                .load(petList.get(position).getUrl())
                .into((ImageView) imgView);


        textViewName = view.findViewById(R.id.home_name);
        textViewAge = view.findViewById(R.id.home_age);
        textViewBreed = view.findViewById(R.id.home_breed);

        textViewName.setText(petList.get(position).getName());
        textViewAge.setText(petList.get(position).getAge());
        textViewBreed.setText(petList.get(position).getBreed());
        return view;
    }
}
