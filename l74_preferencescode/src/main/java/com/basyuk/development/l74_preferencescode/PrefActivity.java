package com.basyuk.development.l74_preferencescode;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;


public class PrefActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //создаем экран
        PreferenceScreen rootScreen = getPreferenceManager().createPreferenceScreen(this);
        //говорим Activity, что rootScreen - корневой
        setPreferenceScreen(rootScreen);

        //далее создаем элементы, присваиваем атрибуты и формируем иерархию

        CheckBoxPreference checkBoxPreference1 = new CheckBoxPreference(this);
        checkBoxPreference1.setKey("chb1");
        checkBoxPreference1.setTitle("CheckBox 1");
        checkBoxPreference1.setSummaryOn("Description of checkbox 1 on");
        checkBoxPreference1.setSummaryOff("Description of checkbox 1 off");

        rootScreen.addPreference(checkBoxPreference1);

        ListPreference listPreference = new ListPreference(this);
        listPreference.setKey("list");
        listPreference.setTitle("List");
        listPreference.setSummary("Description of list");
        listPreference.setEntries(R.array.entries);
        listPreference.setEntryValues(R.array.entry_values);

        rootScreen.addPreference(listPreference);

        CheckBoxPreference checkBoxPreference2 = new CheckBoxPreference(this);
        checkBoxPreference2.setKey("chb2");
        checkBoxPreference2.setTitle("CheckBox 2");
        checkBoxPreference2.setSummary("Description of checkbox 2");

        rootScreen.addPreference(checkBoxPreference2);

        PreferenceScreen preferenceScreen = getPreferenceManager().createPreferenceScreen(this);
        preferenceScreen.setKey("screen");
        preferenceScreen.setTitle("Screen");
        preferenceScreen.setSummary("Description of screen");

        final CheckBoxPreference checkBoxPreference3 = new CheckBoxPreference(this);
        checkBoxPreference3.setKey("chb3");
        checkBoxPreference3.setTitle("CheckBox 3");
        checkBoxPreference3.setSummary("Description of checkbox 3");

        preferenceScreen.addPreference(checkBoxPreference3);

        PreferenceCategory category1 = new PreferenceCategory(this);
        category1.setKey("categ1");
        category1.setTitle("Category 1");
        category1.setSummary("Description of category 1");

        preferenceScreen.addPreference(category1);

        CheckBoxPreference checkBoxPreference4 = new CheckBoxPreference(this);
        checkBoxPreference4.setKey("chb4");
        checkBoxPreference4.setTitle("CheckBox 4");
        checkBoxPreference4.setSummary("Description of checkbox 4");

        category1.addPreference(checkBoxPreference4);

        final PreferenceCategory category2 = new PreferenceCategory(this);
        category2.setKey("categ2");
        category2.setTitle("Category 2");
        category2.setSummary("Description of category 2");

        preferenceScreen.addPreference(category2);

        CheckBoxPreference checkBoxPreference5 = new CheckBoxPreference(this);
        checkBoxPreference5.setKey("chb5");
        checkBoxPreference5.setTitle("CheckBox 5");
        checkBoxPreference5.setSummary("Description of CheckBox 5");

        category2.addPreference(checkBoxPreference5);

        CheckBoxPreference checkBoxPreference6 = new CheckBoxPreference(this);
        checkBoxPreference5.setKey("chb6");
        checkBoxPreference5.setTitle("CheckBox 6");
        checkBoxPreference5.setSummary("Description of CheckBox 6");

        category2.addPreference(checkBoxPreference6);

        rootScreen.addPreference(preferenceScreen);

        listPreference.setDependency("chb1");
        preferenceScreen.setDependency("chb2");

        category2.setEnabled(checkBoxPreference3.isChecked());
        checkBoxPreference3.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                category2.setEnabled(checkBoxPreference3.isChecked());
                return false;
            }
        });
    }
}
