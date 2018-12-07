package leonardoribeiro.seriezando;

import java.util.List;

import leonardoribeiro.seriezando.Models.Serie;
import leonardoribeiro.seriezando.Models.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SeriesService {

    @GET("series")
    Call<List<Serie>> getSeries();
}
