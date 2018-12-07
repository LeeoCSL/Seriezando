package leonardoribeiro.seriezando.MVP.Fragments.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import leonardoribeiro.seriezando.MVP.Activity.Main.MainActivity;
import leonardoribeiro.seriezando.MVP.Activity.MinhaLista.MinhaListaActivity;
import leonardoribeiro.seriezando.MVP.Activity.Perfil.PerfilActivity;
import leonardoribeiro.seriezando.Models.Serie;
import leonardoribeiro.seriezando.R;


public class HomeFragment extends Fragment {

    private View view;
    Button btn_lista_de_series;

    Button btn_minha_lista;
    Button btn_estatisticas;

    DatabaseReference databaseSeries;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseSeries = FirebaseDatabase.getInstance().getReference("series");

        criaSerie();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_home, container, false);
        btn_lista_de_series = view.findViewById(R.id.btn_lista_de_series);
        btn_minha_lista = view.findViewById(R.id.btn_minha_lista);
        btn_estatisticas = view.findViewById(R.id.btn_estatisticas);



        btn_minha_lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MinhaListaActivity.class));
            }
        });

        btn_estatisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.mViewPager.setCurrentItem(2);
            }
        });

        btn_lista_de_series.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.mViewPager.setCurrentItem(1);
            }
        });

        return view;
    }


    public void criaSerie(){


//        Serie serie1 =   new Serie("1", "Supernatural", "Descrição Supernatural", "logo", 5f );
//        databaseSeries.child(String.valueOf(serie1.getId())).setValue(serie1);
//        Toast.makeText(getContext(), "criado", Toast.LENGTH_SHORT).show();


    }
}