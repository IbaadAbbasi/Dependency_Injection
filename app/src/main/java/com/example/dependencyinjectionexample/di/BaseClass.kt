package com.example.dependencyinjectionexample.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseClass : Application() {
}