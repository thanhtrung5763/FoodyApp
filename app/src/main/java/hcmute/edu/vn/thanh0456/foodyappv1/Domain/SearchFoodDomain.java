package hcmute.edu.vn.thanh0456.foodyappv1.Domain;

import java.io.Serializable;

public class SearchFoodDomain implements Serializable {
    private int product_id;
    private int category_id;
    private int res_id;
    private String prod_name;
    private double price;
    private String size;
    private int qty;
    private String img;
    private String ingredients;
    private int status;
    private String desc;
    private int time;
    private double total;
    private String res_name;
    private String res_type;
    private double res_score;
    private double res_rating;
    private Boolean is_favorited = false;

    public SearchFoodDomain(int product_id, int category_id, int res_id, String name, double price, String size, int qty, String img, String ingredients, int status, String desc, int time) {
        this.product_id = product_id;
        this.category_id = category_id;
        this.res_id = res_id;
        this.prod_name = name;
        this.price = price;
        this.size = size;
        this.qty = qty;
        this.img = img;
        this.ingredients = ingredients;
        this.status = status;
        this.desc = desc;
        this.time = time;
    }

    public SearchFoodDomain(int product_id, int category_id, int res_id, String prod_name, double price, String size, String img, String desc, int time, String res_name, String res_type, double res_score, double res_rating) {
        this.product_id = product_id;
        this.category_id = category_id;
        this.res_id = res_id;
        this.prod_name = prod_name;
        this.price = price;
        this.size = size;
        this.img = img;
        this.desc = desc;
        this.time = time;
        this.res_name = res_name;
        this.res_type = res_type;
        this.res_score = res_score;
        this.res_rating = res_rating;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getRes_id() {
        return res_id;
    }

    public void setRes_id(int res_id) {
        this.res_id = res_id;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getRes_name() {
        return res_name;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public String getRes_type() {
        return res_type;
    }

    public void setRes_type(String res_type) {
        this.res_type = res_type;
    }

    public double getRes_score() {
        return res_score;
    }

    public void setRes_score(double res_score) {
        this.res_score = res_score;
    }

    public double getRes_rating() {
        return res_rating;
    }

    public void setRes_rating(double res_rating) {
        this.res_rating = res_rating;
    }

    public Boolean getIs_favorited() {
        return is_favorited;
    }

    public void setIs_favorited(Boolean is_favorited) {
        this.is_favorited = is_favorited;
    }
}
