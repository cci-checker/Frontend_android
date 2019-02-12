package icommons.checklist.Interface_api;

import java.util.List;

import icommons.checklist.Pojo_classes.Checklist_items;
import icommons.checklist.Pojo_classes.Rooms;
import icommons.checklist.Pojo_classes.Users;
import icommons.checklist.Pojo_classes.checkoff_list;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Tushar on 2/4/17.
 */
public interface MyApiEndpointInterface {

    String Base_URL="http://10.250.68.199:8181/";

    @GET("users")
    Call<List<Users>> getUsers();

    @GET("room")
    Call<List<Rooms>> getRooms();

    @GET("checklist")
    Call<List<Checklist_items>> getChecklist_items();

    @POST("checkoff_list/")
    Call<checkoff_list> submit_instance(@Header("Content-Type") String content_type, @Body checkoff_list c_list);





    class factory{
        private static MyApiEndpointInterface service;

        public static MyApiEndpointInterface getInstance(){
            if (service== null){
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(Base_URL)
                        .build();

                service = retrofit.create(MyApiEndpointInterface.class);
            }
            return service;
        }
    }



}
