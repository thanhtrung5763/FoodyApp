package hcmute.edu.vn.thanh0456.foodyappv1.Domain;

import java.io.Serializable;

public class DiscountDomain implements Serializable {
    private int discount_id;
    private String name;

    public DiscountDomain(int discount_id, String name) {
        this.discount_id = discount_id;
        this.name = name;
    }

    public int getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(int discount_id) {
        this.discount_id = discount_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
