package vn.dinosys.dinoad.ui.fragment.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.app.Constants;
import vn.dinosys.dinoad.app.DinoAdApplication;
import vn.dinosys.dinoad.app.LockScreenService;
import vn.dinosys.dinoad.app.Runtime;
import vn.dinosys.dinoad.ui.activity.login.LoginActivity;
import vn.dinosys.dinoad.util.DialogHelper;

/**
 * Created by htsi.
 * Since: 7/18/16 on 10:07 AM
 * Project: DinoAd
 */
public class SettingFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener
                                    , Preference.OnPreferenceClickListener{

    public static final String TAG = SettingFragment.class.getName();

    private GoogleApiClient mGoogleApiClient;

    private Runtime mRuntime;

    public static SettingFragment newInstance(String title) {
        SettingFragment fragment = new SettingFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TITLE", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static final String KEY_ACTIVE_LOCK_SCREEN = "active_lock_screen";
    public static final String KEY_DELETE_ACCOUNT = "delete_account";
    private AlertDialog mInactiveLockScreenDialog;

    @Override
    public void onCreatePreferences(Bundle pBundle, String pS) {
        addPreferencesFromResource(R.xml.pref_setting);

        mInactiveLockScreenDialog = createInactiveLockScreenDialog();
        for(int x = 0; x < getPreferenceScreen().getPreferenceCount(); x++){
            PreferenceCategory preferenceCategory = (PreferenceCategory) getPreferenceScreen().getPreference(x);
            for(int y = 0; y < preferenceCategory.getPreferenceCount(); y++){
                Preference pref = preferenceCategory.getPreference(y);
                pref.setOnPreferenceClickListener(this);
            }
        }

        mRuntime = DinoAdApplication.getInstance().getAppComponent().runtime();
        if (mRuntime.getLoginType() != -1 &&
                Constants.LoginType.values()[mRuntime.getLoginType()] == Constants.LoginType.GOOGLE) {
            initGoogleApiClient();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences pSharedPreferences, String key) {

        if (key.equals(KEY_ACTIVE_LOCK_SCREEN)) {
//            Toast.makeText(getContext(), "active lock screen " + pSharedPreferences.getBoolean(key, false), Toast.LENGTH_SHORT)
//                    .show();
//            if (!pSharedPreferences.getBoolean(key, false)) {
//                mInactiveLockScreenDialog.show();
//            }

            activeLockScreen(pSharedPreferences.getBoolean(key, false));
        }
    }

    public AlertDialog createInactiveLockScreenDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.spinner, null);
        AppCompatSpinner spinner = (AppCompatSpinner) view.findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item
                                , getResources().getStringArray(R.array.inactive_lock_screen));
        spinner.setAdapter(arrayAdapter);
        builder.setView(view);
        builder.setTitle(R.string.inactive_lock_screen_question);
        builder.setPositiveButton(android.R.string.ok, (pDialogInterface, pI) -> {

        });
        builder.setNegativeButton(android.R.string.cancel, null);

        return builder.create();
    }

    public void activeLockScreen(boolean enable) {
        Intent intent = new Intent(getActivity(), LockScreenService.class);

        if (enable) {
            getActivity().startService(intent);
        }
        else {
            getActivity().stopService(intent);
        }
    }

    @Override
    public boolean onPreferenceClick(Preference pPreference) {
        Log.d(TAG, pPreference.getKey() + " click");
        if ( pPreference.getKey() != null &&
                pPreference.getKey().equals(KEY_DELETE_ACCOUNT)) {
            DialogHelper.createConfirmDialog(getContext(), "Confirm", "Do you want to log out?"
                    , this::signOut)
            .show();
        }
        return false;
    }

    public void initGoogleApiClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .enableAutoManage(getActivity(), pConnectionResult -> {
                    Log.d(TAG, pConnectionResult.getErrorMessage());
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        mGoogleApiClient.connect();
        mGoogleApiClient.registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(@Nullable Bundle pBundle) {
                Log.d(TAG, "Google api client connected");
            }

            @Override
            public void onConnectionSuspended(int pI) {

            }
        });
    }


    public void signOut() {
        int loginType = mRuntime.getLoginType();
        Constants.LoginType type = null;
        if (loginType != -1) {
            type = Constants.LoginType.values()[loginType];
        }
        if (type != null) {
            switch (type) {
                case EMAIL:
                    signOutEmail();
                    break;
                case FACEBOOK:
                    signOutFacebook();
                    break;
                case GOOGLE:
                    signOutGoogle();
                    break;
            }

        }
    }

    public void signOutEmail() {

    }

    public void signOutFacebook() {
        LoginManager.getInstance().logOut();
        signOutSuccess();
    }
    public void signOutGoogle() {
        if (mGoogleApiClient.isConnected()) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    status -> {
                        signOutSuccess();
                    });
        }
    }

    public void signOutSuccess() {
        mRuntime.logOut();
        getActivity().finish();
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

    public void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null
                , HttpMethod.DELETE, graphResponse -> LoginManager.getInstance().logOut()).executeAsync();
    }

    private void disconnectFromGoogle() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                status -> {

                });
    }
}
