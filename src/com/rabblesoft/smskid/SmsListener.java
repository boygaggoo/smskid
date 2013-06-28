package com.rabblesoft.smskid;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsListener extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
    	
    	final SharedPreferences replies = context.getSharedPreferences("replies_log", context.MODE_PRIVATE);

        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
            SmsMessage[] msgs = null;
            String msg_from;
            if (bundle != null){
                //---retrieve the SMS message received---
                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for(int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        String msgBody = msgs[i].getMessageBody();
                        //Toast.makeText(context, "YOU GOT A SMS", Toast.LENGTH_LONG).show();
                        SharedPreferences.Editor prefEditor = replies.edit();
                        SimpleDateFormat current_date = new SimpleDateFormat("ddMMyyyyhhmmss");
                        String format = current_date.format(new Date());
                        prefEditor.putString(format,msgBody);
                        prefEditor.commit();
                    }

                }catch(Exception e){
//                            Log.d("Exception caught",e.getMessage());
                }
            }
        }
    }
}