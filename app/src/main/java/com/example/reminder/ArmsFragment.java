package com.example.reminder;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


public class ArmsFragment extends Fragment {
     RecyclerView recyclerView;
    RecyclerViewAdapterSpecific recyclerViewAdapterSpecific;
    ScheduleDatabaseHelper scheduleDatabaseHelper;
    SQLiteDatabase db;
    ArrayList<Schedule> list,list2;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        // Inflate the layout for this fragment
        view=(View) inflater.inflate(R.layout.fragment_arms, container, false);
        list2=new ArrayList<>();

           /* CheckBox checkBox=view.findViewById(R.id.checkBox);
            checkBox.s*/

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // if(ScheduleDatabaseHelper.getAll(db)!=null)
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerViewArms);
        scheduleDatabaseHelper=new ScheduleDatabaseHelper(getContext());
        db=scheduleDatabaseHelper.getWritableDatabase();
       /* list=ScheduleDatabaseHelper.getAll(db);
        if(!list.isEmpty()){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getMuscle()=="Arms"){
                list2.add(list.get(i));
            }
        }}
        Toast.makeText(getContext(),"ONRESUME",Toast.LENGTH_SHORT).show();
        if(list2!=null)
        recyclerViewAdapterSpecific=new RecyclerViewAdapterSpecific(getContext(),list2);*/
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerViewAdapterSpecific);


    }



}