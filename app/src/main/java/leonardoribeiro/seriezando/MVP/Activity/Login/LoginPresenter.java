package leonardoribeiro.seriezando.MVP.Activity.Login;

import android.app.Activity;
import android.widget.EditText;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter implements MVPLogin.Presenter {

    MVPLogin.View view;
    LoginModel model;

    Activity act;

    public LoginPresenter(){
        model = new LoginModel(this);
    }

    @Override
    public void loginRealizado() {
        view.loginFinalizado();
    }

    @Override
    public void loginClicado(String email, String pass, FirebaseAuth mAuth) {
        model.realizarLogin(email, pass, mAuth);
    }

    @Override
    public boolean validaCampos(EditText et_campo) {
        if (et_campo.getText().toString().isEmpty()) {
            view.mostraErro(et_campo, "insira o dado necessário");
            return false;
        } else{
            return true;
        }


    }

    @Override
    public void setView(MVPLogin.View view) {
        this.view = view;
    }

    @Override
    public void setActivity(Activity activity) {
        this.act = activity;
    }

    @Override
    public Activity getActivity() {
        return act;
    }

    @Override
    public void firebaseAuthWithGoogle(GoogleSignInAccount acct, FirebaseAuth mAuth) {
        model.firebaseAuthWithGoogle(acct, mAuth);
    }
}
