package br.edu.utfpr.rodrigorolim.activitiescomplementaries;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import java.io.Serializable;
import java.util.List;

import br.edu.utfpr.rodrigorolim.activitiescomplementaries.modelo.Document;
import br.edu.utfpr.rodrigorolim.activitiescomplementaries.persistencia.DocumentDatabase;

public class DocumentListActivity extends AppCompatActivity{
   private List<Document> listDocuments;
   private ArrayAdapter<Document> listaAdapter;
   private ListView listViewDocuments;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_list);
        android.support.v7.widget.Toolbar mToolbar = findViewById(R.id.tool_bar);
        mToolbar.setTitle(getString(R.string.app_name));
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        listViewDocuments = findViewById(R.id.listaDoc);
        listDocuments();
        listViewDocuments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Document document = (Document) parent.getItemAtPosition(position);
                Intent detailsDocumentScreen = new Intent(getApplicationContext(),
                        DetailDocumentActivity.class);
                detailsDocumentScreen.putExtra("id", document.getId());
                detailsDocumentScreen.putExtra("name", document.getNome());
                detailsDocumentScreen.putExtra("score", document.getScore());
                detailsDocumentScreen.putExtra("group", document.getGroup());
                startActivity(detailsDocumentScreen);
            }
        });
    }
    private void listDocuments() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                listViewDocuments = findViewById(R.id.listaDoc);
                DocumentDatabase database = DocumentDatabase.getDatabase(DocumentListActivity.this);

                listDocuments = database.documentDao().queryAll();

                DocumentListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listaAdapter = new ArrayAdapter<>(DocumentListActivity.this,
                                android.R.layout.simple_list_item_1,
                                listDocuments);
                        listViewDocuments.setAdapter(listaAdapter);
                    }
                });
            }
        });
    }
}
