package com.example.cathaybktest

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.cathaybktest.model.Image
import com.github.chrisbanes.photoview.PhotoView

class ImageDetailActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_detail)
            try {
                val intent = intent
                val bundle = intent.extras
                    val images: ArrayList<Image>? = bundle?.getParcelableArrayList("attraction_images")
                    val viewPager: ViewPager2 = findViewById(R.id.viewPager)
                    val adapter = ImagePagerAdapter(images)
                    viewPager.adapter = adapter

                    val currentPosition = intent.getIntExtra("position", 0)
                    viewPager.setCurrentItem(currentPosition, false)

            } catch (e: Exception) {
                Log.e("ERROR", "Error getting images: " + e.message)
            }

    }
}

class ImagePagerAdapter(private val images:  ArrayList<Image>?) : RecyclerView.Adapter<ImagePagerAdapter.ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val photoView = PhotoView(parent.context)
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        photoView.layoutParams = layoutParams
        return ImageViewHolder(photoView)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images!!.get(position)
        Glide.with(holder.itemView.context)
            .load(image.src)
            .into(holder.photoView)
    }

    override fun getItemCount(): Int {
        return images!!.size
    }

    class ImageViewHolder(val photoView: PhotoView) : RecyclerView.ViewHolder(photoView)
}