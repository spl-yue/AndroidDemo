/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package android.support.v17.leanback.supportleanbackshowcase.app.settings;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v14.preference.PreferenceFragment;
import android.support.v17.leanback.supportleanbackshowcase.R;
import android.support.v17.preference.LeanbackPreferenceFragment;
import android.support.v17.preference.LeanbackSettingsFragment;
import android.support.v7.preference.DialogPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.SeekBarPreference;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Stack;

public class SettingsExampleFragment extends LeanbackSettingsFragment implements DialogPreference.TargetFragment {

    private final Stack<Fragment> fragments = new Stack<Fragment>();

    @Override
    public void onPreferenceStartInitialScreen() {
        startPreferenceFragment(buildPreferenceFragment(R.xml.prefs, null));
    }

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragment preferenceFragment,
                                             Preference preference) {
        return false;
    }

    @Override
    public boolean onPreferenceStartScreen(PreferenceFragment preferenceFragment,
                                           PreferenceScreen preferenceScreen) {
        PreferenceFragment frag = buildPreferenceFragment(R.xml.prefs, preferenceScreen.getKey());
        startPreferenceFragment(frag);
        return true;
    }

    @Override
    public Preference findPreference(CharSequence prefKey) {
        return ((PreferenceFragment) fragments.peek()).findPreference(prefKey);
    }

    private PreferenceFragment buildPreferenceFragment(int preferenceResId, String root) {
        PreferenceFragment fragment = new PrefFragment();
        Bundle args = new Bundle();
        args.putInt("preferenceResource", preferenceResId);
        args.putString("root", root);
        fragment.setArguments(args);
        return fragment;
    }

    public static class PrefFragment extends LeanbackPreferenceFragment {

        private boolean mTrackingTouch;

        @Override
        public void onCreatePreferences(Bundle bundle, String s) {
            String root = getArguments().getString("root", null);
            int prefResId = getArguments().getInt("preferenceResource");
            if (root == null) {
                addPreferencesFromResource(prefResId);
            } else {
                setPreferencesFromResource(prefResId, root);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//            final CustomSeekBarPreference seekBarPreference = (CustomSeekBarPreference)getPreferenceScreen().findPreference("prefs_initial_volume");
//            seekBarPreference.setMax(100);
//            seekBarPreference.setMin(0);
//            seekBarPreference.setSeekBarIncrement(1);
//            seekBarPreference.setValue(50);
//            seekBarPreference.getSeekBar().setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                @Override
//                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                    if (fromUser && !mTrackingTouch) {
////                        syncValueInternal(seekBar);
//                        if(seekBar.getProgress() > 60) {
//                            seekBar.setProgress(60);
//                        }
//                    }
//                }
//
//                @Override
//                public void onStartTrackingTouch(SeekBar seekBar) {
//                    mTrackingTouch = true;
//                }
//
//                @Override
//                public void onStopTrackingTouch(SeekBar seekBar) {
//                    mTrackingTouch = false;
//                    if (seekBar.getProgress() + seekBarPreference.getMin() != seekBarPreference.getValue()) {
////                        syncValueInternal(seekBar);
//                        if(seekBar.getProgress() > 60) {
//                            seekBar.setProgress(60);
//                        }
//                    }
//                }
//            });
//            seekBarPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
//                @Override
//                public boolean onPreferenceChange(Preference preference, Object newValue) {
//                    Log.d("PrefFragment", "onPreferenceChange: newValue=" + newValue.toString());
//                    if (Integer.parseInt(newValue.toString()) > 60) {
//                        seekBarPreference.getSeekBar().setProgress(60);
//                        return false;
//                    }
//                    return true;
//                }
//            });
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        @Override
        public boolean onPreferenceTreeClick(Preference preference) {
            final String[] keys = {"prefs_wifi_connect_wps", "prefs_date", "prefs_time",
                    "prefs_date_time_use_timezone", "app_banner_sample_app", "pref_force_stop",
                    "pref_uninstall", "pref_more_info"};
            if (Arrays.asList(keys).contains(preference.getKey())) {
                Toast.makeText(getActivity(), "Implement your own action handler.", Toast.LENGTH_SHORT).show();
                return true;
            }
            if (preference.getKey().equals("wakeup_disable_key")) {
//                getPreferenceScreen().findPreference("wakeup_start_time_key").setSummary("00:00 PM");
            }
            if (preference.getKey().equals("pres_screen_test")) {
                RadioButton radioButton = ((RadioButton)getActivity().findViewById(R.id.defaultid));
//                radioButton.setId(R.id.defaultid);
                if (radioButton.isChecked()) {
                    radioButton.setChecked(false);
                } else {
                    radioButton.setChecked(true);
                }
            }
//            getPreferenceScreen().setLayoutResource();
//            return super.onPreferenceTreeClick(preference);
            return true;
        }

        @Override
        public void onAttach(Context context) {
//            fragments.push(this);
            super.onAttach(context);
        }

        @Override
        public void onDetach() {
//            fragments.pop();
            super.onDetach();
        }
    }
}
