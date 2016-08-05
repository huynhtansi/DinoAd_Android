package vn.dinosys.dinoad.ui.fragment.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;

import java.lang.reflect.Field;

import butterknife.BindView;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;
import vn.dinosys.dinoad.ui.widget.viewpager.ScrollerSlowAnimation;
import vn.dinosys.dinoad.ui.widget.viewpager.VerticalViewPager;
import vn.dinosys.dinoad.ui.widget.viewpager.ViewPageTransform;

/**
 * Created by htsi.
 * Since: 7/7/16 on 1:48 PM
 * Project: DinoAd
 */
public class LoginContainerFragment extends BaseFragment {


    @BindView(R.id.viewPageHost)
    VerticalViewPager mViewPageHost;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_container, container, false);
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();

        setupUI();
    }

    private void setupUI() {

        try {
            Class<?> viewpager = VerticalViewPager.class;
            Field scroller = viewpager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            Field interpolator = viewpager.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);

            ScrollerSlowAnimation mScroller = new ScrollerSlowAnimation(getContext(),
                    (Interpolator) interpolator.get(null));
            scroller.set(mViewPageHost, mScroller);
        } catch (NoSuchFieldException | IllegalAccessException pE) {
            pE.printStackTrace();
        }

        mViewPageHost.setPageTransformer(false, new ViewPageTransform());
        mViewPageHost.setAdapter(new LoginAdapter(getChildFragmentManager()));
    }

    public void togglePage() {
        if (mViewPageHost.getCurrentItem() > 0)
            mViewPageHost.setCurrentItem(0, true);
        else
            mViewPageHost.setCurrentItem(1, true);
    }

    private class LoginAdapter extends FragmentPagerAdapter {

        public LoginAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new SignInFragment();
            } else {
                return new SignUpFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
