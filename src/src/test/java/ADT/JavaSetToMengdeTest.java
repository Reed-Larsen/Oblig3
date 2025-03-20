package ADT;

import hvl.dat102.ADT.JavaSetToMengde;
import hvl.dat102.ADT.MengdeADT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JavaSetToMengdeTest {
    private JavaSetToMengde<String> mengde;

    @BeforeEach
    public void setUp() {
        mengde = new JavaSetToMengde<>();
    }

    @Test
    public void testLeggTilOgInneholder() {
        mengde.leggTil("A");
        mengde.leggTil("B");
        mengde.leggTil("C");

        assertTrue(mengde.inneholder("A"));
        assertTrue(mengde.inneholder("B"));
        assertTrue(mengde.inneholder("C"));
        assertFalse(mengde.inneholder("D")); // Skal ikke finnes
    }

    @Test
    public void testIngenDuplikater() {
        mengde.leggTil("A");
        mengde.leggTil("A"); // Skal ikke legges til igjen
        assertEquals(1, mengde.antallElementer()); // Skal fortsatt være 1
    }

    @Test
    public void testFjern() {
        mengde.leggTil("X");
        mengde.leggTil("Y");

        assertEquals("X", mengde.fjern("X"));
        assertFalse(mengde.inneholder("X"));
        assertEquals(1, mengde.antallElementer());

        assertNull(mengde.fjern("Z")); // Fjerner et element som ikke finnes
    }

    @Test
    public void testErTom() {
        assertTrue(mengde.erTom()); // Skal være tom i starten
        mengde.leggTil("A");
        assertFalse(mengde.erTom());
        mengde.fjern("A");
        assertTrue(mengde.erTom());
    }

    @Test
    public void testUnion() {
        JavaSetToMengde<String> annenMengde = new JavaSetToMengde<>();
        mengde.leggTil("A");
        mengde.leggTil("B");
        annenMengde.leggTil("B");
        annenMengde.leggTil("C");

        MengdeADT<String> unionMengde = mengde.union(annenMengde);
        assertEquals(3, unionMengde.antallElementer());
        assertTrue(unionMengde.inneholder("A"));
        assertTrue(unionMengde.inneholder("B"));
        assertTrue(unionMengde.inneholder("C"));
    }
}
