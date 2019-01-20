package de.diakonie.miguide;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CSVReader {

    public static final ArrayList<Institution> KNOWN_INSTITUTIONS = new ArrayList<Institution>();

    private static final String CSVSeperator = ";";

    private CSVReader() {

    }

    public static void init(Context context) {
        if (KNOWN_INSTITUTIONS.isEmpty()) {
            readFile(context);
        }
    }

    private static String readLine(BufferedReader reader) throws IOException {
        String buffer = "";
        boolean escaped = false;
        while (true) {
            int c = reader.read();
            if (c == -1 || (c == '\n' && !escaped)) {
                if (buffer.isEmpty() && c == -1) {
                    return null;
                } else {
                    return buffer;
                }
            }

            if (c == '"') {
                escaped = !escaped;
                continue;
            }

            if (escaped && c == ';') {
                continue;
            }

            buffer += (char) c;
        }
    }

    private static void readFile(Context context) {
        try {
            InputStream input = context.getResources().openRawResource(R.raw.institutionen);
            InputStreamReader inputreader = new InputStreamReader(input);
            BufferedReader bufferedReader = new BufferedReader(inputreader);

            String line = null;
            while ((line = readLine(bufferedReader)) != null) {
                String[] splitted = line.split(CSVSeperator);
                if (splitted.length < 11) {
                    Log.w("CSV", "category.length < 11");
                    continue;
                }

                String Lage = splitted[0];
                String Kategorie = splitted[1];
                String Name = splitted[2];
                String PLZ = splitted[3];
                String Ort = splitted[4];
                String Straße = splitted[5];
                String HausNR = splitted[6];
                String Preis = splitted[7];
                String Öffnungszeiten = splitted[8];
                String Beschreibung = splitted[9];
                String Anforderungen = splitted[10];

                Log.i("CSV", Kategorie + " " + Name);

                Institution institution = new Institution();
                institution.Lage = Lage;
                institution.Kategorie = Kategorie;
                institution.Name = Name;
                institution.PLZ = PLZ;
                institution.Ort = Ort;
                institution.Straße = Straße;
                institution.HausNR = HausNR;
                institution.Preis = Preis;
                institution.Öffnungszeiten = Öffnungszeiten;
                institution.Beschreibung = Beschreibung;
                institution.Anforderungen = Anforderungen;

                KNOWN_INSTITUTIONS.add(institution);

                //Lage, Kategorie, Name, PLZ, Ort, Straße, Anschrift, Haus Nr., Preis, Öffnungszeiten, Beschreibung, Anforderungen

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
