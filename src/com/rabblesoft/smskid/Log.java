package com.rabblesoft.smskid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
public class Log extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);
        View linearLayout = findViewById(R.id.ll_log);
        //LinearLayout layout = (LinearLayout) findViewById(R.id.info);
        final ArrayList < HashMap < String, String >> LIST = new ArrayList < HashMap < String, String >> ();
        SharedPreferences settings = getSharedPreferences("replies_log", 0);
        Map < String, ?> items = settings.getAll();
        for (String s: items.keySet()) {
            HashMap < String, String > temp = new HashMap < String, String > ();
            temp.put("key", s);
            temp.put("value", items.get(s).toString());
            LIST.add(temp);
            TextView t = new TextView(this);
            t.setText("Timestamp: " + s + "\nMesasge: " + items.get(s).toString() + "\n");
            //  t.setId(5);
            t.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            ((LinearLayout) linearLayout).addView(t);
        }
    }@Override
    public void onResume() {
        super.onResume();
        LinearLayout submitScoreLayout = (LinearLayout) findViewById(R.id.ll_log);
        submitScoreLayout.removeAllViews();
        View linearLayout = findViewById(R.id.ll_log);
        final ArrayList < HashMap < String, String >> LIST = new ArrayList < HashMap < String, String >> ();
        //SharedPreferences settings = getSharedPreferences("message_settings", 0);
        SharedPreferences settings = getSharedPreferences("replies_log", 0);
        Map < String, ?> items = settings.getAll();
        for (String s: items.keySet()) {
            HashMap < String, String > temp = new HashMap < String, String > ();
            temp.put("key", s);
            temp.put("value", items.get(s).toString());
            LIST.add(temp);
            TextView t = new TextView(this);
            t.setText("Timestamp: " + s + "\nMesasge: " + items.get(s).toString() + "\n");
            t.setId(5);
            t.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            ((LinearLayout) linearLayout).addView(t);
        }
    }
}