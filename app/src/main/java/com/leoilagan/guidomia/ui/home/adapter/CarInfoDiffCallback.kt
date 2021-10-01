package com.leoilagan.guidomia.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.leoilagan.guidomia.data.local.CarInfo

object CarInfoDiffCallback : DiffUtil.ItemCallback<CarInfo>() {

    override fun areContentsTheSame(oldItem: CarInfo, newItem: CarInfo): Boolean {
       return  oldItem.uuid == newItem.uuid
    }

    override fun areItemsTheSame(oldItem: CarInfo, newItem: CarInfo): Boolean {
      return oldItem == newItem
    }
}