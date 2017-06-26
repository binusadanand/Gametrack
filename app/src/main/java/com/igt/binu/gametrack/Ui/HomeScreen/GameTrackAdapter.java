package com.igt.binu.gametrack.Ui.HomeScreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.igt.binu.gametrack.Model.GameData;
import com.igt.binu.gametrack.Model.GameDataViewHolder;
import com.igt.binu.gametrack.R;
import com.igt.binu.gametrack.Ui.DetailScreen.GameDetailActivity;
import com.igt.binu.gametrack.Utils.DateConverter;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by binusadanand on 25/06/2017.
 */

public class GameTrackAdapter extends RecyclerView.Adapter<GameDataViewHolder> {

    private ArrayList<GameData.Data> mData;
    private String mCurrencyLabel;
    private Context mContext;

    GameTrackAdapter(Context aContext)  {
        mData =  new ArrayList<>();
        mContext = aContext;
    }

    public void upDate(String aLabel, ArrayList<GameData.Data> aList) {
        mCurrencyLabel = aLabel;
        mData.clear();
        mData.addAll(aList);
        notifyDataSetChanged();
    }

    @Override
    public GameDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_data_layout, parent, false);
        return new GameDataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GameDataViewHolder holder, int position) {
        final GameData.Data aItem = mData.get(position);
        if (aItem != null) {
            holder.mDateTv.setText(DateConverter.FriendlyFromStr(aItem.date));
            holder.mGameNameTv.setText(aItem.name);
            StringBuilder aSb =  new StringBuilder();
            aSb.append(String.format(Locale.ENGLISH, "%d", aItem.jackpot));
            aSb.append(" ");
            aSb.append(mCurrencyLabel);
            holder.mJackPotTv.setText(aSb.toString());
            holder.mParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(GameDetailActivity.getNewIntent(
                            mContext,
                            aItem,
                            mCurrencyLabel));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }}
