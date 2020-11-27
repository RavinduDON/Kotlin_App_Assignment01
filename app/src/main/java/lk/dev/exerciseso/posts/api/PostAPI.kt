package lk.dev.exerciseso.posts.api

import lk.dev.exerciseso.posts.models.Post
import retrofit2.Call
import retrofit2.http.GET

interface PostAPI {
    @GET("posts")
    fun getPosts(): Call<ArrayList<Post>>
}