package com.example.dependencyinjectionexample.network

import com.example.dependencyinjectionexample.model.Post
import com.example.dependencyinjectionexample.viewmodel.MainViewModel
import javax.inject.Inject

class ApiServiceImp @Inject constructor(private val apiService: ApiService) {
suspend fun getPost(): List<Post> = apiService.getPost()


}