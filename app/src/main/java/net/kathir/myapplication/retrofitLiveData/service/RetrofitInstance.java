package net.kathir.myapplication.retrofitLiveData.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static final String BASE_URL = "https://androidwave.com/api/";

    private static Retrofit retrofit = null;

    public static RestApiService getApiService() {
        if (retrofit == null) {

            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit.create(RestApiService.class);

    }
}
