package com.example.camden.test1;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText programName,hostName,programInfo,telNumber,tutorName,partnersName,ID;
    Button buttonAdd;
    Button buttonViewALl;
    Button buttonUpdate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        myDb = new DatabaseHelper(this);

        ID = (EditText)findViewById(R.id.ID);
        programName = (EditText)findViewById(R.id.programName);
        hostName = (EditText)findViewById(R.id.hostName);
        programInfo = (EditText)findViewById(R.id.programInfo);
        telNumber = (EditText)findViewById(R.id.telNumber);
        tutorName = (EditText)findViewById(R.id.tutorName);
        partnersName = (EditText)findViewById(R.id.partnersName);
        buttonAdd = (Button)findViewById(R.id.buttonAdd);
        buttonViewALl = (Button)findViewById(R.id.buttonViewAll);
        buttonUpdate = (Button)findViewById(R.id.buttonUpdate);



        AddDate();
        viewAll();
        updateData();

    }

    public void AddDate(){
        buttonAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(programName.getText().toString(),
                                 hostName.getText().toString(),
                                 programInfo.getText().toString(),
                                 telNumber.getText().toString(),
                                 tutorName.getText().toString(),
                                 partnersName.getText().toString());
                        if(isInserted == true)
                            Toast.makeText(MainActivity.this,"Add Successfully!!!",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Oops! Bad News",Toast.LENGTH_LONG).show();



                    }
                }
        );
    }

    public void viewAll(){
        buttonViewALl.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            Toast.makeText(MainActivity.this, "No Data", Toast.LENGTH_LONG).show();
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();

                        while (res.moveToNext()){
                            buffer.append("项目编号 : "+res.getString(0)+"\n");
                            buffer.append("项目名称 : "+res.getString(1)+"\n");
                            buffer.append("项目负责人 : "+res.getString(2)+"\n");
                            buffer.append("项目简介 : "+res.getString(3)+"\n");
                            buffer.append("联系电话 : "+res.getString(4)+"\n");
                            buffer.append("导师 : "+res.getString(5)+"\n");
                            buffer.append("组员 : "+res.getString(6)+"\n");
                        }

                        showMessage("所有项目",buffer.toString());


                    }
                }
        );
    }


    public void showMessage(String title,String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }


    public void updateData(){
        buttonUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(ID.getText().toString(),
                                programName.getText().toString(),
                                hostName.getText().toString(),
                                programInfo.getText().toString(),
                                telNumber.getText().toString(),
                                tutorName.getText().toString(),
                                partnersName.getText().toString());
                        if (isUpdate == true)

                            Toast.makeText(MainActivity.this,"Update Successfully!!!",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Oops! Bad News",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
