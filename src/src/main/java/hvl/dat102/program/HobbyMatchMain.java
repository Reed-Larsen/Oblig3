package hvl.dat102.program;

import java.util.HashSet;
import java.util.Set;

public class HobbyMatchMain {
    public static double match(Person a, Person b) {
        Set<String> fellesHobbyer = new HashSet<>(a.getHobbyer());
        fellesHobbyer.retainAll(b.getHobbyer()); // Finn felles hobbyer

        Set<String> kunHosA = new HashSet<>(a.getHobbyer());
        kunHosA.removeAll(b.getHobbyer()); // Hobbyer kun hos A

        Set<String> kunHosB = new HashSet<>(b.getHobbyer());
        kunHosB.removeAll(a.getHobbyer()); // Hobbyer kun hos B

        int antallFelles = fellesHobbyer.size();
        int antallKunHosDenEne = kunHosA.size() + kunHosB.size();

        // Ny formel for å sikre at scoren alltid er positiv
        return antallFelles / (double) (antallFelles + antallKunHosDenEne);
    }

    public static void main(String[] args) {
        Person Mikal = new Person("Mikal", "jakt", "sykling", "venner", "data");
        Person Torgeir = new Person("Daniel", "sykling", "bøker", "musikk", "data");
        Person Daniel = new Person("Torgeir", "jakt", "sykling", "filmer", "data");

        // Finn beste match
        Person beste1 = Mikal;
        Person beste2 = Torgeir;
        double besteScore = match(Mikal, Torgeir);

        if (match(Mikal, Daniel) > besteScore) {
            beste1 = Mikal;
            beste2 = Daniel;
            besteScore = match(Mikal, Daniel);
        }
        if (match(Torgeir, Daniel) > besteScore) {
            beste1 = Torgeir;
            beste2 = Daniel;
            besteScore = match(Torgeir, Daniel);
        }

        System.out.println("Match mellom Mikal og Torgeir: "
                + String.format("%.2f", match(Mikal, Torgeir)));

        System.out.println("Match mellom Mikal og Daniel: "
                + String.format("%.2f", match(Mikal, Daniel)));

        System.out.println("Match mellom Torgeir og Daniel: "
                + String.format("%.2f", match(Torgeir, Daniel)));


        System.out.println("Beste match er mellom " + beste1.getNavn() + " og " + beste2.getNavn() + " med score: " + besteScore);
    }
}