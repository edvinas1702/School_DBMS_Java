package ClassManagement;

public class Klase {

    private int klasesID;

    private int mokytojoID;

    private String klase;

    public Klase(int klasesID, int mokytojoID, String klase) {
        this.klasesID = klasesID;
        this.mokytojoID = mokytojoID;
        this.klase = klase;
    }

    public Klase(String klase) {
        this.klase = klase;
    }

    public int getKlasesID() {
        return klasesID;
    }

    public int getMokytojoID() {
        return mokytojoID;
    }

    public String getKlase() {
        return klase;
    }

    public void setKlasesID(int klasesID) {
        this.klasesID = klasesID;
    }

    public void setMokytojoID(int mokytojoID) {
        this.mokytojoID = mokytojoID;
    }

    public void setKlase(String klase) {
        this.klase = klase;
    }

    @Override
    public String toString()
    {
        return klasesID + " " + mokytojoID + " " + klase;
    }
}
