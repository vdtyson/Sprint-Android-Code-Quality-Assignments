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

//    @Test
//    fun shouldAdd9ToUI() {
//        clearEntry()
//        onView(withId(R.id.digit9)).perform(ViewActions.click())
//        onView(withId(R.id.calcDisplay)).check(matches(isChecked()))
//    }

    /*@Test
    fun c_changeEditText_validEmails() {

        val validEmails = listOf(
            "valid@email.com",
            "more@email.co"
        )

        validEmails.forEach { email ->
            // Type text and then press the button.
            onView(withId(R.id.editTextUserEmail)).perform(
                clearText(),
                typeText(email),
                closeSoftKeyboard()
            )


            // View Matcher ("matches(...)")
            onView(withId(R.id.chkEmail)).check(matches(isChecked()))
        }
    }

    @Test
    fun d_changeEditText_invalidEmails() {

        val invalidEmails = listOf(
            "valid@email.",
            "more@emailco"
        )

        invalidEmails.forEach { email ->
            // Type text and then press the button.
            onView(withId(R.id.editTextUserEmail)).perform(
                clearText(),
                typeText(email),
                closeSoftKeyboard()
            )

            onView(withId(R.id.chkEmail)).check(matches(isNotChecked()))
        }
    }

    @Test
    fun e_changeEditText_validPasswords() {

        val validPasswords = listOf(
            "Adj$2k-29!",
            "029njnJhj3bj-JKJ$()"
        )

        validPasswords.forEach { pwd ->
            // Type text and then press the button.
            onView(withId(R.id.editTextUserPassword)).perform(
                clearText(),
                typeText(pwd),
                closeSoftKeyboard()
            )
            onView(withId(R.id.chkPassword)).check(matches(isChecked()))
        }
    }

    @Test
    fun f_changeEditText_invalidPasswords() {

        val invalidPasswords = listOf(
            "Adj\$sk-!",
            "029njnJhj3bjJKJ"
        )

        invalidPasswords.forEach { pwd ->
            // Type text and then press the button.
            onView(withId(R.id.editTextUserPassword)).perform(
                *commonViewActions(pwd)
            )
            onView(withId(R.id.chkPassword)).check(matches(isNotChecked()))
        }
    }*/

    fun clearEntry(): ViewInteraction =
        onView(withId(R.id.calcDisplay)).perform(ViewActions.click())
}