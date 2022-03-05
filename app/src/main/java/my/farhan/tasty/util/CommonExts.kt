package my.farhan.tasty.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/***
 * best Extensions, I can use it anywhere, and it will return class name
 */
val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }

/***
 * make liveData only observeOnce when not null
 * if null, it will keep observing
 * if non null, it will removeObserver
 */
fun <T> LiveData<T>.observeOnceNonNull(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            t ?: return
            if (t is List<*>)
                if (t.isNullOrEmpty()) return
            removeObserver(this)
        }
    })
}