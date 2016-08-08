package com.norbertovasconcelos.drawapp.Services;

import com.norbertovasconcelos.drawapp.Models.DrawObject;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by norbertovasconcelos on 08/08/16.
 */
public class DAServices {

    private static DARestServices restService;

    private interface  DARestServices {
        @GET("/1n91cjy1")
        Call<String> getRequest();

        @POST("/1n91cjy1")
        Call<ArrayList<DrawObject>> sendDrawInfo(@Body ArrayList<DrawObject> drawObjects);
    }

    private static DARestServices getRest() {
        if (restService==null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://requestb.in")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            restService = retrofit.create(DARestServices.class);
        }
        return restService;
    }


    /*
     *      PUBLIC METHODS
     */

    public static void getRequest(final GetRequestCallback callback){
        DARestServices service = getRest();

        if (service != null) {

            Call<String> currentCall = service.getRequest();
            ((Call<String>) currentCall).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response == null || response.body() == null) {
                        callback.onError();
                        return;
                    }
                    String segment = response.body();

                    callback.onSuccess(segment);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                    if (callback!=null)callback.onError();
                }
            });
        }
    }

    public static void sendDrawInfo(ArrayList<DrawObject> drawObjects, final SendDrawInfoCallback callback){
        DARestServices service = getRest();

        if (service != null) {

            Call<ArrayList<DrawObject>> currentCall = service.sendDrawInfo(drawObjects);
            ((Call<ArrayList<DrawObject>>) currentCall).enqueue(new Callback<ArrayList<DrawObject>>() {
                @Override
                public void onResponse(Call<ArrayList<DrawObject>> call, Response<ArrayList<DrawObject>> response) {
                    if (response == null || response.body() == null) {
                        callback.onError();
                        return;
                    }

                    callback.onSuccess();
                }

                @Override
                public void onFailure(Call<ArrayList<DrawObject>> call, Throwable t) {

                    if (callback!=null)callback.onError();
                }
            });
        }
    }

    public interface GetRequestCallback {
        void onSuccess(String value);
        void onError();
    }

    public interface SendDrawInfoCallback {
        void onSuccess();
        void onError();
    }
}
