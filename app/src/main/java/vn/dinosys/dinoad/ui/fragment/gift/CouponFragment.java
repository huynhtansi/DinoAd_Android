package vn.dinosys.dinoad.ui.fragment.gift;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.data.net.model.CouponItem;
import vn.dinosys.dinoad.ui.adapter.CouponAdapter;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;

/**
 * Created by huutai.
 * Since: 7/21/16 on 5:43 PM
 * Project: DinoAd
 */
public class CouponFragment extends BaseFragment {

    public static CouponFragment newInstance(String title) {
        CouponFragment fragment = new CouponFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TITLE", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.list)
    ListView mListView;

    @BindView(R.id.textCouponCount)
    TextView mTextCouponCount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coupon, container, false);
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();

        String title = getArguments().getString("TITLE");

        setupUI();
    }

    private void setupUI() {
        List<CouponItem> couponItems = new ArrayList<>();
        couponItems.add(new CouponItem(R.drawable.card_mobifone, "Mobifont 20,000 VND", 1546242660000l));
        couponItems.add(new CouponItem(R.drawable.card_viettel, "Viettel 50,000 VND", 1546242660000l));
        couponItems.add(new CouponItem(R.drawable.card_garena, "Viettel 50,000 VND", 1546242660000l));
        couponItems.add(new CouponItem(R.drawable.card_viettel, "Viettel 20,000 VND", 1546242660000l));
        couponItems.add(new CouponItem(R.drawable.card_vinaphone, "Viettel 20,000 VND", 1546242660000l));
        couponItems.add(new CouponItem(R.drawable.card_viettel, "Viettel 20,000 VND", 1546242660000l));

        mListView.setAdapter(new CouponAdapter(couponItems));

        mTextCouponCount.setText(String.format(Locale.US, getString(R.string.coupon_count), couponItems.size()));
    }
}
