package com.harilee.employeeapp.ui.HR;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.harilee.employeeapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HRActivity extends Fragment {


    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.designation)
    TextView designation;
    @BindView(R.id.fathers_name)
    TextView fathersName;
    @BindView(R.id.add_1)
    TextView add1;
    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.state)
    TextView state;
    @BindView(R.id.pincode)
    TextView pincode;
    @BindView(R.id.bank_name)
    TextView bankName;
    @BindView(R.id.account_num)
    TextView accountNum;
    @BindView(R.id.request_exit_form)
    Button requestExitForm;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hr, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @OnClick(R.id.request_exit_form)
    public void onViewClicked() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment
                , new ExitForm()).addToBackStack(null).commitAllowingStateLoss();

    }
}