package com.igt.binu.gametrack.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.igt.binu.gametrack.Model.GameData;
import com.igt.binu.gametrack.Model.PlayerInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import okhttp3.ResponseBody;

/**
 * Created by binusadanand on 25/06/2017.
 */

public class GameDataCache {

    private static final String PREFS_NAME = GameDataCache.class.getSimpleName();
    private static final String USER_GAME_PARAMS = "user_game_params";
    private static final String LAST_REQUEST_TIME = "last_request_time";

    public static boolean isExpired(Context aContext) {
        long lastRequestTime = getPreference(aContext).getLong(LAST_REQUEST_TIME, 0);
        long currentTime = System.currentTimeMillis()/1000;
        return ((currentTime - lastRequestTime) >= 60 );
    }

    public static GameData getGameInfo(Context aContext) {
        String aStr =  get(aContext, USER_GAME_PARAMS);
        if (!TextUtils.isEmpty(aStr)) {
            Gson aGson = new Gson();
            return aGson.fromJson(aStr, GameData.class);
        }
        return null;
    }

    public static void putGameInfo(Context aContext, ResponseBody aBody) {
        String aStr = downloadAndConvert(aBody);

        if (!TextUtils.isEmpty(aStr)) {
            put(aContext, USER_GAME_PARAMS, aStr);

            Long tsLong = System.currentTimeMillis()/1000;
            put(aContext, LAST_REQUEST_TIME, tsLong);
        }

    }

    public static PlayerInfo getPlayerInfo(ResponseBody aBody) {
        String aStr =  downloadAndConvert(aBody);
        if (!TextUtils.isEmpty(aStr)) {
            Gson aGson = new Gson();
            return aGson.fromJson(aStr, PlayerInfo.class);
        }
        return null;
    }

    private static SharedPreferences getPreference(Context aContext) {
        return aContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    private static void put(Context aContext, String aKay, String aStrValue) {
        SharedPreferences.Editor editor = getPreference(aContext).edit();
        editor.putString(aKay, aStrValue);
        editor.apply();
    }

    private static void put(Context aContext, String aKay, long aLongVal) {
        SharedPreferences.Editor editor = getPreference(aContext).edit();
        editor.putLong(aKay, aLongVal);
        editor.apply();
    }

    private static String get(Context aContext, String aKay) {
        return getPreference(aContext).getString(aKay, null);
    }

    private static String downloadAndConvert(ResponseBody aBody) {

        BufferedReader r = new BufferedReader(new InputStreamReader(aBody.byteStream()));
        StringBuilder total = new StringBuilder();
        String line;
        try {
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return total.toString();

    }
}
