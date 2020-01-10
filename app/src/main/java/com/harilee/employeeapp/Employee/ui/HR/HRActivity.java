package com.harilee.employeeapp.Employee.ui.HR;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.harilee.employeeapp.Config;
import com.harilee.employeeapp.Employee.EmployeeModel;
import com.harilee.employeeapp.R;
import com.harilee.employeeapp.Utility;

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
    @BindView(R.id.joining_date)
    TextView joiningDate;
    @BindView(R.id.dob)
    TextView dob;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hr, container, false);
        ButterKnife.bind(this, view);
        setView();
        return view;
    }

    private void setView() {

        String emp = Utility.getUtilityInstance().getPreference(getContext(), Config.EMP_DATA);
        Gson gson1 = new Gson();
        EmployeeModel model1 = gson1.fromJson(emp, EmployeeModel.class);
        name.setText("Employee Name : " + model1.getfName() + " " + model1.getlName());
        fathersName.setText("Father's Name : " + model1.getFatherName());
        designation.setText("Designation : " + model1.getDesgination());
        bankName.setText("Bank Name : " + model1.getBankName());
        accountNum.setText("Account Number : " + model1.getAccNumber());
        add1.setText("Address : " + model1.getAddress());
        city.setText("City : " + model1.getCity());
        state.setText("State : " + model1.getState());
        pincode.setText("Pincode : " + model1.getPincode());
        joiningDate.setText("Joining Date : "+model1.getDoj());
        dob.setText("DOB : "+model1.getDob());


    }


    @OnClick(R.id.request_exit_form)
    public void onViewClicked() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment
                , new ExitForm()).addToBackStack(null).commitAllowingStateLoss();

    }
}