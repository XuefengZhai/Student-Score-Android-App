package com.cmu.ss.app;

/**
 * Created by SafenZhai on 4/7/14.
 */


public class CountStudentNo {

    private static int studentNo = 0;


    public void addStudentNo(){
        studentNo++;
    }

    public int getStudentNo(){
        return studentNo;
    }

    public void resetStudentNo(){
        studentNo = 0;
    }

    public boolean isFortyStudentNo(){
        if (studentNo == 40)
            return true;
        else
            return false;
    }


}
