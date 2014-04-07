package com.cmu.ss.app;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class StartPage extends ActionBarActivity {
    CountStudentNo countStudentNo = new CountStudentNo();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        SQLiteDatabase db = openOrCreateDatabase("score.db", Context.MODE_PRIVATE, null);


        db.execSQL("DROP TABLE IF EXISTS student");

        countStudentNo.resetStudentNo();

        db.execSQL("CREATE TABLE student (_id INTEGER PRIMARY KEY AUTOINCREMENT, stu_id VARCHAR, " +
                "q1 INTEGER, q2 INTEGER, q3 INTEGER, q4 INTEGER, q5 INTEGER)");

        TextView stuNumber = (TextView) findViewById(R.id.stuNumber);

        ContentValues cv = new ContentValues();
        cv.put("stu_id", 1234);
        cv.put("q1",87);
        cv.put("q2",76);
        cv.put("q3",78);
        cv.put("q4",87);
        cv.put("q5",78);
        db.insert("student", null, cv);
        countStudentNo.addStudentNo();

        cv.put("stu_id", 889);
        cv.put("q1",98);
        cv.put("q2",78);
        cv.put("q3",23);
        cv.put("q4",45);
        cv.put("q5",34);
        db.insert("student", null, cv);
        countStudentNo.addStudentNo();


        String sql = "SELECT stu_id from student";
        Cursor c = db.rawQuery(sql,null);
        int studentCount = c.getCount();
        stuNumber.setText(Integer.toString(studentCount));
        c.close();
        db.close();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void calculate(View view) {
        Intent intent = new Intent(this, ShowResult.class);
        startActivity(intent);
    }

    public void input(View view) {
        Intent intent = new Intent(this, InputScore.class);
        startActivity(intent);
    }

    public void clear(View view) {
        SQLiteDatabase db = openOrCreateDatabase("score.db", Context.MODE_PRIVATE, null);
        db.execSQL("DROP TABLE IF EXISTS student");
        db.execSQL("CREATE TABLE student (_id INTEGER PRIMARY KEY AUTOINCREMENT, stu_id VARCHAR, " +
                "q1 INTEGER, q2 INTEGER, q3 INTEGER, q4 INTEGER, q5 INTEGER)");

        Intent intent = new Intent(this, InputScore.class);
        startActivity(intent);
        db.close();
    }

}
