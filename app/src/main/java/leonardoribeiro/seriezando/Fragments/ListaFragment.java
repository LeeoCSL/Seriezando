package leonardoribeiro.seriezando.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import leonardoribeiro.seriezando.Activity.InfoSerieActivity;
import leonardoribeiro.seriezando.Adapter.ListaSeriesAdapter;
import leonardoribeiro.seriezando.Models.Serie;
import leonardoribeiro.seriezando.R;
import leonardoribeiro.seriezando.dao.SeriesDAO;


public class ListaFragment extends Fragment {

    private View view;

    ListView listaSeries;

    public ListaFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_lista, container, false);

        configuraLista();


        return view;
    }

    private void configuraLista() {
        listaSeries = view.findViewById(R.id.listaSeries);
        final List<Serie> series = new SeriesDAO().lista();
        listaSeries.setAdapter(new ListaSeriesAdapter(series, getContext()));
        listaSeries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long l) {

                Serie serieClicada = series.get(posicao);

                Intent intent = new Intent(getContext(),
                        InfoSerieActivity.class);
                intent.putExtra("serie", serieClicada);
                startActivity(intent);
            }
        });
    }

}