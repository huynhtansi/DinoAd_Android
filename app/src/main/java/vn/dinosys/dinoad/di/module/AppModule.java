package vn.dinosys.dinoad.di.module;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.dinosys.dinoad.R;
import vn.dinosys.dinoad.app.Constants;
import vn.dinosys.dinoad.app.Runtime;
import vn.dinosys.dinoad.data.database.migration.DbUpgradeHelper;
import vn.dinosys.dinoad.data.database.table.dao.DaoMaster;
import vn.dinosys.dinoad.data.database.table.dao.DaoSession;
import vn.dinosys.dinoad.data.net.repository.banner.BannerRepository;
import vn.dinosys.dinoad.data.net.repository.banner.IBannerRepository;
import vn.dinosys.dinoad.data.net.service.banner.BannerService;
import vn.dinosys.dinoad.util.security.ObscuredSharedPreferences;

/**
 * Created by htsi.
 * Since: 7/1/16 on 6:02 PM
 * Project: DinoAd
 */
@Module
public class AppModule {

    private final Application mApplication;

    public AppModule(Application pApplication) {
        mApplication = pApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() { return mApplication;}

    @Provides
    @Singleton
    ObscuredSharedPreferences provideObscuredSharedPreferences() {
        return new ObscuredSharedPreferences(this.mApplication,
                mApplication.getSharedPreferences(this.mApplication.getString(R.string.app_name), Context.MODE_PRIVATE));
    }

    @Provides
    @Singleton
    Runtime provideRuntime(ObscuredSharedPreferences pSharedPreferences) {
        Log.d("TAG", "create runtime");
        return new Runtime(pSharedPreferences);
    }

    @Provides
    @Singleton
    DaoSession provideDaoSession(Context pContext) {
        DaoMaster.OpenHelper helper = new DbUpgradeHelper(pContext, Constants.APP_DATABASE_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }

    @Provides
    @Singleton
    @Named("REST_ADAPTER")
    Retrofit provideRestAdapter() {
        Log.d("TAG", "create rest adapter");
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.1.34:8080");
        return builder.build();
    }

    @Singleton
    @Provides
    IBannerRepository provideBannerRepository(BannerService pBannerService) {
        return new BannerRepository(pBannerService);
    }

    @Singleton
    @Provides
    BannerService provideBannerService(@Named("REST_ADAPTER")Retrofit pRestAdapter) {
        return pRestAdapter.create(BannerService.class);
    }
}
