package com.servelt.librarysystem_servlet.entity;

public class Admin {
    private String adminname;
    private String adminpassword;

    public Admin(String adminpassword, String adminname) {
        this.adminpassword = adminpassword;
        this.adminname = adminname;
    }

    public Admin() {
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getAdminpassword() {
        return adminpassword;
    }

    public void setAdminpassword(String adminpassword) {
        this.adminpassword = adminpassword;
    }


}
