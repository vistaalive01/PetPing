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

public class PetStatusAdapter extends BaseAdapter {
    private Context context;
    private List<PetSearch> petList;
    public PetStatusAdapter(Context context, ArrayList<PetSearch> petList) {
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
        View view = View.inflate(context, R.layout.pet_listview_status, null);
        ImageView imgView = (ImageView) view.findViewById(R.id.status_img);

        Glide.with(context)
                .load(petList.get(position).getUrl())
                .into((ImageView) imgView);

        TextView textViewName, textViewBreed, textViewStatus;
        textViewName = view.findViewById(R.id.status_name);
        textViewBreed = view.findViewById(R.id.status_breed);
        textViewStatus = view.findViewById(R.id.status_status);

        textViewName.setText(petList.get(position).getName());
        textViewBreed.setText(petList.get(position).getBreed());
        textViewStatus.setText(petList.get(position).getStatus());

        return view;
    }
}
