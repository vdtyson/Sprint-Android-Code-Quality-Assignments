package com.lambdaschool.uitesting

import android.app.Activity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.lambdaschool.uitcalculator.MainActivity
import com.lambdaschool.uitcalculator.R
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

/**
 * Basic tests showcasing simple view matchers and actions like [ViewMatchers.withId],
 * [ViewActions.click] and [ViewActions.typeText].
 *
 *
 * Note that there is no need to tell Espresso that a view is in a different [Activity].
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CalcUITest {

    /**
     * Use [ActivityScenarioRule] to create and launch the activity under test, and close it
     * after test completes. This is a replacement for [androidx.test.rule.ActivityTestRule].
     */
    @Rule
    @JvmField
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun shouldClearEntry() {
        clearEntry()
        onView(withId(R.id.calcDisplay)).check(matches(withText("0.0")))
    }

    @Test
    fun shouldAdd1to9ToUI() {
        clearEntry()
        onView(withId(R.id.digit1)).perform(ViewActions.click())
        onView(withId(R.id.calcDisplay)).check(matches(withText("1.0")))

        clearEntry()
        onView(withId(R.id.digit2)).perform(ViewActions.click())
        onView(withId(R.id.calcDisplay)).check(matches(withText("2.0")))

        clearEntry()
        onView(withId(R.id.digit3)).perform(ViewActions.click())
        onView(withId(R.id.calcDisplay)).check(matches(withText("3.0")))

        clearEntry()
        onView(withId(R.id.digit4)).perform(ViewActions.click())
        onView(withId(R.id.calcDisplay)).check(matches(withText("4.0")))

        clearEntry()
        onView(withId(R.id.digit5)).perform(ViewActions.click())
        onView(withId(R.id.calcDisplay)).check(matches(withText("5.0")))

        clearEntry()
        onView(withId(R.id.digit6)).perform(ViewActions.click())
        onView(withId(R.id.calcDisplay)).check(matches(withText("6.0")))

        clearEntry()
        onView(withId(R.id.digit7)).perform(ViewActions.click())
        onView(withId(R.id.calcDisplay)).check(matches(withText("7.0")))

        clearEntry()
        onView(withId(R.id.digit8)).perform(ViewActions.click())
        onView(withId(R.id.calcDisplay)).check(matches(withText("8.0")))

        clearEntry()
        onView(withId(R.id.digit9)).perform(ViewActions.click())
        onView(withId(R.id.calcDisplay)).check(matches(withText("9.0")))
    }

    fun clearEntry(): ViewInteraction =
        onView(withId(R.id.clearEntry)).perform(ViewActions.click())
}