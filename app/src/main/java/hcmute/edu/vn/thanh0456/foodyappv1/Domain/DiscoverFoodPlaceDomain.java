package hcmute.edu.vn.thanh0456.foodyappv1.Domain;

public class DiscoverFoodPlaceDomain {
    private String pic;
    private String restaurant;
    private String address;
    private Double score;
    private int rating;
    private String promotion;

    public DiscoverFoodPlaceDomain(String pic, String restaurant, String address, Double score, int rating, String promotion) {
        this.pic = pic;
        this.restaurant = restaurant;
        this.address = address;
        this.score = score;
        this.rating = rating;
        this.promotion = promotion;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }
}
