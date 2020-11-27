package lk.dev.exerciseso.posts.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostClient {

    private val BASE_URL = "https://jsonplaceholder.typicode.com/"
    val getPostClient: PostAPI

    get() {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(PostAPI::class.java)
    }
}