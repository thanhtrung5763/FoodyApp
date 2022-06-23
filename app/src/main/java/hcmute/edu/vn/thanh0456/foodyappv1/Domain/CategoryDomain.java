package hcmute.edu.vn.thanh0456.foodyappv1.Domain;

public class CategoryDomain {
    private int category_id;
    private String title;
    private String pic;

    public CategoryDomain(int category_id, String title, String pic) {
        this.category_id = category_id;
        this.title = title;
        this.pic = pic;
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
