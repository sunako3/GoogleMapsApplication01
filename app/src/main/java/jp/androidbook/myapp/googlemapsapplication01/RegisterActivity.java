package jp.androidbook.myapp.googlemapsapplication01;

import android.app.*;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.sql.Time;


public class RegisterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    public  void onStart(){
        super.onStart();

        final Button regBtn = (Button)findViewById(R.id.regButton);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regIntent = new Intent(RegisterActivity.this, ListActivity.class);

                //入力情報の受け取り
                EditText name = (EditText) findViewById(R.id.regNameEdit);
                EditText time = (EditText) findViewById(R.id.regTimeEdit);
                EditText add = (EditText) findViewById(R.id.regAddEdit);
                EditText par = (EditText) findViewById(R.id.regParEdit);
                EditText pro = (EditText) findViewById(R.id.regProEdit);

                //受け取った情報をIntentに設定
                regIntent.putExtra("name", name.getText().toString());
                regIntent.putExtra("time", time.getText().toString());
                regIntent.putExtra("add", add.getText().toString());
                regIntent.putExtra("par", par.getText().toString());
                regIntent.putExtra("pro", pro.getText().toString());

                startActivity(regIntent);

            }
        });
    }
}
