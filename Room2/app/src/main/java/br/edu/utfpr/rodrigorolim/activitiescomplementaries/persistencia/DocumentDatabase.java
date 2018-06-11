package br.edu.utfpr.rodrigorolim.activitiescomplementaries.persistencia;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import br.edu.utfpr.rodrigorolim.activitiescomplementaries.modelo.Document;

@Database(entities = {Document.class}, version = 1)
public abstract class DocumentDatabase extends RoomDatabase {

    public abstract DocumentDAO documentDao();

    private static DocumentDatabase instance;

    public static DocumentDatabase getDatabase(final Context context) {

        if (instance == null) {

            synchronized (DocumentDatabase.class) {
                if (instance == null) {
                   RoomDatabase.Builder builder =  Room.databaseBuilder(context,
                                                                        DocumentDatabase.class,
                                                                        "document.db");
                   instance = (DocumentDatabase) builder.build();
                }
            }
        }

        return instance;
    }

}
