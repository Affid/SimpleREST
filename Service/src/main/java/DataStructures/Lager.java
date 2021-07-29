package DataStructures;

public class Lager extends Beer {

    public Lager(String label, Integer price, String flavor) {
        super(label, price, flavor);
    }

    @Override
    public String toString() {
        return String.format("Lager named %s, costs %o, and has flavor %s", getLabel(), getPrice(), getFlavor());
    }
}
