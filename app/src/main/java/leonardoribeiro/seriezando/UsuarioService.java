package leonardoribeiro.seriezando;

import leonardoribeiro.seriezando.Models.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioService {

    @POST("user")
    Call<Usuario> register(@Body Usuario user);

    @PATCH("user/{id}")
    Call<Usuario> att(@Path("id") String id, @Body Usuario user);

    @GET("user/{id}")
    Call<Usuario> getUser(@Path("id") String id);

    @DELETE("user/{id}")
    Call<Usuario> deleteUser(@Path("id") String id);
}
