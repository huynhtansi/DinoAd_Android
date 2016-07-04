package vn.dinosys.dinoad.ui.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.app.DinoAdApplication;
import vn.dinosys.dinoad.di.component.AppComponent;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;

/**
 * Created by htsi.
 * Since: 7/1/16 on 5:24 PM
 * Project: DinoAd
 */
public class BaseActivity extends AppCompatActivity {


    protected AppComponent getApplicationComponent() {
        return ((DinoAdApplication) getApplication()).getAppComponent();
    }

    protected void setupActivityComponent() {
        // Implement in child class
    }

    protected BaseFragment hostFragment() {
        // Implement in child class
        return null;
    }

    private void addFragment(BaseFragment baseFragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tf = fm.beginTransaction();
        tf.add(R.id.container, baseFragment, baseFragment.getClass().getName());
        tf.commit();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent();
        if (hostFragment() != null) {
            setContentView(R.layout.activity_base);
        }
        if (savedInstanceState == null && hostFragment() != null) {
            addFragment(hostFragment());
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
