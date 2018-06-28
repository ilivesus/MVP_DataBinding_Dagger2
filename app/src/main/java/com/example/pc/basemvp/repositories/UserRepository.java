package com.example.pc.basemvp.repositories;

import com.example.pc.basemvp.data.model.User;
import com.example.pc.basemvp.data.source.remote.api.response.ApiResponse;
import com.example.pc.basemvp.data.source.remote.api.response.LoginResponse;
import io.reactivex.Observable;

public interface UserRepository {

    Observable<ApiResponse<LoginResponse>> doLogin(String email, String password, int type);

    Observable<ApiResponse<User>> getUser();
}
