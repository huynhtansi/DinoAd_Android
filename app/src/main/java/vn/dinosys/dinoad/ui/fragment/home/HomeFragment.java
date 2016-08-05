package vn.dinosys.dinoad.ui.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.ui.activity.InviteFriendActivity;
import vn.dinosys.dinoad.ui.activity.PointDetailActivity;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;

/**
 * Created by htsi.
 * Since: 7/18/16 on 10:06 AM
 * Project: DinoAd
 */
public class HomeFragment extends BaseFragment {



    public static HomeFragment newInstance(String title) {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TITLE", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.textTitle)
    TextView mTextTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();

        String title = getArguments().getString("TITLE");
        mTextTitle.setText(title);
    }



    @OnClick(R.id.llBalance)
    public void onClickTextBalance(View view) {
        startActivity(PointDetailActivity.createIntent(getActivity()));
    }

    @OnClick(R.id.llCouponBox)
    public void onClickCouponBox(View view) {
        ((HomeContainerFragment) getParentFragment()).goToCouponTab();
    }

    @OnClick(R.id.llInviteFriends)
    public void onClickInviteFriends(View view) {
        InviteFriendActivity.show(getActivity());
    }
}
