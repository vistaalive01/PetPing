package com.example.petping;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AdoptionQAFragment extends Fragment {
    private ViewFlipper flipper;
    private ImageButton btnOne, btnTwo;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_adoption_qa_process, null);
        flipper = view.findViewById(R.id.flipper_qa_process);

        flipper = view.findViewById(R.id.flipper_qa_process);
        btnOne = view.findViewById(R.id.qa_one_btn);
        btnTwo = view.findViewById(R.id.qa_two_btn);

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.setDisplayedChild(flipper.indexOfChild(view.findViewById(R.id.qa_two)));
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.setDisplayedChild(flipper.indexOfChild(view.findViewById(R.id.qa_one)));
            }
        });
        return view;
    }
}
