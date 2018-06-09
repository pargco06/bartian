package com.parg.bartian.ui.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parg.bartian.R;

import java.util.ArrayList;

public class FareDialogFragment extends DialogFragment {

    public static final String FARE_LIST_ITEMS = "FareListItems";

    public static FareDialogFragment newInstance(ArrayList<String> fareListItems) {
        FareDialogFragment fareDialogFragment = new FareDialogFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(FARE_LIST_ITEMS, fareListItems);
        fareDialogFragment.setArguments(args);
        return fareDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fare_dialog, container, false);
        ListView listView = v.findViewById(R.id.fareList);
        ArrayAdapter fareListAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, getArguments().getStringArrayList(FARE_LIST_ITEMS));
        listView.setAdapter(fareListAdapter);
        return v;
    }
}
