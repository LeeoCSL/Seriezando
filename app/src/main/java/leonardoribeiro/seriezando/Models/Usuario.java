package leonardoribeiro.seriezando.Models;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable {



    List<Integer> seriesVistas;
    String email;
    String id;

    public Usuario(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List getSeriesVistas() {
        return seriesVistas;
    }

    public void setSeriesVistas(List<Integer> seriesVistas) {
        this.seriesVistas = seriesVistas;
    }

    public void addTeste(){
        seriesVistas.add(1);
    }
}
