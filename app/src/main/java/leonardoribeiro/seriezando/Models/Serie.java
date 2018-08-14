package leonardoribeiro.seriezando.Models;

import java.io.Serializable;

public class Serie implements Serializable{


    private int id;
    private String nome;
    private String descricao;
    private String foto;
    private float rate;

    public Serie(){}



    public Serie(int id, String nome, String descricao, String foto, float rate){
        this.id = id;

        this.nome = nome;
        this.descricao = descricao;
        this.foto = foto;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Série{" +
                "nome='" + nome + '\'' +
                "descroção='" + descricao + '\''+
                "rate='" + rate + " estrelas" + '\'' +
                '}';
    }
}
