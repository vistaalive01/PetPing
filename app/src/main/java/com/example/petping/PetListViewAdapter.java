package com.example.petping;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.List;

class PetListViewAdapter extends BaseAdapter {
    private Context context;
    private List<PetSearch> petSearchList;

    public PetListViewAdapter(Context context, List<PetSearch> petSearchList) {
        this.context = context;
        this.petSearchList = petSearchList;
    }

    @Override
    public int getCount() {
        return petSearchList.size();
    }

    @Override
    public Object getItem(int position) {
        return petSearchList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
        //return Long.parseLong(petSearchList.get(position).getID());
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.pet_listview_adapter, null);
        TextView textViewName, textViewSex, textViewAge, textViewBreed;
        ImageView imgView = (ImageView) view.findViewById(R.id.adapter_img);

        Glide.with(context)
                .load(petSearchList.get(position).getUrl())
                .into((ImageView) imgView);

        textViewName = view.findViewById(R.id.adapter_name);
        textViewSex = view.findViewById(R.id.adapter_sex);
        textViewAge = view.findViewById(R.id.adapter_age);
        textViewBreed = view.findViewById(R.id.adapter_breed);

        textViewName.setText(petSearchList.get(position).getName());
        textViewSex.setText(petSearchList.get(position).getSex());
        textViewAge.setText(petSearchList.get(position).getAge());
        textViewBreed.setText(petSearchList.get(position).getBreed());

        return view;
    }
}
