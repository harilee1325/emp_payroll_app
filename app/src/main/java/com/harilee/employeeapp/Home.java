package com.harilee.employeeapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.harilee.employeeapp.Admin.Admin;
import com.harilee.employeeapp.Employee.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Home extends AppCompatActivity {

    @BindView(R.id.admin_card)
    CardView adminCard;
    @BindView(R.id.emp_card)
    CardView empCard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.admin_card, R.id.emp_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.admin_card:
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.login);
                dialog.setTitle("Login");
                Button login = dialog.findViewById(R.id.login_bt);
                EditText loginId = dialog.findViewById(R.id.login_id);
                dialog.show();
                login.setOnClickListener(v -> {
                    if (loginId.getText().toString().trim().equalsIgnoreCase("123")) {
                        dialog.cancel();
                        startActivity(new Intent(Home.this, Admin.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong credentials", Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            case R.id.emp_card:
                Dialog dialog2 = new Dialog(this);
                dialog2.setContentView(R.layout.login);
                dialog2.setTitle("Login");
                Button login2 = dialog2.findViewById(R.id.login_bt);
                EditText loginId2 = dialog2.findViewById(R.id.login_id);
                dialog2.show();
                login2.setOnClickListener(v -> {
                    verifyEmployee(loginId2.getText().toString().trim());
                    dialog2.cancel();
                });
                break;
        }
    }

    private void verifyEmployee(String trim) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ProgressDialog dialog = ProgressDialog.show(Home.this, "",
                "Authenticating employee. Please wait...", true);
        db.collection("employees")
                .whereEqualTo("emp_id", trim)
                .get()
                .addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {
                        if (task.getResult().size() > 0) {
                            dialog.cancel();
                            Utility.getUtilityInstance().setPreference(this, Config.E_ID, trim);
                            Toast.makeText(getApplicationContext(), "Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Home.this, MainActivity.class));
                        } else {
                            dialog.cancel();
                            Toast.makeText(getApplicationContext(), "Wrong credentials", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(), "Error fetching data", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
