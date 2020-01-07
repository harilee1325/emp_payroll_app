package com.harilee.employeeapp.ui.Vacation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.harilee.employeeapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RequestFragment extends Fragment {


    @BindView(R.id.emp_name)
    TextView empName;
    @BindView(R.id.today_date)
    TextView todayDate;
    @BindView(R.id.request_vacation)
    Button requestVacation;
    @BindView(R.id.vacation_list)
    RecyclerView vacationList;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_request, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @OnClick(R.id.request_vacation)
    public void onViewClicked() {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment
                    , new RequestVacation()).addToBackStack(null).commitAllowingStateLoss();

    }
}