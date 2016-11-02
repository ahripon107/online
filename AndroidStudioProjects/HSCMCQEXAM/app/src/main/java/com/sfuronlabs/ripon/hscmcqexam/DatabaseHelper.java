package com.sfuronlabs.ripon.hscmcqexam;

/**
 * Created by Ripon on 5/12/15.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "mydatabase.sqlite";
    private static int DB_VERSION = 1;
    private SQLiteDatabase database;
    private String DB_PATH;
    private Context context;
    private static String TABLE_NAME = "QuestionTable";
    private static String QUESTION_ID = "_id";
    private static String QUESTION_QUEST = "question";
    private static String QUESTION_OPTION1 = "option1";
    private static String QUESTION_OPTION2 = "option2";
    private static String QUESTION_OPTION3 = "option3";
    private static String QUESTION_OPTION4 = "option4";
    private static String QUESTION_CORRECT = "correct";
    private static String QUESTION_HARDNESS = "hardness";
    private static String CREATE_QUESTION_TABLE = "create table " + TABLE_NAME
            + "( " + QUESTION_ID + " INTEGER PRIMARY KEY, " + QUESTION_QUEST
            + " text, " + QUESTION_OPTION1 + " text, " + QUESTION_OPTION2
            + " text, " + QUESTION_OPTION3 + " text, " + QUESTION_OPTION4
            + " text, " + QUESTION_CORRECT + " text )";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        // TODO Auto-generated constructor stub
        this.context = context;
        String packagename = context.getPackageName();
        DB_PATH = "/data/data/" + packagename + "/databases/";

        this.database = openDatabase();
    }

    public SQLiteDatabase getDatabase() {
        return this.database;
    }

    public SQLiteDatabase openDatabase() {
        String path = DB_PATH + DB_NAME;
        if (database == null) {
            createDatabase();
            database = SQLiteDatabase.openDatabase(path, null,
                    SQLiteDatabase.OPEN_READWRITE);
        }
        return database;
    }

    public void createDatabase() {
        boolean exists = checkDatabase();
        if (!exists) {
            this.getReadableDatabase();
            //Toast.makeText(context, "Copying database...", Toast.LENGTH_LONG)
            //	.show();
            copyDatabase();
        } else {
            //Toast.makeText(context, "Database already exists",
            //	Toast.LENGTH_LONG).show();
        }
    }

    public boolean checkDatabase() {
        String path = DB_PATH + DB_NAME;
        File file = new File(path);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    private void copyDatabase() {
        try {
            InputStream is = context.getAssets().open(DB_NAME);
            String path = DB_PATH + DB_NAME;
            OutputStream os = new FileOutputStream(path);
            byte[] buffer = new byte[4096];
            int readCount = 0;
            readCount = is.read(buffer);
            while (readCount > 0) {
                os.write(buffer, 0, readCount);
                readCount = is.read(buffer);

            }
            is.close();
            os.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void close() {
        if (this.database != null) {
            this.database.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        // db.execSQL(CREATE_QUESTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    public long Insert(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QUESTION_ID, question.getId());
        values.put(QUESTION_QUEST, question.getQues());
        values.put(QUESTION_OPTION1, question.getOption1());
        values.put(QUESTION_OPTION2, question.getOption2());
        values.put(QUESTION_OPTION3, question.getOption3());
        values.put(QUESTION_OPTION4, question.getOption4());
        values.put(QUESTION_CORRECT, question.getCorrect());
        values.put(QUESTION_HARDNESS, question.getHardness());
        long inserted = db.insert(TABLE_NAME, null, values);

        db.close();
        return inserted;
    }

    public ArrayList<Question> AllQuestions(String tablename) {
        ArrayList<Question> questions = new ArrayList<Question>();

        Cursor cursor = this.database.query(tablename, null, null, null, null,
                null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                int id = cursor.getInt(cursor.getColumnIndex(QUESTION_ID));
                String question = cursor.getString(cursor
                        .getColumnIndex(QUESTION_QUEST));
                String option1 = cursor.getString(cursor
                        .getColumnIndex(QUESTION_OPTION1));
                String option2 = cursor.getString(cursor
                        .getColumnIndex(QUESTION_OPTION2));
                String option3 = cursor.getString(cursor
                        .getColumnIndex(QUESTION_OPTION3));
                String option4 = cursor.getString(cursor
                        .getColumnIndex(QUESTION_OPTION4));
                String correctans = cursor.getString(cursor
                        .getColumnIndex(QUESTION_CORRECT));
                String hardness = cursor.getString(cursor
                        .getColumnIndex(QUESTION_HARDNESS));
                Question q = new Question(id, question, option1, option2,
                        option3, option4, correctans, hardness);
                questions.add(q);
                cursor.moveToNext();
            }
        }
        cursor.close();

        return questions;
    }

    public ArrayList<String> CorrectAns() {
        ArrayList<String> CorrectAnswers = new ArrayList<String>();

        Cursor cursor = this.database.query(TABLE_NAME, null, null, null, null,
                null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {

                String correctans = cursor.getString(cursor
                        .getColumnIndex(QUESTION_CORRECT));
                CorrectAnswers.add(correctans);
                cursor.moveToNext();
            }
        }
        cursor.close();

        return CorrectAnswers;
    }

    public ArrayList<Question> SelectedQuestions(String tablename,
                                                 String[] SelectedCheckList) {
        ArrayList<Question> questions = new ArrayList<Question>();
        Cursor cursor = this.database.query(tablename, null, QUESTION_HARDNESS
                + "=='" + SelectedCheckList[0] + "' or " + QUESTION_HARDNESS
                + "=='" + SelectedCheckList[1] + "' or " + QUESTION_HARDNESS
                + "=='" + SelectedCheckList[2] + "' or " + QUESTION_HARDNESS
                + "=='" + SelectedCheckList[3] + "' or " + QUESTION_HARDNESS
                //+ "=='" + SelectedCheckList[4]+"' or "+QUESTION_HARDNESS
                //+ "=='" + SelectedCheckList[5]+"' or "+QUESTION_HARDNESS
                //+ "=='" + SelectedCheckList[6]+"' or "+QUESTION_HARDNESS
                //+ "=='" + SelectedCheckList[7]+"'",
                , null, null, null, null);
        //Cursor cursor = this.database.query(tablename, null, null, null, null,
        //	null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                int id = cursor.getInt(cursor.getColumnIndex(QUESTION_ID));
                String question = cursor.getString(cursor
                        .getColumnIndex(QUESTION_QUEST));
                String option1 = cursor.getString(cursor
                        .getColumnIndex(QUESTION_OPTION1));
                String option2 = cursor.getString(cursor
                        .getColumnIndex(QUESTION_OPTION2));
                String option3 = cursor.getString(cursor
                        .getColumnIndex(QUESTION_OPTION3));
                String option4 = cursor.getString(cursor
                        .getColumnIndex(QUESTION_OPTION4));
                String correctans = cursor.getString(cursor
                        .getColumnIndex(QUESTION_CORRECT));
                String hardness = cursor.getString(cursor
                        .getColumnIndex(QUESTION_HARDNESS));
                Question q = new Question(id, question, option1, option2,
                        option3, option4, correctans, hardness);
                questions.add(q);
                cursor.moveToNext();
            }
        }
        cursor.close();

        return questions;
    }
}
