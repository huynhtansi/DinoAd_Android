package vn.dinosys.dinoad.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import vn.dinosys.dinoad.di.base.HasComponent;
import vn.dinosys.dinoad.di.component.AppComponent;
import vn.dinosys.dinoad.ui.activity.base.BaseActivity;
import vn.dinosys.dinoad.ui.fragment.InviteFriendFragment;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;

public class InviteFriendActivity extends BaseActivity implements HasComponent<AppComponent> {


    public static void show(Activity pActivity) {
        Intent intent = new Intent(pActivity, InviteFriendActivity.class);
        pActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected BaseFragment hostFragment() {

        return InviteFriendFragment.newInstance();
    }

    @Override
    public AppComponent getComponent() {
        return getApplicationComponent();
    }
}
