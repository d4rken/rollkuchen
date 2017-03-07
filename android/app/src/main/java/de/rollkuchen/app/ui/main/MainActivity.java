package de.rollkuchen.app.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.rollkuchen.app.App;
import de.rollkuchen.app.R;
import de.rollkuchen.app.core.Rollkuchen;
import eu.darken.ommvplib.injection.ComponentPresenterActivity;

/**
 * Created by darken (darken@darken.eu) on 07.03.2017.
 */

public class MainActivity extends ComponentPresenterActivity<MainActivityPresenter.View, MainActivityPresenter, MainActivityComponent> implements MainActivityPresenter.View, GalleryAdapter.Callback {
    @BindView(R.id.gallery) RecyclerView gallery;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gallery.setHasFixedSize(true);
        gallery.setItemAnimator(new DefaultItemAnimator());
        gallery.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void inject(@NonNull MainActivityComponent component) {
        super.inject(component);
        component.injectMembers(this);
    }

    @Override
    protected MainActivityComponent createComponent() {
        MainActivityComponent.Builder builder = App.Injector.INSTANCE.getComponentBuilder(MainActivity.class);
        return builder.build();
    }

    @Override
    public Class<? extends MainActivityPresenter> getTypeClazz() {
        return MainActivityPresenter.class;
    }

    @Override
    public void showRollkuchen(List<Rollkuchen> rollkuchens) {
        GalleryAdapter adapter = new GalleryAdapter(this);
        adapter.setData(rollkuchens);
        gallery.setAdapter(adapter);
    }

    @Override
    public void onShowRollkuchen(Rollkuchen rollkuchen) {

    }
}
