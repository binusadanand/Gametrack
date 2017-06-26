package com.igt.binu.gametrack.Ui.DetailScreen;

import com.igt.binu.gametrack.Model.PlayerInfo;

/**
 * Created by binusadanand on 25/06/2017.
 */

public interface GameDetailView {
    void showProfileProgress();
    void dismissProfileProgress();
    void showProfilePanel(PlayerInfo aObj);
    void showError(String aReason);
}
