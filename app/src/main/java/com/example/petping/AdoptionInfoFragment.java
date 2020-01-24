package com.example.petping;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AdoptionInfoFragment extends Fragment {
    private EditText eNID, eDOB, eAddr, eJob, eSalary;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adoption_info_process, null);
        eNID = view.findViewById(R.id.edit_info_nid);
        eDOB = view.findViewById(R.id.edit_info_dob);
        eAddr = view.findViewById(R.id.edit_info_addr);
        eSalary = view.findViewById(R.id.edit_info_salary);
        return view;
    }
}
