package com.gmail.killian.tp03_sowa_killian.helpers.validators

import android.util.Patterns

/**
 * created by : Mustufa Ansari
 * Email : mustufaayub82@gmail.com
 */

object FieldValidators {

    /**
     * checking pattern of email
     * @param email input email
     * @return true if matches with email address else false
     */
    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    /**
     * checking pattern of url
     * @param url input url
     * @return true if matches with url else false
     */
    fun isValidUrl(url: String): Boolean {
        return Patterns.WEB_URL.matcher(url).matches()
    }

    /**
     * checking pattern of phoneNumber
     * @param phoneNumber input phoneNumber
     * @return true if matches with phoneNumber else false
     */
    fun isValidPhoneNumber(phoneNumber: String): Boolean {
        return Patterns.PHONE.matcher(phoneNumber).matches()
    }

}