package StudentManagement;

import java.time.LocalDate;

public class Mokinys {

    private int mokinioID;
    private int tevuID;
    private int klasesID;
    private String mokinioVardas;
    private String mokinioPavarde;
    private LocalDate gimimoData;

    public Mokinys(int mokinioID, int tevuID, int klasesID, String mokinioVardas, String mokinioPavarde, LocalDate gimimoData) {
        this.mokinioID = mokinioID;
        this.tevuID = tevuID;
        this.klasesID = klasesID;
        this.mokinioVardas = mokinioVardas;
        this.mokinioPavarde = mokinioPavarde;
        this.gimimoData = gimimoData;
    }

    public Mokinys(int mokinioID) {
        this.mokinioID = mokinioID;
    }

    public Mokinys(int tevuID, int klasesID) {
        this.tevuID = tevuID;
        this.klasesID = klasesID;
    }

    public Mokinys(int tevuID, int klasesID, String mokinioVardas, String mokinioPavarde, LocalDate gimimoData) {
        this.tevuID = tevuID;
        this.klasesID = klasesID;
        this.mokinioVardas = mokinioVardas;
        this.mokinioPavarde = mokinioPavarde;
        this.gimimoData = gimimoData;
    }

    public int getMokinioID() {
        return mokinioID;
    }

    public int getTevuID() {
        return tevuID;
    }

    public int getKlasesID() {
        return klasesID;
    }

    public String getMokinioVardas() {
        return mokinioVardas;
    }

    public String getMokinioPavarde() {
        return mokinioPavarde;
    }

    public LocalDate getGimimoData() {
        return gimimoData;
    }

    public void setMokinioID(int mokinioID) {
        this.mokinioID = mokinioID;
    }

    public void setTevuID(int tevuID) {
        this.tevuID = tevuID;
    }

    public void setKlasesID(int klasesID) {
        this.klasesID = klasesID;
    }

    public void setMokinioVardas(String mokinioVardas) {
        this.mokinioVardas = mokinioVardas;
    }

    public void setMokinioPavarde(String mokinioPavarde) {
        this.mokinioPavarde = mokinioPavarde;
    }

    public void setGimimoData(LocalDate gimimoData) {
        this.gimimoData = gimimoData;
    }

    @Override
   public String toString()
   {
       return mokinioID + " " + tevuID + " " + klasesID + " " + mokinioVardas + " " + mokinioPavarde + " " + gimimoData;
   }

    public int toText(){return mokinioID;};
}
