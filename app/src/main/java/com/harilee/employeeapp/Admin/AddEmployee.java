package com.harilee.employeeapp.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.harilee.employeeapp.Config;
import com.harilee.employeeapp.R;

import java.util.HashMap;
import java.util.Map;

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
    @BindView(R.id.emp_id)
    EditText empId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_employee);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.add_emp)
    public void onViewClicked() {
        try {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            ProgressDialog dialog = ProgressDialog.show(AddEmployee.this, "",
                    "Adding employee. Please wait...", true);
            Map<String, Object> employee = new HashMap<>();
            employee.put(Config.FIRST_NAME, fName.getText().toString().trim());
            employee.put(Config.LAST_NAME, lName.getText().toString().trim());
            employee.put(Config.DESIGNATION, designation.getText().toString().trim());
            int day = joiningDate.getDayOfMonth();
            int month = joiningDate.getMonth() + 1;
            int year = joiningDate.getYear();
            String dateOfJoin = day + "-" + month + "-" + year;
            employee.put(Config.DOJ, dateOfJoin);
            employee.put(Config.FATHER_NAME, fatherName.getText().toString().trim());
            employee.put(Config.ADDRESS, address.getText().toString().trim());
            employee.put(Config.CITY, city.getText().toString().trim());
            employee.put(Config.STATE, state.getText().toString().trim());
            employee.put(Config.PINCODE, pincode.getText().toString().trim());
            employee.put(Config.EMP_ID, empId.getText().toString().trim());

            int day1 = dob.getDayOfMonth();
            int month1 = dob.getMonth() + 1;
            int year1 = dob.getYear();
            String dobStr = day1 + "-" + month1 + "-" + year1;
            employee.put(Config.DOB, dobStr);
            employee.put(Config.BANK, bankName.getText().toString().trim());
            employee.put(Config.ACC_NUM, accNumber.getText().toString().trim());
            db.collection(Config.EMP_COLLECTION)
                    .whereEqualTo(Config.EMP_ID, empId.getText().toString().trim())
                    .get()
                    .addOnCompleteListener(task -> {

                        if (task.isSuccessful()) {
                            if (task.getResult().size() > 0) {
                                Toast.makeText(getApplicationContext(), "employee already added", Toast.LENGTH_SHORT).show();
                            } else {
                                db.collection(Config.EMP_COLLECTION)
                                        .add(employee)
                                        .addOnSuccessListener(documentReference -> {
                                            dialog.cancel();
                                            Toast.makeText(getApplicationContext(), "employee added successfully", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(AddEmployee.this, Admin.class));
                                        })
                                        .addOnFailureListener(e -> {
                                            dialog.cancel();
                                            Toast.makeText(getApplicationContext(), "Error adding document", Toast.LENGTH_SHORT).show();

                                        });
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Error fetching data", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Some error happened please check all fields are entered", Toast.LENGTH_SHORT).show();

        }
        // Add a new document with a generated ID


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddEmployee.this, Admin.class));
    }
}
