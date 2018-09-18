package leonardoribeiro.seriezando.MVP.Activity.Register;

import android.app.Activity;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public interface MVPRegister {

    interface View{


        void cadastrar();
        void mostraErro(EditText et_campo, String mensagem);
        void cadastroFinalizado(String email, String senha);

    }

    interface Presenter{
        void setView(MVPRegister.View view);
        Activity getActivity();
        void setActivity(Activity activity);
        boolean validaCampos(EditText et_campo);
        void realizaCadastro(String email, String senha, FirebaseAuth mAuth);
        void cadastroFeito(String email, String senha);

    }
}
