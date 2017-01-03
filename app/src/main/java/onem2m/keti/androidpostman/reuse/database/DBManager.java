package onem2m.keti.androidpostman.reuse.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {

    // Defining the DB NAME, TABLE NAME, DB VERSION
    static final String DB_NAME = "forest";
    static final String TABLE_NAME = "blossom";
    static final int DB_VERSION = 1;

    Context context = null;

    public static DBManager mDbManager = null;

    public static DBManager getInstance(Context context) {
        if (mDbManager == null)
            mDbManager = new DBManager(context, DB_NAME, null, DB_VERSION);

        return mDbManager;
    }

    private DBManager(Context context, String dbName, CursorFactory factory, int version) {
        super(context, dbName, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                        "(" + "requestnumber  INTEGER PRIMARY KEY, " +
                            "operation        TEXT NOT NULL, " +
                            "url          TEXT NOT NULL, " +
                            "header1    TEXT, " +
                            "header2    TEXT, " +
                            "header3    TEXT, " +
                            "header4    TEXT, " +
                            "header5    TEXT, " +
                            "header6    TEXT, " +
                            "header7    TEXT, " +
                            "header8    TEXT, " +
                            "header9    TEXT, " +
                            "xmlbody    TEXT, " +
                            "jsonbody   TEXT); ");
    }

    @Override
    public void onOpen(SQLiteDatabase db)
    {
        super.onOpen( db );
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < newVersion) {
            // 기존 테이블을 삭제하고 새로운 테이블을 생성한다.
            db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME );
            onCreate(db);
        }
    }

    public long insert(ContentValues addRowValue) {
        return getWritableDatabase().insert(TABLE_NAME, null, addRowValue);
    }

    public Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return getReadableDatabase().query(TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy);
    }
    
    public int update(ContentValues updateRowValue, String whereClause, String[] whereArgs) {
        return getWritableDatabase().update(TABLE_NAME, updateRowValue, whereClause, whereArgs);
    }
    
    public int delete(String whereClause, String[] whereArgs) {
        return getWritableDatabase().delete(TABLE_NAME, whereClause, whereArgs);
    }
}