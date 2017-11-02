package org.andriodtown.firebasebasic2;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by user on 2017-11-02.
 */

public interface IRetro {
    @POST("sendNotification")
    Call<ResponseBody> sendNotification(@Body RequestBody postdata);
}
