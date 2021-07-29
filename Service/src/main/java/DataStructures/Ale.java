package DataStructures;

public class Ale extends Beer {

    public Ale(String label, Integer price, String flavor) {
        super(label, price, flavor);
    }

    @Override
    public String toString() {
        return String.format("Ale named %s, costs %o, and has flavor %s", getLabel(), getPrice(), getFlavor());
    }


}
