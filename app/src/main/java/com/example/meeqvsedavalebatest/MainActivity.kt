package com.example.meeqvsedavalebatest

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.meeqvsedavalebatest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val mails = mutableListOf<String>()
    val deletedUsersList = mutableListOf<String>()
    val mapOfUsers = mutableMapOf<String, UsersList>()


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddUser.setOnClickListener {
            addUserAndCheckforDuplicates()
        }
        binding.btnRemoveUser.setOnClickListener {
            removeUser()
        }
        binding.btnUpdateUser.setOnClickListener {
            updateUser()
        }
    }

    private fun addUserAndCheckforDuplicates() {

        val mail = binding.etEmail.text.toString()
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val age = binding.etAge.text.toString()
        val userToStore = UsersList(firstName, lastName, mail, age)

        if (mails.contains("$mail")) {
            binding.tvSuccessError.text = "Error, user $mail already exists"
            binding.tvSuccessError.setTextColor(Color.RED)
        } else if (!mail.isNullOrEmpty()) {
            mails.add(mail)
            mapOfUsers[mail] = userToStore
            binding.tvSuccessError.setTextColor(Color.GREEN)
            binding.tvSuccessError.text = "$mail added"
            binding.tvActiveUsers.text = "Active Users: ${mails.size}"
        } else {
            binding.tvSuccessError.text = "Error"
            binding.tvSuccessError.setTextColor(Color.RED)
        }
    }

    private fun removeUser() {
        val mail = binding.etEmail.text.toString()


        if (mails.contains("$mail")) {
            mails.remove(mail.toString())
            binding.tvSuccessError.text = "user $mail removed"
            binding.tvSuccessError.setTextColor(Color.GREEN)
            deletedUsersList.add(mail.toString())
            binding.tvDeletedUsers.text = "Deleted Users: ${deletedUsersList.size}"
            binding.tvActiveUsers.text = "Active Users: ${mails.size}"
        } else {
            binding.tvSuccessError.text = "user not found"
            binding.tvSuccessError.setTextColor(Color.RED)
        }
    }

    private fun updateUser() {
        val email = binding.etEmail.text.toString()
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val age = binding.etAge.text.toString()

        if (mapOfUsers.containsKey(email)) {
            mapOfUsers[email]!!.firstName = firstName
            mapOfUsers[email]!!.lastName = lastName
            mapOfUsers[email]!!.age = age
            Toast.makeText(this, "User is now updated!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No user found!", Toast.LENGTH_SHORT).show()
        }
    }

}