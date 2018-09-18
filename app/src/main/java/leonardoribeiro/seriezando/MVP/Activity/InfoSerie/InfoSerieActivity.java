package leonardoribeiro.seriezando.MVP.Activity.InfoSerie;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import leonardoribeiro.seriezando.MVP.Activity.Main.MainActivity;
import leonardoribeiro.seriezando.Models.Serie;
import leonardoribeiro.seriezando.R;
import leonardoribeiro.seriezando.Util.ResourcesUtil;

public class InfoSerieActivity extends AppCompatActivity {

    ImageView img_serie;
    TextView tv_titulo;
    TextView tv_desc;
    Button btn_voltar, btnJaVi;

    List<String> seriesVistas;
    Serie serie;

    DatabaseReference databaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_serie);

        loadUI();
        carregaSerieRecebida();

        databaseUser = FirebaseDatabase.getInstance().getReference("usuarios");



        btnJaVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseUser.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("seriesVistas").setValue(serie);
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
            serie= (Serie) intent.getSerializableExtra("serie");

            inicializaCampos(serie);


        }


    }

    private void inicializaCampos(Serie serie) {

        mostraNome(serie);
        mostraDesc(serie);
        mostraFoto(serie);
    }

    private void mostraFoto(Serie serie) {

        Drawable drawableDoPacote = ResourcesUtil.devolveDrawable(this, serie.getFoto());
        img_serie.setImageDrawable(drawableDoPacote);
    }

    private void mostraDesc(Serie serie) {
        tv_desc.setText(serie.getDescricao());
    }

    private void mostraNome(Serie serie) {
        tv_titulo.setText(serie.getNome());
    }
}
