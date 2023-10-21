package com.example.cathaybktest.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cathaybktest.ContentFragment
import com.example.cathaybktest.R
import com.example.cathaybktest.databinding.ItemAttractionBinding
import com.example.cathaybktest.model.Attraction

    class AttractionAdapter : ListAdapter<Attraction, AttractionViewHolder>(AttractionDiffCallback()) {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {
        val binding = ItemAttractionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AttractionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        val attraction = getItem(position)
        holder.bind(attraction)

        holder.itemView.setOnClickListener {
            val fragment = ContentFragment()
            val bundle = Bundle()
            bundle.putString("attraction_name", attraction.name)
            bundle.putString("attraction_introduction", attraction.introduction)
            bundle.putParcelableArray("attraction_images", attraction.images)
            bundle.putString("url", attraction.url)

            fragment.arguments = bundle

            val fragmentManager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,R.anim.slide_in_left, R.anim.slide_out_right)
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }
    }

    class AttractionViewHolder(private val binding: ItemAttractionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(attraction: Attraction) {
            binding.attraction = attraction
            binding.executePendingBindings()

            if (attraction.images.isNotEmpty()) {
                val imageUrl = attraction.images[0].src
                Glide.with(binding.image)
                    .load(imageUrl)
                    .into(binding.image)
            }
        }
    }

    class AttractionDiffCallback : DiffUtil.ItemCallback<Attraction>() {
        override fun areItemsTheSame(oldItem: Attraction, newItem: Attraction): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Attraction, newItem: Attraction): Boolean {
            return oldItem == newItem
        }
    }
