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
    private int group;

    /* Para utilizar o Room, caso exista construtor com parâmetros as variáveis dos parâmetros
       devem ter o mesmo nome dos atributos persistidos da classe.

       Caso um atributo persistido não sejm público, deve existir os métodos get e set
       apropriadas para este atributo, e o nome dos métodos devem seguir as Convenções de código
       do Java

       É recomendável criar indices para os campos que armazenam chaves extrangeiras, caso não
       faça é gerado um warning*/

    public Document(String nome, int group, int score){

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

    public int getGroup() { return group; }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getScore() {return score;}

    public void setScore(int score) {this.score = score;}
    @Override
    public String toString(){
        return getNome();
    }
}
