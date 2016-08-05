package vn.dinosys.dinoad.ui.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabClickListener;

import butterknife.BindView;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;
import vn.dinosys.dinoad.ui.fragment.gift.GiftFragment;

/**
 * Created by htsi.
 * Since: 7/13/16 on 1:54 PM
 * Project: DinoAd
 */
public class HomeContainerFragment extends BaseFragment {

    private static final String TAG = HomeContainerFragment.class.getSimpleName();

    @BindView(R.id.viewPageHome)
    ViewPager mViewPageHome;

    private GiftFragment mGiftFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_container, container, false);
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();
        setupUI();
    }

    private void setupUI() {
        final BottomBar bottomBar = BottomBar.attach(getActivity(), null);
        bottomBar.noTopOffset();
        bottomBar.setItems(R.menu.bottom_bar_menu);
        bottomBar.setOnTabClickListener(new OnTabClickListener() {
            @Override
            public void onTabSelected(int position) {
                mViewPageHome.setCurrentItem(position, true);
            }

            @Override
            public void onTabReSelected(int position) {

            }
        });

        mGiftFragment = GiftFragment.newInstance("Gift Fragment");

        mViewPageHome.setAdapter(new PageHomeAdapter(getChildFragmentManager()));
        mViewPageHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomBar.selectTabAtPosition(position, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void goToCouponTab() {
        Log.d(TAG, "goToCouponTab");
        mViewPageHome.setCurrentItem(2);
        mGiftFragment.setCurrentTab(1);
    }

    private class PageHomeAdapter extends FragmentPagerAdapter {

        public PageHomeAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return HomeFragment.newInstance("Home Fragment");
                case 1:
                    return PromotionFragment.newInstance("Promotion Fragment");
                case 2:
                    return mGiftFragment;
                case 3:
                    return SettingFragment.newInstance("Setting Fragment");
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
