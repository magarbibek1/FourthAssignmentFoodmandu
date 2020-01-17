package com.bibek.foodmandu.bll;

import com.bibek.foodmandu.api.UserApi;
import com.bibek.foodmandu.model.username;
import com.bibek.foodmandu.serverresponse.SignUpResponse;
import com.bibek.foodmandu.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBll {

    boolean isSuccess = false;

    public boolean checkUser(String username, String password) {

       com.bibek.foodmandu.model.username Username= new username(username,password);
       UserApi userapi= Url.getInstance().create(UserApi.class);
       Call<SignUpResponse> usersCall = userapi.checklogin(Username);

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
