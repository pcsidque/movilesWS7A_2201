package com.example.app_sem05_02_agenda_20220421

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var contacts = ArrayList<Contact>()

    //creamos el adapter
    var contactAdapter = ContactAdapter(contacts)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadContacts()
        initView()
    }

    private fun initView() {
        //vinculo el adapter al rV
        val rvContact = findViewById<RecyclerView>(R.id.rvContact)
        rvContact.adapter = contactAdapter
        rvContact.layoutManager = LinearLayoutManager(this)
    }

    private fun loadContacts() {
        contacts.add(Contact("Antonia", "1234567"))
        contacts.add(Contact("Antonieta", "1248567"))
        contacts.add(Contact("Ana", "1234519"))
        contacts.add(Contact("Antonia", "1234567"))
        contacts.add(Contact("Antonieta", "1248567"))
        contacts.add(Contact("Ana", "1234519"))
        contacts.add(Contact("Alan", "1234567"))
        contacts.add(Contact("Axel", "1248567"))
        contacts.add(Contact("Ana", "1234519"))
        contacts.add(Contact("Antonia", "1234567"))
        contacts.add(Contact("Antonieta", "1248567"))
        contacts.add(Contact("Ana", "1234519"))
        contacts.add(Contact("Antonia", "1234567"))
        contacts.add(Contact("Antonieta", "1248567"))
        contacts.add(Contact("Ana", "1234519"))
        contacts.add(Contact("Antonia", "1234567"))
        contacts.add(Contact("Antonieta", "1248567"))
        contacts.add(Contact("Ana", "1234519"))
        contacts.add(Contact("Alan", "1234567"))
        contacts.add(Contact("Axel", "1248567"))
    }
}