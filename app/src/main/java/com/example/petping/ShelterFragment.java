package com.example.petping;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class ShelterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shelter, null);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(getId(), new MapsActivity());
        ft.commit();
        Toast.makeText(getContext(),"อยู่ระหว่างการดำเนินการ",Toast.LENGTH_LONG).show();
        return view;
    }
}
