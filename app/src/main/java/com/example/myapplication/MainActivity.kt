package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {


    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recycleradpter:Video_adapter
    val queue = Volley.newRequestQueue(this)
    val url = "https://raw.githubusercontent.com/bikashthapa01/myvideos-android-app/master/data.json"
    var videolist: ArrayList<String> = ArrayList<String>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)


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
                  }
              }

          }
          catch (e:JSONException){
              Log.i("error ",e.toString())

          }


        },Response.ErrorListener {error ->

            Log.i("JSon_request_error",error.toString())


        })

        queue.add(request)

        recyclerView = findViewById(R.id.recycler_view)
        layoutManager = LinearLayoutManager(this)
        recycleradpter = Video_adapter(videolist)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = recycleradpter

    }

}