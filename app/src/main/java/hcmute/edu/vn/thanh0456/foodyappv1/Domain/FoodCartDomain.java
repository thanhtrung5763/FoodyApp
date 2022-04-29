package hcmute.edu.vn.thanh0456.foodyappv1.Domain;

public class FoodCartDomain {
    private String pic;
    private String name;
    private String size;
    private Double price;
    private int qty;

    public FoodCartDomain(String pic, String name, String size, Double price, int qty) {
        this.pic = pic;
        this.name = name;
        this.size = size;
        this.price = price;
        this.qty = qty;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
