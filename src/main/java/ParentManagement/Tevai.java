package ParentManagement;

public class Tevai {

    private int tevuID;

    private String motinosVardas;

    private String motinosPavarde;

    private String motinosTelNr;

    private String adresas;

    private String tevoVardas;

    private String tevoPavarde;

    private String tevoTelNr;

    public Tevai(int tevuID, String motinosVardas, String motinosPavarde, String motinosTelNr, String adresas, String tevoVardas, String tevoPavarde, String tevoTelNr) {
        this.tevuID = tevuID;
        this.motinosVardas = motinosVardas;
        this.motinosPavarde = motinosPavarde;
        this.motinosTelNr = motinosTelNr;
        this.adresas = adresas;
        this.tevoVardas = tevoVardas;
        this.tevoPavarde = tevoPavarde;
        this.tevoTelNr = tevoTelNr;
    }



    public Tevai() {
    }

    public Tevai(String motinosVardas) {
        this.motinosVardas = motinosVardas;
    }

    public int getTevuID() {
        return tevuID;
    }

    public void setTevuID(int tevuID) {
        this.tevuID = tevuID;
    }

    public String getMotinosVardas() {
        return motinosVardas;
    }

    public void setMotinosVardas(String motinosVardas) {
        this.motinosVardas = motinosVardas;
    }

    public String getMotinosPavarde() {
        return motinosPavarde;
    }

    public void setMotinosPavarde(String motinosPavarde) {
        this.motinosPavarde = motinosPavarde;
    }

    public String getMotinosTelNr() {
        return motinosTelNr;
    }

    public void setMotinosTelNr(String motinosTelNr) {
        this.motinosTelNr = motinosTelNr;
    }

    public String getAdresas() {
        return adresas;
    }

    public void setAdresas(String adresas) {
        this.adresas = adresas;
    }

    public String getTevoVardas() {
        return tevoVardas;
    }

    public void setTevoVardas(String tevoVardas) {
        this.tevoVardas = tevoVardas;
    }

    public String getTevoPavarde() {
        return tevoPavarde;
    }

    public void setTevoPavarde(String tevoPavarde) {
        this.tevoPavarde = tevoPavarde;
    }

    public String getTevoTelNr() {
        return tevoTelNr;
    }

    public void setTevoTelNr(String tevoTelNr) {
        this.tevoTelNr = tevoTelNr;
    }

    @Override
    public String toString()
    {
        return tevuID + " " + motinosVardas + " " + motinosPavarde + " " + motinosTelNr + " " + adresas + " " + tevoVardas + " " + tevoPavarde + " " + tevoTelNr;
    }
}
