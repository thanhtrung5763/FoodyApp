package hcmute.edu.vn.thanh0456.foodyappv1.Domain;

import java.io.Serializable;

public class CustomerDomain implements Serializable {
    private int customer_id;
    private String fname;
    private String lname;
    private String email;
    private String phone;
    private String pro_pic;
    private String username;
    private String password;
    private int status;

    public CustomerDomain(int customer_id, String fname, String lname, String email, String phone, String pro_pic, String username, String password, int status) {
        this.customer_id = customer_id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.pro_pic = pro_pic;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPro_pic() {
        return pro_pic;
    }

    public void setPro_pic(String pro_pic) {
        this.pro_pic = pro_pic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
