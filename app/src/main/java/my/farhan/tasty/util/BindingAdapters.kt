package my.farhan.tasty.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import my.farhan.tasty.R

/***
 * Personal best class by far, as it provide bridge between XML and data
 */
object BindingAdapters {
    @BindingAdapter(value = ["hideIfEmpty"])
    @JvmStatic
    fun hideIfEmpty(view: View, isEmpty: Boolean) {
        view.visibility = if (isEmpty) View.GONE else View.VISIBLE
    }

    @BindingAdapter("showIfEmpty")
    @JvmStatic
    fun showIfEmpty(view: View, isEmpty: Boolean) {
        view.visibility = if (isEmpty) View.VISIBLE else View.GONE
    }

    /***
     * in the event if [url] is empty, it will load [R.drawable.ic_no_image]
     * [url] is referring to
     * [my.farhan.favy.data.db.Movie.posterUrl] and
     * [my.farhan.favy.data.db.Movie.backDropUrl] and
     */
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, url: String?) {
        if (url.isNullOrEmpty())
            view.setImageResource(R.drawable.ic_no_image)
        else
            Glide.with(view.context).load(url).centerCrop().into(view)
    }
}