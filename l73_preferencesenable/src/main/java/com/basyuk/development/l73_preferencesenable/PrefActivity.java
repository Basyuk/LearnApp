package com.basyuk.development.l73_preferencesenable;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;

public class PrefActivity extends PreferenceActivity {

    CheckBoxPreference checkBox3;
    PreferenceCategory preferenceCategory2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);

        checkBox3 = (CheckBoxPreference) findPreference("chb3");
        preferenceCategory2 = (PreferenceCategory) findPreference("categ2");
        preferenceCategory2.setEnabled(checkBox3.isChecked());

        checkBox3.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                preferenceCategory2.setEnabled(checkBox3.isChecked());
                return false;
            }
        });
    }
}
