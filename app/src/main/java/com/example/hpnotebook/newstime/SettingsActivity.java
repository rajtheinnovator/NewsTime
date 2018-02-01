package com.example.hpnotebook.newstime;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Hp Notebook on 29-01-2018.
 */

public class SettingsActivity extends AppCompatActivity {

    private static final String LOG_TAG = SettingsActivity.class.getName();
    private static Preference mPreference;
    private static String dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
    }

    public static class NewsPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_main);

            Preference section = findPreference(getString(R.string.settings_section_key));
            bindPreferenceSummaryToValue(section);
            Preference orderBy = findPreference(getString(R.string.settings_order_by_key));
            bindPreferenceSummaryToValue(orderBy);
            Preference pgSize = findPreference(getString(R.string.settings_page_size_key));
            bindPreferenceSummaryToValue(pgSize);
            Preference date = findPreference(getString(R.string.settings_date_key));
            date.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    showDatePicker();
                    return true;
                }
            });
            mPreference = date;
        }

        private void bindPreferenceSummaryToValue(Preference preference) {
            if(preference.getKey().equals("date")){
                Log.v(LOG_TAG,"inide if");
                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(getString(R.string.settings_date_key), dob);
                Log.v("my_tag", "date is: "+dob);
                editor.apply();
            }
            preference.setOnPreferenceChangeListener(this);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();
            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int prefIndex = listPreference.findIndexOfValue(stringValue);
                if (prefIndex >= 0) {
                    CharSequence[] labels = listPreference.getEntries();
                    preference.setSummary(labels[prefIndex]);
                }
            } else {
                preference.setSummary(stringValue);
            }
            return true;
        }

        private void showDatePicker() {

            Log.v(LOG_TAG, "inside showDatePicker");
            DatePickerFragment date = new DatePickerFragment();

            /**
             * Set Up Current Date Into dialog
             */
            Calendar calender = Calendar.getInstance();
            Bundle args = new Bundle();
            args.putInt("year", calender.get(Calendar.YEAR));
            args.putInt("month", calender.get(Calendar.MONTH));
            args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
            date.setArguments(args);

            /**
             * Set Call back to capture selected date
             */
            date.setCallBack(ondate);
            date.show(getFragmentManager(), "Date Picker");
            bindPreferenceSummaryToValue(mPreference);

        }

        DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                dob = String.valueOf(year) + "-" + String.valueOf(monthOfYear+1) + "-" + String.valueOf(dayOfMonth);
            }
        };
    }
}
