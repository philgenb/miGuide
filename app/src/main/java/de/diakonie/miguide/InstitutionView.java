package de.diakonie.miguide;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import static de.diakonie.miguide.InstitutionActivity.institutions;
import static de.diakonie.miguide.CSVReader.KNOWN_INSTITUTIONS;

public class InstitutionView extends AppCompatActivity {

    private Institution currentInstitution;

    private TextView headline;
    private TextView description;
    private TextView openingtimes;
    private TextView address;
    private TextView restrictions;

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
        headline = findViewById(R.id.headline);
        description = findViewById(R.id.description);
        openingtimes = findViewById(R.id.openingtimes);
        address = findViewById(R.id.address);
        restrictions = findViewById(R.id.restrictions);

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

    private void parseIncomingIntent() {
        int institutionID = getIntent().getIntExtra("institutionID", -1);
        if (institutionID == -1) {
            Log.e("de.diakonie.miguide.InstitutionActivity", "Institution not defined");
            return;
        }

        String institutionName = institutions.get(institutionID).name;
        this.currentInstitution = getInstitutionbyName(institutionName);

        updateHeadline(currentInstitution.Name);
        updateDescription(currentInstitution.Beschreibung);
        updateOpeningTimes(currentInstitution.Öffnungszeiten);
        updateAddress(currentInstitution.Anschrift);
        updateRestrictions(currentInstitution.Anforderungen);
        Log.i("test", "-" + currentInstitution.Anforderungen + "-");
    }

    private void updateHeadline(String headlineText) {
        headline.setText(headlineText);
    }

    private void updateDescription(String descriptionText) {
        description.setText(descriptionText);
    }
    private void updateAddress(String Address) {
        address.setText(getResources().getString(R.string.address) + ": " + Address);
    }

    private void updateOpeningTimes(String Openingtimes) {
        openingtimes.setText(getResources().getString(R.string.openingtime) + ": " + Openingtimes);
    }

    private void updateRestrictions(String restrictionText) {
        //TODO Bugfix
        if(restrictionText.equals("") || restrictionText.isEmpty() || restrictionText == null) {
            restrictions.setText(getResources().getString(R.string.restrictions) + ":   -");
            return;
        }

        restrictions.setText(getResources().getString(R.string.restrictions) + ": " + restrictionText);
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
