package hcmute.edu.vn.thanh0456.foodyappv1.Domain;

import java.io.Serializable;
import java.util.Date;

public class OrderDetailDomain implements Serializable {
    private int order_detail_id;
    private int order_id;
    private int product_id;
    private int qty;
    private double price;
    private String size;
    private double sub_total;
    private String name;
    private String prod_pic;
    public OrderDetailDomain(int order_detail_id, int order_id, int product_id, int qty, double price, String size, double sub_total) {
        this.order_detail_id = order_detail_id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.qty = qty;
        this.price = price;
        this.size = size;
        this.sub_total = sub_total;
    }

    public OrderDetailDomain(int order_detail_id, int order_id, int product_id, int qty, double price, String size, double sub_total, String name, String prod_pic) {
        this.order_detail_id = order_detail_id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.qty = qty;
        this.price = price;
        this.size = size;
        this.sub_total = sub_total;
        this.name = name;
        this.prod_pic = prod_pic;
    }

    public int getOrder_detail_id() {
        return order_detail_id;
    }

    public void setOrder_detail_id(int order_detail_id) {
        this.order_detail_id = order_detail_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getSub_total() {
        return sub_total;
    }

    public void setSub_total(double sub_total) {
        this.sub_total = sub_total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProd_pic() {
        return prod_pic;
    }

    public void setProd_pic(String prod_pic) {
        this.prod_pic = prod_pic;
    }
}
