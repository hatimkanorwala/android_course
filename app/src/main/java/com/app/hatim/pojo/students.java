package com.app.hatim.pojo;

public class students {
    public String id;
    public String name;
    public String rollno;
    public String email;
    public String mobile;

    public students(String id, String name, String rollno, String email, String mobile) {
        this.id = id;
        this.name = name;
        this.rollno = rollno;
        this.email = email;
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
