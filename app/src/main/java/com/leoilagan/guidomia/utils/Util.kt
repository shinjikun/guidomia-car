package com.leoilagan.guidomia.utils

import android.content.Context
import android.icu.text.CompactDecimalFormat
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.leoilagan.guidomia.R
import java.util.*


fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.ic_placeholder)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(Uri.parse(uri))
        .into(this)
}


@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, id: Int) {
    id.let {
        view.loadImage("file:///android_asset/car$id.jpg", getProgressDrawable(view.context))

    }
}

@BindingAdapter("android:formatPrice")
fun formatPrice(view: TextView, price: Double) {
    price.let {
        val formattedNumber = CompactDecimalFormat.getInstance(
            Locale.getDefault(),
            CompactDecimalFormat.CompactStyle.SHORT
        ).format(price)
        view.text = formattedNumber
    }

}

@BindingAdapter("android:listContent")
fun explodeList(view: LinearLayout, list: List<String>) {

    view.removeAllViews()
    var listPros: List<String> = list
    listPros = listPros.filter { x: String? -> x != "" }
    if (listPros.isEmpty()) {
        view.visibility = View.GONE

    } else {
        view.visibility = View.VISIBLE

        for (i in listPros.indices) {
            val textView = TextView(view.context)
            textView.textSize = 14f
            textView.setPadding(16, 0, 0, 8)

            val content = "●  ${listPros[i]}"
            textView.text = content

            textView.setTextColor(view.resources.getColor(R.color.black))
            view.addView(textView)
        }

    }

}


