package com.lateef.rxjavatutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.lateef.rxjavatutorial.Adapter.PostAdapter
import com.lateef.rxjavatutorial.Retrofit.MyAPI
import com.lateef.rxjavatutorial.Retrofit.RetrofitClient
import com.lateef.rxjavatutorial.model.Post
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.create

class MainActivity : AppCompatActivity() {

    internal lateinit var myAPI: MyAPI
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init API
        val retrofit = RetrofitClient.instance
        myAPI = retrofit.create(MyAPI::class.java)

        recycler_post.setHasFixedSize(true)
        recycler_post.layoutManager = LinearLayoutManager(this)
        fatchData()
    }

    private fun fatchData(){
        compositeDisposable.add(myAPI.posts
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { posts-> displayData(posts) }
        )
    }

    private fun displayData(posts: List<Post>?){
        val adapter = PostAdapter(this, posts!!)
        recycler_post.adapter = adapter

    }
}