package icommons.checklist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import icommons.checklist.Interface_api.MyApiEndpointInterface;
import icommons.checklist.Pojo_classes.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tushar on 7/18/16.
 */
public class LoginID_page extends Activity {
    private static final String TAG = "Login Page";
    private EditText id;
    private String check;
    private Button submit;
    private TextView wrong_pass;
    Integer st_UID=null;

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



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApiEndpointInterface.factory.getInstance().getUsers().enqueue(new Callback<List<Users>>() {
                    @Override
                    public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {

                        if (response.isSuccessful()) {
                            List<Users> usersList = response.body();


                            Boolean match_found = false;
                            String val = id.getText().toString();
                            if (val.equalsIgnoreCase("")) {
                                wrong_pass.setText("Please enter your Student ID (Ex:12345678)");
                            } else {
                                int input_id = Integer.parseInt(id.getText().toString());
                                for (int a = 0; a < usersList.size(); a++) {
                                    if (response.body().get(a).getStudentID() == input_id) {
                                        match_found = true;
                                        st_UID=response.body().get(a).getId();
                                        break;
                                    }
                                }

                                if (match_found == true) {
                                    Intent in=new Intent(LoginID_page.this, scanner.class);
                                    in.putExtra("St_uid", st_UID);
                                    startActivity(in);
                                    wrong_pass.setText("");
                                } else {
                                    wrong_pass.setText("You are not in the system :(");
                                }

                            }
                        }else{
                            wrong_pass.setText("Oops, something went wrong!");
                        }


                    }
                    @Override
                    public void onFailure(Call<List<Users>> call, Throwable t) {
                        Log.d(TAG, "Failed :"+t.getMessage());
                        wrong_pass.setText("API call failed");

                    }
                });

            }

        });


    }



}
