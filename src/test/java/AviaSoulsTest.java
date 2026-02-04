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

        // Используем assertArrayEquals для проверки массива
        Ticket[] expected = {
                new Ticket("Moscow", "Kazan", 3000, 1100, 1300),
                new Ticket("Moscow", "Kazan", 5000, 1000, 1200),
                new Ticket("Moscow", "Kazan", 7000, 900, 1130)
        };
        assertArrayEquals(expected, tickets);
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

        // Проверяем порядок по длительности полёта
        Ticket[] expected = {
                new Ticket("SPb", "Novgorod", 4000, 800, 1000),
                new Ticket("SPb", "Novgorod", 6000, 900, 1110),
                new Ticket("SPb", "Novgorod", 5000, 700, 930)
        };
        assertArrayEquals(expected, tickets);
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
