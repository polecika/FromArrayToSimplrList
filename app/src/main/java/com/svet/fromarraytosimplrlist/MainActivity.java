package com.svet.fromarraytosimplrlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String KEY1 = "key1";
    private static final String KEY2 = "key2";
    private static final String KEY3 = "key3";
    private static final String APP_PREFERENCES = "hello";
    private static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] arrayValue = checkSharedPreferences();
        ListView list = findViewById(R.id.list);
        List<Map<String, String>> values = prepareContent(arrayValue);

        BaseAdapter listContentAdapter = createAdapter(values);

        list.setAdapter(listContentAdapter);
    }

    private String[] checkSharedPreferences() {
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        if (!sharedPreferences.contains(APP_PREFERENCES)) {
            SharedPreferences.Editor e = sharedPreferences.edit();
            String newStr = getString(R.string.large_text);
            e.putString(APP_PREFERENCES, newStr);
            e.commit();
        }
       String str = sharedPreferences.getString(APP_PREFERENCES, "");
        String[] result = str.split("\n\n");
        return result;
    }

    @NonNull
    private BaseAdapter createAdapter(List<Map<String, String>> values) {
        return new SimpleAdapter(this, values, R.layout.activity_for_simple_adapter, new String[]{KEY1, KEY2, KEY3}, new int[]{R.id.text1, R.id.text2,R.id.text3});
    }

    @NonNull
    private List<Map<String, String>> prepareContent(String[] arrayValue) {
        List<Map<String, String>> result = new ArrayList<>();
        for (String s : arrayValue) {
            Map<String, String> map = new HashMap<>();
            map.put(KEY1, s);
            map.put(KEY2, s.length() + "");
            map.put(KEY3, s.length() + "");
            result.add(map);
        }
        return result;
    }
}