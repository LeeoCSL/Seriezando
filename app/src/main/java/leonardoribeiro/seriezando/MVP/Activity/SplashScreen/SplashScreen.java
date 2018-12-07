package leonardoribeiro.seriezando.MVP.Activity.SplashScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.auth.GooglePlayServicesAvailabilityException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;
import java.util.List;

import leonardoribeiro.seriezando.MVP.Activity.Login.LoginActivity;
import leonardoribeiro.seriezando.MVP.Activity.Main.MainActivity;
import leonardoribeiro.seriezando.Models.Serie;
import leonardoribeiro.seriezando.R;
import leonardoribeiro.seriezando.RetrofitInit;
import leonardoribeiro.seriezando.application.CustomApplication;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.appsee.Appsee;

public class SplashScreen extends AppCompatActivity {

    private ProgressBar mProgress;

    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();
    public static List<Integer> vistas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mProgress = findViewById(R.id.progressBar);
        Appsee.start();
        getSeries();

        Handler h = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
//                getGAID();
            }
        };
        h.post(r);

//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
//        String series = sp.getString("seriesVistas", "");
//        if(!series.equals("")) {
//            String[] ids = series.split(" ");
//            for (String id : ids) {
//                CustomApplication.addVistas(Integer.parseInt(id));
//            }
//        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mProgressStatus < 100) {
                    mProgressStatus++;
                    android.os.SystemClock.sleep(50);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mProgress.setProgress(mProgressStatus);
                        }
                    });
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                    }
                });
            }
        }).start();

    }

    private void getGAID() {

        AdvertisingIdClient.Info adInfo = null;
        try {
            adInfo = AdvertisingIdClient.getAdvertisingIdInfo(getApplicationContext());

        } catch (IOException e) {
            // Unrecoverable error connecting to Google Play services (e.g.,
            // the old version of the service doesn't support getting AdvertisingId).

        } catch (GooglePlayServicesNotAvailableException e) {
            // Google Play services is not available entirely.
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        }


        final String id = adInfo.getId();
        final boolean isLAT = adInfo.isLimitAdTrackingEnabled();


    }

    private void getSeries() {
        Call<List<Serie>> call = new RetrofitInit(SplashScreen.this).getSeriesService().getSeries();
        call.enqueue(new Callback<List<Serie>>() {
            @Override
            public void onResponse(Call<List<Serie>> call, Response<List<Serie>> response) {
                CustomApplication.setSeries(response.body());
//                Toast.makeText(SplashScreen.this, "foi", Toast.LENGTH_SHORT).show();
//                for (int i = 0; i < response.body().size(); i++){
//                    Log.v("serieee" + i, response.body().get(i).toString());
//                    CustomApplication.addSerie(response.body().get(i));
//                }
            }

            @Override
            public void onFailure(Call<List<Serie>> call, Throwable t) {
                getSeries();
            }
        });
    }
}
