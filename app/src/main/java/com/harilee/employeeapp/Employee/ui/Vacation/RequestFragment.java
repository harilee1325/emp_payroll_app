package com.harilee.employeeapp.Employee.ui.Vacation;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.gson.Gson;
import com.harilee.employeeapp.Config;
import com.harilee.employeeapp.Employee.EmployeeModel;
import com.harilee.employeeapp.Employee.VacationModel;
import com.harilee.employeeapp.R;
import com.harilee.employeeapp.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RequestFragment extends Fragment {


    @BindView(R.id.emp_name)
    TextView empName;
    @BindView(R.id.joining_date)
    TextView todayDate;
    @BindView(R.id.request_vacation)
    Button requestVacation;
    @BindView(R.id.vacation_list)
    RecyclerView vacationList;
    private View view;
    private ArrayList<EmployeeModel> empList = new ArrayList<>();
    private String TAG = "Employee";
    private ArrayList<VacationModel> vacationModels = new ArrayList<>();
    private VacationAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_request, container, false);
        ButterKnife.bind(this, view);
        adapter = new VacationAdapter(getContext(), vacationModels);
        vacationList.setAdapter(adapter);
        getData();
        return view;
    }

    private void getData() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ProgressDialog dialog = ProgressDialog.show(getContext(), "",
                "Loading. Please wait...", true);
        db.collection(Config.EMP_COLLECTION)
                .whereEqualTo(
                        Config.EMP_ID, Utility.getUtilityInstance().getPreference(getContext(), Config.E_ID)
                )
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        empList.clear();
                        Log.e(TAG, "openAddbookDialog: " + task.getResult().size());
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.e(TAG, document.getId() + " => " + document.getData().get("author"));
                            EmployeeModel employeeModel = new EmployeeModel();
                            employeeModel.setAccNumber(String.valueOf(document.getData().get(Config.ACC_NUM)));
                            employeeModel.setAddress(String.valueOf(document.getData().get(Config.ADDRESS)));
                            employeeModel.setBankName(String.valueOf(document.getData().get(Config.BANK)));
                            employeeModel.setCity(String.valueOf(document.getData().get(Config.CITY)));
                            employeeModel.setDob(String.valueOf(document.getData().get(Config.DOB)));
                            employeeModel.setDoj(String.valueOf(document.getData().get(Config.DOJ)));
                            employeeModel.setState(String.valueOf(document.getData().get(Config.STATE)));
                            employeeModel.setDesgination(String.valueOf(document.getData().get(Config.DESIGNATION)));
                            employeeModel.setlName(String.valueOf(document.getData().get(Config.LAST_NAME)));
                            employeeModel.setFatherName(String.valueOf(document.getData().get(Config.FATHER_NAME)));
                            employeeModel.setfName(String.valueOf(document.getData().get(Config.FIRST_NAME)));
                            employeeModel.setPincode(String.valueOf(document.getData().get(Config.PINCODE)));
                            empList.add(employeeModel);
                        }

                        Gson gson = new Gson();
                        EmployeeModel model = empList.get(0);
                        String empData = gson.toJson(model);
                        Utility.getUtilityInstance().setPreference(getContext(), Config.EMP_DATA, empData);
                        String emp = Utility.getUtilityInstance().getPreference(getContext(), Config.EMP_DATA);
                        Gson gson1 = new Gson();
                        EmployeeModel model1 = gson1.fromJson(emp, EmployeeModel.class);
                        empName.setText("Employee Name : " + model1.getfName() + " " + model1.getlName());
                        todayDate.setText("Joining Date : " + model1.getDoj());

                    } else {
                        Toast.makeText(getContext(), "Error getting details", Toast.LENGTH_SHORT).show();
                    }
                });

        db.collection(Config.VACATION_COLLECTION)
                .whereEqualTo(
                        Config.EMP_ID, Utility.getUtilityInstance().getPreference(getContext(), Config.E_ID)
                )
                .get()
                .addOnCompleteListener(task -> {
                    dialog.cancel();
                    if (task.isSuccessful()) {
                        dialog.cancel();
                        vacationModels.clear();
                        Log.e(TAG, "openAddbookDialog: " + task.getResult().size());
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.e(TAG, document.getId() + " => " + document.getData().get("author"));
                            VacationModel vacationModel = new VacationModel();
                            vacationModel.setFrom(String.valueOf(document.getData().get(Config.VACATION_FROM)));
                            vacationModel.setTo(String.valueOf(document.getData().get(Config.VACATION_TILL)));
                            vacationModel.setEmail(String.valueOf(document.getData().get(Config.EMAIL)));
                            vacationModel.setReason(String.valueOf(document.getData().get(Config.REASON)));
                            vacationModel.setHours(String.valueOf(document.getData().get(Config.HOURS)));
                            vacationModel.setDescription(String.valueOf(document.getData().get(Config.DESCRIPTION)));
                            vacationModels.add(vacationModel);
                        }
                            adapter.notifyDataSetChanged();
                    } else {
                        dialog.cancel();
                        Toast.makeText(getContext(), "Error getting details", Toast.LENGTH_SHORT).show();
                    }
                });


    }


    @OnClick(R.id.request_vacation)
    public void onViewClicked() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment
                , new RequestVacation()).addToBackStack(null).commitAllowingStateLoss();

    }

    public class VacationAdapter extends RecyclerView.Adapter<VacationAdapter.ViewHolder> {

        private Context context;
        private ArrayList<VacationModel> vacationModels;

        public VacationAdapter(Context context, ArrayList<VacationModel> vacationModels) {

            this.context = context;
            this.vacationModels = vacationModels;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.vacation_card, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            holder.description.setText("Description :\n"+vacationModels.get(position).getDescription());
            holder.reason.setText("Reason :\n"+vacationModels.get(position).getReason());
            holder.fromDate.setText("From Date : "+vacationModels.get(position).getFrom());
            holder.toDate.setText("To Date : "+vacationModels.get(position).getTo());
        }

        @Override
        public int getItemCount() {
            return vacationModels.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.from_date)
            TextView fromDate;
            @BindView(R.id.to_date)
            TextView toDate;
            @BindView(R.id.reason)
            TextView reason;
            @BindView(R.id.description)
            TextView description;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}