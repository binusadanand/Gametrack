package com.igt.binu.gametrack.Ui.HomeScreen;

import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class GameTrackActivity extends AppCompatActivity implements GameTrackView {

    @BindView(R.id.main_list_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.main_list_empty_view)
    TextView mInfoTv;

    @BindView(R.id.main_list_progress_view)
    ContentLoadingProgressBar mProgress;



    @BindView(R.id.profile_avatar_iv)
    ImageView mProfileImageIv;

    @BindView(R.id.profile_name_tv)
    TextView mProfileNameTv;

    @BindView(R.id.profile_balance_tv)
    TextView mProfileBalanceTv;

    @BindView(R.id.profile_last_login_tv)
    TextView mProfileLastLoginTv;

    @BindView(R.id.profile_progress_view)
    ContentLoadingProgressBar mProfileProgress;

    private GameTrackAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_layout);
        ButterKnife.bind(this);
        setTitle(R.string.app_name);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new GameTrackAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void  onResume() {
        super.onResume();
        GameTrackPresenter presenter = new GameTrackPresenter(this);
        presenter.getProfileDetails();
        presenter.getGameDetails(this);
    }


    @Override
    public void showProgress() {
        mProgress.show();
        mInfoTv.setVisibility(View.GONE);
    }

    @Override
    public void dismissProgress() {
        mProgress.hide();
        mInfoTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void showGameList(GameData aDetailObj) {
        mProgress.hide();
        mInfoTv.setVisibility(View.GONE);
        mAdapter.upDate(aDetailObj.currency, aDetailObj.dataList);
    }

    @Override
    public void showProfileProgress() {
        mProfileProgress.show();
    }

    @Override
    public void dismissProfileProgress() {
        mProfileProgress.hide();
    }

    @Override
    public void showProfilePanel(PlayerInfo aObj) {
        Glide.with(this).load(aObj.avatarLink).into(mProfileImageIv);

        mProfileNameTv.setText(aObj.name);

        String aBalanceStr = getString(R.string.balance_label, String.format(Locale.ENGLISH, "%d", aObj.balance));
        String aLastLoginTime = getString(R.string.last_login_date, DateConverter.PrettyFromStr(aObj.lastLogindate));

        mProfileBalanceTv.setText(aBalanceStr);
        mProfileLastLoginTv.setText(aLastLoginTime);
    }

    @Override
    public void showError(String aReason) {
        mProgress.hide();
        mInfoTv.setVisibility(View.VISIBLE);
        mInfoTv.setText(aReason);

    }
}
