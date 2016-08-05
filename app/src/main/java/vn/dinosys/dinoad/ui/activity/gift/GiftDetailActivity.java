package vn.dinosys.dinoad.ui.activity.gift;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import vn.dinosys.dinoad.app.Constants;
import vn.dinosys.dinoad.di.base.HasComponent;
import vn.dinosys.dinoad.di.component.AppComponent;
import vn.dinosys.dinoad.ui.activity.base.BaseActivity;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;
import vn.dinosys.dinoad.ui.fragment.gift.GiftDetailFragment;

/**
 * Created by huutai.
 * Since: 7/28/16 on 4:42 PM
 * Project: DinoAd
 */
public class GiftDetailActivity extends BaseActivity implements HasComponent<AppComponent> {

    private int mGiftType;
    public static final String KEY_GIFT_CARD_TYPE = "KEY_GIFT_CARD_TYPE";

    public static void show(Activity pActivity, Constants.GiftCardType pType) {
        Intent intent = new Intent(pActivity, GiftDetailActivity.class);
        intent.putExtra(KEY_GIFT_CARD_TYPE, pType.ordinal());
        pActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("GiftDetail", mGiftType + "");
    }

    @Override
    protected BaseFragment hostFragment() {
        mGiftType = getIntent().getIntExtra(KEY_GIFT_CARD_TYPE, -1);
        Constants.GiftCardType type = null;
        if (mGiftType != -1) {
            type = Constants.GiftCardType.values()[mGiftType];
        }
        return GiftDetailFragment.newInstance("Gift card", type);
    }

    @Override
    public AppComponent getComponent() {
        return getApplicationComponent();
    }
}
