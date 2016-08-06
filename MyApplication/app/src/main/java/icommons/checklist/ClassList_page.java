package icommons.checklist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Tushar on 7/7/16.
 */
public class ClassList_page extends Activity {
    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.class_list);

        list = (ListView)findViewById(R.id.listView);
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(adapter);
        arrayList.add("SC 326");
        arrayList.add("UC 149");
        arrayList.add("UC 151");
        arrayList.add("UC 153");
        arrayList.add("RUSH 006");
        arrayList.add("RUSH 009");
        arrayList.add("RUSH 014");
        arrayList.add("RUSH 205");
        arrayList.add("RUSH 209");
        arrayList.add("iCommons LAB");
        arrayList.add("uCommons LAB");

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String item = ((TextView)view).getText().toString();

                Toast.makeText(getBaseContext(), item, Toast.LENGTH_SHORT).show();
                if ((((TextView)view).getText().toString()).equalsIgnoreCase("rush009")){
                    startActivity(new Intent(ClassList_page.this, checklist_page.class));
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(ClassList_page.this, scanner.class));
        return;
    }
}
