package com.nyc.in_class_dec_10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Success success = new Success();
        try {
            success.setMessage(buildMessageListFromJson(Constant.jsonConstantOne));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (String s: success.getMessage()) {
            Log.d("MainActivity", s);
        }
        recyclerView = findViewById(R.id.recyclerView);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        Adapter adapter = new Adapter(success.getMessage());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);




    }

    private List<String> buildMessageListFromJson(String jsonConstantOne) throws JSONException {
        List<String> messages = new ArrayList<>();
        JSONObject success = new JSONObject(jsonConstantOne);
        JSONArray message = success.getJSONArray("message");
        for (int i = 0; i < message.length() ; i++) {
            messages.add(message.get(i).toString());
        }


        return messages;
    }
}
