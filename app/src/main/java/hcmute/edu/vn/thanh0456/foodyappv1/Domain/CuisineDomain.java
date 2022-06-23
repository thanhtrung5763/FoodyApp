package hcmute.edu.vn.thanh0456.foodyappv1.Domain;


public class CuisineDomain {
    private String name;
    private boolean isSelected = false;

    public CuisineDomain(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
