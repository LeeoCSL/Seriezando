package leonardoribeiro.seriezando.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {



    List<Serie> seriesVistas = new ArrayList<>();

    String id;

    public Usuario(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Serie> getSeriesVistas() {
        return seriesVistas;
    }

    public void setSeriesVistas(List<Serie> seriesVistas) {
        this.seriesVistas = seriesVistas;
    }

    public void addSerieVista(Serie serie){
        seriesVistas.add(serie);
    }

    public void removeSerieVista(int i){
        seriesVistas.remove(i);
    }
}
