import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AviaSoulsTest {

    @Test
    public void testSearchSortsByPrice() {
        AviaSouls manager = new AviaSouls();
        manager.add(new Ticket("Moscow", "Kazan", 5000, 1000, 1200));
        manager.add(new Ticket("Moscow", "Kazan", 3000, 1100, 1300));
        manager.add(new Ticket("Moscow", "Kazan", 7000, 900, 1130));

        Ticket[] tickets = manager.search("Moscow", "Kazan");

        assertEquals(3000, tickets[0].getPrice());
        assertEquals(5000, tickets[1].getPrice());
        assertEquals(7000, tickets[2].getPrice());
    }

    @Test
    public void testSearchAndSortByTime() {
        AviaSouls manager = new AviaSouls();
        // Длительность: 200, 210, 230 минут
        manager.add(new Ticket("SPb", "Novgorod", 4000, 800, 1000));   // 200 мин
        manager.add(new Ticket("SPb", "Novgorod", 6000, 900, 1110));  // 210 мин
        manager.add(new Ticket("SPb", "Novgorod", 5000, 700, 930));    // 230 мин

        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket[] tickets = manager.searchAndSortBy("SPb", "Novgorod", comparator);

        assertEquals(200, tickets[0].getTimeTo() - tickets[0].getTimeFrom());
        assertEquals(210, tickets[1].getTimeTo() - tickets[1].getTimeFrom());
        assertEquals(230, tickets[2].getTimeTo() - tickets[2].getTimeFrom());
    }

    @Test
    public void testCompareToByPrice() {
        Ticket t1 = new Ticket("A", "B", 1000, 0, 100);
        Ticket t2 = new Ticket("A", "B", 2000, 0, 100);

        assertTrue(t1.compareTo(t2) < 0);
        assertTrue(t2.compareTo(t1) > 0);
        assertEquals(0, t1.compareTo(t1));
    }

    @Test
    public void testTicketTimeComparator() {
        Ticket t1 = new Ticket("X", "Y", 0, 0, 100);  // 100 мин
        Ticket t2 = new Ticket("X", "Y", 0, 50, 200); // 150 мин

        TicketTimeComparator comparator = new TicketTimeComparator();
        assertTrue(comparator.compare(t1, t2) < 0);
        assertTrue(comparator.compare(t2, t1) > 0);
        assertEquals(0, comparator.compare(t1, t1));
    }
}
