package vn.dinosys.dinoad.ui.fragment.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.ui.activity.home.HomeActivity;
import vn.dinosys.dinoad.ui.fragment.base.BaseFragment;
import vn.dinosys.dinoad.ui.view.ILoginView;
import vn.dinosys.dinoad.util.Util;

/**
 * Created by htsi.
 * Since: 7/12/16 on 10:30 AM
 * Project: DinoAd
 */
public class SignInFragment extends BaseFragment implements ILoginView {

    @BindView(R.id.editUsername)
    EditText mEditUsername;

    @BindView(R.id.editPassword)
    EditText mEditPassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();
        setupUI();
    }

    private void setupUI() {
    }

    @OnClick(R.id.btnSignIn)
    public void onSignInClicked(View pView) {
        Util.hideKeyboard(pView);
        if (isInputValidate()) {
            String username = mEditUsername.getText() + "";
            String password = mEditPassword.getText() + "";
            if (Util.isNetworkAvailable(getContext())) {
//                mAuthenticatePresenter.login(username.trim(), password);
//                mPdLoading.show();
                startActivity(HomeActivity.createIntent(getContext()));
            }
            else {
                showError(getString(R.string.no_internet_connection));
            }
        }

    }


    private boolean isInputValidate() {
        String username = mEditUsername.getText() + "";
        String password = mEditPassword.getText() + "";

        boolean isValidate = true;
        if (username.isEmpty()) {
            mEditUsername.setError(getString(R.string.input_username));
            isValidate = false;
        }
        if (password.isEmpty()) {
            mEditPassword.setError(getString(R.string.input_password));
            isValidate = false;
        }

        return isValidate;
    }

    @OnClick(R.id.btnSignUp)
    public void onSignUpClicked(View pView) {
        LoginContainerFragment fragment = (LoginContainerFragment) getParentFragment();
        fragment.togglePage();
    }

    @Override
    public void showLoginSuccess() {

    }

    @Override
    public void showLoginFail() {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
