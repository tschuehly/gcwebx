package de.cschillingtschuehly.gcwebx.modell;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    String text;

    public int getId(){
        return id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Content(String text) {
        this.text = text;
    }
}
