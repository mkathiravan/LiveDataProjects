package net.kathir.myapplication.retrofitLiveData.service;


import net.kathir.myapplication.retrofitLiveData.model.BlogWrapper;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApiService {

    @GET("feed.json")
    Call<BlogWrapper> getPopularBlog();
}
