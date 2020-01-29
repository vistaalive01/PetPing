package com.example.petping;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MenuFragment extends Fragment {
    private Button btnEditUser, btnLikeList, btnPetStory, btnHistory, btnFAQ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_menu, container, false);

        btnEditUser = view.findViewById(R.id.btn_user);
        btnLikeList = view.findViewById(R.id.btn_like);
        btnPetStory = view.findViewById(R.id.btn_story);
        btnHistory = view.findViewById(R.id.btn_history);
        btnFAQ = view.findViewById(R.id.btn_faq);

        btnEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnLikeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(getId(), new UserLikeFragment());
                ft.commit();
            }
        });

        btnPetStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(getId(), new UserHistoryFragment());
                ft.commit();
            }
        });

        btnFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }


//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        MenuInflater inflater = getLayoutInflater();
//        inflater.inflate(R.layout.fragment_menu,menu);
//        return true;
//
//    }

    //    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
////        final View temp = inflater.inflate(R.layout.fragment_search, null);
//        final View view = inflater.inflate(R.layout.fragment_menu, null);
//        btnUser = view.findViewById(R.id.btn_user);
//        btnHistory = view.findViewById(R.id.btn_history);
//
////        btnUser.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                FragmentTransaction ft = getFragmentManager().beginTransaction();
////                ft.replace(getId(), new UserFragment());
////                ft.commit();
////            }
////        });
//
//        btnHistory.setOnClickListener(listener);
//
//
//
//
//
//        return view;
//    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        final View view = inflater.inflate(R.layout.fragment_menu_history,container,false);
//
//        btnHistory = view.findViewById(R.id.btn_history);
//        btnHistory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(getId(), new UserHistoryFragment());
//                ft.commit();
//            }
//        });
//
//        return view;
//    }

}