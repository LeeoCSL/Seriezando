package leonardoribeiro.seriezando.MVP.Activity.InfoSerie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

import leonardoribeiro.seriezando.MVP.Activity.Main.MainActivity;
import leonardoribeiro.seriezando.Models.Serie;
import leonardoribeiro.seriezando.Models.Usuario;
import leonardoribeiro.seriezando.R;
import leonardoribeiro.seriezando.RetrofitInit;
import leonardoribeiro.seriezando.application.CustomApplication;
import leonardoribeiro.seriezando.dao.SeriesDAO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoSerieActivity extends AppCompatActivity {

    ImageView img_serie;
    TextView tv_titulo;
    TextView tv_desc;
    Button btn_voltar, btnJaVi;

    List<Integer> listaAtualizada;
    Serie serie;
    boolean vista = false;

    DatabaseReference databaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_serie);

        loadUI();
        carregaSerieRecebida();





        btnJaVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!vista){
                    //TODO chamada incluir em vistas

//                deleteUser();

                CustomApplication.currentUser.addSerieVista(serie);
                    Call<Usuario> call = new RetrofitInit(InfoSerieActivity.this).getUsuarioService().att(CustomApplication.currentUser.getId(), CustomApplication.currentUser);
                    call.enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                            btnJaVi.setText("Retirar da minha lista");
                            vista = true;
                            Toast.makeText(InfoSerieActivity.this, "aq", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Usuario> call, Throwable t) {
                            Toast.makeText(InfoSerieActivity.this, "ou aq", Toast.LENGTH_SHORT).show();
                        }
                    });

                }else if(vista){
                    //TODO chamada retirar de vistas

                    for(int c = 0; c < CustomApplication.currentUser.getSeriesVistas().size(); c++){
                        if(CustomApplication.currentUser.getSeriesVistas().get(c).getId() == serie.getId()){
                            CustomApplication.currentUser.removeSerieVista(c);
                            break;
                        }
                    }

                    Call<Usuario> call = new RetrofitInit(InfoSerieActivity.this).getUsuarioService().att(CustomApplication.currentUser.getId(), CustomApplication.currentUser);
                    call.enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                            btnJaVi.setText("Incluir na minha lista");
                            vista = true;
                        }

                        @Override
                        public void onFailure(Call<Usuario> call, Throwable t) {

                        }
                    });
                }
            }
        });

        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InfoSerieActivity.this, MainActivity.class));
                finish();
            }
        });
    }



    private void deleteUser() {

        Call<Usuario> call = new RetrofitInit(InfoSerieActivity.this).getUsuarioService().deleteUser(CustomApplication.currentUser.getId());
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {

            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });

    }


    private void loadUI() {
        img_serie = findViewById(R.id.img_serie);
        tv_titulo = findViewById(R.id.tv_titulo);
        tv_desc = findViewById(R.id.tv_desc);
        btn_voltar  = findViewById(R.id.btn_voltar);
        btnJaVi = findViewById(R.id.btnJaVi);
    }

    private void carregaSerieRecebida() {
        Intent intent = getIntent();
        if(intent.hasExtra("serie")){
            serie = (Serie) intent.getSerializableExtra("serie");

            inicializaCampos(serie);




        }else{
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }


    }

    private void inicializaCampos(Serie serie) {

        mostraNome(serie);
        mostraDesc(serie);
        mostraFoto(serie);
        if(!CustomApplication.currentUser.getSeriesVistas().isEmpty()) {
            for (int i = 0; i < CustomApplication.currentUser.getSeriesVistas().size(); i++) {
                if (serie.getId() == CustomApplication.currentUser.getSeriesVistas().get(i).getId()) {
                    btnJaVi.setText("Retirar da minha lista");
                    vista = true;
                    break;
                }
            }
        }
    }



    private void mostraFoto(Serie serie) {
        //TODO array fotos por indice igual das series
//        img_serie.setBackgroundResource(R.drawable.btn_only_border);
        img_serie.setBackgroundResource(SeriesDAO.getImages()[serie.getId() - 1]);
    }

    private void mostraDesc(Serie serie) {
        tv_desc.setText(serie.getDescricao());
    }

    private void mostraNome(Serie serie) {
        tv_titulo.setText(serie.getNome());
    }
}
