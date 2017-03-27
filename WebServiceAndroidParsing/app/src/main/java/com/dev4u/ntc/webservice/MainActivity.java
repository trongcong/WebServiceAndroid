package com.dev4u.ntc.webservice;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dev4u.ntc.webservice.models.Student;
import com.dev4u.ntc.webservice.webservice.APIService;
import com.dev4u.ntc.webservice.webservice.ApiUtils;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnGetStudent;
    private Button btnGetStudents;
    private TextView tvResult;

    private APIService mAPIService;
    private Button btnInsertStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btnGetStudent = (Button) findViewById(R.id.btnGetStudent);
        btnGetStudents = (Button) findViewById(R.id.btnGetStudents);
        btnInsertStudent = (Button) findViewById(R.id.btnInsertStudent);
        tvResult = (TextView) findViewById(R.id.tvResult);
        mAPIService = ApiUtils.getAPIService();

        btnGetStudent.setOnClickListener(this);
        btnGetStudents.setOnClickListener(this);
        btnInsertStudent.setOnClickListener(this);
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
            case R.id.btnInsertStudent:
                displayInsertDialog();
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
                    Log.e("onResponse", "Error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.e("onFailure", t.toString());
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
                    Log.e("onResponse", "Error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.e("onFailure", t.toString());
            }
        });
    }

    private void displayInsertDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layout_custom_dialog, null);
        final EditText edStudentName, edStudentAge, edStudentClass;
        edStudentName = (EditText) alertLayout.findViewById(R.id.edName);
        edStudentAge = (EditText) alertLayout.findViewById(R.id.edAge);
        edStudentClass = (EditText) alertLayout.findViewById(R.id.edClass);

        AlertDialog.Builder alert = new AlertDialog.Builder(this)
                .setTitle("Insert a new student")
                .setView(alertLayout)
                .setCancelable(false)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "Cancel", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("Insert", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // code for insert student
                        String name = edStudentName.getText().toString();
                        String age = edStudentAge.getText().toString();
                        String nclass = edStudentClass.getText().toString();
                        if ("".equals(name) || "".equals(age) || "".equals(nclass)) {
                            Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                        } else insertStudent(name, Integer.parseInt(age), nclass);
                    }
                });
        alert.create().show();
    }

    private void insertStudent(String name, int age, String nclass) {
        mAPIService.insertStudent(name, age, nclass).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String status = response.body().string().toString().trim();
                    Log.e("response", status);
                    if (status.length() > 0) {
                        getAllStudents();
                        Toast.makeText(getBaseContext(), status, Toast.LENGTH_LONG).show();
                    } else {
                        tvResult.setText(response.body().string());
                    }
                } catch (Exception e) {
                    Log.e("onResponse", "Error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("onFailure", t.toString());
            }
        });
    }
}
