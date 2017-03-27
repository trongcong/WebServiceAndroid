package com.dev4u.ntc.webservice.webservice;

import com.dev4u.ntc.webservice.models.Student;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.webservice.models
 * Name project: WebService
 * Date: 3/24/2017
 * Time: 17:52
 */

public interface APIService {
    // GET students from server
    // Server return json array
    @GET("/student_manager/api.php")
    Call<List<Student>> getStudents();

    // GET student by id student from server
    // Server return json object
    @GET("/student_manager/api.php")
    Call<List<Student>> getStudent(@Query("id") String id);

    // POST student from client to server
    // Server return string
    @FormUrlEncoded
    @POST("/student_manager/api.php")
    Call<ResponseBody> insertStudent(@Field("name") String name, @Field("age") int age, @Field("nclass") String nclass);
}
