package com.example

import android.content.Context

class SharedPreferences (val context: Context) {
    private val PREFS_NAME = "sharedPreferences"

    private val sharedPreferences = context.getSharedPreferences(
        PREFS_NAME, Context.MODE_PRIVATE
    )

    fun save(keyName: String, value: String){
        val editor = sharedPreferences.edit()
        editor.putString(keyName, value)
        editor.commit()
    }

    fun getValue(keyName: String): String?{
        return sharedPreferences.getString(keyName, null)
    }

    fun clearSharedPreferences(){
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.commit()
    }

    fun removeValue(keyName: String){
        val editor = sharedPreferences.edit()
        editor.remove(keyName)
        editor.commit()
    }
}