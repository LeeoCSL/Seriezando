package leonardoribeiro.seriezando.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import leonardoribeiro.seriezando.Models.Serie;

public class SeriesDAO {

    public List<Serie> lista(){
        List<Serie> series = new ArrayList<>(Arrays.asList(
                new Serie(1, "Supernatural", "Descrição supernatural", "logo", 5f ),
                new Serie(2, "House MD.", "Descrição House MD.", "logo", 3f ),
                new Serie(3, "Sons of Anarchy", "Descrição SoA", "logo", 2f ),
                new Serie(4, "Lost", "Descrição Lost", "logo", 0f ),
                new Serie(5, "How I Met Your Mother", "Descrição HIMYM", "logo", 1f )));
        return series;
    }

}
