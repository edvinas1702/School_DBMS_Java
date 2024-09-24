package StudentManagement;

import java.time.LocalDate;

public class Mokinys2 {

    private int mokinioID;
    private int tevuID;
    private int klasesID;
    private String mokinioVardas;
    private String mokinioPavarde;
    private LocalDate gimimoData;

    private String klasesPavadinimas;

    public Mokinys2(int mokinioID, int tevuID, int klasesID, String mokinioVardas, String mokinioPavarde, LocalDate gimimoData, String klasesPavadinimas) {
        this.mokinioID = mokinioID;
        this.tevuID = tevuID;
        this.klasesID = klasesID;
        this.mokinioVardas = mokinioVardas;
        this.mokinioPavarde = mokinioPavarde;
        this.gimimoData = gimimoData;
        this.klasesPavadinimas = klasesPavadinimas;
    }

    public int getMokinioID() {
        return mokinioID;
    }

    public void setMokinioID(int mokinioID) {
        this.mokinioID = mokinioID;
    }

    public int getTevuID() {
        return tevuID;
    }

    public void setTevuID(int tevuID) {
        this.tevuID = tevuID;
    }

    public int getKlasesID() {
        return klasesID;
    }

    public void setKlasesID(int klasesID) {
        this.klasesID = klasesID;
    }

    public String getMokinioVardas() {
        return mokinioVardas;
    }

    public void setMokinioVardas(String mokinioVardas) {
        this.mokinioVardas = mokinioVardas;
    }

    public String getMokinioPavarde() {
        return mokinioPavarde;
    }

    public void setMokinioPavarde(String mokinioPavarde) {
        this.mokinioPavarde = mokinioPavarde;
    }

    public LocalDate getGimimoData() {
        return gimimoData;
    }

    public void setGimimoData(LocalDate gimimoData) {
        this.gimimoData = gimimoData;
    }

    public String getKlasesPavadinimas() {
        return klasesPavadinimas;
    }

    public void setKlasesPavadinimas(String klasesPavadinimas) {
        this.klasesPavadinimas = klasesPavadinimas;
    }
}
