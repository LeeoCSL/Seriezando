package leonardoribeiro.seriezando.application;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import leonardoribeiro.seriezando.Models.Serie;
import leonardoribeiro.seriezando.Models.Usuario;


public class CustomApplication extends Application {

    public static Usuario currentUser = new Usuario();
    public static List<Serie> series = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }



    public static boolean isUserLoggedIn() {
        return currentUser != null;
    }

    public static void setCurrentUser(Usuario currentUser) {
        CustomApplication.currentUser = currentUser;
    }

    public static List<Serie> getSeries() {
        return series;
    }

    public static void setSeries(List<Serie> series) {
        CustomApplication.series = series;
    }

    public static void addSerie(Serie series) {
        CustomApplication.series.add(series);
    }

    public static void updateSeriesVistas(List<Serie> series) {
        List<Serie> newList = series;
        currentUser.setSeriesVistas(newList);
    }


    public void destroySession() {
        currentUser = null;
    }
}
