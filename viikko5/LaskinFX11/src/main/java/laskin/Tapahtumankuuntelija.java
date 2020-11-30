package laskin;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public abstract class Komento {
    protected IO io;
    Io revert;

    public Komento(IO io, Io revert) {
        this.io = io;
        this.revert = revert;
    }

    public void suorita() {
        this.io();
    }

    public void peruuta() {
        this.revert();
    }

}

public class Tapahtumankuuntelija implements EventHandler {
    private Button undo;
    private Sovelluslogiikka sovellus;
    
    private Map<Button, Komento> komennot;
    private Komento edellinen = null;

    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        this.komennot = new HashMap<>();
        this.komennot.put(miinus, new Erotus(tuloskentta, syotekentta, nollaa, undo, sovellus) );
        this.komennot.put(nollaa, new Nollaa(tuloskentta, syotekentta,  nollaa, undo, sovellus) );
        this.komennot.put(plus, new Summa(tuloskentta, syotekentta,  nollaa, undo, sovellus) );
    }
    @Override
    public void handle(Event event) {
        if ( event.getTarget() != undo ) {
            Komento komento = this.komennot.get((Button)event.getTarget());
            komento.suorita();
            this.edellinen = komento;
        } else {
            this.edellinen.peru();
            this.edellinen = null;
        }                  
    }

}
