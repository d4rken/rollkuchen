package de.rollkuchen.app.ui.main;

import dagger.Subcomponent;
import eu.darken.ommvplib.injection.PresenterComponent;
import eu.darken.ommvplib.injection.activity.ActivityComponent;
import eu.darken.ommvplib.injection.activity.ActivityComponentBuilder;


@MainActivityScope
@Subcomponent(modules = {
})
public interface MainActivityComponent extends ActivityComponent<MainActivity>, PresenterComponent<MainActivityPresenter.View, MainActivityPresenter> {

    @Subcomponent.Builder
    interface Builder extends ActivityComponentBuilder<MainActivity, MainActivityComponent> {

    }

}
