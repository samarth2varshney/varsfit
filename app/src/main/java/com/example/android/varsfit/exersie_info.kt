package com.example.android.varsfit

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.android.varsfit.databinding.ActivityExersieInfoBinding
import java.util.*
import kotlin.collections.ArrayList

class exersie_info : AppCompatActivity() {
    private lateinit var binding: ActivityExersieInfoBinding
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExersieInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)

        var info = ArrayList<String>()
        var videorefrence = ArrayList<String>()
        var videoid = ArrayList<String>()

        if(day==2||day==5){
            info.add("A great exersie for Chest,Front Delts and Triceps")
            info.add("A great exersie for Chest,Front Delts and Triceps")
            info.add("Targets the upper Chest")
            info.add("A great to target chest and gives more range of motion")
            info.add("One of the best Triceps Exercise it Isolates triceps")
            info.add("Can work to Failure and it also isolates triceps")
            videorefrence.add("https://img.youtube.com/vi/IODxDxX7oi4/0.jpg")
            videorefrence.add("https://img.youtube.com/vi/IODxDxX7oi4/0.jpg")
            videorefrence.add("https://img.youtube.com/vi/eGo4IYlbE5g/0.jpg")
            videorefrence.add("https://img.youtube.com/vi/gsNoPYwWXeM/0.jpg")
            videorefrence.add("https://img.youtube.com/vi/iNiL57S6lZs/0.jpg")
            videorefrence.add("https://img.youtube.com/vi/iNiL57S6lZs/0.jpg")
            videoid.add("IODxDxX7oi4")
            videoid.add("IODxDxX7oi4")
            videoid.add("eGo4IYlbE5g")
            videoid.add("gsNoPYwWXeM")
            videoid.add("AETFvQonfV8")
            videoid.add("AETFvQonfV8")
        }else if(day==3||day==6){
            info.add("Pull Ups")
            info.add("Lat pulldown")
            info.add("Cabel Rows")
            info.add("Precher curls")
            info.add("Hammer curls")
            videorefrence.add("https://img.youtube.com/vi/eGo4IYlbE5g/0.jpg")
            videorefrence.add("https://img.youtube.com/vi/lueEJGjTuPQ/0.jpg")
            videorefrence.add("https://img.youtube.com/vi/IzoCF_b3cIY/0.jpg")
            videorefrence.add("https://img.youtube.com/vi/RgN216Cumtw/0.jpg")
            videorefrence.add("https://img.youtube.com/vi/0IAM2YtviQY/0.jpg")
            videoid.add("eGo4IYlbE5g")
            videoid.add("lueEJGjTuPQ")
            videoid.add("IzoCF_b3cIY")
            videoid.add("RgN216Cumtw")
            videoid.add("0IAM2YtviQY")
        }else if(day==4||day==7){
            info.add("Squats are the best body weight exercise to develop leg muscles ")
            info.add("Dumble Squats this exercise add resistance to squats which helps to build more muscles")
            info.add("Bulgarian Squats it helps increase stability and to remove muscle imbalance")
            info.add("Shoulder Press great exercise for overall shoulder development")
            info.add("Lateral Raises targets side sholuders helps you give a wider look")
            info.add("Bent Over reverse fly builds the posterior head of delt(back side of shoulder)")
            videorefrence.add("https://img.youtube.com/vi/gsNoPYwWXeM/0.jpg")
            videorefrence.add("https://img.youtube.com/vi/v_c67Omje48/0.jpg")
            videorefrence.add("https://img.youtube.com/vi/vLuhN_glFZ8/0.jpg")
            videorefrence.add("https://img.youtube.com/vi/0JfYxMRsUCQ/0.jpg")
            videorefrence.add("https://img.youtube.com/vi/E3abEP8SIh0/0.jpg")
            videorefrence.add("https://img.youtube.com/vi/WCvRMULhUVU/0.jpg")
            videoid.add("gsNoPYwWXeM")
            videoid.add("v_c67Omje48")
            videoid.add("vLuhN_glFZ8")
            videoid.add("0JfYxMRsUCQ")
            videoid.add("E3abEP8SIh0")
            videoid.add("WCvRMULhUVU")
        }

        val gif = arrayOf(R.drawable.legexercise,R.drawable.weightedsquats,R.drawable.bulgarian,R.drawable.shoulderpress,R.drawable.lateralraises,R.drawable.bentover)

        val mainnews :TextView = findViewById(R.id.heading)
        val imageView = binding.gifid
        val infotext = binding.infoid

        val bundle : Bundle?= intent.extras

        val news = bundle?.getString("news")
        val index = bundle?.getInt("position")

        mainnews.text = news
        Glide.with(this).load(gif[index!!]).into(imageView)
        infotext.text = info[index]
        Glide.with(this@exersie_info).load(videorefrence[index]).into(binding.thumbnailid)

        binding.thumbnailid.setOnClickListener {
             intent = Intent(this@exersie_info,CustomUiActivity::class.java)
             intent.putExtra("videoid",videoid)
             intent.putExtra("position",index)
            startActivity(intent)
        }

    }
}