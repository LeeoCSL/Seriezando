package leonardoribeiro.seriezando;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.IOException;

import leonardoribeiro.seriezando.application.CustomApplication;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;



public class RetrofitInit {

    private Retrofit retrofit;

    public RetrofitInit(Context activity) {

        final CustomApplication customApplication = (CustomApplication) activity.getApplicationContext();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder();

//                Log.i("CHAMOU INTERCEPTOR", "SIM");

                if (CustomApplication.isUserLoggedIn()) {

//                    Log.v("token envio", customApplication.getAPItoken());
                }

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        client.addInterceptor(interceptor);

        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000")
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client.build())
                .build();
    }

    public UsuarioService getUsuarioService() {
        return retrofit.create(UsuarioService.class);
    }
    public SeriesService getSeriesService() {
        return retrofit.create(SeriesService.class);
    }
}

