package com.example.android.varsfit

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
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
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

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

        val check = GoogleSignIn.getLastSignedInAccount(this)
        if(check!=null){
            startActivity(Intent(this,Home_page::class.java))
            finish()
        }

        var gender = "Male"
        val languages = resources.getStringArray(R.array.programming_languages)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_menu, languages)
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        autocompleteTV.setAdapter(arrayAdapter)

        binding.transparentImage2.visibility = View.GONE
        binding.spinner3.visibility = View.GONE

        binding.btnSignUp.setOnClickListener {
            val name = binding.etName2.text.toString()
            val email = binding.etEmail2.text.toString()
            val password = binding.etPassword2.text.toString()
            val cpassword = binding.etConfirmPass2.text.toString()


            if (name.isEmpty()) {
                binding.etName2.error = "Name is required."
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                binding.etEmail2.error = "Email is required."
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                binding.etPassword2.error = "Password is required."
                return@setOnClickListener
            }
            if (cpassword.isEmpty()) {
                binding.etConfirmPass2.error = "Password Confirmation is required."
                return@setOnClickListener
            }

            if (cpassword != password) {
                binding.etConfirmPass2.error = "Password doesn't match"
                return@setOnClickListener
            }
            if (password.length < 7) {
                binding.etConfirmPass2.error = "Password should be greater than 7 words"
                return@setOnClickListener
            }

            binding.spinner3.visibility = View.VISIBLE
            binding.transparentImage2.visibility = View.VISIBLE

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign up success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                            .build()

                        user?.updateProfile(profileUpdates)
                            ?.addOnCompleteListener { profileUpdateTask ->
                                if (profileUpdateTask.isSuccessful) {
                                    // Display name updated successfully
                                    Toast.makeText(
                                        baseContext, "Sign Up and Authentication successful",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    val collectionRef =
                                        FirebaseFirestore.getInstance().collection("users")
                                    val documentRef = collectionRef.document(user.uid)

                                    var testmap: HashMap<String, HashMap<String, String>>? = HashMap<String, HashMap<String, String>>()
                                    val data = hashMapOf(
                                        "username" to name,
                                        "gender" to gender,
                                        "email" to email,
                                        "myPlans" to testmap,
                                    )

                                    documentRef.set(data)
                                        .addOnSuccessListener {
                                            val intent = Intent(this, Home_page::class.java)
                                            startActivity(intent)
                                            finish()
                                        }
                                        .addOnFailureListener { e ->
                                        }

                                } else {
                                    Toast.makeText(
                                        baseContext, "Failed to update display name.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    } else {
                        binding.spinner3.visibility = View.GONE
                        binding.transparentImage2.visibility = View.GONE

                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

        }

    }

    private fun signingoogl() {
        Log.i("samarth","signingoogl()")
        val signinintent = googlesigninclient.signInIntent
        launcher.launch(signinintent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
        Log.i("samarth", "${result.resultCode} ${Activity.RESULT_OK}")
        if(result.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }else{
            Toast.makeText(this,"Chud gaya",Toast.LENGTH_LONG).show()
        }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        Log.i("samarth","handleResults()")
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
        Log.i("samarth","updateUI()")
        val credential = GoogleAuthProvider.getCredential(account.idToken,null)
        auth.signInWithCredential(credential).addOnCompleteListener{
            if(it.isSuccessful){
                val intent = Intent(this,Home_page::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }
}