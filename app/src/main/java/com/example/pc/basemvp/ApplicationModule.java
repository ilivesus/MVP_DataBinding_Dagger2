package com.example.pc.basemvp;

import android.content.Context;
import com.example.pc.basemvp.data.source.local.sharePrf.SharedPrefsApi;
import com.example.pc.basemvp.data.source.local.sharePrf.SharedPrefsImpl;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class ApplicationModule {
    private Context mContext;

    ApplicationModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return mContext;
    }

    @Provides
    @Singleton
    public SharedPrefsApi provideSharedPrefsApi() {
        return new SharedPrefsImpl(mContext);
    }
}
