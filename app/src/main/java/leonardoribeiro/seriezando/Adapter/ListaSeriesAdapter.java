package leonardoribeiro.seriezando.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import leonardoribeiro.seriezando.Models.Serie;
import leonardoribeiro.seriezando.R;
import leonardoribeiro.seriezando.Util.ResourcesUtil;
import leonardoribeiro.seriezando.dao.SeriesDAO;


public class ListaSeriesAdapter extends BaseAdapter {

    private final List<Serie> series;
    private final Context context;

    public ListaSeriesAdapter(List<Serie> series, Context context) {

        this.series = series;
        this.context = context;
    }

    @Override
    public int getCount() {
        return series.size();
    }

    @Override
    public Serie getItem(int posicao) {
        return series.get(posicao);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int posicao, View view, ViewGroup parent) {
        View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.list_view_layout, parent, false);

        Serie serie = series.get(posicao);

        mostraRate(viewCriada, serie);
        mostraImagem(viewCriada, serie);
//        mostraDescricao(viewCriada, serie);
        mostraNome(viewCriada, serie);
        return viewCriada;
    }

    private void mostraNome(View viewCriada, Serie serie) {
        TextView tv_titulo = viewCriada.findViewById(R.id.tv_titulo);
        tv_titulo.setText(serie.getNome());
    }


    private void mostraDescricao(View viewCriada, Serie serie) {

    }


    private void mostraImagem(View viewCriada, Serie serie) {
        ImageView imagem = viewCriada.findViewById(R.id.lv_imagem);
//        Drawable drawableImagemPacote = ResourcesUtil.devolveDrawable(context, SeriesDAO.getImages()[serie.getId()-1]);
//        imagem.setImageDrawable(drawableImagemPacote);
//        imagem.setBackgroundResource(R.drawable.btn_only_border);
        imagem.setBackgroundResource(SeriesDAO.getImages()[serie.getId() - 1]);
    }


    private void mostraRate(View viewCriada, Serie serie) {
        RatingBar ratingBar = viewCriada.findViewById(R.id.ratingBar);
        ratingBar.setRating(serie.getRate());
    }
}
