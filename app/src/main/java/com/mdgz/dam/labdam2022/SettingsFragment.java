package com.mdgz.dam.labdam2022;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import java.io.File;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        Preference preference = findPreference("preference_archivo");
        CheckBoxPreference checkbox = findPreference("check_box_preference_2");
        preference.setOnPreferenceClickListener(preference1 -> {
            NavHostFragment navHostFragment =
                    (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
            NavController navController = navHostFragment.getNavController();

            navController.navigate(R.id.action_settingsFragment_to_visualizaArchivo);

            return true;
        });
        checkbox.setOnPreferenceClickListener(preference1 -> {
            CheckBoxPreference preferencia = (CheckBoxPreference) preference1;
            if (!preferencia.isChecked()) {
                File dir = getContext().getFilesDir();
                File file = new File(dir, "datosBusqueda.json");
                file.delete();
            }

            return true;
        });
    }

}