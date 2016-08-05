package vn.dinosys.dinoad.app;

/**
 * Created by htsi.
 * Since: 7/1/16 on 5:21 PM
 * Project: DinoAd
 */
public class Constants {

    public static final String BASE_URL = "http://192.168.1.34:8080";
    public static final String IMG_PREFIX = "/adserver/www/images/";
    public static final String API_PREFIX = "adserver/www/delivery/api.php/";

    public static final String APP_DEFAULT_FONT = "fonts/MYRIADPRO-REGULAR.OTF";
    public static final String LOCK_SCREEN_ACTION = "android.intent.action.LOCK_SCREEN_ACTION";

    public static final String APP_DATABASE_NAME = "dinoad.dat";

    public static final int RC_SIGN_IN_GOOGLE = 100;

    public enum LoginType {
        FACEBOOK, GOOGLE, EMAIL
    }

    public enum GiftCardType {
        Prepaid, Game, Voucher
    }
}
