package leonardoribeiro.seriezando.MVP.Activity.Login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.paperdb.Paper;
import leonardoribeiro.seriezando.MVP.Activity.Main.MainActivity;
import leonardoribeiro.seriezando.MVP.Activity.Register.RegisterActivity;
import leonardoribeiro.seriezando.Models.Usuario;
import leonardoribeiro.seriezando.R;
import leonardoribeiro.seriezando.RetrofitInit;
import leonardoribeiro.seriezando.application.CustomApplication;
import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements MVPLogin.View{
    private FirebaseAuth mAuth;

    FancyButton btn_login;
    FancyButton btn_registrar_se;
    FancyButton btn_google;
//    FancyButton btn_facebook;

    EditText et_email;
    EditText et_senha;

    String email, password;

    MVPLogin.Presenter presenter = new LoginPresenter();

    DatabaseReference databaseUser;

    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        loadUI();
        databaseUser = FirebaseDatabase.getInstance().getReference("usuarios");

        presenter.setActivity(this);
        presenter.setView(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // [END config_signin]

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
        Paper.init(this);
        Intent it = getIntent();

        if(it.hasExtra("email") && it.hasExtra("password")){
            email = (String) it.getSerializableExtra("email");
            password = (String) it.getSerializableExtra("password");
            presenter.loginClicado(email, password, mAuth);
        } else if(Paper.book().read("email")  != null && Paper.book().read("password") != null){
            email = Paper.book().read("email");
            password = Paper.book().read("password");
            presenter.loginClicado(email, password, mAuth);
        } else{
            btn_google.performClick();
        }

        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        btn_registrar_se.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if(et_email.getText().toString().isEmpty()){
                    et_email.setError("insira email");
                    return;
                } else if(et_senha.getText().toString().isEmpty()){
                    et_senha.setError("insira senha");
                    return;
                }

                else{
                    email = et_email.getText().toString();
                    password = et_senha.getText().toString();


                    presenter.loginClicado(email, password, mAuth);
                }



            }



        });


    }

    private void loadUI() {
        btn_login = findViewById(R.id.btn_login);
        et_email = findViewById(R.id.et_email);
        et_senha = findViewById(R.id.et_senha);
        btn_registrar_se = findViewById(R.id.btn_registrar_se);
        btn_google = findViewById(R.id.btn_google);
//        btn_facebook = findViewById(R.id.btn_facebook);

//        btn_facebook.setEnabled(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                presenter.firebaseAuthWithGoogle(account, mAuth);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                // [START_EXCLUDE]
                // [END_EXCLUDE]
            }
        }

    }



    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // [END signin]

    private void signOut() {
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    }
                });
    }

    private void revokeAccess() {
        // Firebase sign out
        mAuth.signOut();

        // Google revoke access
        mGoogleSignInClient.revokeAccess().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    }
                });
    }



    @Override
    public void mostraErro(EditText et_campo, String mensagem) {
        et_campo.setError(mensagem);
    }

    @Override
    public void loginFinalizado() {

        getInfosLogin();

        startActivity(new Intent(this, MainActivity.class));
    }

    private void getInfosLogin() {
        //TODO pegar infos do bd
        Log.v("aaaaaaaaa", CustomApplication.currentUser.getId());
        Call<Usuario> call = new RetrofitInit(LoginActivity.this).getUsuarioService().getUser(CustomApplication.currentUser.getId());
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.body().getSeriesVistas() != null) {
                    CustomApplication.currentUser.setSeriesVistas(response.body().getSeriesVistas());
                }
                Toast.makeText(LoginActivity.this, "pegou tudo", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "deu ruim", Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

}
