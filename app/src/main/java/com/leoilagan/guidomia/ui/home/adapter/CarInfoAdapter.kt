package com.leoilagan.guidomia.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.leoilagan.guidomia.R
import com.leoilagan.guidomia.data.local.CarInfo
import com.leoilagan.guidomia.databinding.ListItemBinding
import kotlinx.android.synthetic.main.list_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class CarInfoAdapter(private val itemClickedListener: CustomItemClickedListener) :
    ListAdapter<CarInfo, CarInfoAdapter.CarInfoViewHolder>(
        CarInfoDiffCallback
    )  {




    private var expandedPosition: Int = -1

    class CarInfoViewHolder(private val itemBinding: ListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(
            item: CarInfo,
            itemClickedListener: CustomItemClickedListener
        ) {
            itemBinding.carInfo = item
            itemBinding.executePendingBindings()
            itemBinding.itemClickListener = itemClickedListener
            checkLabels(item.prosList, itemView.lblPros)
            checkLabels(item.consList, itemView.lblCons)
        }

        fun expandableView(): View {
            return itemBinding.pnlExtension
        }


        private fun checkLabels(list: List<String>, view: View) {
            var listPros: List<String> = list
            listPros = listPros.filter { x: String? -> x != "" }
            if (listPros.isEmpty()) {
                view.visibility = View.GONE

            } else
                view.visibility = View.VISIBLE
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarInfoViewHolder {
        val binding: ListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item,
            parent,
            false
        )
        return CarInfoViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CarInfoViewHolder, position: Int) {

        val model = getItem(position)

        val isExpanded: Boolean = position == expandedPosition
        holder.expandableView().visibility = (if (isExpanded) View.VISIBLE else View.GONE)
        holder.itemView.setOnClickListener {
            expandedPosition = if (isExpanded) -1 else position
             notifyDataSetChanged()

//            notifyItemChanged(position)
            itemClickedListener.itemClicked(model)
        }

        holder.bind(model, itemClickedListener)

    }




}