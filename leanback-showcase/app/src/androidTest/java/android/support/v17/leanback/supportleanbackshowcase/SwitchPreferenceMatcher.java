/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.support.v17.leanback.supportleanbackshowcase;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static com.google.android.exoplayer2.util.Assertions.checkNotNull;
import static org.hamcrest.Matchers.is;

/**
 * A custom matcher that checks the hint property of an {@link EditText}. It
 * accepts either a {@link String} or a {@link Matcher}.
 */
public class SwitchPreferenceMatcher {

    static Matcher<View> withSwitchPreferenceChecked(final Boolean isChecked) {
        return withSwitchPreferenceChecked(is(isChecked));
    }

    static Matcher<View> withSwitchPreferenceChecked(final Matcher<Boolean> isCheckedMatcher) {
        return new BoundedMatcher<View, Switch>(Switch.class) {

            @Override
            public boolean matchesSafely(Switch view) {
                final boolean isChecked = view.isChecked();
                return isCheckedMatcher.matches(isChecked);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with SwitchPreferenceChecked: ");
                isCheckedMatcher.describeTo(description);
            }
        };
    }
}