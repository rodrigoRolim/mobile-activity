package br.edu.utfpr.rodrigorolim.activitiescomplementaries.modelo;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "document")
public class Document {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String nome;
    private int score;
    private String group;

    public Document(String nome, String group, int score){

        setNome(nome);
        setGroup(group);
        setScore(score);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getNome() {
        return nome;
    }

    public void setNome(@NonNull String nome) {
        this.nome = nome;
    }

    public String getGroup() { return group; }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getScore() {return score;}

    public void setScore(int score) {this.score = score;}
    @Override
    public String toString(){
        return getNome();
    }
}
