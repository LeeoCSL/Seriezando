package leonardoribeiro.seriezando.MVP.Activity.Register;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterModel {

    MVPRegister.Presenter presenter;


    public RegisterModel(MVPRegister.Presenter presenter){
        this.presenter = presenter;

    }


    public void realizarCadastro(final String email, String password, final FirebaseAuth mAuth) {
        final String mail = email;
        final String pass = password;
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(presenter.getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            presenter.cadastroFeito(mail, pass);
//                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                            intent.putExtra("email", mail);
//                            intent.putExtra("password", pass);
//                            startActivity(intent);
//                            finish();
                        } else {
                            // If sign in fails, display a message to the user.

                        }

                        // ...
                    }
                });
    }
}
