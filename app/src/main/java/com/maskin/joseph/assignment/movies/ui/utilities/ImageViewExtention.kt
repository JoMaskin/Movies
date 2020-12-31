package com.maskin.joseph.assignment.movies.ui.utilities

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.maskin.joseph.assignment.movies.app.MoviesApplication.Companion.context
import com.maskin.joseph.assignment.movies.workers.ClearGlideCacheWork
import java.util.concurrent.TimeUnit

/**
 * This is an Extension function for ImageView.
 * It allow to set image from Url using glide.
 * In addition, if Glide succeeded to load and cache some image it starts
 * a WorkManager task to clear the cache after some time.
 * @param url - url to load image from.
 * @param width - width of the loaded image.
 * @param height - height of the loaded image.
 * @param holder - placeholder to set in case of failure.
 * @param context - Context.
 */
fun ImageView.setImage(url: String, width: Int, height: Int, holder: Drawable?, context: Context) {
    val options = RequestOptions()
        .override(width, height)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(holder)

    Glide.with(context)
        .load(url)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                startClearCacheWork()
                return false
            }
        })
        .apply(options)
        .into(this)
}

fun startClearCacheWork() {
    val clearCacheWork = OneTimeWorkRequest.Builder(ClearGlideCacheWork::class.java)
        .setInitialDelay(1, TimeUnit.DAYS).build();
    WorkManager.getInstance(context).beginUniqueWork(
        "Clear cache",
        ExistingWorkPolicy.KEEP, clearCacheWork
    ).enqueue();
}