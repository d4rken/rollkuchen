package de.rollkuchen.app;


import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import de.rollkuchen.app.ui.main.MainActivity;
import de.rollkuchen.app.ui.main.MainActivityComponent;
import eu.darken.ommvplib.injection.activity.ActivityComponentBuilder;
import eu.darken.ommvplib.injection.activity.ActivityKey;


@Module(subcomponents = {
        MainActivityComponent.class
})
public abstract class AppBinderModule {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract ActivityComponentBuilder main(MainActivityComponent.Builder impl);

}
