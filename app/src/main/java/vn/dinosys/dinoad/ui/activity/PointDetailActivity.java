package vn.dinosys.dinoad.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import vn.dinosys.dinoad.di.base.HasComponent;
import vn.dinosys.dinoad.di.component.AppComponent;
import vn.dinosys.dinoad.ui.activity.base.BaseActivity;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;
import vn.dinosys.dinoad.ui.fragment.point.PointDetailFragment;

public class PointDetailActivity extends BaseActivity implements HasComponent<AppComponent> {

    public static final Intent createIntent(Context pContext) {
        return new Intent(pContext, PointDetailActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected BaseFragment hostFragment() {
        return PointDetailFragment.newInstance("");
    }

    @Override
    public AppComponent getComponent() {
        return getApplicationComponent();
    }
}
