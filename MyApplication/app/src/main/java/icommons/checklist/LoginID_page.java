package icommons.checklist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Tushar on 7/18/16.
 */
public class LoginID_page extends Activity {
    private EditText id;
    private String check;
    private Button submit;
    private TextView wrong_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.studentid);

        id=(EditText) findViewById(R.id.id_field);
        wrong_pass = (TextView) findViewById(R.id.response);
        id.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        submit = (Button)findViewById(R.id.submit);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        check = id.getText().toString();

        id.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (id.getText().toString().equalsIgnoreCase("13736568")) {
                    startActivity(new Intent(LoginID_page.this, scanner.class));
                } else {
                    wrong_pass.setText("Wrong Password! Try Again");
                }


                return true;
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id.getText().toString().equalsIgnoreCase("13736568")) {
                    startActivity(new Intent(LoginID_page.this, scanner.class));
                } else {
                    wrong_pass.setText("Wrong Password! Try Again");
                }

            }

        });


    }



}
