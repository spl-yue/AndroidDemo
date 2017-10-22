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

import android.app.Activity;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.PreferenceMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v17.leanback.supportleanbackshowcase.app.settings.SettingsExampleActivity;
import android.support.v7.preference.Preference;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Switch;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.assertion.ViewAssertions.selectedDescendantsMatch;
import static android.support.test.espresso.matcher.PreferenceMatchers.withKey;
import static android.support.test.espresso.matcher.PreferenceMatchers.withSummaryText;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.v17.leanback.supportleanbackshowcase.SwitchPreferenceMatcher.withSwitchPreferenceChecked;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;


/**
 * Basic tests showcasing simple view matchers and actions like {@link ViewMatchers#withId},
 * {@link ViewActions#click} and {@link ViewActions#typeText}.
 * <p>
 * Note that there is no need to tell Espresso that a view is in a different {@link Activity}.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class PreferenceUITest{

    public static final String STRING_TO_BE_TYPED = "Espresso";

    /**
     * A JUnit {@link Rule @Rule} to launch your activity under test. This is a replacement
     * for {@link ActivityInstrumentationTestCase2}.
     * <p>
     * Rules are interceptors which are executed for each test method and will run before
     * any of your setup code in the {@link Before @Before} method.
     * <p>
     * {@link ActivityTestRule} will create and launch of the activity for you and also expose
     * the activity under test. To get a reference to the activity you can use
     * the {@link ActivityTestRule#getActivity()} method.
     */
    @Rule
    public ActivityTestRule<SettingsExampleActivity> mActivityRule = new ActivityTestRule<>(
            SettingsExampleActivity.class);


    @Test
    public void testListPreference() {
        // Type text and then press the button.
//        onView(withId(R.id.editTextUserInput))
//                .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
//        onView(withId(R.id.changeTextBt)).perform(click());
//
//        // Check that the text was changed.
//        onView(withId(R.id.actionIcon)).check(matches(withText(STRING_TO_BE_TYPED)));

//        onData(PreferenceUIMatcher.withPreferenceKey("prefs_parental_control_level_key")).perform(click());
//        onData(PreferenceMatchers.withKey("prefs_parental_control_level_key")).check(matches(isCompletelyDisplayed()));
//        onData(anything()).inAdapterView(allOf(isDescendantOfA(withId(R.id)),withId(R.id.)));
//        onData(allOf(is(instanceOf(Preference.class)), PreferenceMatchers.withKey("prefs_parental_control_level_key"))).perform(click());
//        onData(is(instanceOf(Preference.class))).check(matches(isCompletelyDisplayed()));

//        onView(allOf(withText("Parental Control"))).check(matches(isDisplayed()));
//        onView(withChild(withText("Parental Control"))).check(matches(isDisplayed()));

        //首先根据title和text找出preference的title View，然后根据summary和text找出preference的summary View
        //然后分别用withChild可以找出他们的父View，再根据allOf来判断他们的父View是否相同
//        onView(allOf(withChild(allOf(withResourceName("title"), withText("Parental Control"))),
//                withChild(allOf(withResourceName("summary"), withText("Everyone"))))).check(matches(isDisplayed()));

        //首先根据title和text找出preference的title View,然后用withChild找出父View，然后查找父View的子元素中是否有要找的summary
        onView(withChild(allOf(withResourceName("title"), withText("Parental Control"))))
                .check(selectedDescendantsMatch(withResourceName("summary"), withText("Everyone"))).check(matches(isDisplayed()));
//                withChild(allOf(withResourceName("summary"), withText("Everyone"))))).check(matches(isDisplayed()));

        //Click preference
        onView(withChild(allOf(withResourceName("title"), withText("Parental Control")))).perform(click());

    }

    @Test
    public void testSwitchPreference() {
        //Click SwitchPreference
        onView(withChild(withChild(allOf(withResourceName("title"), withText("Auto power on"))))).check(matches(isDisplayed())).perform(click());

        //Check SwitchPreference is checked.
//        onView(withChild(withChild(allOf(withResourceName("title"), withText("Auto power on")))))
//                .check(selectedDescendantsMatch(isAssignableFrom(Switch.class), withResourceName("switch_widget"))).check(matches(isDisplayed()));
        onView(withChild(withChild(allOf(withResourceName("title"), withText("Auto power on")))))
                .check(selectedDescendantsMatch(isAssignableFrom(Switch.class), withSwitchPreferenceChecked(true))).check(matches(isDisplayed()));

    }
}