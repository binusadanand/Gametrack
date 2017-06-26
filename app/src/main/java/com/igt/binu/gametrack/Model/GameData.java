package com.igt.binu.gametrack.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by binusadanand on 25/06/2017.
 */

public class GameData {
    public String response;
    public String currency;

    @SerializedName("data")
    public ArrayList<Data> dataList;

    public class Data {
        public String name;
        public int jackpot;
        public String date;
    }
}
