package leonardoribeiro.seriezando.Fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import br.com.zazzytec.meusaldo.DialogListener;
import br.com.zazzytec.meusaldo.NFC.ConsultarBilheteActivity;
import br.com.zazzytec.meusaldo.R;
import br.com.zazzytec.meusaldo.activities.CadastrarRotinaActivity;
import br.com.zazzytec.meusaldo.activities.EditarSaldoActivity;
import br.com.zazzytec.meusaldo.activities.SplashScreen;
import br.com.zazzytec.meusaldo.application.CustomApplication;
import br.com.zazzytec.meusaldo.broadcast.GpsLocationReceiver;
import br.com.zazzytec.meusaldo.constants.MeuSaldoConstants;
import br.com.zazzytec.meusaldo.dto.LogsResponse;
import br.com.zazzytec.meusaldo.dto.PrevisaoResponse;
import br.com.zazzytec.meusaldo.dto.StatusResponse;
import br.com.zazzytec.meusaldo.eventapp.EventsApp;
import br.com.zazzytec.meusaldo.eventbus.events.UpdateGPSEvent;
import br.com.zazzytec.meusaldo.eventbus.events.UpdateUserSaldoEvent;
import br.com.zazzytec.meusaldo.gps.LigarGps;
import br.com.zazzytec.meusaldo.model.BilheteUnico;
import br.com.zazzytec.meusaldo.model.Logs;
import br.com.zazzytec.meusaldo.model.Mobile;
import br.com.zazzytec.meusaldo.model.Rotina;
import br.com.zazzytec.meusaldo.model.Usuario;
import br.com.zazzytec.meusaldo.retrofit.RetrofitInit;
import br.com.zazzytec.meusaldo.retrofit.RetrofitInit3001;
import br.com.zazzytec.meusaldo.retrofit.RetrofitInitLogs;
import br.com.zazzytec.meusaldo.sharedpreferences.SecurityPreferences;
import br.com.zazzytec.meusaldo.util.MonetaryUtil;
import br.com.zazzytec.meusaldo.util.PegaDataHoraAtual;
import br.com.zazzytec.meusaldo.util.UtilNotificacao;
import br.com.zazzytec.meusaldo.util.Utility;
import io.paperdb.Paper;
import me.rishabhkhanna.customtogglebutton.CustomToggleButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class HomeFragment extends Fragment {

    private View view;


    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

    }

}