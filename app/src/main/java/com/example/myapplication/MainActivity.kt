package com.example.myapplication

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.MediaController
import android.widget.Toolbar
import android.widget.VideoView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    lateinit var toolbar:androidx.appcompat.widget.Toolbar
lateinit var Video_view:VideoView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        Video_view = findViewById(R.id.video_view)

        var mediaController = MediaController(this)
       var path = "android.resource://"+getPackageName()+"/"+R.raw.tt;
        video_view.setVideoURI(Uri.parse(path))
        video_view.setMediaController(mediaController)
        video_view.start()
        video_view.setOnTouchListener(View.OnTouchListener { v, event ->
            when(event.action){
                MotionEvent. ACTION_POINTER_UP->{
                    Log.i("MotionEvent","It is working")
                }
                MotionEvent.ACTION_POINTER_DOWN->{
                    Log.i("Action Down","Yes Man this is what i wanted")
                }
            }
            true
        })












    }
}