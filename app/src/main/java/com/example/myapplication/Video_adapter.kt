package com.example.myapplication

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.VideoView
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.VideoDetails

class Video_adapter(var videolist: ArrayList<VideoDetails>):RecyclerView.Adapter<Video_adapter.ViewHolder>(){


    class ViewHolder(view: CardView):RecyclerView.ViewHolder(view){
        val video:VideoView = view.findViewById(R.id.video)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_view,parent,false) as CardView
        Log.i("OnCreateView","OnCreateViewHolder is working")
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return videolist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var video = videolist[position]
        holder.video.setVideoURI(video.videoUrl.toUri())
        holder.video.start()

    }
}