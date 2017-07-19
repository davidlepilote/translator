import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by David et Monireh on 19/07/2017.
 */
public interface TranslatorTokenAPI {

    @POST("IssueToken")
    @Headers("Accept: application/jwt")
    Call<String> getToken(@Header("Ocp-Apim-Subscription-Key") String subscriptionKey);

    @GET("Translate")
    @Headers("Accept: application/xml")
    Call<String> translate(@Query("appid") String appid, @Query("contentType") String contentType, @Query("text") String text, @Query("from") String from, @Query("to") String to, @Query("category") String category);

    @GET("GetLanguagesForTranslate")
    @Headers("Accept: application/xml")
    Call<Languages> languages(@Query("appid") String appid);
}

