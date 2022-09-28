package com.kinnu.wallpaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.kinnu.wallpaper.Adapter.ImageAdapter
import com.kinnu.wallpaper.Model.ImagelistModel
import com.kinnu.wallpaper.databinding.ActivityMainBinding
import org.json.JSONArray
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
        var url =  "https://picsum.photos/v2/list"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var list = ArrayList<ImagelistModel>()

        var request  = StringRequest(Request.Method.GET,url,{Response ->


            var array = JSONArray(Response)


            for (x in 0..array.length()-1){
                var obj =array.getJSONObject(x)
                var id = obj.getInt("id")
                var author = obj.getString("author")
                var width = obj.getInt("width")
                var height = obj.getInt("height")
                var url = obj.getString("url")
                var download_url = obj.getString("download_url")


                var data = ImagelistModel(id,author,width,height,url,download_url)
                list.add(data)

            }

              binding.wallpaper.layoutManager = GridLayoutManager(applicationContext,2)
              binding.wallpaper.adapter = ImageAdapter(list)

          },Response.ErrorListener{


        })

        var que = Volley.newRequestQueue(this)
        que.add(request)

    }
}