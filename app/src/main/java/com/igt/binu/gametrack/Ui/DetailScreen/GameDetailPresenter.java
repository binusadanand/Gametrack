package com.igt.binu.gametrack.Ui.DetailScreen;

import com.igt.binu.gametrack.ApiService.Api;
import com.igt.binu.gametrack.ApiService.Provider;
import com.igt.binu.gametrack.Utils.GameDataCache;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by binusadanand on 25/06/2017.
 */

public class GameDetailPresenter {

    private Api mApiService;
    private final GameDetailView mView;
    private CompositeSubscription mSubscriptions;

    public GameDetailPresenter(GameDetailView aView) {
        mView = aView;
        mSubscriptions = new CompositeSubscription();
        mApiService = Provider.getApiService();
    }

    public void getProfileDetails () {
        mView.showProfileProgress();
        Subscription event = mApiService.getProfileFile()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dismissProfileProgress();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ResponseBody aResponseBody) {
                        mView.dismissProfileProgress();
                        mView.showProfilePanel(GameDataCache.getPlayerInfo(aResponseBody));
                    }
                });
    }


    public void onStop() {
        mSubscriptions.unsubscribe();
    }
}
