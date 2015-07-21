package jp.androidbook.myapp.googlemapsapplication01;

/**
 * Created by SunakoY on 2015/07/14.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "myDatabase.db";
    static final int DATABASE_VERSION = 1;
    static final String TABLE_NAME = "myData";

    static final String ID = "id";

    static final String NAME = "name";
    static final String TIME = "time";
    static final String ADDRESS = "address";
    static final String PARKING = "parking";
    static final String PRODUCT = "product";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NAME + " TEXT," +
                TIME + " TEXT," +
                ADDRESS + " TEXT," +
                PARKING + " TEXT," +
                PRODUCT + " TEXT);";

        db.execSQL(query);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
