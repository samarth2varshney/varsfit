package com.example.android.varsfit

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.android.varsfit.databinding.FragmentPersonBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class person : Fragment() {

    private lateinit var binding: FragmentPersonBinding
    private lateinit var auth:FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        auth = FirebaseAuth.getInstance()
        binding = FragmentPersonBinding.inflate(layoutInflater)

        val profileimage = auth.currentUser!!.photoUrl
        Glide.with(this).load(profileimage).into(binding.profilepic)

        val name = auth.currentUser!!.displayName
        binding.username.text = "Hello $name"

        val sharedPreferences: SharedPreferences = this.requireContext().getSharedPreferences("weight", Context.MODE_PRIVATE)
        val weight: String? = sharedPreferences.getString("weight",null)

        binding.weight.text = weight

        binding.logout.setOnClickListener {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("239556124814-lvlont2p06qtp681759tn0tlg39g5mec.apps.googleusercontent.com")
                .requestEmail()
                .build()

            val googlesigninclient = GoogleSignIn.getClient(this.requireContext(),gso)
            googlesigninclient.signOut()
            startActivity(Intent(this.requireContext(),login_page::class.java))
        }

        return (binding.root)
    }

}