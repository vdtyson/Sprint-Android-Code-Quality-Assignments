package com.example.uitesting.util

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern

object Validator {
    // PASSWORD Validation:
    // Ref: https://stackoverflow.com/questions/36574183/how-to-validate-password-field-in-android
    private val PASSWORD_PATTERN: Pattern =
        Pattern.compile("^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$")

    fun isEmailValid(target: CharSequence) : Boolean {

        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    fun isPasswordValid(target: CharSequence) : Boolean {
        return !TextUtils.isEmpty(target) && PASSWORD_PATTERN.matcher(target).matches()
    }
}