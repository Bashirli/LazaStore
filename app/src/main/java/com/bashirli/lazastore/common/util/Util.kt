package com.bashirli.lazastore.common.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentActivity
import com.bashirli.lazastore.R
import okhttp3.ResponseBody
import org.json.JSONObject
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle


class CustomProgressBar(){
    companion object{
        fun progressDialog(context: Context): Dialog {
            val dialog=Dialog(context)
            val layout=LayoutInflater.from(context).inflate(R.layout.loading_layout,null)
            dialog.setContentView(layout)
            dialog.setCancelable(false)
                dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            return dialog
        }
    }
}


fun findExceptionMessage(errorBody:ResponseBody?):String{
   if(errorBody!=null){
       val errorObj=JSONObject(errorBody.charStream().readText())
       val errorMessage=errorObj.getString("message")
       return errorMessage
   }else{
       return "Error"
   }
}

fun findExceptionMessageList(errorBody: ResponseBody?):String{
    if(errorBody!=null){
        val errorObj=JSONObject(errorBody.charStream().readText())
        val errorArray=errorObj.getJSONArray("message")
        val errorMessage= errorArray.getString(0)
        return errorMessage
    }else {
        return "Error"
    }
}

fun errorToast(activity: FragmentActivity,errorMessage:String){
    MotionToast.createColorToast(
        activity,
        activity.resources.getString(R.string.error),
        errorMessage,
        MotionToastStyle.ERROR,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(activity,R.font.raleway_regular)
    )
}


fun successToast(activity: FragmentActivity,successMessage:String){
    MotionToast.createColorToast(
        activity,
        activity.resources.getString(R.string.success),
        successMessage,
        MotionToastStyle.SUCCESS,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(activity,R.font.raleway_regular)
    )
}

