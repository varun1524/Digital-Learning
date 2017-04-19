package com.example.varun.signtuarecapture;

/**
 * Created by PANAM SHAH on 14-02-2015.
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.provider.MediaStore;
import android.util.Log;


public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "ImgDB";
    private SQLiteDatabase mDatabase;

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table

        String CREATE_IMAGE_TABLE = "CREATE TABLE Images ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +

                "name TEXT )";

        System.out.println("table created");

        // create books table
        db.execSQL(CREATE_IMAGE_TABLE);
    }

    //public void makeTable(SQLiteDatabase )

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS Images");

        // create fresh books table
        this.onCreate(db);
    }
    //---------------------------------------------------------------------

    /**
     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
     */

    // Books table name
    private static final String TABLE_IMAGES = "Images";

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "name";


    private static final String[] COLUMNS = {KEY_ID,KEY_TITLE};

    public void addImage(Image img) {
        Log.d("addImage", img.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, img.getImgName()); // get title


        // 3. insert
        db.insert(TABLE_IMAGES, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }
    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_IMAGES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public Image getImage(int id) {

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_IMAGES, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[]{String.valueOf(id)}, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object
        Image image = new Image("");
        image.setId(Integer.parseInt(cursor.getString(0)));
        image.setImgName(cursor.getString(1));


        Log.d("getImage(" + id + ")", image.toString());

        // 5. return book
        return image;
    }

    // Get All Books
    public ArrayList<Image> getAllImages() {
        ArrayList<Image> images = new ArrayList<Image>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_IMAGES;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Image image = null;
        if (cursor.moveToFirst()) {
            do {
                image = new Image("");
                image.setId(Integer.parseInt(cursor.getString(0)));
                image.setImgName(cursor.getString(1));


                // Add book to books
                images.add(image);
            } while (cursor.moveToNext());
        }

        Log.d("getAllImages()", images.toString());

        // return books
        return images;
    }
/*
    // Updating single book
    public int updateBook(Book book) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("title", book.getTitle()); // get title
        values.put("author", book.getAuthor()); // get author

        // 3. updating row
        int i = db.update(TABLE_BOOKS, //table
                values, // column/value
                KEY_ID + " = ?", // selections
                new String[]{String.valueOf(book.getId())}); //selection args

        // 4. close
        db.close();

        return i;

    }

    */

    // Deleting single book
    public void deleteImage(Image image) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_IMAGES,
                KEY_ID + " = ?",
                new String[]{String.valueOf(image.getId())});

        // 3. close
        db.close();

        Log.d("deleteBook", image.toString());

    }
    public void deleteAll()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_IMAGES,null,null);
    }

    public long fetchPlacesCount() {
        String sql = "SELECT COUNT(*) FROM " + TABLE_IMAGES;
        SQLiteStatement statement = mDatabase.compileStatement(sql);
        long count = statement.simpleQueryForLong();
        return count;
    }
}

















