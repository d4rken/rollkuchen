package de.rollkuchen.app;

import android.app.Activity;
import android.app.Application;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import eu.darken.ommvplib.injection.activity.ActivityComponent;
import eu.darken.ommvplib.injection.activity.ActivityComponentBuilder;
import eu.darken.ommvplib.injection.activity.ActivityComponentBuilderSource;
import timber.log.Timber;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) Timber.plant(new Timber.DebugTree());
        Injector.INSTANCE.init(this);
    }

    public enum Injector implements ActivityComponentBuilderSource {
        INSTANCE;
        @Inject AppComponent appComponent;
        @Inject Map<Class<? extends Activity>, Provider<ActivityComponentBuilder>> activityBuilderMap;

        public void init(App app) {
            DaggerAppComponent.builder()
                    .appModule(new AppModule(app))
                    .build()
                    .inject(this);
        }

        public AppComponent get() {
            return appComponent;
        }

        @Override
        public <ActivityT extends Activity, BuilderT extends ActivityComponentBuilder<ActivityT, ? extends ActivityComponent<ActivityT>>>
        BuilderT getComponentBuilder(Class<ActivityT> activityClass) {
            //noinspection unchecked
            return (BuilderT) activityBuilderMap.get(activityClass).get();
        }
    }
}
