package br.edu.utfpr.rodrigorolim.activitiescomplementaries.persistencia;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.edu.utfpr.rodrigorolim.activitiescomplementaries.modelo.Document;

@Dao
public interface DocumentDAO {

    @Insert
    long insert(Document document);

    @Delete
    void delete(Document document);

    @Update
    void update(Document document);

    @Query("SELECT * FROM Document WHERE id = :id")
    Document queryForId(long id);

    @Query("SELECT * FROM Document ORDER BY nome ASC")
    List<Document> queryAll();

}