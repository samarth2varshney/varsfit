package com.example.android.varsfit

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.android.varsfit.databinding.ActivityLoginPageBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class login_page : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var googlesigninclient : GoogleSignInClient
    private lateinit var database : DatabaseReference
    private lateinit var binding: ActivityLoginPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("Users")

        val sharedPreferences: SharedPreferences = getSharedPreferences("gender", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        val weigh: SharedPreferences = getSharedPreferences("weight", Context.MODE_PRIVATE)
        val edit: SharedPreferences.Editor = weigh.edit()

        val check = GoogleSignIn.getLastSignedInAccount(this)
        if(check!=null){
//            database.child(userid).get().addOnSuccessListener {
//                if (it.exists()) {
//                    editor.putInt("gender",it.child("gender").value.toString().toInt())
//                    editor.apply()
//                    edit.putString("weight",it.child("weight").value.toString())
//                    edit.apply()
                    startActivity(Intent(this,Home_page::class.java))
                    finish()
//                }
//            }

        }

        binding.signInbtn.setOnClickListener {
            binding.progressbar.visibility = View.VISIBLE
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("239556124814-lvlont2p06qtp681759tn0tlg39g5mec.apps.googleusercontent.com")
                .requestEmail()
                .build()
            googlesigninclient = GoogleSignIn.getClient(this,gso)
            signingoogl()
        }

    }

    private fun signingoogl() {
        val signinintent = googlesigninclient.signInIntent
        launcher.launch(signinintent)
    }
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
        if(result.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if(task.isSuccessful){
            val account : GoogleSignInAccount? = task.result
            if(account!=null){
                updateUI(account)
            }
        }else{
            Toast.makeText(this,task.exception.toString(), Toast.LENGTH_SHORT).show()
        }

    }
    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken,null)
        auth.signInWithCredential(credential).addOnCompleteListener{
            if(it.isSuccessful){
                val intent = Intent(this,male_female::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }
}