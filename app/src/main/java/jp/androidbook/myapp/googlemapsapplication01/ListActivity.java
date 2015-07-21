package jp.androidbook.myapp.googlemapsapplication01;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


public class ListActivity extends Activity {

    private ListView myListView;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    @Override
    public void onStart() {
        super.onStart();

        myListView = (ListView) findViewById(R.id.listView);
        DBHelper myDbHelper = new DBHelper(this);
        db = myDbHelper.getWritableDatabase();

        final Intent listIntent = getIntent();

        final String[] regData = new String[5];

        Button mainBtn = (Button) findViewById(R.id.listButton);
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(ListActivity.this, RegisterActivity.class);

                startActivity(mainIntent);


            }
        });

        try {

            //regDateにgetIntentで受け取ったデータを格納
            regData[0] = listIntent.getStringExtra("name");
            regData[1] = listIntent.getStringExtra("time");
            regData[2] = listIntent.getStringExtra("add");
            regData[3] = listIntent.getStringExtra("par");
            regData[4] = listIntent.getStringExtra("pro");

            insertToDB(regData);
            Cursor c = searchToDB();

            String[] from = {DBHelper.NAME, DBHelper.TIME, DBHelper.ADDRESS, DBHelper.PARKING, DBHelper.PRODUCT};

            int[] to = {R.id.name, R.id.time, R.id.add, R.id.par, R.id.pro};

            SimpleCursorAdapter adapter =
                    new SimpleCursorAdapter(this, R.layout.listitem_layout, c, from, to, 0);

            myListView.setAdapter(adapter);

            myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

                    String s1 = ((TextView) view.findViewById(R.id.name)).getText().toString();
                    String s2 = ((TextView) view.findViewById(R.id.time)).getText().toString();
                    String s3 = ((TextView) view.findViewById(R.id.add)).getText().toString();
                    String s4 = ((TextView) view.findViewById(R.id.par)).getText().toString();
                    String s5 = ((TextView) view.findViewById(R.id.pro)).getText().toString();

                    Log.v("name   ", "=" + s1);
                    Log.v("time   ", "=" + s2);
                    Log.v("address", "=" + s3);
                    Log.v("parking", "=" + s4);
                    Log.v("product", "=" + s5);

                    //Listの項目がクリックされたらAlertDialogを表示する
                    AlertDialog.Builder ad = new AlertDialog.Builder(ListActivity.this);
                    ad.setTitle("店名" + s1 + "\n");
                    ad.setMessage("" + s2 + "\n" + s3 + "\n" + s4 + "\n" + s5);
                    ad.create().show();

                    Intent mapIntent = new Intent(ListActivity.this, MapsActivity.class);

                    mapIntent.putExtra("add", regData[2].toString());

                    startActivity(mapIntent);

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

        public void insertToDB(String[] regData) throws Exception{

                db.execSQL("INSERT INTO myData (" + DBHelper.NAME + ","
                + DBHelper.TIME + ","
                + DBHelper.ADDRESS + ","
                + DBHelper.PARKING + ","
                + DBHelper.PRODUCT + ")values('" + regData[0] + "','" + regData[1] + "','" + regData[2] +
                "','" + regData[3] + "','" + regData[4] + "')");

        }

    public Cursor searchToDB() throws Exception{
            Cursor c = db.rawQuery("select * from " + DBHelper.TABLE_NAME,null);


            return c;
    }

}
