package leonardoribeiro.seriezando.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import leonardoribeiro.seriezando.Models.Serie;
import leonardoribeiro.seriezando.R;
import leonardoribeiro.seriezando.Util.ResourcesUtil;

public class InfoSerieActivity extends AppCompatActivity {

    ImageView img_serie;
    TextView tv_titulo;
    TextView tv_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_serie);

        img_serie = findViewById(R.id.img_serie);
        tv_titulo = findViewById(R.id.tv_titulo);
        tv_desc = findViewById(R.id.tv_desc);

        carregaSerieRecebida();

    }

    private void carregaSerieRecebida() {
        Intent intent = getIntent();
        if(intent.hasExtra("serie")){
            final Serie serie= (Serie) intent.getSerializableExtra("serie");

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
