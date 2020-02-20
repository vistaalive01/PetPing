package com.example.petping;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ManagePetInfoShelterAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<PetSearch> petList;
    public ManagePetInfoShelterAdapter(Context context, ArrayList<PetSearch> petList) {
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
        View view = View.inflate(context, R.layout.manage_pet_info_adapter, null);
        TextView textName, textType, textBreed;
        textName = view.findViewById(R.id.name);
        textType = view.findViewById(R.id.type);
        textBreed = view.findViewById(R.id.breed);

        textName.setText(petList.get(position).getName());
        textType.setText(petList.get(position).getType());
        textBreed.setText(petList.get(position).getBreed());
        return view;
    }
}
