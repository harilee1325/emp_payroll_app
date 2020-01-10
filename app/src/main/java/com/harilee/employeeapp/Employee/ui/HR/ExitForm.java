package com.harilee.employeeapp.Employee.ui.HR;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.harilee.employeeapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExitForm extends Fragment {

    @BindView(R.id.to_date)
    DatePicker toDate;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.reason)
    EditText reason;
    @BindView(R.id.description)
    EditText description;
    @BindView(R.id.submit_vacation)
    Button submitVacation;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.exit_form, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.submit_vacation)
    public void onViewClicked() {
    }
}
