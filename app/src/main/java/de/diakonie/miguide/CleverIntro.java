package de.diakonie.miguide;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class CleverIntro extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addSlide(AppIntroFragment.newInstance("", "", R.drawable.ic_01_appintro, Color.WHITE));
        addSlide(AppIntroFragment.newInstance("Kategorien", "Test", R.drawable.ic_missing_image, Color.YELLOW));
        addSlide(AppIntroFragment.newInstance("Institution", "Test", R.drawable.ic_bekleidung, Color.GREEN));

        showSkipButton(true);


    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finishIntro();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finishIntro();
    }

    private void finishIntro() {
        Intent intent = new Intent(CleverIntro.this, LanguageSelectorActivity.class);
        startActivity(intent);
    }
}
