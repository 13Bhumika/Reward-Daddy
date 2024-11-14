package com.app.rewarddaddy.myreward.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.rewarddaddy.myreward.R
import de.hdodenhof.circleimageview.CircleImageView

class AvatarAdapter(private val avatarImages: IntArray, private val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<AvatarAdapter.AvatarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_avatar, parent, false)
        return AvatarViewHolder(view)
    }

    override fun onBindViewHolder(holder: AvatarViewHolder, position: Int) {
        holder.avatarImage.setImageResource(avatarImages[position])
        holder.itemView.setOnClickListener {
            onItemClick(position) // Trigger the item click
        }
    }

    override fun getItemCount(): Int {
        return avatarImages.size
    }

    class AvatarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarImage: CircleImageView = itemView.findViewById(R.id.iv_avatar_image)
    }
}