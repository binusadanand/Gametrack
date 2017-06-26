package com.igt.binu.gametrack.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.igt.binu.gametrack.R;

/**
 * Created by binusadanand on 25/06/2017.
 */

public class GameDataViewHolder extends RecyclerView.ViewHolder {

    public View mParent;
    public TextView mDateTv;
    public TextView mGameNameTv;
    public TextView mJackPotTv;

    public GameDataViewHolder(View itemView) {
        super(itemView);
        mParent = itemView;
        mDateTv = (TextView) itemView.findViewById(R.id.item_date_tv);
        mGameNameTv = (TextView) itemView.findViewById(R.id.item_main_text_tv);
        mJackPotTv = (TextView) itemView.findViewById(R.id.item_jackpot_tv);

    }

}