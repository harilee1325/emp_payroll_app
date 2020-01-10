package com.harilee.employeeapp.Employee.ui.Vacation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.FirebaseFirestore;
import com.harilee.employeeapp.Config;
import com.harilee.employeeapp.R;
import com.harilee.employeeapp.Utility;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RequestVacation extends Fragment {

    @BindView(R.id.from_date)
    DatePicker fromDate;
    @BindView(R.id.to_date)
    DatePicker toDate;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.hour_count)
    EditText hourCount;
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
        view = inflater.inflate(R.layout.request_vacation, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.submit_vacation)
    public void onViewClicked() {

        try {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            ProgressDialog dialog = ProgressDialog.show(getContext(), "",
                    "Applying Leave. Please wait...", true);
            Map<String, Object> vacation = new HashMap<>();
            int day = fromDate.getDayOfMonth();
            int month = fromDate.getMonth() + 1;
            int year = fromDate.getYear();
            String fromDate = day + "-" + month + "-" + year;
            int day1 = toDate.getDayOfMonth();
            int month1 = toDate.getMonth() + 1;
            int year1 = toDate.getYear();
            String toDate = day1 + "-" + month1 + "-" + year1;
            vacation.put(Config.VACATION_FROM, fromDate);
            vacation.put(Config.VACATION_TILL, toDate);
            vacation.put(Config.EMAIL, email.getText().toString().trim());
            vacation.put(Config.REASON, reason.getText().toString().trim());
            vacation.put(Config.DESCRIPTION, description.getText().toString().trim());
            vacation.put(Config.HOURS, hourCount.getText().toString().trim());
            vacation.put(Config.EMP_ID, Utility.getUtilityInstance().getPreference(getContext(), Config.E_ID));

            db.collection(Config.VACATION_COLLECTION)
                    .add(vacation)
                    .addOnSuccessListener(documentReference -> {
                        dialog.cancel();
                        Toast.makeText(getActivity(), "vacation added successfully, please await confirmation", Toast.LENGTH_SHORT).show();
                        getActivity().getSupportFragmentManager().popBackStackImmediate();
                    })
                    .addOnFailureListener(e -> {
                        dialog.cancel();
                        Toast.makeText(getActivity(), "Error adding document", Toast.LENGTH_SHORT).show();

                    });
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Some error happened please check all fields are entered", Toast.LENGTH_SHORT).show();

        }
    }
}
