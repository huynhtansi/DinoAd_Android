package vn.dinosys.dinoad.ui.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import vn.dinosys.dinoad.di.base.HasComponent;
import vn.dinosys.dinoad.di.component.AppComponent;
import vn.dinosys.dinoad.ui.activity.base.BaseActivity;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;
import vn.dinosys.dinoad.ui.fragment.home.HomeContainerFragment;

/**
 * Created by htsi.
 * Since: 7/13/16 on 1:45 PM
 * Project: DinoAd
 */
public class HomeActivity extends BaseActivity implements HasComponent<AppComponent> {

    public static final Intent createIntent(Context pContext) {
        return new Intent(pContext, HomeActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected BaseFragment hostFragment() {
        return new HomeContainerFragment();
    }

    @Override
    public AppComponent getComponent() {
        return getApplicationComponent();
    }
}
