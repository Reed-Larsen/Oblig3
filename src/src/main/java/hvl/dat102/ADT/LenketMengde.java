package hvl.dat102.ADT;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LenketMengde<T> implements MengdeADT<T>, Iterable<T> {
    private static class Node<T> {
        T data;
        Node<T> neste;

        Node(T data) {
            this.data = data;
            this.neste = null;
        }
    }

    private Node<T> start;
    private int antall;

    public LenketMengde() {
        start = null;
        antall = 0;
    }

    @Override
    public void leggTil(T element) {
        if (!inneholder(element)) {
            Node<T> nyNode = new Node<>(element);
            nyNode.neste = start;
            start = nyNode;
            antall++;
        }
    }

    @Override
    public boolean inneholder(T element) {
        Node<T> aktuell = start;
        while (aktuell != null) {
            if (aktuell.data.equals(element)) return true;
            aktuell = aktuell.neste;
        }
        return false;
    }

    @Override
    public T fjern(T element) {
        if (start == null) return null; // Tom liste

        if (start.data.equals(element)) { // Hvis elementet er først i lista
            T resultat = start.data;
            start = start.neste;
            antall--;
            return resultat;
        }

        Node<T> aktuell = start;
        while (aktuell.neste != null && !aktuell.neste.data.equals(element)) {
            aktuell = aktuell.neste;
        }

        if (aktuell.neste != null) {
            T resultat = aktuell.neste.data;
            aktuell.neste = aktuell.neste.neste;
            antall--;
            return resultat;
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
        LenketMengde<T> nyMengde = new LenketMengde<>();
        for (T element : this) nyMengde.leggTil(element);
        for (T element : annenMengde) nyMengde.leggTil(element);
        return nyMengde;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> aktuell = start;

            @Override
            public boolean hasNext() {
                return aktuell != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T data = aktuell.data;
                aktuell = aktuell.neste;
                return data;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (T element : this) {
            sb.append(element).append(", ");
        }
        if (sb.length() > 1) sb.setLength(sb.length() - 2);
        sb.append("]");
        return sb.toString();
    }
}
