package com.igt.binu.gametrack.Ui.HomeScreen;

import com.igt.binu.gametrack.Model.GameData;
import com.igt.binu.gametrack.Model.PlayerInfo;

/**
 * Created by binusadanand on 25/06/2017.
 */

public interface GameTrackView {
    void showProgress();
    void dismissProgress();
    void showGameList(GameData aObj);

    void showProfileProgress();
    void dismissProfileProgress();
    void showProfilePanel(PlayerInfo aObj);

    void showError(String aReason);
}
