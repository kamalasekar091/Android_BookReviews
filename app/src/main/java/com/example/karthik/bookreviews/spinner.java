package com.example.karthik.bookreviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

public class spinner extends AppCompatActivity {

    Spinner spi;

    SqlHelper sh = new SqlHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner);

        spi=(Spinner) findViewById((R.id.spin));

        spi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View view, int position, long id) {
                int item = spi.getSelectedItemPosition();

               
                if(item==1){

                    String HighTitle=sh.highestRatedBook();
                    Toast.makeText(getBaseContext(), "Book with Highest Rating:- \n"+HighTitle, Toast.LENGTH_LONG).show();


                }
                if(item==2){
                    String LowTitle=sh.lowestRatedBook();
                    Toast.makeText(getBaseContext(), "Book with lowest rating:- \n"+LowTitle, Toast.LENGTH_LONG).show();

                }
                if(item==3){
                    int count= sh.getIds();

                    Toast.makeText(getBaseContext(), "The number of items in database is: - "+count, Toast.LENGTH_LONG).show();

                }
                if(item==4){

                    String Titles=sh.getandroid4();
                    Toast.makeText(getBaseContext(), "Titiles with Android 4:- \n"+Titles, Toast.LENGTH_LONG).show();

                }

            }
            public void onNothingSelected(AdapterView<?> arg0) { }
        });


    }



}
