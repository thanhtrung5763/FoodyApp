package hcmute.edu.vn.thanh0456.foodyappv1.Domain;

import java.io.Serializable;

public class FavoriteDomain implements Serializable {
    private int favorite_id;
    private int customer_id;
    private int product_id;


    public FavoriteDomain(int favorite_id, int customer_id, int product_id) {
        this.favorite_id = favorite_id;
        this.customer_id = customer_id;
        this.product_id = product_id;
    }

    public int getFavorite_id() {
        return favorite_id;
    }

    public void setFavorite_id(int favorite_id) {
        this.favorite_id = favorite_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
