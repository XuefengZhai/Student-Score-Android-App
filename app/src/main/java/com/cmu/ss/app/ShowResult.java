package com.cmu.ss.app;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;


public class ShowResult extends ActionBarActivity {



    CountStudentNo countStudentNo = new CountStudentNo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);

        TextView stuNumber = (TextView) findViewById(R.id.stuNumber);

        SQLiteDatabase db = openOrCreateDatabase("score.db", Context.MODE_PRIVATE, null);


        String sql = "SELECT stu_id from student";
        Cursor c = db.rawQuery(sql,null);
        int studentCount = c.getCount();
        stuNumber.setText(Integer.toString(studentCount));
        c.close();

        displayHigh();
        displayLow();
        displayAvg();
        displayStudent();

        db.close();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.show_result, menu);
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

    public void back(View view) {
        Intent intent = new Intent(this, InputScore.class);
        startActivity(intent);
    }

    public void clear(View view) {
        SQLiteDatabase db = openOrCreateDatabase("score.db", Context.MODE_PRIVATE, null);
        db.execSQL("DROP TABLE IF EXISTS student");
        db.execSQL("CREATE TABLE student (_id INTEGER PRIMARY KEY AUTOINCREMENT, stu_id VARCHAR, " +
                "q1 INTEGER, q2 INTEGER, q3 INTEGER, q4 INTEGER, q5 INTEGER)");

        countStudentNo.resetStudentNo();

        Intent intent = new Intent(this, InputScore.class);
        startActivity(intent);
        db.close();
    }


    public void displayHigh(){

        TextView highTV = (TextView) findViewById(R.id.high);


        SQLiteDatabase db = openOrCreateDatabase("score.db", Context.MODE_PRIVATE, null);
        String result = "High: ";


        String columnName;

        for(int i=1;i<6;i++)
        {
            Cursor c;
            columnName = "q"+Integer.toString(i);

            String sql = "SELECT MAX("+columnName+") as max from student";
            c = db.rawQuery(sql,null);
            c.moveToFirst();
            result = result + c.getString(0) + "   ";

            c.close();
        }

        //Log.d("result", result);
        highTV.setText(result);

    }

    public void displayLow(){
        TextView lowTV = (TextView) findViewById(R.id.low);

        SQLiteDatabase db = openOrCreateDatabase("score.db", Context.MODE_PRIVATE, null);
        String result = "Low: ";


        String columnName;

        for(int i=1;i<6;i++)
        {
            Cursor c;
            columnName = "q"+Integer.toString(i);

            String sql = "SELECT MIN("+columnName+") as max from student";
            c = db.rawQuery(sql,null);
            c.moveToFirst();
            result = result + c.getString(0) + "   ";

            c.close();
        }

        //Log.d("result", result);
        lowTV.setText(result);


    }

    public void displayAvg(){

        TextView avgTV = (TextView) findViewById(R.id.avg);


        SQLiteDatabase db = openOrCreateDatabase("score.db", Context.MODE_PRIVATE, null);
        String result = "Avg: ";

        String columnName;

        for(int i=1;i<6;i++)
        {
            Cursor c;
            columnName = "q"+Integer.toString(i);

            String sql = "SELECT AVG("+columnName+") as max from student";
            c = db.rawQuery(sql,null);
            c.moveToFirst();
            result = result + c.getString(0) + "   ";

            c.close();
        }

        //Log.d("result", result);
        avgTV.setText(result);


    }

    public void displayStudent(){

        SQLiteDatabase db = openOrCreateDatabase("score.db", Context.MODE_PRIVATE, null);


        TextView stuIdTV = (TextView) findViewById(R.id.stuId);
         TextView q1TV = (TextView) findViewById(R.id.q1);
        TextView q2TV = (TextView) findViewById(R.id.q2);
        TextView q3TV = (TextView) findViewById(R.id.q3);
        TextView q4TV = (TextView) findViewById(R.id.q4);
        TextView q5TV = (TextView) findViewById(R.id.q5);

        String stud="Stud\n",
                q1="Q1\n",
                q2="Q2\n",
                q3="Q3\n",
                q4="Q4\n",
                q5="Q5\n";



        String sql = "SELECT stu_id from student";
        Cursor c;
        c = db.rawQuery(sql,null);
        c.moveToFirst();
        do{
            stud=stud+c.getString(0)+"\n";
        }
        while(c.moveToNext());
        c.close();
        stuIdTV.setText(stud);



        String sql1 = "SELECT q1 from student";
        Cursor c1;
        c1 = db.rawQuery(sql1,null);
        c1.moveToFirst();
        do{
            q1=q1+c1.getString(0)+"\n";
        }
        while(c1.moveToNext());
        c1.close();
        q1TV.setText(q1);


        String sql2 = "SELECT q2 from student";
        Cursor c2;
        c2 = db.rawQuery(sql2,null);
        c2.moveToFirst();
        do{
            q2=q2+c2.getString(0)+"\n";
        }
        while(c2.moveToNext());
        c2.close();
        q2TV.setText(q2);

        String sql3 = "SELECT q3 from student";
        Cursor c3;
        c3 = db.rawQuery(sql3,null);
        c3.moveToFirst();
        do{
            q3=q3+c3.getString(0)+"\n";
        }
        while(c3.moveToNext());
        c3.close();
        q3TV.setText(q3);

        String sql4 = "SELECT q4 from student";
        Cursor c4;
        c4 = db.rawQuery(sql4,null);
        c4.moveToFirst();
        do{
            q4=q4+c4.getString(0)+"\n";
        }
        while(c4.moveToNext());
        c4.close();
        q4TV.setText(q4);

        String sql5 = "SELECT q5 from student";
        Cursor c5;
        c5 = db.rawQuery(sql5,null);
        c5.moveToFirst();
        do{
            q5=q5+c5.getString(0)+"\n";
        }
        while(c5.moveToNext());
        c5.close();
        q5TV.setText(q5);



    }




}
