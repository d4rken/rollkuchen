package de.rollkuchen.app.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import de.rollkuchen.app.core.ImageRepo;
import de.rollkuchen.app.core.Rollkuchen;
import eu.darken.ommvplib.base.Presenter;
import eu.darken.ommvplib.injection.ComponentPresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@MainActivityScope
public class MainActivityPresenter extends ComponentPresenter<MainActivityPresenter.View, MainActivityComponent> {

    private final ImageRepo imageRepo;

    @Inject
    public MainActivityPresenter(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onBindChange(@Nullable View view) {
        super.onBindChange(view);
        updateGallery();
    }

    private void updateGallery() {
        if (getView() == null) return;
        imageRepo.getRollkuchen()
                .subscribeOn(Schedulers.io())
                .map(map -> new ArrayList<>(map.values()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(rollkuchens -> {
                    if (getView() != null) getView().showRollkuchen(rollkuchens);
                });
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

    }

    @Override
    public void onDestroy() {

    }

    public interface View extends Presenter.View {
        void showRollkuchen(List<Rollkuchen> FBRollkuchen);
    }
}
