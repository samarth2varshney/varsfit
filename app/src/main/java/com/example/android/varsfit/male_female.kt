package com.example.android.varsfit

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.android.varsfit.databinding.ActivityMaleFemaleBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class male_female : AppCompatActivity() {

    private lateinit var binding: ActivityMaleFemaleBinding
    private lateinit var database : DatabaseReference
    private lateinit var Auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaleFemaleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Auth = FirebaseAuth.getInstance()

        //email data
        val userid = Auth.currentUser!!.uid
        val username = Auth.currentUser!!.displayName.toString()

        //mobile storage variables
        val sharedPreferences:SharedPreferences = getSharedPreferences("gender", Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor = sharedPreferences.edit()
        val weigh:SharedPreferences = getSharedPreferences("weight",Context.MODE_PRIVATE)
        val edit:SharedPreferences.Editor = weigh.edit()

        //give database the path
        database = FirebaseDatabase.getInstance().getReference("Users")

        /*check if userid is in database*/
        database.child(userid).get().addOnSuccessListener {
            if (it.exists()) {
                binding.progressBar.visibility = View.VISIBLE
                editor.putInt("gender",it.child("gender").value.toString().toInt())
                editor.apply()
                edit.putString("weight",it.child("weight").value.toString())
                edit.apply()
                startActivity(Intent(this,Home_page::class.java))
                finish()
            }
        }

        val female = binding.femaleid
        val male = binding.maleid
        val priorExperienceyes = binding.prioryes


        binding.sumbitid.setOnClickListener {
            val weight = binding.weightid.text.toString()
            var gender = 0

            edit.putString("weight",weight)
            edit.apply()
            if(male.isChecked){
                gender = 1
                editor.putInt("gender",1)
                editor.apply()}
            if(female.isChecked){
                editor.putInt("gender",0)
                editor.apply()
            }

            //storing data into firebase
            val User = user(username,weight,gender.toString())
            database.child(userid).setValue(User).addOnSuccessListener {

                Toast.makeText(this,"Successfully saved",Toast.LENGTH_LONG).show()
            }.addOnFailureListener{
                Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show()
            }
            //end of storing data to fire store

            if(priorExperienceyes.isChecked){
                startActivity(Intent(this@male_female,prior_experience::class.java))
                finish()
            }else{
                startActivity(Intent(this@male_female,Home_page::class.java))
                finish()
            }
        }


    }
}