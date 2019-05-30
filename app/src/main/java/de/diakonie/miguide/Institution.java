package de.diakonie.miguide;

public class Institution {

    public int ID;
    public String Lage;
    public String Kategorie;
    public String Name;
    public String PLZ;
    public String Ort;
    public String Straße;
    public String Anschrift;
    public String HausNR;
    public String Preis;
    public String Öffnungszeiten;
    public String openinghours;
    public String BeschreibungD;
    public String BeschreibungE;
    public String BeschreibungA;
    public String Anforderungen;
    public String ImagePath;

    //Lage;Kategorie;Name;PLZ;Ort;Straße;Anschrift;Haus Nr.;Preis;Öffnungszeiten;Beschreibung;Anforderungen

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setLage(String Lage) {
        this.Lage = Lage;
    }

    public void setKategorie (String Kategorie) {
        this.Kategorie = Kategorie;
    }

    public void setName (String Name) {
        this.Name = Name;
    }

    public void setPLZ (String PLZ) {
        this.Lage = PLZ;
    }

    public void setOrt (String Ort) {
        this.Ort = Ort;
    }

    public void setHausNR (String HausNR) {
        this.HausNR = HausNR;
    }

    public void setAnschrift (String Anschrift) {
        this.Anschrift = Anschrift;
    }

    public void setPreis(String Preis) {
        this.Preis = Preis;
    }

    public void setImagePath(String ImagePath) {
        this.ImagePath = ImagePath;
    }

}
