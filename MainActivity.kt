package com.example.brahimmasmoudi.changelanguages

import android.content.Context
import android.content.DialogInterface
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.button
import java.util.Locale

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocale()
        setContentView(R.layout.activity_main)

        button.setOnClickListener({
            onCreateDialog()
        })
    }

    private fun onCreateDialog() {
        val listItems = arrayOf("French", "عربية", "English")
        val builder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val view = inflater.inflate(R.layout.activity_main, null)
        builder.setView(view)
        builder.setSingleChoiceItems(listItems, -1, DialogInterface.OnClickListener { dialog, i ->

            if (i == 0) {
                setLocale("fr")
                recreate()
            } else if (i == 1) {
                setLocale("ar")
                recreate()
            } else if (i == 2) {
                setLocale("en")
                recreate()
            }
            dialog.dismiss()

        })
        builder.setNegativeButton("cancel", DialogInterface.OnClickListener { dialog, id -> })
        val dialog = builder.create()
        dialog.show()
    }

    private fun setLocale(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config,
                baseContext.resources.displayMetrics)
        val editor = getSharedPreferences("settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_language ", lang)
        editor.apply()
    }

    private fun loadLocale() {
        val prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val language = prefs.getString("My_language", "")
        setLocale(language)
    }
}

