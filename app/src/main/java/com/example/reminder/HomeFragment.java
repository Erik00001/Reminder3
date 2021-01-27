package com.example.reminder;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
   static RecyclerView recyclerView;
   static RecyclerViewAdapterGeneral recyclerViewAdapterGeneral;
    ScheduleDatabaseHelper scheduleDatabaseHelper;
   static SQLiteDatabase db;
    static ArrayList<Schedule> list;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



            // Inflate the layout for this fragment
        view=(View) inflater.inflate(R.layout.fragment_home, container, false);


           /* CheckBox checkBox=view.findViewById(R.id.checkBox);
            checkBox.s*/

        return view;
        }

    @Override
    public void onResume() {
        super.onResume();

       // if(ScheduleDatabaseHelper.getAll(db)!=null)
        recyclerView=(RecyclerView)view.findViewById(R.id.schedule_recycler);
        scheduleDatabaseHelper=new ScheduleDatabaseHelper(getContext());
        db=scheduleDatabaseHelper.getWritableDatabase();
        list=ScheduleDatabaseHelper.getAll(db);
        Toast.makeText(getContext(),"ONRESUME",Toast.LENGTH_SHORT).show();
        recyclerViewAdapterGeneral=new RecyclerViewAdapterGeneral(getContext(),list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerViewAdapterGeneral);


    }



   /* public  void refresh(){
     this.onResume();
    }*/


}
