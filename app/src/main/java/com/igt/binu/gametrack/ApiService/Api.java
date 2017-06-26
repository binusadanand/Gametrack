package com.igt.binu.gametrack.ApiService;

import com.igt.binu.gametrack.Model.GameData;
import com.igt.binu.gametrack.Model.PlayerInfo;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by binusadanand on 25/06/2017.
 */

public interface Api {
    @GET("s/2ewt6r22zo4qwgx/gameData.json")
    Observable<ResponseBody> getGamesFile();

    @GET("s/5zz3hibrxpspoe5/playerInfo.json")
    Observable<ResponseBody> getProfileFile();

}
