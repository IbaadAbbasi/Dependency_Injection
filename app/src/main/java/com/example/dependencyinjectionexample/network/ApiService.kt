package com.example.dependencyinjectionexample.network

import com.example.dependencyinjectionexample.model.Post
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
suspend fun getPost():List<Post>
}