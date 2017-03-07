package de.rollkuchen.app;

import dagger.Component;
import de.rollkuchen.app.core.FirebaseModule;


@ApplicationScope
@Component(modules = {
        AppModule.class,
        AppBinderModule.class,
        FirebaseModule.class
})
interface AppComponent {

    void inject(App.Injector injector);

    @Component.Builder
    interface Builder {
        Builder appModule(AppModule appModule);

        AppComponent build();
    }
}
