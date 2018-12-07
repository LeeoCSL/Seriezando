package leonardoribeiro.seriezando.MVP.Activity.MinhaLista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import leonardoribeiro.seriezando.Adapter.ListaSeriesAdapter;
import leonardoribeiro.seriezando.MVP.Activity.InfoSerie.InfoSerieActivity;
import leonardoribeiro.seriezando.Models.Serie;
import leonardoribeiro.seriezando.R;
import leonardoribeiro.seriezando.application.CustomApplication;

public class MinhaListaActivity extends AppCompatActivity {

    Button btn_voltar;
    ListView listaSeries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minha_lista);

        btn_voltar = findViewById(R.id.btn_voltar);
        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        listaSeries = findViewById(R.id.listinha);

//        final List<Serie> series = presenter.configuraLista();

//        listaSeries.setAdapter(new ListaSeriesAdapter(series, getContext()));

        final List<Serie> series = lista();

        listaSeries.setAdapter(new ListaSeriesAdapter(series, MinhaListaActivity.this));
        listaSeries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long l) {

                Serie serieClicada = series.get(posicao);

                Intent intent = new Intent(MinhaListaActivity.this,
                        InfoSerieActivity.class);
                intent.putExtra("serie", serieClicada);
                startActivity(intent);
            }
        });
    }


    public List<Serie> lista(){
//        List<Serie> series = new ArrayList<>(Arrays.asList(
//                new Serie(1, "Supernatural", "Descrição Supernatural", 5f ),
//                new Serie(2, "House MD.", "Descrição House MD.",  3f ),
//                new Serie(3, "Sons of Anarchy", "Descrição SoA",  2f ),
//                new Serie(4, "Lost", "Descrição Lost", 0f ),
//                new Serie(5, "How I Met Your Mother", "Descrição HIMYM", 1f )
//        ));
        return CustomApplication.currentUser.getSeriesVistas();
    }

}
