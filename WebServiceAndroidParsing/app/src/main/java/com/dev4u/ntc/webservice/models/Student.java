package com.dev4u.ntc.webservice.models;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.webservice.models
 * Name project: WebService
 * Date: 3/24/2017
 * Time: 17:47
 */

public class Student {
    private int id;
    private String name;
    private int age;
    private String nclass;

    public Student(int id, String name, int age, String nclass) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.nclass = nclass;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", nclass='" + +'\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNclass() {
        return nclass;
    }

    public void setNclass(String nclass) {
        this.nclass = nclass;
    }
}
