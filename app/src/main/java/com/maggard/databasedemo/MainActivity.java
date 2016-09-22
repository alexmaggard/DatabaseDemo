package com.maggard.databasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText entryEditText;
    private Button addButton;
    private Button showButton;
    private Button deleteButton;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get reference to widgets
        entryEditText = (EditText) findViewById(R.id.entryEditText);
        addButton = (Button) findViewById(R.id.addButton);
        showButton = (Button) findViewById(R.id.showButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);

        dbHandler = new MyDBHandler(this,null,null,1);

    }

    public void addClick(View view) {
        Products products = new Products(entryEditText.getText().toString());
        dbHandler.addProduct(products);
    }

    public void deleteClick(View view) {
        String entryText = entryEditText.getText().toString();
        dbHandler.deleteProduct(entryText);
    }

    public void showClick(View view) {
        
    }


}
