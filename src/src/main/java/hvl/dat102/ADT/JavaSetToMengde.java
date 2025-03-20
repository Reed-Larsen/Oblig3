package hvl.dat102.ADT;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class JavaSetToMengde<T> implements MengdeADT<T> {
    private Set<T> mengde;

    public JavaSetToMengde() {
        mengde = new HashSet<>();
    }

    @Override
    public void leggTil(T element) {
        mengde.add(element); // HashSet hindrer duplikater automatisk
    }

    @Override
    public boolean inneholder(T element) {
        return mengde.contains(element);
    }

    @Override
    public T fjern(T element) {
        if (mengde.remove(element)) {
            return element; // Returnerer elementet hvis det ble fjernet
        }
        return null; // Returnerer null hvis elementet ikke fantes
    }

    @Override
    public boolean erTom() {
        return mengde.isEmpty();
    }

    @Override
    public int antallElementer() {
        return mengde.size();
    }

    @Override
    public MengdeADT<T> union(MengdeADT<T> annenMengde) {
        JavaSetToMengde<T> nyMengde = new JavaSetToMengde<>();
        nyMengde.mengde.addAll(this.mengde); // Legger til alle elementer fra denne mengden
        for (T element : annenMengde) {
            nyMengde.leggTil(element); // Legger til elementer fra annenMengde
        }
        return nyMengde;
    }

    @Override
    public Iterator<T> iterator() {
        return mengde.iterator();
    }

    @Override
    public String toString() {
        return mengde.toString();
    }
}
