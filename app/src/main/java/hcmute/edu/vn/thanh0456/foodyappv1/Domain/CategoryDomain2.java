package hcmute.edu.vn.thanh0456.foodyappv1.Domain;

public class CategoryDomain2 {
    private int category_id;
    private String title;

    public CategoryDomain2(int category_id, String title) {
        this.category_id = category_id;
        this.title = title;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
