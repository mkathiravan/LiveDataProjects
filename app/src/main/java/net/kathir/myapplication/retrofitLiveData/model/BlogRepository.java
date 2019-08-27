package net.kathir.myapplication.retrofitLiveData.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import net.kathir.myapplication.Blog;
import net.kathir.myapplication.retrofitLiveData.service.RestApiService;
import net.kathir.myapplication.retrofitLiveData.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogRepository {

    private ArrayList<Blog> movies = new ArrayList<>();
    private MutableLiveData<List<Blog>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public BlogRepository(Application application)
    {
        this.application = application;
    }

    public MutableLiveData<List<Blog>> getMutableLiveData()
    {
        RestApiService apiService = RetrofitInstance.getApiService();

        Call<BlogWrapper> call = apiService.getPopularBlog();

        call.enqueue(new Callback<BlogWrapper>() {
            @Override
            public void onResponse(Call<BlogWrapper> call, Response<BlogWrapper> response) {

                BlogWrapper blogWrapper = response.body();

                if(blogWrapper!= null && blogWrapper.getBlog()!=null)
                {
                    movies = (ArrayList<Blog>) blogWrapper.getBlog();
                    mutableLiveData.setValue(movies);
                }

            }

            @Override
            public void onFailure(Call<BlogWrapper> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }
}
