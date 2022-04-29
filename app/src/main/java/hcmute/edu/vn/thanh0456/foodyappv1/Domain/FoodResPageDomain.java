package hcmute.edu.vn.thanh0456.foodyappv1.Domain;

public class FoodResPageDomain {
    private String pic;
    private String name;
    private Double price;

    public FoodResPageDomain(String pic, String name, Double price) {
        this.pic = pic;
        this.name = name;
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
