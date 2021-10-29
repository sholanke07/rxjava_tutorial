package com.lateef.rxjavatutorial.Retrofit

import com.lateef.rxjavatutorial.model.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface MyAPI {

    @get: GET("posts")
    val posts:Observable<List<Post>>
}