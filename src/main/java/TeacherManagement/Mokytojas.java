package TeacherManagement;

public class Mokytojas {

    private int mokytojoID;

    private String mokytojoVardas;

    private String mokytojoPavarde;

    private String mokytojoTelNr;

    private String mokytojoElPastas;


    public Mokytojas(int mokytojoID, String mokytojoVardas, String mokytojoPavarde, String mokytojoTelNr, String mokytojoElPastas) {
        this.mokytojoID = mokytojoID;
        this.mokytojoVardas = mokytojoVardas;
        this.mokytojoPavarde = mokytojoPavarde;
        this.mokytojoTelNr = mokytojoTelNr;
        this.mokytojoElPastas = mokytojoElPastas;
    }

    public Mokytojas(String mokytojoVardas, String mokytojoPavarde, String mokytojoTelNr, String mokytojoElPastas) {
        this.mokytojoVardas = mokytojoVardas;
        this.mokytojoPavarde = mokytojoPavarde;
        this.mokytojoTelNr = mokytojoTelNr;
        this.mokytojoElPastas = mokytojoElPastas;
    }

    public Mokytojas() {
    }

    public int getMokytojoID() {
        return mokytojoID;
    }

    public String getMokytojoVardas() {
        return mokytojoVardas;
    }

    public String getMokytojoPavarde() {
        return mokytojoPavarde;
    }

    public String getMokytojoTelNr() {
        return mokytojoTelNr;
    }

    public String getMokytojoElPastas() {
        return mokytojoElPastas;
    }

    public void setMokytojoID(int mokytojoID) {
        this.mokytojoID = mokytojoID;
    }

    public void setMokytojoVardas(String mokytojoVardas) {
        this.mokytojoVardas = mokytojoVardas;
    }

    public void setMokytojoPavarde(String mokytojoPavarde) {
        this.mokytojoPavarde = mokytojoPavarde;
    }

    public void setMokytojoTelNr(String mokytojoTelNr) {
        this.mokytojoTelNr = mokytojoTelNr;
    }

    public void setMokytojoElPastas(String mokytojoElPastas) {
        this.mokytojoElPastas = mokytojoElPastas;
    }

    @Override
    public String toString()
    {
        return mokytojoID + " " + mokytojoVardas + " " + mokytojoPavarde + " " + mokytojoTelNr + " " + mokytojoElPastas;
    }
}
