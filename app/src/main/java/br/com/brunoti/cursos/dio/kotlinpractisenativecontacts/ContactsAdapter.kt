package br.com.brunoti.cursos.dio.kotlinpractisenativecontacts

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.brunoti.cursos.dio.kotlinpractisenativecontacts.ContactsAdapter.*
import br.com.brunoti.cursos.dio.kotlinpractisenativecontacts.databinding.ContactViewBinding

class ContactsAdapter(private val contactsList: ArrayList<Contact>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ContactViewBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(contactsList[position])
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    inner class ViewHolder(
        private val binding: ContactViewBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bindItem(contact: Contact) {
            binding.txtCardName.text = contact.name
            binding.txtCardNameAlternative.text = contact.nameAlternative
            binding.txtCardPhone.text = contact.phone
            binding.mcvContent.setOnClickListener {
                Toast.makeText(it.context, contact.name, Toast.LENGTH_SHORT).show()
            }
        }

    }
}