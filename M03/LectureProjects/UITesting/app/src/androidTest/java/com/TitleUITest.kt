package com

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.uitesting.MainActivity
import com.example.uitesting.R
import kotlinx.android.synthetic.main.activity_main.view.*
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TitleUITest {

    companion object {
        private const val TITLE_TEXT = "This Is My Title"
    }
    @Rule // junit test rule
    @JvmField // allows for compatability for rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun a_changeTitle_sameActivity() {

        // SETUP
        onView(withId(R.id.title_input))
            .perform(typeText(TITLE_TEXT), closeSoftKeyboard())

        // EXECUTE
        onView(withId(R.id.change_title_button)).perform(click())

        // CHECK
        onView(withId(R.id.title_view)).check(matches(withText(TITLE_TEXT)))
    }

    @Test
    fun b_changeTitle_newActivity() {

        // SETUP
        onView(withId(R.id.title_input))
            .perform(typeText(TITLE_TEXT), closeSoftKeyboard())

        // EXECUTE
        onView(withId(R.id.change_title_button)).perform(click())

        // CHECK
        onView(withId(R.id.show_title_view)).check(matches(withText(TITLE_TEXT)))

        // onView(withId(R.id.title_view)).check(matches(withText(TITLE_TEXT)))
    }


}