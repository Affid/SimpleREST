package api.DataBaseClient.DataStructures;

public abstract class AlcoholicDrink {
    private Integer price;
    private String flavor;

    abstract String getFlavor();

    abstract Integer getPrice();

    abstract void setFlavor(String flavor);

    public abstract void setPrice(Integer price);
}
