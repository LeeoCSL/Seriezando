package leonardoribeiro.seriezando.MVP.Activity.Login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginModel {

    MVPLogin.Presenter presenter;

    public LoginModel(MVPLogin.Presenter presenter){
        this.presenter = presenter;
    }

    public void realizarLogin(String email, String password, final FirebaseAuth mAuth) {
        final String mail = email;
        final String pass = password;
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(presenter.getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("login", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
//                            Paper.book().write("email", mail);
//                            Paper.book().write("password", pass);
                              presenter.loginRealizado();
//                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("login", "signInWithEmail:failure", task.getException());
                        }

                        // ...
                    }
                });
    }

    public void firebaseAuthWithGoogle(GoogleSignInAccount acct, final FirebaseAuth mAuth) {
        // [START_EXCLUDE silent]

        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(presenter.getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();

                            presenter.loginRealizado();
                        } else {
                            // If sign in fails, display a message to the user.
                        }

                        // [START_EXCLUDE]
                        // [END_EXCLUDE]
                    }
                });
    }



}
