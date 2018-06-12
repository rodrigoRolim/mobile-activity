package br.edu.utfpr.rodrigorolim.activitiescomplementaries;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.rodrigorolim.activitiescomplementaries.modelo.Document;
import br.edu.utfpr.rodrigorolim.activitiescomplementaries.persistencia.DocumentDatabase;


public class DocumentActivity extends AppCompatActivity {
    Spinner group;
    EditText nameDoc;
    EditText score;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_add);
        Toolbar mToolbar = findViewById(R.id.tool_bar);
        mToolbar.setTitle(getString(R.string.app_name));
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
        fillSpinnerGroup();
    }
    public void fillSpinnerGroup(){
        Spinner spinnerGroup = findViewById(R.id.spinnerGroup);
        List<String> list = new ArrayList<String>();
        list.add("grupo 1");
        list.add("grupo 2");
        list.add("grupo 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGroup.setAdapter(dataAdapter);
    }
    public void save() {

        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                group = findViewById(R.id.spinnerGroup);
                nameDoc = findViewById(R.id.editTextDoc);
                score = findViewById(R.id.editTextScore);
                Document document = new Document(
                        String.valueOf(nameDoc.getText()),
                        String.valueOf(group.getSelectedItem()),
                        Integer.parseInt(score.getText().toString()));
                DocumentDatabase database = DocumentDatabase.getDatabase(DocumentActivity.this);
                database.documentDao().insert(document);
                Intent newDocumentScreen = new Intent(getApplicationContext(),
                        DocumentListActivity.class);
                startActivity(newDocumentScreen);
                finish();
            }
        });

    }
}
