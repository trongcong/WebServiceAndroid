package com.dev4u.ntc.webservice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dev4u.ntc.webservice.models.Student;
import com.dev4u.ntc.webservice.webservice.APIService;
import com.dev4u.ntc.webservice.webservice.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnGetStudent;
    private Button btnGetStudents;
    private TextView tvResult;

    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btnGetStudent = (Button) findViewById(R.id.btnGetStudent);
        btnGetStudents = (Button) findViewById(R.id.btnGetStudents);
        tvResult = (TextView) findViewById(R.id.tvResult);
        mAPIService = ApiUtils.getAPIService();

        btnGetStudent.setOnClickListener(this);
        btnGetStudents.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGetStudent:
                getStudentById();
                break;
            case R.id.btnGetStudents:
                getAllStudents();
                break;
        }
    }

    private void getAllStudents() {
        mAPIService.getStudents().enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                try {
                    String result = "";
                    for (int i = 0; i < response.body().size(); i++) {
                        result += "ID: " + response.body().get(i).getId()
                                + "\nName: " + response.body().get(i).getName()
                                + "\nAge: " + response.body().get(i).getAge()
                                + "\nClass: " + response.body().get(i).getNclass() + "\n\n";
                    }
                    tvResult.setText(result);
                } catch (Exception e) {
                    Log.d("onResponse", "Error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }

    private void getStudentById() {
        mAPIService.getStudent("1").enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                try {
                    tvResult.setText("ID: " + response.body().get(0).getId()
                            + "\nName: " + response.body().get(0).getName()
                            + "\nAge: " + response.body().get(0).getAge()
                            + "\nClass: " + response.body().get(0).getNclass());
                } catch (Exception e) {
                    Log.d("onResponse", "Error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }
}
