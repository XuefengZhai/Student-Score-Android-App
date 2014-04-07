package com.cmu.ss.app;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.database.sqlite.*;
import android.content.Context;
import android.content.ContentValues;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class InputScore extends ActionBarActivity {



    private EditText stuIdET;
    private EditText q1ET;
    private EditText q2ET;
    private EditText q3ET;
    private EditText q4ET;
    private EditText q5ET;

    private int stuId;
    private int q1;
    private int q2;
    private int q3;
    private int q4;
    private int q5;

    CountStudentNo countStudentNo = new CountStudentNo();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_input_score);


        stuIdET = (EditText) findViewById(R.id.id);
        q1ET = (EditText) findViewById(R.id.q1);
        q2ET = (EditText) findViewById(R.id.q2);
        q3ET = (EditText) findViewById(R.id.q3);
        q4ET = (EditText) findViewById(R.id.q4);
        q5ET = (EditText) findViewById(R.id.q5);

        TextView stuNumber = (TextView) findViewById(R.id.textView9);
        SQLiteDatabase db = openOrCreateDatabase("score.db", Context.MODE_PRIVATE, null);


        String sql = "SELECT stu_id from student";
        Cursor c = db.rawQuery(sql, null);
        int studentCount = c.getCount();
        stuNumber.setText(Integer.toString(studentCount));
        c.close();
        db.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.input_score, menu);
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


        if(emptyTextWarning()){
            Context context = getApplicationContext();
            CharSequence text = "Please enter all the data";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            Intent intent = new Intent(this, InputScore.class);
            startActivity(intent);

        }

        else if(studentAlreadyExit()){

            Context context = getApplicationContext();
            CharSequence text = "Student already exit!";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            Intent intent = new Intent(this, InputScore.class);
            startActivity(intent);

        }


        else if (countStudentNo.isFortyStudentNo()) {

            Context context = getApplicationContext();
            CharSequence text = "More Then 40 Student!\nWill Calculate without Adding New Data";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Intent intent = new Intent(this, ShowResult.class);
            startActivity(intent);


        } else {


            SQLiteDatabase db = openOrCreateDatabase("score.db", Context.MODE_PRIVATE, null);
            ContentValues cv = new ContentValues();

            stuId = Integer.parseInt(stuIdET.getText().toString());
            q1 = Integer.parseInt(q1ET.getText().toString());
            q2 = Integer.parseInt(q2ET.getText().toString());
            q2 = Integer.parseInt(q2ET.getText().toString());
            q3 = Integer.parseInt(q3ET.getText().toString());
            q4 = Integer.parseInt(q4ET.getText().toString());
            q5 = Integer.parseInt(q5ET.getText().toString());

            cv.put("stu_id", stuId);
            cv.put("q1", q1);
            cv.put("q2", q2);
            cv.put("q3", q3);
            cv.put("q4", q4);
            cv.put("q5", q5);
            db.insert("student", null, cv);

            countStudentNo.addStudentNo();

            db.close();

            Intent intent = new Intent(this, ShowResult.class);
            startActivity(intent);
        }
    }

    public void next(View view) {

        if(emptyTextWarning()){
            Context context = getApplicationContext();
            CharSequence text = "Please enter all the data";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            Intent intent = new Intent(this, InputScore.class);
            startActivity(intent);

        }

        else if(studentAlreadyExit()){

            Context context = getApplicationContext();
            CharSequence text = "Student already exit!";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            Intent intent = new Intent(this, InputScore.class);
            startActivity(intent);


        }

        else if (countStudentNo.isFortyStudentNo()) {

            Context context = getApplicationContext();
            CharSequence text = "More Then 40 Student!\nCan't adding more!\nShow Result";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            Intent intent = new Intent(this, ShowResult.class);
            startActivity(intent);


        } else {


            SQLiteDatabase db = openOrCreateDatabase("score.db", Context.MODE_PRIVATE, null);
            ContentValues cv = new ContentValues();

            stuId = Integer.parseInt(stuIdET.getText().toString());
            q1 = Integer.parseInt(q1ET.getText().toString());
            q2 = Integer.parseInt(q2ET.getText().toString());
            q2 = Integer.parseInt(q2ET.getText().toString());
            q3 = Integer.parseInt(q3ET.getText().toString());
            q4 = Integer.parseInt(q4ET.getText().toString());
            q5 = Integer.parseInt(q5ET.getText().toString());

            cv.put("stu_id", stuId);
            cv.put("q1", q1);
            cv.put("q2", q2);
            cv.put("q3", q3);
            cv.put("q4", q4);
            cv.put("q5", q5);
            db.insert("student", null, cv);

            db.close();

            countStudentNo.addStudentNo();

            Intent intent = new Intent(this, InputScore.class);
            startActivity(intent);

        }

    }

    public boolean emptyTextWarning(){

        stuIdET = (EditText) findViewById(R.id.id);
        q1ET = (EditText) findViewById(R.id.q1);
        q2ET = (EditText) findViewById(R.id.q2);
        q3ET = (EditText) findViewById(R.id.q3);
        q4ET = (EditText) findViewById(R.id.q4);
        q5ET = (EditText) findViewById(R.id.q5);

        if( stuIdET.getText().toString().isEmpty()||
                q1ET.getText().toString().isEmpty()||
                q2ET.getText().toString().isEmpty()||
                q3ET.getText().toString().isEmpty()||
                q4ET.getText().toString().isEmpty()||
                q5ET.getText().toString().isEmpty())
        {
            return true;
        }
        else return false;

    }

    public boolean studentAlreadyExit(){

        stuIdET = (EditText) findViewById(R.id.id);
        stuId = Integer.parseInt(stuIdET.getText().toString());

        SQLiteDatabase db = openOrCreateDatabase("score.db", Context.MODE_PRIVATE, null);
        String sql = "SELECT stu_id FROM student WHERE stu_id="+stuId;
        Cursor c;
        c = db.rawQuery(sql,null);
        if( c.getCount()>0){
            return true;
        }
        else return false;


    }
}
