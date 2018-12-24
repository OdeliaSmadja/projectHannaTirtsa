package com.project5779.javaproject2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.project5779.javaproject2.controller.Register;
import com.project5779.javaproject2.model.backend.BackEndFactory;

public class LoginActivity extends Activity {

    //private LinearLayout loginLayout;
    private EditText Email;
    private EditText password;
    private CheckBox CheckBoxRememberMe;
    private Button ButtonLogin;
    private TextView createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
       // BackEndFactory.getInstance(LoginActivity.this).getListDriveAvailable();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPreferences.getBoolean(getString(R.string.save_Password), false)) {
            Email.setText(sharedPreferences.getString(String.valueOf(R.string.Email), null));
            password.setText(sharedPreferences.getString(String.valueOf(R.string.password), null));
            CheckBoxRememberMe.setChecked(true);
        }
    }

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-12-18 12:59:03 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
       // loginLayout = (LinearLayout)findViewById( R.id.login_layout );
        Email = (EditText)findViewById( R.id.EditTextEmail );
        password = (EditText)findViewById( R.id.password );
        CheckBoxRememberMe = (CheckBox)findViewById( R.id.CheckBoxRememberMe );
        ButtonLogin = (Button)findViewById( R.id.ButtonLogin );
        createAccount = (TextView)findViewById( R.id.createAccount );

        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(String.valueOf(R.string.Email), Email.getText().toString());
                editor.putString(String.valueOf(R.string.password), password.getText().toString());
                editor.putBoolean(getString(R.string.save_Password), CheckBoxRememberMe.isChecked());
                editor.apply();
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Register.class);
                startActivity(intent);
            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        Email.addTextChangedListener(textWatcher);
        password.addTextChangedListener(textWatcher);
    }

}
