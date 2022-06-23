package hcmute.edu.vn.thanh0456.foodyappv1.Domain;

import java.io.Serializable;
import java.util.Date;

public class OrderDomain implements Serializable {
    private int order_id;
    private int restaurant_id;
    private int customer_id;
    private Date order_date;
    private double total;
    private int status;
    private String restaurant_name;
    private String restaurant_address;
    private Double score;
    private int rating;
    private String restaurant_pic;

    public OrderDomain(int order_id, int restaurant_id, int customer_id, Date order_date, double total, int status) {
        this.order_id = order_id;
        this.restaurant_id = restaurant_id;
        this.customer_id = customer_id;
        this.order_date = order_date;
        this.total = total;
        this.status = status;
    }

    public OrderDomain(int restaurant_id, int customer_id, Date order_date, double total, int status) {
        this.restaurant_id = restaurant_id;
        this.customer_id = customer_id;
        this.order_date = order_date;
        this.total = total;
        this.status = status;
    }

    public OrderDomain(int order_id, int restaurant_id, int customer_id, Date order_date, double total, int status, String restaurant_name, String restaurant_address, Double score, int rating, String restaurant_pic) {
        this.order_id = order_id;
        this.restaurant_id = restaurant_id;
        this.customer_id = customer_id;
        this.order_date = order_date;
        this.total = total;
        this.status = status;
        this.restaurant_name = restaurant_name;
        this.restaurant_address = restaurant_address;
        this.score = score;
        this.rating = rating;
        this.restaurant_pic = restaurant_pic;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRestaurant_pic() {
        return restaurant_pic;
    }

    public void setRestaurant_pic(String restaurant_pic) {
        this.restaurant_pic = restaurant_pic;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getRestaurant_address() {
        return restaurant_address;
    }

    public void setRestaurant_address(String restaurant_address) {
        this.restaurant_address = restaurant_address;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
