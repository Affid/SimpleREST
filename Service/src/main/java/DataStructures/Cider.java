package DataStructures;

public class Cider extends AlcoholicDrink {
    private String label;
    private Integer price;
    private String flavor;
    private String ingredient;


    public Cider(String label, Integer price, String flavor, String ingridient) {
        this.label = label;
        this.price = price;
        this.flavor = flavor;
        this.ingredient = ingridient;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String getFlavor() {
        return flavor;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
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
        return String.format("Cider named %s, made from %s, costs %o, and has flavor %s", label, ingredient, price, flavor);
    }
}
