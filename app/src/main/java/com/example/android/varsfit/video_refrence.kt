package com.example.android.varsfit

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import java.util.*

class video_refrence : YouTubeBaseActivity() {

    val api_key =  "AIzaSyAmhzJlpQtI0obrvZmaby0J-qsJ6SvVsNg"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_refrence)

        val ytplayer = findViewById<YouTubePlayerView>(R.id.ytPlayer)

        val bundle : Bundle?= intent.extras

        val index = bundle?.getInt("position")
        var videoid = bundle?.getStringArrayList("videoid")

        ytplayer.initialize(api_key, object : YouTubePlayer.OnInitializedListener {

            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider?,
                player: YouTubePlayer?,
                p2: Boolean
            ) {
                player?.loadVideo(videoid!![index!!],100)
                player?.play()
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(this@video_refrence, "Video player Failed" , Toast.LENGTH_SHORT).show()
            }
        })
    }
}