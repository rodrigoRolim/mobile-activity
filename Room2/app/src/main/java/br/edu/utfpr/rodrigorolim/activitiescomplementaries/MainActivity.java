package br.edu.utfpr.rodrigorolim.activitiescomplementaries;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button buttonMenu = findViewById(R.id.button_menu);

        buttonMenu.setOnClickListener(this);
    }
    private void menu(int option) {
        switch(option){
            case 0:
                Intent newDocumentScreen = new Intent(getApplicationContext(),
                        DocumentActivity.class);
                startActivity(newDocumentScreen);
                break;
            case 1:
                Intent listDocumentScreen = new Intent(getApplicationContext(),
                        DocumentListActivity.class);
                startActivity(listDocumentScreen);
                break;
            case 2:
                Intent progressScreen = new Intent(getApplicationContext(),
                        Progress.class);
                startActivity(progressScreen);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        Spinner spinnerMenu = findViewById(R.id.spinner_menu);
        menu(spinnerMenu.getSelectedItemPosition());
    }
}
