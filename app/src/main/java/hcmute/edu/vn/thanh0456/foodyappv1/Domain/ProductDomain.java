package hcmute.edu.vn.thanh0456.foodyappv1.Domain;

import java.io.Serializable;

public class ProductDomain implements Serializable {
    private int product_id;
    private int category_id;
    private int res_id;
    private String name;
    private double price;
    private String size;
    private int qty;
    private String img;
    private String ingredients;
    private int status;
    private String desc;
    private int time;
    private double total;
    private Boolean is_favorited = false;

    public ProductDomain(int product_id, int category_id, int res_id, String name, double price, String size, int qty, String img, String ingredients, int status, String desc, int time) {
        this.product_id = product_id;
        this.category_id = category_id;
        this.res_id = res_id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.qty = qty;
        this.img = img;
        this.ingredients = ingredients;
        this.status = status;
        this.desc = desc;
        this.time = time;
    }
    public ProductDomain(SearchFoodDomain searchFoodDomain) {
        this.product_id = searchFoodDomain.getProduct_id();
        this.category_id = searchFoodDomain.getCategory_id();
        this.res_id = searchFoodDomain.getRes_id();
        this.name = searchFoodDomain.getProd_name();
        this.price = searchFoodDomain.getPrice();
        this.size = searchFoodDomain.getSize();
        this.img = searchFoodDomain.getImg();
        this.desc = searchFoodDomain.getDesc();
        this.time = searchFoodDomain.getTime();
        this.is_favorited = searchFoodDomain.getIs_favorited();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean getIs_favorited() {
        return is_favorited;
    }

    public void setIs_favorited(Boolean is_favorited) {
        this.is_favorited = is_favorited;
    }
}
