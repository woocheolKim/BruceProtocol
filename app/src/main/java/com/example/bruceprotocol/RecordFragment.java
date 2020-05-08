package com.example.bruceprotocol;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bruceprotocol.databinding.FragmentRecordBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecordFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentRecordBinding layout = DataBindingUtil.inflate(inflater, R.layout.fragment_record, container, false);

        return layout.getRoot();
    }
}
