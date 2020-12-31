package com.maskin.joseph.assignment.movies.app

import android.app.Application
import android.content.Context

class MoviesApplication : Application() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}