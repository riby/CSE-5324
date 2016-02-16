package com.example.uta.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    public static final String DUMMY_CREDENTIALS = "user@mail.com:123";

    private View loginForm;
    private View loginProgressBar;
    private EditText loginEmail;
    private EditText loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* These trivial processes can be taken care by some userful tools: try butterknife
         * Portal to butterknife: https://github.com/JakeWharton/butterknife */
        loginForm = findViewById(R.id.scrollViewLoginForm);
        loginProgressBar = findViewById(R.id.progressBarLogin);
        loginEmail = (EditText) findViewById(R.id.editTextEmailAddress);
        loginPassword = (EditText) findViewById(R.id.editTextLoginPassword);

        /* Press login button to init login process */
        Button loginButton = (Button) findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initLogin();
            }
        });
    }

    private void initLogin() {
        //TODO: Login credential verification

        /* Login */
        showProgress(true);
        UserLoginTask task = new UserLoginTask(loginEmail.getText().toString(), loginPassword.getText().toString());
        task.execute();
    }

    /**
     * Sometimes you have to be careful with internet access, be careful not to block your main thread with I/O events!!
     * Lazy to deal with all these asynchronous calls? Try Retrofit!
     * Portal to retrofit: http://square.github.io/retrofit/
     */
    private class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private String emailStr;
        private String passwordStr;

        UserLoginTask(String email, String password) {
            emailStr = email;
            passwordStr = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            String[] credentials = DUMMY_CREDENTIALS.split(":");
            if (credentials[0].equals(emailStr) && credentials[1].equals(passwordStr))
                return true;
            else
                return false;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            showProgress(false);

            if (success) {
                /* Start the main activity */
                Toast.makeText(getApplicationContext(), "Login Success!", Toast.LENGTH_LONG).show();
                /* Start main activity */
                navigateToHomeScreen();
            } else {
                Toast.makeText(getApplicationContext(), "Login Failed: Invalid!", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * Simply visualize the login progress
     *
     * @param show
     */
    private void showProgress(boolean show) {
        loginProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    /**
     * Navigate out login screen to home screen
     */
    private void navigateToHomeScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
