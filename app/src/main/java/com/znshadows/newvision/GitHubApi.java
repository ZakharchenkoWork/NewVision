package com.znshadows.newvision;

import com.znshadows.newvision.models.ReposData;
import com.znshadows.newvision.models.SubscriberData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by kostya on 07.01.2017.
 */

public interface GitHubApi {
    // https://api.github.com/search/repositories
    @GET("/search/repositories")
    Call<ReposData> getData(@Query("q") String keyWords);

    //https://api.github.com/repos/NASAWorldWind/WorldWindJava/subscribers
    @GET
    Call<List<SubscriberData>> getSubscribers(@Url String url);
}
