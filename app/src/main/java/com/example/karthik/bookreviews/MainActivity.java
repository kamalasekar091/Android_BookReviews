package com.example.karthik.bookreviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Karthik","Selvaraj");
        SqlHelper db = new SqlHelper(this);

        /** CRUD Operations **/
        // add Books
        db.addBook(new Book("Professional Android 4 Application Development", "Reto Meier"));
        db.addBook(new Book("Beginning Android 4 Application Development", "Wei-Meng Lee"));
        db.addBook(new Book("Programming Android", "Wallace Jackson"));
        db.addBook(new Book("Hello, Android", "Wallace Jackson"));

        // get all books
        List<Book> list = db.getAllBooks();

        // update one book
        int j = db.updateBook(list.get(3), "Hello, Android", "Ben Jackson");

        // delete one book
        //db.deleteBook(list.get(0));

        // get all books
        db.getAllBooks();
        ListView listContent = (ListView) findViewById(R.id.list);
        List<Book> books = new ArrayList<Book>();
        books=db.getAllBooks();

//get data from the table by the ListAdapter
        ListAdapter customAdapter = new ListAdapter(this, R.layout.itemlistrow,  books);
        listContent.setAdapter(customAdapter);


        db.getIds();
    }
}

