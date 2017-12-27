package com.example.varun.digitallearning;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Dell on 23-03-2015.
 */
public class UserDatabase  extends SQLiteOpenHelper {


    /**
     * Created by Dell on 12-02-2015.
     */




    // Database Version
    private static final int DATABASE_VERSION = 7;
    // Database Name
    private static final String DATABASE_NAME = "UserDB";

    public static int ROWS=0;


    public UserDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create table
        String CREATE_USER_TABLE = "CREATE TABLE Users ( " +
                "id INTEGER PRIMARY KEY  AUTOINCREMENT, " +

                "name TEXT ," + "std INTEGER ,"+" age INTEGER," + " pass INTEGER" + " imgPath TEXT)";
        System.out.println("table create");

        // create USER table
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older users table if existed
        db.execSQL("DROP TABLE IF EXISTS Users");

        // create fresh users table
        this.onCreate(db);
    }
    //---------------------------------------------------------------------

    /**
     * CRUD operations (create "add", read "get", update, delete) user + get all users + delete all users
     */

    // users table name
    private static final String TABLE_Users = "Users";

    // users Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_STD = "std";
    public static final String KEY_AGE = "age";
    public static final String KEY_PASS = "pass";
   // private static final String KEY_PATH="imgPath";



    private static final String[] COLUMNS = {KEY_ID, KEY_NAME, KEY_STD,KEY_AGE ,KEY_PASS};

    public void addUser(User user) {
        Log.d("addUser", user.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, user.getName()); // get title
        values.put(KEY_STD, user.getStd());
        values.put(KEY_AGE,user.getAge());// get author
        values.put(KEY_PASS,user.getPass()); // get password
        //values.put(KEY_PATH,user.getImgPath());
        // 3. insert
        db.insert(TABLE_Users, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }


    public User getUser(int id) {

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_Users, // a. table
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


        // 4. build user object
        User user = new User("");
        user.setId(Integer.parseInt(cursor.getString(0)));
        user.setName(cursor.getString(1));
        user.setStd(cursor.getString(2));
        user.setAge(cursor.getString(3));
        user.setPass(cursor.getString(4));
       // user.setImgPath(cursor.getString(5));


        Log.d("getUser(" + id + ")", user.toString());

        // 5. return user
        return user;
    }

   public Cursor fetchAllCountries() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor mCursor = db.query(TABLE_Users, new String[]{KEY_ID,
                        KEY_NAME, KEY_STD, KEY_AGE},
                null, null, null, null,null);
        System.out.println(mCursor);
        if (mCursor != null) {
            mCursor.moveToFirst();

        }
        return mCursor;
    }


    // Get All users
    public ArrayList<User> getAllUsers() {
               ArrayList<User> users = new ArrayList<User>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_Users;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build user and add it to list
        User user = null;
        if (cursor.moveToFirst()) {
            do {
                user = new User("");
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setName(cursor.getString(1));
                user.setStd(cursor.getString(2));
                user.setAge(cursor.getString(3));
                user.setPass(cursor.getString(4));
            //    user.setImgPath(cursor.getString(5));

                // Add user to users
                users.add(user);
                System.out.println(cursor.getString(1));

            } while (cursor.moveToNext());
        }

        Log.d("getAllUsers()", users.toString());

        // return users
        return users;
    }

    // Updating single user
    public int updateUser(User user) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("Name", user.getName()); // get title
        values.put("Std", user.getStd()); // get author
        values.put("Age",user.getAge());
        values.put("Pass",user.getPass());
        //values.put("Path",user.getImgPath());


        // 3. updating row
        int i = db.update(TABLE_Users, //table
                values, // column/value
                KEY_ID + " = ?", // selections
                new String[]{String.valueOf(user.getId())}); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single user
    public void deleteUser(User user) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_Users,
                KEY_ID + " = ?",
                new String[]{String.valueOf(user.getId())});

        // 3. close
        db.close();

        Log.d("deleteUser", user.toString());

    }
    public  void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Users, null, null);

    }

    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_Users;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        ROWS=cnt;
        return cnt;
    }
}