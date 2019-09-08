package sp.senior.wd;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText account, password;
    TextView loginStatus;
    Button loginButton,createAccount,forgetPassword;

    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        loginStatus = (TextView) findViewById(R.id.loginStatus);
        loginButton = (Button) findViewById(R.id.loginButton);
        createAccount = (Button)findViewById(R.id.createAcctount);
        forgetPassword = (Button)findViewById(R.id.forgatPassword);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mAuth = FirebaseAuth.getInstance();

        updateUserStatus();
        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                updateUserStatus();
            }
        });



    }
    public void login(View v){
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            String accountString = account.getText().toString();
            String passworString = password.getText().toString();
            if(accountString.isEmpty()){
                Toast.makeText(this,"Please Enter Account",Toast.LENGTH_LONG).show();
                return;
            }
            if(passworString.isEmpty()){
                Toast.makeText(this,"Please Enter Password",Toast.LENGTH_LONG).show();
                return;
            }
            mAuth.signInWithEmailAndPassword(accountString,passworString)
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Login.this,"Login Fail:"+e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                        }
                    });

        }else {
            Intent intent1 = new Intent(Login.this, homepage.class);
            intent1.putExtra("id", user.getUid());
            intent1.putExtra("name", user.getDisplayName());
            startActivity(intent1);
            Login.this.finish();
        }
    }

    public void updateUserStatus(){
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            loginButton.setText("LogIn");
            loginStatus.setText("Not Login");
            createAccount.setVisibility(View.VISIBLE);
            forgetPassword.setVisibility(View.VISIBLE);
        }else {
            //loginButton.setText("LogOut");
            loginStatus.setText("Login\n"+user.getEmail()+"\nNickName:"+user.getDisplayName()+"\nVerified："+user.isEmailVerified());
           // createAccount.setVisibility(View.INVISIBLE);
           // forgetPassword.setVisibility(View.INVISIBLE);
          // Intent intent1 = new Intent(Login.this, Date_Budget.class);
           //startActivity(intent1);
         //  Login.this.finish();
            if(!(user.isEmailVerified())){
                user.sendEmailVerification()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this,"Verify email has been successfully sent",Toast.LENGTH_LONG).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this,"Failed："+e.getLocalizedMessage(),Toast.LENGTH_LONG).show();

                    }
                });
            }
        }
    }
    public void forgetPassword(View v){
        String accountString = account.getText().toString();
        if(accountString.isEmpty()){
            Toast.makeText(this,"Please Enter Account",Toast.LENGTH_LONG).show();
            return;
        }
        mAuth.sendPasswordResetEmail(accountString).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                Toast.makeText(Login.this,"Please check your email",Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this,"Failed："+e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

    public void createNewAccount(View v) {
        Intent intent = new Intent(this, NewAccountActivity.class);
        startActivity(intent);
    }
    public void signOut (View v){
        mAuth.signOut();
    }



}
