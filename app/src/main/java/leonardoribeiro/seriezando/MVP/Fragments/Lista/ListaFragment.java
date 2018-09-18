package leonardoribeiro.seriezando.MVP.Fragments.Lista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import leonardoribeiro.seriezando.Adapter.ListaSeriesAdapter;
import leonardoribeiro.seriezando.MVP.Activity.InfoSerie.InfoSerieActivity;
import leonardoribeiro.seriezando.Models.Serie;
import leonardoribeiro.seriezando.R;
import leonardoribeiro.seriezando.dao.SeriesDAO;


public class ListaFragment extends Fragment{

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
        listaSeries = view.findViewById(R.id.listaSeries);

        final List<Serie> series = lista();

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

        return view;
    }


    public List<Serie> lista(){
        List<Serie> series = new ArrayList<>(Arrays.asList(
                new Serie("1", "Supernatural", "Descrição Supernatural", "logo", 5f ),
                new Serie("2", "House MD.", "Descrição House MD.", "logo", 3f ),
                new Serie("3", "Sons of Anarchy", "Descrição SoA", "logo", 2f ),
                new Serie("4", "Lost", "Descrição Lost", "logo", 0f ),
                new Serie("5", "How I Met Your Mother", "Descrição HIMYM", "logo", 1f )
        ));
        return series;
    }



}