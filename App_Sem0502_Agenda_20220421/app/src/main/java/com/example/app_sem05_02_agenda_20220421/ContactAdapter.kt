package com.example.app_sem05_02_agenda_20220421

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(var contacts: ArrayList<Contact>): RecyclerView.Adapter<ContactPrototype>() {
    //crea el prototipo para c/fila
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactPrototype {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_contact, parent, false)

        return ContactPrototype(view)
    }

    //vincula la informacion con la vista para c/elemento
    override fun onBindViewHolder(holder: ContactPrototype, position: Int) {
        holder.bind(contacts.get(position))
    }

    //deveulve la cantidad de elementos
    override fun getItemCount(): Int {
        return contacts.size
    }

}

class ContactPrototype(itemView: View) : RecyclerView.ViewHolder(itemView) {
    //vinculamos los controles del prototype con variables
    val tvName = itemView.findViewById<TextView>(R.id.tvName)
    val tvTelephone = itemView.findViewById<TextView>(R.id.tvTelephone)

    //vinculamos las variables con la clase
    fun bind(contact: Contact){
        tvName.text = contact.name
        tvTelephone.text = contact.telephone
    }
}