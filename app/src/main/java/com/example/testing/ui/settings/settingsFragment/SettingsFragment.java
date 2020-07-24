package com.example.testing.ui.settings.settingsFragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import android.text.InputType;
import android.widget.EditText;
import com.example.testing.R;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class SettingsFragment extends PreferenceFragmentCompat implements androidx.preference.Preference.OnPreferenceChangeListener{

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        EditTextPreference minMagnitudePreference = findPreference(getString(R.string.min_magnitude_key));
        assert minMagnitudePreference != null;
        customizeDialog(minMagnitudePreference);
        bindPreferenceSummaryToValue(minMagnitudePreference);

        EditTextPreference maxMagnitudePreference = findPreference(getString(R.string.max_magnitude_key));
        assert maxMagnitudePreference != null;
        customizeDialog(maxMagnitudePreference);
        bindPreferenceSummaryToValue(maxMagnitudePreference);

        EditTextPreference itemsPreference = findPreference(getString(R.string.items_key));
        assert itemsPreference != null;
        customizeDialog(itemsPreference);
        bindPreferenceSummaryToValue(itemsPreference);

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

    private void customizeDialog(EditTextPreference numberPreference){
        if (numberPreference != null) {
            numberPreference.setOnBindEditTextListener(
                    new EditTextPreference.OnBindEditTextListener() {
                        @Override
                        public void onBindEditText(@NonNull EditText editText) {
                            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                        }
                    });
        }
    }

    private void bindPreferenceSummaryToValue(Preference preference) {
        preference.setOnPreferenceChangeListener(this);
        SharedPreferences preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(preference.getContext());
        String preferenceString = preferences.getString(preference.getKey(), "");
        onPreferenceChange(preference, preferenceString);
    }
}
