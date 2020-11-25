package lk.dev.exerciseso.posts.api;

import java.util.List;

import lk.dev.exerciseso.posts.models.Post;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PostApi {
    @GET("/posts")
    Call<List<Post>> getPosts();
}
