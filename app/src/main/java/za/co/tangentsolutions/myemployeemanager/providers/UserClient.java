package za.co.tangentsolutions.myemployeemanager.providers;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import za.co.tangentsolutions.myemployeemanager.constants.Constants;
import za.co.tangentsolutions.myemployeemanager.models.LoginModel;
import za.co.tangentsolutions.myemployeemanager.models.LoginObject;
import za.co.tangentsolutions.myemployeemanager.models.UserModel;
import za.co.tangentsolutions.myemployeemanager.models.UserModel2;

public interface UserClient {
    @POST(Constants.LOGIN)
    Call<LoginModel> loginUser(@Body LoginObject loginModel);

    @GET(Constants.CURRENT_USER)
    Call<UserModel> getUser(@Header("Authorization") String authToken);
}
