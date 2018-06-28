package com.example.pc.basemvp.screen.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Toast;
import com.example.pc.basemvp.MainApplication;
import com.example.pc.basemvp.R;
import com.example.pc.basemvp.base.BaseActivity;
import com.example.pc.basemvp.data.model.User;
import com.example.pc.basemvp.data.source.remote.api.error.BaseException;
import com.example.pc.basemvp.databinding.ActivityLoginBinding;
import com.example.pc.basemvp.repositories.TokenRepository;
import com.example.pc.basemvp.repositories.UserRepository;
import javax.inject.Inject;

/**
 * Login Screen.
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {

    @Inject
    UserRepository mUserRepository;
    @Inject
    TokenRepository mTokenRepository;

    LoginContract.Presenter mPresenter;

    @Override
    protected void bundleData(Bundle savedInstanceState) {

    }

    @Override
    protected void initRootView(Bundle savedInstanceState) {
        ((MainApplication) getApplication()).getAppComponent().inject(this);

        ActivityLoginBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setViewModel(this);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        mPresenter = new LoginPresenter(this, this.mUserRepository, this.mTokenRepository);
        mPresenter.doLogin("user25@yopmail.com", "12345678", 1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onStop() {
        mPresenter.onStop();
        super.onStop();
    }

    @Override
    public void onGetUserSuccess(User user) {
        Toast.makeText(this, user.getAddress(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetError(BaseException exception) {
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}
