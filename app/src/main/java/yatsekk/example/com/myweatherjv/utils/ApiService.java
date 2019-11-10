package yatsekk.example.com.myweatherjv.utils;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import yatsekk.example.com.myweatherjv.utils.pojo.MainModel;

public interface ApiService {
    @GET("data/2.5/weather")
    Observable<MainModel> getResponse(@Query("q") String city,
                                      @Query("appid") String keyApi,
                                      @Query("lang") String lang,
                                      @Query("units") String units);
}
