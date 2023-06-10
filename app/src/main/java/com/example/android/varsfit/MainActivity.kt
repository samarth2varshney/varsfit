package com.example.android.varsfit

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.android.varsfit.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity(),TextToSpeech.OnInitListener{
    private lateinit var binding: ActivityMainBinding
    private var tts: TextToSpeech? = null

    var listofanimal = ArrayList<Animal>()
    var adapter:AnimalsAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        val sharedPreferences: SharedPreferences = getSharedPreferences("gender",Context.MODE_PRIVATE)
        val check:Int = sharedPreferences.getInt("gender",0)
        tts = TextToSpeech(this, this)

        /* if male */
        if(check == 1){
            // on monday
            if(day==2||day==5){
                listofanimal.add(
                    Animal("pushups","works chest and triceps", R.drawable.adm6))
                listofanimal.add(
                    Animal("Benches press","isolates chest and triceps", R.drawable.sagar1))
                listofanimal.add(
                    Animal("incline bench press","works more upper chest", R.drawable.pull))
                listofanimal.add(
                    Animal("chest fly","isolates the chest muscles", R.drawable.squats))
                listofanimal.add(
                    Animal("dumblle overhead press","isolates the triceps", R.drawable.bench_press))
                listofanimal.add(
                    Animal("cabel tricep extension","also a great exersise for triceps", R.drawable.latpulldonw))
                adapter = AnimalsAdapter(this,listofanimal)
                binding.list.adapter = adapter
                binding.list.isClickable = true}
            // on tuesday
            else if(day==3||day==6){
                listofanimal.add(
                    Animal("pull ups","works biceps and triceps", R.drawable.pull))
                listofanimal.add(
                    Animal("lat pulldown","isolates the back and biceps",  R.drawable.latpulldonw))
                listofanimal.add(
                    Animal("cabel rows","gives thickness to the back", R.drawable.cabelrow))
                listofanimal.add(
                    Animal("precher curls","works qudas and hamstrings", R.drawable.preachercurls))
                listofanimal.add(
                    Animal("hammer curls","great exersice for chest", R.drawable.hammercurls))

                adapter = AnimalsAdapter(this,listofanimal)
                binding.list.adapter = adapter
                binding.list.isClickable = true}
            // on wednesday
            else if(day==4||day==7){
                listofanimal.add(
                    Animal("squats","works chest and triceps", R.drawable.squats))
                listofanimal.add(
                    Animal("weighted squats","works the abs", R.drawable.weighted_squats))
                listofanimal.add(
                    Animal("bulgarian squats","works biceps and almost whole back muscles", R.drawable.bulgarian_split_squat))
                listofanimal.add(
                    Animal("shoulder press","works qudas and hamstrings", R.drawable.shoulder_press))
                listofanimal.add(
                    Animal("lateral raises","great exersice for chest", R.drawable.lateral_raise))
                listofanimal.add(
                    Animal("bent over reverse fly","great exersie for baack", R.drawable.bentover_fly))
                adapter = AnimalsAdapter(this,listofanimal)
                binding.list.adapter = adapter
                binding.list.isClickable = true}

            if(day==1){
                listofanimal.add(
                    Animal("chill","No exerise Today", R.drawable.chill))
                adapter = AnimalsAdapter(this,listofanimal)
                binding.list.adapter = adapter
                }
        }

        if(check == 0){

            listofanimal.add(
                Animal("squats","works qudas and hamstrings", R.drawable.squats))
            listofanimal.add(
                Animal("bench press","great exersice for chest", R.drawable.bench_press))
            listofanimal.add(
                Animal("lat pulldown","great exersie for baack", R.drawable.latpulldonw))
            adapter = AnimalsAdapter(this,listofanimal)
            binding.list.adapter = adapter
            binding.list.isClickable = true
        }

        binding.list.setOnItemClickListener { parent, view, position, id ->
            intent = Intent(this@MainActivity, exersie_info::class.java)
            intent.putExtra("news",listofanimal[position].name)
            intent.putExtra("position",position)
             startActivity(intent)
        }


        Timer().schedule(400){
            val text = "Let's start with some warm-ups"
            tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
        }


    }
    class AnimalsAdapter: BaseAdapter  {

        var listofanimal=ArrayList<Animal>()
        var context:Context?=null
        constructor(context:Context,listofanimal: ArrayList<Animal>):super(){
            this.listofanimal=listofanimal
            this.context=context
        }
        override fun getCount(): Int {
            return listofanimal.size
        }

        override fun getItem(p0: Int): Any {
           return "TEST STRING"
        }

        override fun getItemId(p0: Int): Long {
            val id = p0.toLong()
            return id
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

            val animal = listofanimal[p0]
            var inflator = LayoutInflater.from(context)
            var myview = inflator.inflate(R.layout.animail_ticket,null)
            val title= myview.findViewById<TextView>(R.id.title1)
            title.text = animal.name
            val des= myview.findViewById<TextView>(R.id.desciption)
            des.text = animal.des
            val img :ImageView= myview.findViewById(R.id.image)
            animal.image?.let { img.setImageResource(it) }

            return myview
        }

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