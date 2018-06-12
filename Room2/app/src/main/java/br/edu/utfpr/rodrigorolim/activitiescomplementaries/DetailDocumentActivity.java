package br.edu.utfpr.rodrigorolim.activitiescomplementaries;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.edu.utfpr.rodrigorolim.activitiescomplementaries.modelo.Document;
import br.edu.utfpr.rodrigorolim.activitiescomplementaries.persistencia.DocumentDatabase;
import br.edu.utfpr.rodrigorolim.activitiescomplementaries.utils.UtilsGUI;

public class DetailDocumentActivity extends AppCompatActivity{
    private Toolbar mToolbar;
    private ActionBar actionBar;
    private Button btnEdit;
    private Button btnDelete;
    private Document document;
    private List<Document> listDocuments;
    private ArrayAdapter<Document> listaAdapter;
    private ListView listViewDocuments;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_detail);
        getmToolbar();
        fillView();
        listViewDocuments = findViewById(R.id.listaDoc);
        btnEdit = findViewById(R.id.btn_edit);
        btnDelete = findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
    }
    private void delete() {
        String mensagem = getString(R.string.deseja_realmente_apagar)
                + "\n" + document.getNome();

        DialogInterface.OnClickListener listener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch(which){
                            case DialogInterface.BUTTON_POSITIVE:

                                AsyncTask.execute(new Runnable() {
                                    @Override
                                    public void run() {

                                        DocumentDatabase database =
                                                DocumentDatabase.getDatabase(DetailDocumentActivity.this);

                                        database.documentDao().delete(document);
                                        System.out.println(document.getGroup());
                                        updateListDocuments();
//                                        DetailDocumentActivity.this.runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                Intent documentList = new Intent(getApplicationContext(),
//                                                        DocumentListActivity.class);
//
//                                                startActivity(documentList);
//                                            }
//                                        });
                                    }
                                });

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }
                    }
                };

        UtilsGUI.confirmaAcao(this, mensagem, listener);
    }
    public void updateListDocuments() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                listViewDocuments = findViewById(R.id.listaDoc);
                DocumentDatabase database = DocumentDatabase.getDatabase(DetailDocumentActivity.this);

                listDocuments = database.documentDao().queryAll();

                DetailDocumentActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listaAdapter = new ArrayAdapter<>(DetailDocumentActivity.this,
                                android.R.layout.simple_list_item_1,
                                listDocuments);
                        listViewDocuments.setAdapter(listaAdapter);
                        Intent detailDocument = new Intent(getApplicationContext(),
                                DocumentListActivity.class);
                        startActivity(detailDocument);
                    }
                });
            }
        });
    }
    public void fillView(){

        String doc = getIntent().getExtras().getString("name");
        int score = getIntent().getExtras().getInt("score");
        String group = getIntent().getExtras().getString("group");
        document = new Document(doc, group, score);
        TextView txtGroup = findViewById(R.id.textGroup);
        TextView txtName = findViewById(R.id.textName);
        TextView txtScore = findViewById(R.id.textScore);
        txtName.setText(doc);
        txtScore.setText(String.valueOf(score));
        txtGroup.setText(group);
    }
    public void getmToolbar() {
        mToolbar = findViewById(R.id.toolbar_details);
        mToolbar.setTitle(getString(R.string.app_name));
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        setSupportActionBar(mToolbar);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_items, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

}
