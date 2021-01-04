package us.createandlearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

import us.createandlearn.huntthewumpus.HuntTheWumpusActivity;
import us.createandlearn.tendotfive.TenDotFiveActivity;

public class MainActivity extends AppCompatActivity {
    private static final ImmutableMap<Integer, Class> LESSONS =
            new ImmutableMap.Builder<Integer, Class>()
                    .put(R.string.title_activity_ten_dot_five, TenDotFiveActivity.class)
                    .put(R.string.title_activity_hunt_the_wumpus, HuntTheWumpusActivity.class)
                    .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Create and Learn Android Projects");
        setContentView(R.layout.activity_main);
        ViewGroup viewGroup = findViewById(R.id.lesson_launcher);
        for (Map.Entry<Integer, Class> entry : LESSONS.entrySet()) {
            Button button = new Button(this);
            int buttonPadding = getResources().getDimensionPixelSize(R.dimen.button_padding);
            button.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding);
            button.setText(getResources().getString(entry.getKey()));
            button.setOnClickListener(
                    (v) -> startActivity(new Intent(this, entry.getValue())));
            viewGroup.addView(button);
        }
    }
}