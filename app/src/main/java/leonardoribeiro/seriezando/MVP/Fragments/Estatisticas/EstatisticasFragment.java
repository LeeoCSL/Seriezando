package leonardoribeiro.seriezando.MVP.Fragments.Estatisticas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import leonardoribeiro.seriezando.R;
import leonardoribeiro.seriezando.application.CustomApplication;


public class EstatisticasFragment extends Fragment {

    private View view;
    int numeroSeries;

    TextView tvNumero;

    public EstatisticasFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_estatisticas, container, false);
        tvNumero=  view.findViewById(R.id.tvNumero);

        if(CustomApplication.currentUser.getSeriesVistas() != null) {
            numeroSeries = CustomApplication.currentUser.getSeriesVistas().size();
        }
        tvNumero.setText("Voce ja assistiu " + numeroSeries + " series");
        return view;
    }

}