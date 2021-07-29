package DataStructures;

public class Cider extends AlcoholicDrink {
    private String label;
    private Integer price;
    private String flavor;
    private String ingridient;


    public Cider(String label, Integer price, String flavor, String ingridient) {
        this.label = label;
        this.price = price;
        this.flavor = flavor;
        this.ingridient = ingridient;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String getFlavor() {
        return flavor;
    }

    @Override
    public Integer getPrice() {
        return this.price;
    }

    public String getLabel() {
        return this.label;
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
        return String.format("Cider named %s, made from %s, costs %o, and has flavor %s", label, ingridient, price, flavor);
    }
}
