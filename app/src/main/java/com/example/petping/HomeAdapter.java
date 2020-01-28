package com.example.petping;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
        TextView textViewName, textViewBreed, textViewStatus;
        textViewName = view.findViewById(R.id.home_name);
        textViewBreed = view.findViewById(R.id.home_breed);
        textViewStatus = view.findViewById(R.id.home_status);

        textViewName.setText(petList.get(position).getName());
        textViewBreed.setText(petList.get(position).getBreed());
        textViewStatus.setText(petList.get(position).getStatus());

        return view;
    }
}
