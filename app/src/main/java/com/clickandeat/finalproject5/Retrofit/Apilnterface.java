package com.clickandeat.finalproject5.Retrofit;
import com.clickandeat.finalproject5.Model.foodData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Apilnterface {

    @GET("clickToEatDB.json")
    Call<List<foodData>> getAllData();
}
