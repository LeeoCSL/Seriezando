package leonardoribeiro.seriezando.MVP.Activity.Register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import leonardoribeiro.seriezando.MVP.Activity.Login.LoginActivity;
import leonardoribeiro.seriezando.Models.Usuario;
import leonardoribeiro.seriezando.R;
import mehdi.sakout.fancybuttons.FancyButton;

public class RegisterActivity extends AppCompatActivity implements MVPRegister.View {

    RegisterPresenter presenter = new RegisterPresenter();

    FancyButton btn_cadastro;

    EditText et_email;
    EditText et_senha;
    EditText et_nome;

    String email, password;
    private FirebaseAuth mAuth;

    DatabaseReference databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        presenter.setActivity(this);
        presenter.setView(this);

        loadUI();
        databaseUser = FirebaseDatabase.getInstance().getReference("usuarios");
        btn_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cadastrar();

            }


        });

    }

    private void loadUI() {
        btn_cadastro = findViewById(R.id.btn_cadastro);
        et_email = findViewById(R.id.et_email);
        et_senha = findViewById(R.id.et_senha);
        et_nome = findViewById(R.id.et_nome);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    public void cadastrar() {

        if (!presenter.validaCampos(et_email)) return;
        if (!presenter.validaCampos(et_senha)) return;

        presenter.realizaCadastro(et_email.getText().toString(), et_senha.getText().toString(), mAuth);
    }

    @Override
    public void mostraErro(EditText et_campo, String mensagem) {
        et_campo.setError(mensagem);
    }

    @Override
    public void cadastroFinalizado(String email, String senha) {

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        String id = databaseUser.push().getKey();
        usuario.setId(id);
        usuario.setSeriesVistas(null);
        databaseUser.child(id).setValue(usuario);

        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.putExtra("email", email);
        intent.putExtra("password", senha);
        startActivity(intent);
        finish();
    }
}
