package leonardoribeiro.seriezando.MVP.Activity.Register;

import android.app.Activity;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterPresenter implements MVPRegister.Presenter {

    String email, password;

    MVPRegister.View view;
    RegisterModel model;



    Activity act;

    @Override
    public void setActivity(Activity activity) {
        this.act = activity;
    }

    @Override
    public Activity getActivity() {

        return act;
    }


    @Override
    public void setView(MVPRegister.View view) {
        this.view = view;
    }

    public RegisterPresenter(){

        model = new RegisterModel(this);
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
    public void realizaCadastro(String email, String senha, FirebaseAuth mAuth) {
        model.realizarCadastro(email, senha, mAuth);
    }

    @Override
    public void cadastroFeito(String mail, String pass) {
        view.cadastroFinalizado(mail, pass);
    }
}
