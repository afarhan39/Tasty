package my.farhan.tasty.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import my.farhan.tasty.R

/***
 * control visibility by using boolean
 */
@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean?) {
    value?.let {
        view.visibility = if (it) View.VISIBLE else View.GONE
    }
}

/***
 * in the event if [url] is empty, it will load [R.drawable.ic_no_image]
 * [url] is referring to
 * [my.farhan.favy.data.db.Movie.posterUrl] and
 * [my.farhan.favy.data.db.Movie.backDropUrl] and
 */
@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (url.isNullOrEmpty())
        view.setImageResource(R.drawable.ic_no_image)
    else
        Glide.with(view.context).load(url).centerCrop().into(view)
}
