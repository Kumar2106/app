package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.model.VideoDetails
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log

class MainActivity : AppCompatActivity() {


    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recycleradpter:Video_adapter
    lateinit var videolist: ArrayList<VideoDetails>
    lateinit var notificationManager: NotificationManager





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        recyclerView = findViewById(R.id.recycler_view)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        videolist = ArrayList()
        recycleradpter = Video_adapter(videolist)

        Log.i("Video_list",videolist.size.toString())

        Log.i("Video_list",videolist.size.toString())
        val snapHelper = LinearSnapHelper() // Or PagerSnapHelper
        snapHelper.attachToRecyclerView(recyclerView)
        recyclerView.adapter = recycleradpter
        getJson()
        notification()






    }
    fun getJson(){
        val url = "http://ec2-54-162-94-51.compute-1.amazonaws.com:9003/videos"
        val queue = Volley.newRequestQueue(this)
        //volley request
        val request = JsonArrayRequest(Request.Method.GET,url,null,Response.Listener<JSONArray>{ response ->
            try {
               for(i in 0 until response.length()){
                   val video = response.getJSONObject(i)
                   videolist.add(VideoDetails(video.getString("createdDate") ,
                       video.getString("lastModifiedDate") ,
                       video.getInt("videoId"),
                       video.getString("headline"),
                       video.getString("videoUrl")
                   ))

               }

                recycleradpter.notifyDataSetChanged()

            }
            catch (e:JSONException){
                Log.i("error ",e.toString())

            }


        },Response.ErrorListener {error ->

            Log.i("JSon_request_error",error.toString())


        })
        Log.i("Video_list",videolist.size.toString())

        queue.add(request)
    }

    fun notification(){
        var builder = NotificationCompat.Builder(this,"MyApp")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("HI")
            .setContentText("Our notification text")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            Log.i("Notification Channel","Notification channel is working")

            val channel = NotificationChannel("MyApp", "MyApp", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        notificationManager.notify(1,builder.build())
        Log.i("Notificatio","Notification is working")

    }


}