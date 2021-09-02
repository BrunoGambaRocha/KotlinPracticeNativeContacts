package br.com.brunoti.cursos.dio.kotlinpractisenativecontacts

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.brunoti.cursos.dio.kotlinpractisenativecontacts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    val REQUEST_CONTACT = 1
    val LINEAR_LAYOUT_VERTICAL = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_CONTACT)
        } else {
            setContacts()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CONTACT) setContacts()
    }

    private fun setContacts() {
        val contactList: ArrayList<Contact> = ArrayList()

        //val selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " LIKE ?"
        //val selectionArgs: Array<String> = arrayOf("Name Test%")

        val cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)

        if (cursor != null) {
            while (cursor.moveToNext()) {
                contactList.add(
                    Contact(
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE)),
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    ))
            }
            cursor.close()
        }

        val adapter by lazy { ContactsAdapter(contactList) }

        binding.rcvContact.layoutManager = LinearLayoutManager(this, LINEAR_LAYOUT_VERTICAL, false)
        binding.rcvContact.adapter = adapter

    }
}