package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import kotlin.math.log

class MainActivity : AppCompatActivity() {


    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recycleradpter:Video_adapter
    lateinit var videolist: ArrayList<String>




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        recyclerView = findViewById(R.id.recycler_view)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        videolist = ArrayList()
        recycleradpter = Video_adapter(videolist)
        getJson()
        recycleradpter.notifyDataSetChanged()

        Log.i("Video_list",videolist.size.toString())

        Log.i("Video_list",videolist.size.toString())
        recyclerView.adapter = recycleradpter


    }
    fun getJson(){
        val url = "https://raw.githubusercontent.com/bikashthapa01/myvideos-android-app/master/data.json"
        val queue = Volley.newRequestQueue(this)
        //volley request
        val request = JsonObjectRequest(Request.Method.GET,url,null,Response.Listener<JSONObject>{ response ->
            try {
                val categories = response.getJSONArray("categories")
                for (i in 0 until categories.length()){
                    val category = categories.getJSONObject(i)
                    val videos = category.getJSONArray("videos")
                    for (i in 0 until videos.length()){
                        val video = videos.getJSONObject(i)
                        val source = video.getString("sources")
                        videolist.add(source)
                        Log.i("Url",source)
                        Toast.makeText(this,"Json request working",Toast.LENGTH_LONG).show()
                    }
                }

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


}