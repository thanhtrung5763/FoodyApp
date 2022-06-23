package hcmute.edu.vn.thanh0456.foodyappv1.Domain;

import java.io.Serializable;

public class DiscoverFoodPlaceDomain implements Serializable {
    private int id;
    private String name;
    private String type;
    private String address;
    private int serve_time;
    private Double score;
    private int rating;
    private String img;
    private String promotion;

    public DiscoverFoodPlaceDomain(int id, String name, String type, String address, int serve_time, Double score, int rating, String img, String promotion) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.address = address;
        this.serve_time = serve_time;
        this.score = score;
        this.rating = rating;
        this.img = img;
        this.promotion = promotion;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getServe_time() {
        return serve_time;
    }

    public void setServe_time(int serve_time) {
        this.serve_time = serve_time;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }
}
