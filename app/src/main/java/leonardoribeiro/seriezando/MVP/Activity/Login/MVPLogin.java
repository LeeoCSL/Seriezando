package leonardoribeiro.seriezando.MVP.Activity.Login;

import android.app.Activity;
import android.widget.EditText;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

public interface MVPLogin {

    interface View{
        void mostraErro(EditText et_campo, String mensagem);
        void loginFinalizado();
    }

    interface Presenter{
        void setActivity(Activity activity);
        Activity getActivity();
        void setView(MVPLogin.View view);
        boolean validaCampos(EditText et_campo);
        void loginClicado(String email, String senha, FirebaseAuth mAuth);
        void loginRealizado();
        void firebaseAuthWithGoogle(GoogleSignInAccount acct, FirebaseAuth mAuth);
    }
}
