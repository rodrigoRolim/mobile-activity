package br.edu.utfpr.rodrigorolim.activitiescomplementaries;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class DocumentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_add);
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
}
