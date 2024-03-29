package com.aman.firebasedbapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signUpButton = findViewById<Button>(R.id.btnSignUp)
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etMail = findViewById<TextInputEditText>(R.id.etMail)
        val userId = findViewById<TextInputEditText>(R.id.etUsername)
        val Userpassword = findViewById<TextInputEditText>(R.id.etPassword)

        signUpButton.setOnClickListener {
            val name = etName.text.toString()
            val mail = etMail.text.toString()
            val uniqueId = userId.text.toString()
            val password = Userpassword.text.toString()

            val user = User(name, mail, password, uniqueId)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(uniqueId).setValue(user).addOnSuccessListener{
                etName.text?.clear()
                etMail.text?.clear()
                userId.text?.clear()
                Userpassword.text?.clear()
                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
        val signInText = findViewById<TextView>(R.id.tvSignIN)
        signInText.setOnClickListener {
            val openSignInActivity = Intent(this, SignInActivity::class.java)
            startActivity(openSignInActivity)
        }

    }
}