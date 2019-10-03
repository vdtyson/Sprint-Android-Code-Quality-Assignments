package com



import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isNotChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
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
class ValidationTests {

    @Rule
    @JvmField
    var activityScenariorule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun changeEditText_validEmails() {
        val validEmails = listOf(
            "valid@email.com",
            "test@gmail.com"
        )

        validEmails.forEach {email ->
            onView(withId(R.id.email_input)).perform(
                clearText(),
                typeText(email),
                closeSoftKeyboard()
            )
        }
        // CHECK
        onView(withId(R.id.check_email_box)).check(matches(isNotChecked()))
    }

    @Test
    fun changedEditText_validPasswords() {

        // SETUP
        val validPasswords = listOf(
            "Adj$2k-29!",
            "029njnJhj3bj-JKJ$()"
        )

        // EXECUTE
        validPasswords.forEach {
            onView(withId(R.id.password_input)).perform(
                clearText(),
                typeText(it),
                closeSoftKeyboard()
            )
        }
    }

}