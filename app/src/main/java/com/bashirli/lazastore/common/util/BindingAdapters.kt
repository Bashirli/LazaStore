package com.bashirli.lazastore.common.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter


object BindingAdapters {
@JvmStatic
@BindingAdapter("app:setImageWithURL")
fun setImageWithURL(view:ImageView,url:String?){
    view.setImageURL(url?:" ",view.context)
}


}