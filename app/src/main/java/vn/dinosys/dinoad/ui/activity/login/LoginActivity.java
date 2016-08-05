package vn.dinosys.dinoad.ui.activity.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

import vn.dinosys.dinoad.app.DinoAdApplication;
import vn.dinosys.dinoad.app.Runtime;
import vn.dinosys.dinoad.di.base.HasComponent;
import vn.dinosys.dinoad.di.component.AppComponent;
import vn.dinosys.dinoad.ui.activity.base.BaseActivity;
import vn.dinosys.dinoad.ui.activity.home.HomeActivity;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;
import vn.dinosys.dinoad.ui.fragment.login.LoginContainerFragment;

/**
 * Created by htsi.
 * Since: 7/7/16 on 1:43 PM
 * Project: DinoAd
 */
public class LoginActivity extends BaseActivity implements HasComponent<AppComponent> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Runtime runtime = DinoAdApplication.getInstance().getAppComponent().runtime();
        if (runtime.getUserLoggedIn()) {
            finish();
            startActivity(HomeActivity.createIntent(this));
        }
    }

    @Override
    protected BaseFragment hostFragment() {
        return new LoginContainerFragment();
    }

    @Override
    public AppComponent getComponent() {
        return getApplicationComponent();
    }
}
