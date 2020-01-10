package com.kanchan.foodmandu.bll;

import com.kanchan.foodmandu.api.UserApi;
import com.kanchan.foodmandu.serverresponse.SignUpResponse;
import com.kanchan.foodmandu.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {


    public boolean checkUser(String username, String password) {

        boolean isSuccess = false;

        UserApi usersAPI = Url.getInstance().create(UserApi.class);
        Call<SignUpResponse> usersCall = usersAPI.checkUser(username, password);

        try {
            Response<SignUpResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {

                Url.token += loginResponse.body().getToken();
                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

}
