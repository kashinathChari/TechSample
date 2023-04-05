package om.tech.sample.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition



@SuppressLint("UnrememberedMutableState")
@Composable
fun LoadImage(context: Context, url:String, @DrawableRes defaultImg:Int): MutableState<Bitmap?>
{
 val bitmapState:MutableState<Bitmap?> =mutableStateOf(null)

    Glide.with(context)
        .asBitmap()
        .load(defaultImg)
        .into(object: CustomTarget<Bitmap>(){
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
               bitmapState.value=resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }

        })


    Glide.with(context)
        .asBitmap()
        .load(url)
        .into(object: CustomTarget<Bitmap>(){
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value=resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }

        })

    return bitmapState
    }