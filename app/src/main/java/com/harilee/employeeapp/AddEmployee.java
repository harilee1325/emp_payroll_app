package com.harilee.employeeapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddEmployee extends AppCompatActivity {

    @BindView(R.id.f_name)
    EditText fName;
    @BindView(R.id.l_name)
    EditText lName;
    @BindView(R.id.dob)
    DatePicker dob;
    @BindView(R.id.designation)
    EditText designation;
    @BindView(R.id.father_name)
    EditText fatherName;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.city)
    EditText city;
    @BindView(R.id.state)
    EditText state;
    @BindView(R.id.pincode)
    EditText pincode;
    @BindView(R.id.bank_name)
    EditText bankName;
    @BindView(R.id.acc_number)
    EditText accNumber;
    @BindView(R.id.joining_date)
    DatePicker joiningDate;
    @BindView(R.id.add_emp)
    Button addEmp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_employee);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.add_emp)
    public void onViewClicked() {
    }
}
