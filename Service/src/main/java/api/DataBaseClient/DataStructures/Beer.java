package api.DataBaseClient.DataStructures;

public class Beer extends AlcoholicDrink {
    private String label;
    private Integer price;
    private String flavor;
    private String type;


    public Beer(String label, Integer price, String flavor, String type) {
        this.label = label;
        this.price = price;
        this.flavor = flavor;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Integer getPrice() {
        return this.price;
    }

    @Override
    public String getFlavor() {
        return flavor;
    }

    @Override
    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return String.format("%s named %s, costs %o, and has flavor %s",this.getClass().getName(), getLabel(), getPrice(), getFlavor());
    }


}
