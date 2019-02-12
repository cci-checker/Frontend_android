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
 * Created by Tushar on 7/7/16.
 */
public class PIN extends Activity{
    private EditText pin_text;
    private String check;
    private Button submit;
    private TextView wrong_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.pin);

        pin_text=(EditText) findViewById(R.id.pin_field);
        wrong_pass = (TextView) findViewById(R.id.textView2);
        pin_text.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        submit = (Button)findViewById(R.id.button2);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        check = pin_text.getText().toString();

       pin_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
           @Override
           public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
               if (pin_text.getText().toString().equalsIgnoreCase("0000")) {
                   startActivity(new Intent(PIN.this, ClassList_page.class));
               } else {
                   wrong_pass.setText("Wrong Password! Try Again");
               }


               return true;
           }
       });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pin_text.getText().toString().equalsIgnoreCase("0000")) {
                    startActivity(new Intent(PIN.this, ClassList_page.class));
                } else {
                    wrong_pass.setText("Wrong Password! Try Again");
                }

            }

        });


    }



}
