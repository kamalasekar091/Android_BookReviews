package com.example.karthik.bookreviews;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by Karthik on 4/4/2016.
 */
public class SqlHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 8;
    // Database Name
    private static final String DATABASE_NAME = "BookDB";

    // Books table name
    private static final String TABLE_BOOKS = "books";

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AUTHOR = "author";

    int i=0;

    public SqlHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_BOOK_TABLE = "CREATE TABLE books ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, "+
                "author TEXT )";

        // create books table
        db.execSQL(CREATE_BOOK_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS books");

        // create fresh books table
        this.onCreate(db);
        Log.i("I'm Onupgrade","Before Alter");

        String upgradeQuery = "ALTER TABLE books ADD COLUMN rating TEXT";
        if (oldVersion == 7 && newVersion == 8)
            db.execSQL(upgradeQuery);


    }
    public String highestRatedBook(){
       Book book = new Book();
        String Title;
        String selectQuery = "SELECT title FROM books WHERE rating='4'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        Title=cursor.getString(0);
        return Title;
    }

    public String lowestRatedBook(){
        Book book = new Book();
        String Title;
        String selectQuery = "SELECT title FROM books WHERE rating='1'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        Title=cursor.getString(0);
        return Title;
    }
    public String getandroid4() {
        String Title = "";
        int i=0;
        StringBuilder sb = new StringBuilder();
        String selectQuery = "SELECT title FROM books WHERE title LIKE '%Android 4%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {


            sb.append(cursor.getString(0));
                if (i==0){
                    sb.append(",\n ");
                    i++;
                }

        } while (cursor.moveToNext()) ;
    }
     Title=sb.toString();
        return Title;
    }



    /*CRUD operations (create "add", read "get", update, delete) */



    public void addBook(Book book){


        if (i==0){
            i=i+1;
        }

        Log.d("addBook", book.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, book.getTitle()); // get title
        values.put(KEY_AUTHOR, book.getAuthor()); // get author

        // 3. insert
        db.insert(TABLE_BOOKS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/values

        // 4. Close dbase
        db.close();
    }
    // Get All Books
    public List<Book> getAllBooks() {
        List<Book> books = new LinkedList<Book>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_BOOKS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Book book = null;
        if (cursor.moveToFirst()) {
            do {
                book = new Book();
                book.setId(Integer.parseInt(cursor.getString(0)));
                book.setTitle(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                book.setRating(cursor.getString(3));
                // Add book to books
                books.add(book);
            } while (cursor.moveToNext());
        }

        Log.d("getAllBooks()", books.toString());

        return books; // return books
    }
    // Updating single book
    public int updateBook(Book book,String newTitle, String newAuthor) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        System.out.print("New Title:- "+newTitle);
        System.out.print("New Author:- "+newAuthor);
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("title", newTitle); // get title
        values.put("author",newAuthor); // get author

        // 3. updating row
        int i = db.update(TABLE_BOOKS, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(book.getId()) }); //selection args
        // 4. close dbase
        db.close();
        Log.d("UpdateBook", book.toString());
        return i;

    }
    // Deleting single book
    public void deleteBook(Book book) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_BOOKS,
                KEY_ID+" = ?",
                new String[] { String.valueOf(book.getId()) });

        // 3. close
        db.close();

        Log.d("deleteBook", book.toString());
    }

//    public int getIds(Book book)
    //{

    public int getIds()
    {
        String selectQuery = "SELECT id FROM books";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.rawQuery(selectQuery, null);
        c.moveToFirst();
        int total = c.getCount();
        Log.d("Number of Records:- ",String.valueOf(total));

        return total;


    }

}
