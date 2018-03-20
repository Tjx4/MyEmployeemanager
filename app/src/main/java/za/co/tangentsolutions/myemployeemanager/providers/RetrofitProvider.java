package za.co.tangentsolutions.myemployeemanager.providers;

import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import za.co.tangentsolutions.myemployeemanager.constants.Constants;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeModel;
import za.co.tangentsolutions.myemployeemanager.models.LoginModel;
import za.co.tangentsolutions.myemployeemanager.models.LoginObject;
import za.co.tangentsolutions.myemployeemanager.models.UserModel;

public interface RetrofitProvider {
    @POST(Constants.LOGIN)
    Call<LoginModel> getUserLogin(@Body LoginObject loginModel);

    @GET(Constants.CURRENT_USER)
    Call<UserModel> getUser(@Header("Authorization") String token);

    @GET(Constants.EMPLOYEES)
    Call<List<EmployeeModel>> getEmployeesList(@Header("Authorization") String token, @QueryMap Map<String, String> params);

    @GET(Constants.EMPLOYEE)
    Call<EmployeeModel> getEmployee(@Header("Authorization") String token);
}