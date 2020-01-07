package com.harilee.employeeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Admin extends AppCompatActivity {

    @BindView(R.id.add_emp)
    Button addEmp;
    @BindView(R.id.vacation_request)
    Button vacationRequest;
    @BindView(R.id.exit_list)
    Button exitList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.add_emp, R.id.vacation_request, R.id.exit_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_emp:
                startActivity(new Intent(Admin.this, AddEmployee.class));
                break;
            case R.id.vacation_request:
                break;
            case R.id.exit_list:
                break;
        }
    }
}
