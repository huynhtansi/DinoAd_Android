package vn.dinosys.dinoad.ui.fragment.lockscreen;

import android.app.WallpaperManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.app.Constants;
import vn.dinosys.dinoad.data.net.model.Banner;
import vn.dinosys.dinoad.di.component.AppComponent;
import vn.dinosys.dinoad.presenter.lockscreen.LockScreenPresenter;
import vn.dinosys.dinoad.ui.activity.lockscreen.YoutubePlayerActivity;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;
import vn.dinosys.dinoad.ui.view.ILockScreenView;
import vn.dinosys.dinoad.ui.widget.viewpager.ViewPageTransform;

/**
 * Created by htsi.
 * Since: 7/3/16 on 9:50 AM
 * Project: DinoAd
 */
public class LockScreenFragment extends BaseFragment implements ILockScreenView {

    private static final String TAG = LockScreenFragment.class.getName();

    @Inject
    LockScreenPresenter mLockScreenPresenter;

    @BindView(R.id.imgBackground)
    ImageView mImgBackground;

    @BindView(R.id.textHour)
    TextView mTextHour;

    @BindView(R.id.textDate)
    TextView mTextDate;

    @BindView(R.id.textSlideUnlock)
    ShimmerTextView mTextSlideToUnlock;

    @BindView(R.id.viewPageBanner)
    VerticalViewPager mViewPageBanner;

    private static List<Banner> mBannerList;

    public static LockScreenFragment newInstance() {
        return new LockScreenFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lockscreen, container, false);
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();
        setupUI();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void setupUI() {

        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getContext());
        Drawable drawable = wallpaperManager.getDrawable();
        mImgBackground.setImageDrawable(drawable);

        updateTextDateTime();

        Shimmer shimmer = new Shimmer();
        shimmer.setDuration(2000).start(mTextSlideToUnlock);

        mViewPageBanner.setPageTransformer(true, new ViewPageTransform());

        initialize();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
        updateTextDateTime();
    }

    private void initialize() {
        this.getComponent(AppComponent.class).inject(this);
        mLockScreenPresenter.setView(this);
    }

    private void updateTextDateTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdfTime = new SimpleDateFormat("h:mm", Locale.getDefault());
        String time = sdfTime.format(calendar.getTime());

        SimpleDateFormat sdfTimeId = new SimpleDateFormat("a", Locale.getDefault());
        String timeID = sdfTimeId.format(calendar.getTime());

        mTextHour.setText(String.format(getResources().getString(R.string.time_format), time, timeID));

        sdfTime = new SimpleDateFormat("EE, dd MMM", Locale.getDefault());
        String date = sdfTime.format(calendar.getTime());
        mTextDate.setText(date);
    }

    private void setupViewPageBanner() {
        mViewPageBanner.setAdapter(new BannerAdapter(getChildFragmentManager()));
    }

    @Override
    public void renderBannerList(List<Banner> pBannerList) {
        for (Banner banner: pBannerList) {
            Log.d(TAG, banner.toString());
        }
        this.mBannerList = pBannerList;
        setupViewPageBanner();
    }


    public class BannerAdapter extends FragmentPagerAdapter {

        public BannerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a BannerFragment (defined as a static inner class below).
            return BannerFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 10 total pages.
            return mBannerList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class BannerFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static BannerFragment newInstance(int sectionNumber) {
            BannerFragment fragment = new BannerFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public BannerFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            Banner banner = mBannerList.get(getArguments().getInt(ARG_SECTION_NUMBER));

            if (!banner.getStorageType().equals("html")) {

                ImageView bannerView = new ImageView(getContext());
                bannerView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                int equalIndex = banner.getDestUrl().indexOf("=") + 1;
                String videoId = banner.getDestUrl().substring(equalIndex);
                Log.d(TAG, videoId);

                /*if (banner.getContentType().equals("gif")) {
                    Glide.with(getContext()).load(Constants.BASE_URL + Constants.IMG_PREFIX + banner.getImgFileName()).asGif().into(bannerView);
                } else {*/
                    Glide.with(getContext()).load(Constants.BASE_URL + Constants.IMG_PREFIX + banner.getImgFileName()).into(bannerView);
                //}
                Log.d(TAG, "Video Id: " + videoId);
                bannerView.setOnClickListener(pView -> startActivity(YoutubePlayerActivity.createIntent(getContext(), videoId)));

                return bannerView;

            } else {

                WebView bannerView = new WebView(getContext());
                bannerView.getSettings().getJavaScriptEnabled();

                Log.d(TAG, banner.getHtmlTemplate());
                bannerView.loadData("<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "\t<title></title>\n" +
                        "</head>\n" +
                        "<body><A>" + banner.getHtmlTemplate() + "</body>\n" +
                        "</html>", "text/html", "utf-8");
                return bannerView;
            }
        }
    }
}
