package hvl.dat102.ADT;

import java.util.Arrays;
import java.util.Iterator;

public class TabellMengde<T> implements MengdeADT<T>, Iterable<T>{

    private static final int DEFAULT_CAPACITY = 10;
    private  T[] tabell;
    private int antall;

    //Konstruktør
    @SuppressWarnings("unchecked")
    public TabellMengde(){
        tabell = (T[]) new Object[DEFAULT_CAPACITY];
        antall = 0;
    }

    //Dobbler lengden på tabellen.
    public void utvidKapasitet(){
        tabell = Arrays.copyOf(tabell, tabell.length * 2);
    }

    @Override
    public void leggTil(T element) {
        if (inneholder(element))return;
        if (antall == tabell.length) utvidKapasitet();
        tabell[antall++] = element;
    }

    @Override
    public boolean inneholder(T element) {

        for (int i = 0; i < antall; i++) {
            if (tabell[i].equals(element)) return true;
        }
        return false;
    }

    @Override
    public T fjern(T element) {
        for (int i = 0; i < antall; i++) {
            if (tabell[i].equals(element)) {
                T resultat = tabell[i];
                tabell[i] = tabell[--antall]; // Flytter siste element til den fjernede posisjonen
                tabell[antall] = null;
                return resultat;
            }
        }
        return null;
    }

    @Override
    public boolean erTom() {
        return antall == 0;
    }

    @Override
    public int antallElementer() {
        return antall;
    }

    @Override
    public MengdeADT<T> union(MengdeADT<T> annenMengde) {
        TabellMengde<T> nyMengde = new TabellMengde<>();
        for (int i = 0; i < antall; i++) {
            nyMengde.leggTil(tabell[i]);
        }
        for (T element : annenMengde) {
            nyMengde.leggTil(element);
        }
        return nyMengde;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(tabell, antall));
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < antall;
            }

            @Override
            public T next() {
                return tabell[pos++];
            }

        };
    }
}
