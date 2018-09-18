package leonardoribeiro.seriezando.MVP.Activity.SplashScreen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.List;

import leonardoribeiro.seriezando.MVP.Activity.Login.LoginActivity;
import leonardoribeiro.seriezando.R;

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
                while(mProgressStatus < 100){
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
}
