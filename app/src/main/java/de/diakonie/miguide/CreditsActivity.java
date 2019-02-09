package de.diakonie.miguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

public class CreditsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        setHeadline();
    }

    private void setHeadline() {
        TextView headline = findViewById(R.id.Headline);
/*
        Spannable headlineString = new SpannableString("CLEVERBUY");
        headlineString.setSpan(new ForegroundColorSpan(222222), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        headlineString.setSpan(new ForegroundColorSpan(111111), 6, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        headline.setText(headlineString);
        */
    }



    

}
