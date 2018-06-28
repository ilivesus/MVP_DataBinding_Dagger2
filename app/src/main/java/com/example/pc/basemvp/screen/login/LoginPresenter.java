package com.example.pc.basemvp.screen.login;

import com.example.pc.basemvp.data.source.remote.api.error.BaseException;
import com.example.pc.basemvp.repositories.TokenRepository;
import com.example.pc.basemvp.repositories.UserRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Listens to user actions from the UI ({@link LoginActivity}), retrieves the data and updates
 * the UI as required.
 */
final class LoginPresenter implements LoginContract.Presenter {
    private static final String TAG = LoginPresenter.class.getName();

    private final LoginContract.View mView;
    private UserRepository mUserRepository;
    private TokenRepository mTokenRepository;
    private CompositeDisposable mCompositeDisposable;

    public LoginPresenter(LoginContract.View view, UserRepository userRepository,
            TokenRepository tokenRepository) {
        this.mView = view;
        this.mUserRepository = userRepository;
        this.mTokenRepository = tokenRepository;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void doLogin(String email, String password, int type) {
        Disposable disposable =
                mUserRepository.doLogin(email, password, type)
                        .flatMap(dataResponse -> {
                            mTokenRepository.saveToken(dataResponse.getData().getToken());
                            return mUserRepository.getUser();
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(userResponse -> {
                            if (userResponse == null || userResponse.getData() == null) {
                                return;
                            }
                            mView.onGetUserSuccess(userResponse.getData());
                        }, throwable -> {
                            if (throwable instanceof BaseException) {
                                mView.onGetError((BaseException) throwable);
                            }
                        });
        mCompositeDisposable.add(disposable);
    }
}
