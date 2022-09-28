package com.kinnu.wallpaper.Adapter

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kinnu.wallpaper.Model.ImagelistModel
import com.kinnu.wallpaper.R
import android.content.Context as Context1

class ImageAdapter(list: ArrayList<ImagelistModel>) : RecyclerView.Adapter<ImageAdapter.ImageHolder>() {

    var list = list
    lateinit var context: Context1

    class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var imgitem = itemView.findViewById<ImageView>(R.id.img)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
          context = parent.context
          return ImageHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_item,parent,false))


    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ImageHolder, position: Int) {

        Glide.with(context).load(list.get(position).download_url).into(holder.imgitem)

        holder.itemView.setOnClickListener {

             var dialog = Dialog(context)
             dialog.window?.setBackgroundDrawable(ColorDrawable(android.R.color.transparent))
             dialog.setContentView(R.layout.img_dialog_item)


            var imgitem1 = dialog.findViewById<ImageView>(R.id.img_dialog)
            Glide.with(context).load(list.get(position).download_url).into(imgitem1)

            dialog.show()

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}