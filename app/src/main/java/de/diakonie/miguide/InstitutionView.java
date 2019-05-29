package de.diakonie.miguide;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import static de.diakonie.miguide.InstitutionActivity.institutions;
import static de.diakonie.miguide.CSVReader.KNOWN_INSTITUTIONS;

public class InstitutionView extends AppCompatActivity {

    private Institution currentInstitution;

    private TextView headline;
    private TextView description;
    private TextView openingtimes;
    private TextView address;
    private TextView restrictions;

    //only shown if existing
    private TextView price;
    private ImageView Imageprice;
    //----------

    private ImageButton navigationbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institution_view);

        registerUI();

        parseIncomingIntent();
        registerToolBar();
    }

    private void registerUI() {
        getLanguage();
        headline = findViewById(R.id.headline);
        description = findViewById(R.id.description);
        openingtimes = findViewById(R.id.openingtimes);
        address = findViewById(R.id.address);
        restrictions = findViewById(R.id.restrictions);

        price = findViewById(R.id.price);
        Imageprice = findViewById(R.id.ImagePreis);


        navigationbutton = findViewById(R.id.navigation);
        navigationbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q=" + currentInstitution.Anschrift));
                startActivity(intent);
            }
        });
    }

    public Locale getLanguage() {
        return getResources().getConfiguration().locale;
    }

    private void parseIncomingIntent() {
        int institutionID = getIntent().getIntExtra("institutionID", -1);
        if (institutionID == -1) {
            Log.e("de.diakonie.miguide.InstitutionActivity", "Institution not defined");
            return;
        }

        String institutionName = institutions.get(institutionID).name;
        this.currentInstitution = getInstitutionbyName(institutionName);

        updateHeadline(currentInstitution.Name);
        loadDescription();
        updateOpeningTimes(currentInstitution.Öffnungszeiten);
        updateAddress(currentInstitution.Anschrift);
        updateRestrictions(currentInstitution.Anforderungen);
        updatePrice(currentInstitution.Preis);
    }

    public void loadDescription() {
        String locale = getLanguage().toString();
        Log.i("InstitutionView - Locale", "Locale: " + locale);
        if(locale.equals("en")) {
            Log.i("InstitutionView - Locale", "Locale EN");
            updateDescription(currentInstitution.BeschreibungE);
        } else if(locale.equals("ar")) {
            if(!currentInstitution.BeschreibungA.isEmpty()) {
                updateDescription(currentInstitution.BeschreibungA);
            } else {
                updateDescription(currentInstitution.BeschreibungE);
            }
        } else {
            updateDescription(currentInstitution.BeschreibungD);
        }
    }

    private void updateHeadline(String headlineText) {
        headline.setText(headlineText);
    }

    private void updateDescription(String descriptionText) {
        description.setText(descriptionText);
    }
    private void updateAddress(String Address) {
        String adressPlaceholder = getResources().getString(R.string.address) + ": ";
        address.setText(Address);
    }

    private void updateOpeningTimes(String Openingtimes) {
        String openingtimePlaceholder = getResources().getString(R.string.openingtime) + ": ";
        openingtimes.setText(Openingtimes);
    }

    private void updatePrice(String Preis) {
        if(Preis.isEmpty()) {
            hidePrice();
        } else {
            showPrice();
            String pricePlacerholder = getResources().getString(R.string.price) + ": ";
            price.setText(Preis);
        }
    }

    private void hidePrice() {
        price.setVisibility(View.INVISIBLE);
        Imageprice.setVisibility(View.INVISIBLE);
    }

    public void showPrice() {
        price.setVisibility(View.VISIBLE);
        Imageprice.setVisibility(View.VISIBLE);
    }

    private void updateRestrictions(String restrictionText) {
        String retrictionPlacerholder = getResources().getString(R.string.restrictions) + ": ";
        if(restrictionText.trim().isEmpty() || restrictionText == null) {
            restrictions.setText("—");
        } else {
            restrictions.setText(restrictionText);
        }
    }

    private Institution getInstitutionbyName(String name) {
        for (Institution institution : KNOWN_INSTITUTIONS) {
            if(institution.Name.equals(name)) {
                return institution;
            }
        }
        return null;
    }



    // Setzt die Actionbar
    public void registerToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(null);
        setSupportActionBar(toolbar);
    }

}
