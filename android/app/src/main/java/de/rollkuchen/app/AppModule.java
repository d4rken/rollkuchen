package de.rollkuchen.app;

import android.app.ActivityManager;
import android.content.Context;

import dagger.Module;
import dagger.Provides;


@Module
class AppModule {
    private final App app;

    AppModule(App app) {this.app = app;}

    @ApplicationScope
    @ApplicationContext
    @Provides
    Context provideContext() {
        return app;
    }

    @ApplicationScope
    @Provides
    ActivityManager activityManager(@ApplicationContext Context context) {
        return (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    }

}
