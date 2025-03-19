package hvl.dat102.ADT;

public interface MengdeADT<T> {
    void leggTil(T element);
    boolean inneholder (T element);
    T fjern (T element);
    boolean erTom();
    int antallElementer();
    MengdeADT<T> union(MengdeADT<T> annenMengde);
}
