package vn.dinosys.dinoad.app;

import android.util.Log;

import vn.dinosys.dinoad.util.BaseRuntime;
import vn.dinosys.dinoad.util.security.ObscuredSharedPreferences;
import vn.dinosys.dinoad.util.security.SecurityUtil;

/**
 * Created by huutai.
 * Since: 7/26/16 on 5:46 PM
 * Project: DinoAd
 */
public class Runtime extends BaseRuntime {

    public static final String PREF_LOGIN_TYPE = SecurityUtil.sha256("PREF_LOGIN_TYPE");

    protected int mLoginType;

    public Runtime(ObscuredSharedPreferences sharedPreferences) {
        super(sharedPreferences);

        mLoginType = mSharedPreferences.getInt(PREF_LOGIN_TYPE, -1);
    }

    public void setLoginType(int pType) {
        mLoginType = pType;
        mSharedPreferences.edit().putInt(PREF_LOGIN_TYPE, pType).commit();
    }

    public int getLoginType() {
        return mLoginType;
    }

    public void saveUser(String email, String accessToken, int type) {
        this.setUsernameKey(email);
        this.setUserTokenKey(accessToken);
        this.setLoginType(type);
        this.setUserLoggedIn(true);
        Log.d("Runtime", email + " " + accessToken + " " + type);
    }

    @Override
    public void logOut() {
        super.logOut();
        mLoginType = -1;
        deleteKey(PREF_LOGIN_TYPE);
    }
}
