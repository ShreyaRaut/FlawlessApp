package com.example.beau;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class nightroutine extends Fragment {

    Button prod_fw,prod_ag,prod_mo,inst1,inst2,inst3;

    public nightroutine() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_nightroutine, container, false);
        prod_fw=(Button) view.findViewById(R.id.prodfw);
        prod_ag=(Button) view.findViewById(R.id.prodag);
        prod_mo=(Button) view.findViewById(R.id.prodm);
        inst1=(Button) view.findViewById(R.id.inst1);
        inst2=(Button) view.findViewById(R.id.inst2);
        inst3=(Button) view.findViewById(R.id.inst3);


        prod_fw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getActivity(),CombFwActivity.class);
                startActivity(i);
            }
        });

        prod_ag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getActivity(),ProdAgActivity.class);
                startActivity(i);
            }
        });

        prod_mo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getActivity(),ProdMoActivity.class);
                startActivity(i);
            }
        });

        inst1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getActivity(),InstFwActivity.class);
                startActivity(i);
            }
        });

        inst2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getActivity(),InstAgActivity.class);
                startActivity(i);
            }
        });

        inst3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getActivity(),InstMoActivity.class);
                startActivity(i);
            }
        });



        return view;
    }

}
