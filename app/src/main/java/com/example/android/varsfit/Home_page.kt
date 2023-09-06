package com.example.android.varsfit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.android.varsfit.databinding.ActivityHomePageBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.*
import kotlin.concurrent.schedule

class Home_page : AppCompatActivity(),TextToSpeech.OnInitListener{

    private var tts: TextToSpeech? = null
    private lateinit var binding: ActivityHomePageBinding
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()
        setContentView(binding.root)

        binding.bottomNavigationView3.setOnItemSelectedListener {

            when(it.itemId){
                R.id.Home ->replaceFragment(TrainingProgramFragment())
                R.id.person ->replaceFragment(person())
            }

            true
        }

        replaceFragment(TrainingProgramFragment())

        tts = TextToSpeech(this, this)
        val name = auth.currentUser!!.displayName

//        Timer().schedule(500){
//            val text = "hello $name welcome back"
//            tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
//        }

    }
    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(binding.frameLayout.id, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language not supported!")
            } else {
            }
        }
    }
    public override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

}