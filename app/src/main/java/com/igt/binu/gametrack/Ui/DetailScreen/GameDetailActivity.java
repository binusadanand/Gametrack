package com.igt.binu.gametrack.Ui.DetailScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.igt.binu.gametrack.Model.GameData;
import com.igt.binu.gametrack.Model.PlayerInfo;
import com.igt.binu.gametrack.R;
import com.igt.binu.gametrack.Utils.DateConverter;
import java.util.Locale;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by binusadanand on 25/06/2017.
 */

public class GameDetailActivity extends AppCompatActivity implements GameDetailView {

    private static final String RECIPIENT_GAME_DATE_KEY = "game_date";
    private static final String RECIPIENT_JACKPOT_VALUE_KEY = "game_jackpot";
    private static final String RECIPIENT_GAME_NAME_KEY = "game_name";
    private static final String RECIPIENT_CURRENCY_KEY = "currency";

    @BindView(R.id.profile_avatar_iv)
    ImageView mProfileImageIv;

    @BindView(R.id.profile_name_tv)
    TextView mProfileNameTv;

    @BindView(R.id.profile_balance_tv)
    TextView mProfileBalanceTv;

    @BindView(R.id.profile_last_login_tv)
    TextView mProfileLastLoginTv;

    @BindView(R.id.detail_progress_view)
    ContentLoadingProgressBar mProgress;


    @BindView(R.id.item_jackpot_tv)
    TextView mJackpotTv;

    @BindView(R.id.item_date_tv)
    TextView mGameDate;


    public static Intent getNewIntent(Context context,
                                      GameData.Data aGame,
                                      String aCurrencyStr) {
        Intent intent = new Intent(context, GameDetailActivity.class);

        intent.putExtra(RECIPIENT_GAME_DATE_KEY, aGame.date);
        intent.putExtra(RECIPIENT_JACKPOT_VALUE_KEY, aGame.jackpot);
        intent.putExtra(RECIPIENT_GAME_NAME_KEY, aGame.name);
        intent.putExtra(RECIPIENT_CURRENCY_KEY, aCurrencyStr);


        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_view_layout);
        ButterKnife.bind(this);

        String aGameName =  getIntent().getStringExtra(RECIPIENT_GAME_NAME_KEY);
        if(TextUtils.isEmpty(aGameName)) {
            setTitle(R.string.app_name);
        } else {
            setTitle(aGameName);
        }

        int aJackpot = getIntent().getIntExtra(RECIPIENT_JACKPOT_VALUE_KEY, 0);
        String aCurrency = getIntent().getStringExtra(RECIPIENT_CURRENCY_KEY);
        String aDateStr = getIntent().getStringExtra(RECIPIENT_GAME_DATE_KEY);

        StringBuilder aSb =  new StringBuilder();
        aSb.append(String.format(Locale.ENGLISH, "%d", aJackpot));
        aSb.append(" ");
        aSb.append(aCurrency);

        mJackpotTv.setText(aSb.toString());
        mGameDate.setText(DateConverter.FriendlyFromStr(aDateStr));

    }

    @Override
    protected void  onResume() {
        super.onResume();
        GameDetailPresenter presenter = new GameDetailPresenter(this);
        presenter.getProfileDetails();
    }

    @Override
    public void showProfileProgress() {
        mProgress.show();
    }

    @Override
    public void dismissProfileProgress() {
        mProgress.hide();
    }

    @Override
    public void showProfilePanel(PlayerInfo aObj) {
        Glide.with(this).load(aObj.avatarLink).into(mProfileImageIv);
        mProfileNameTv.setText(aObj.name);

        String aBalanceStr = getString(R.string.balance_label, String.format(Locale.ENGLISH, "%d", aObj.balance));

        mProfileBalanceTv.setText(aBalanceStr);
        mProfileLastLoginTv.setVisibility(View.GONE);
    }

    @Override
    public void showError(String aReason) {

    }
}
