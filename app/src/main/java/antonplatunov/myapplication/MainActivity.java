package antonplatunov.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;




public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etRss;
    private Button btnShow;
    private SharedPreferences sPref;
    private final String SAVED_TEXT = "saved_text";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShow = (Button)findViewById(R.id.btnShow);
        btnShow.setOnClickListener(this);

        etRss = (EditText)findViewById(R.id.etRss);

        loadText();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnShow:
                saveText();
                Intent intent = new Intent(this, ListActivity.class);
                intent.putExtra("rssLink", etRss.getText().toString());
                startActivity(intent);
                break;
        }

    }
    private void saveText(){
        sPref = getSharedPreferences("RSSUpdatePreferences",MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT , etRss.getText().toString());
        ed.commit();
    }
    private void loadText(){
        sPref = getSharedPreferences("RSSUpdatePreferences", MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        etRss.setText(savedText);

    }

    @Override
    protected void onDestroy() {
        saveText();
        super.onDestroy();
    }
}