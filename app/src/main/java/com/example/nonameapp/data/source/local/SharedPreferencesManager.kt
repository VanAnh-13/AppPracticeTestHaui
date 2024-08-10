package com.example.nonameapp.data.source.local

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.nonameapp.model.User
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date

object SharedPreferencesManager {
    private const val PREF_NAME = "app_preferences"
    private const val ACCESS_TOKEN = "access_token"

    fun saveToken(context: Context, token: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(ACCESS_TOKEN, token).apply()
    }

    fun getToken(context: Context): String? {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(ACCESS_TOKEN, null)
    }

    fun clearToken(context: Context) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(ACCESS_TOKEN).apply()
    }

    fun saveUserInfo(context: Context, user: User) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("user_id", user.id)
            putString("user_avatar", user.avatar)
            putString("user_email", user.email)
            putString("user_fullname", user.fullName)
            putString("user_date_of_birth", user.dateOfBirth.toString())
            putString("user_role", user.role)
            putString("user_star", user.star.toString())
            putString("user_gender", user.gender)
            putString("user_created_at", user.createdAt.toString())
            putString("user_updated_at", user.updatedAt.toString())
            putString("user_is_locked", user.isLocked.toString())
            apply()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat")
    fun getUserInfo(context: Context): User? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("user_id", null)
        val userAvatar = sharedPreferences.getString("user_avatar", null)
        val userEmail = sharedPreferences.getString("user_email", null)
        val userFullName = sharedPreferences.getString("user_fullname", null)
        val userDateOfBirth = sharedPreferences.getString("user_date_of_birth", null)
        val userRole = sharedPreferences.getString("user_role", null)
        val userStar = sharedPreferences.getString("user_star", null)
        val userGender = sharedPreferences.getString("user_gender", null)
        val userCreatedAt = sharedPreferences.getString("user_created_at", null)
        val userUpdatedAt = sharedPreferences.getString("user_updated_at", null)
        val userIsLocked = sharedPreferences.getString("user_is_locked", null)

        return if (userId != null && userAvatar != null && userEmail != null && userFullName != null && userDateOfBirth != null && userRole != null && userStar != null && userGender != null && userCreatedAt != null && userUpdatedAt != null && userIsLocked != null) {
                User(
                    userId,
                    userEmail,
                    userFullName,
                    convertDate(userDateOfBirth),
                    userIsLocked.toBoolean(),
                    userAvatar,
                    userRole,
                    userStar.toInt(),
                    userGender,
                    convertDate(userCreatedAt),
                    convertDate(userUpdatedAt),
                    ""
                )
        } else {
            return null;
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun convertDate(data: String): Date {
        val dateTimeString = "2024-06-30T09:50:18.977Z"

        // Parse the string into an Instant
        val instant = Instant.parse(dateTimeString)
        return Date.from(instant)
    }
}
