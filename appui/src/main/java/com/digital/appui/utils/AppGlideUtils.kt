package com.digital.appui.utils

import android.Manifest
import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.InputStream
import java.util.concurrent.TimeUnit
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory
import android.content.pm.PackageManager
import android.os.Environment
import androidx.core.content.ContextCompat
import com.bumptech.glide.GlideBuilder
import com.digital.appui.BuildConfig

class AppGlideUtils  {


    companion object {
        /**
         * change the timeout values
         * @param readTime
         * @param writeTime
         * @param connectTime
         * @param loggingLevel HttpLoggingInterceptor level {BODY,BASIC,HEADERS,NONE}
         * , default `BODY`. & only applied in DEBUG mode.
         * */
        fun registerComponents(
            context: Context, glide: Glide, registry: Registry
            , readTime: Long = 30
            , writeTime: Long = 30
            , connectTime: Long = 30
            , loggingLevel: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY
        ) {
            val builder = OkHttpClient.Builder()
            // set your timeout here
            builder.readTimeout(readTime, TimeUnit.SECONDS)
            builder.writeTimeout(writeTime, TimeUnit.SECONDS)
            builder.connectTimeout(connectTime, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                val log = HttpLoggingInterceptor().setLevel(loggingLevel)
                builder.addInterceptor(log)
            }
            registry.replace(
                GlideUrl::class.java,
                InputStream::class.java,
                OkHttpUrlLoader.Factory(builder.build())
            )

//        val client = UnsafeOkHttpClient.getUnsafeOkHttpClient()
//        registry.replace(GlideUrl::class.java, InputStream::class.java,
//                OkHttpUrlLoader.Factory(client))
        }

        /**
         * change disk cache location & size
         * @param downloadDir where to save cached images.
         * @param cacheDisSize max size allowed to cached image in byte.
         * */
        fun applyOptionsDiskCache(
            context: Context, builder: GlideBuilder,
            downloadDir: String = Environment.DIRECTORY_DOWNLOADS,
            cacheDisSize: Long = 250 * 1024 * 1024
        ) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
                == PackageManager.PERMISSION_DENIED
            ) return

//        val size = 250 * 1024 * 1024/*Mb,default size 250mb*/
            builder.setDiskCache(DiskLruCacheFactory(DiskLruCacheFactory.CacheDirectoryGetter {
                context.getExternalFilesDir(downloadDir)
            }, cacheDisSize))
        }
    }
}