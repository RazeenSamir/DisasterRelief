import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class Testing {

    @Test
    @DisplayName("STUDENT TEST - Case #1")
    public void firstCaseTest() {
        Region r1 = new Region("Region #1", 500);
        Region r2 = new Region("Region #2", 700);
        Region r3 = new Region("Region #3", 900);
        Region r4 = new Region("Region #4", 400);
        Region r5 = new Region("Region #5", 300);
        Region r6 = new Region("Region #6", 800);

        // Adding connections and their costs
        r1.addConnection(r2, 2000.0);
        r1.addConnection(r4, 1500.0);
        r1.addConnection(r5, 1800.0);
        r2.addConnection(r1, 2000.0);
        r2.addConnection(r3, 1500.0);
        r2.addConnection(r4, 500.0);
        r2.addConnection(r5, 700.0);
        r3.addConnection(r2, 1500.0);
        r4.addConnection(r1, 1500.0);
        r4.addConnection(r2, 500.0);
        r4.addConnection(r5, 1400.0);
        r4.addConnection(r6, 200.0);
        r5.addConnection(r1, 1800.0);
        r5.addConnection(r2, 700.0);
        r5.addConnection(r4, 1400.0);
        r6.addConnection(r4, 200.0);

        List<Region> sites = Arrays.asList(r1, r2, r3, r4, r5);
        Path result = Client.findPath(sites);

        List<Region> expectedPath = Arrays.asList(r1, r4, r5, r2, r3);
        assertTrue(result.getRegions().containsAll(expectedPath) && expectedPath.containsAll(result.getRegions()));
    }

    @Test
    @DisplayName("STUDENT TEST - Case #2")
    public void secondCaseTest() {
        Region r1 = new Region("Region #1", 1200);
    Region r2 = new Region("Region #2", 9000);
    Region r3 = new Region("Region #3", 4500);
    Region r4 = new Region("Region #4", 4600);
    Region r5 = new Region("Region #5", 1300);
    Region r6 = new Region("Region #6", 7800);
    Region r7 = new Region("Region #7", 2400);

    // Adding connections and their costs
    r1.addConnection(r2, 2900.0);
    r1.addConnection(r4, 2400.0);
    r2.addConnection(r1, 2900.0);
    r2.addConnection(r3, 1600.0);
    r2.addConnection(r4, 1300.0);
    r2.addConnection(r5, 3100.0);
    r3.addConnection(r2, 1600.0);
    r3.addConnection(r5, 900.0);
    r4.addConnection(r1, 2400.0);
    r4.addConnection(r2, 1300.0);
    r4.addConnection(r6, 1700.0);
    r4.addConnection(r7, 1200.0);
    r5.addConnection(r2, 3100.0);
    r5.addConnection(r3, 900.0);
    r6.addConnection(r4, 1700.0);
    r6.addConnection(r7, 600.0);
    r7.addConnection(r4, 1200.0);
    r7.addConnection(r6, 600.0);

    List<Region> sites = Arrays.asList(r1, r2, r4, r6, r7);
    Path result = Client.findPath(sites);

    // Ensure the result path matches the expected path in the exact order
    List<Region> expectedPath = Arrays.asList(r1, r4, r7, r6);
    assertEquals(expectedPath, result.getRegions(), "The calculated path does not match the expected path.");
    }

    @Test
    @DisplayName("STUDENT TEST - DIY")
    public void diyTest() {
        Region r1 = new Region("Region #1", 600);
        Region r2 = new Region("Region #2", 900);
        Region r3 = new Region("Region #3", 800);
        Region r4 = new Region("Region #4", 500);
        Region r5 = new Region("Region #5", 700);

        // Adding connections and their costs
        r1.addConnection(r2, 1200.0);
        r1.addConnection(r3, 1000.0);
        r2.addConnection(r1, 1200.0);
        r2.addConnection(r4, 900.0);
        r3.addConnection(r1, 1000.0);
        r3.addConnection(r5, 1100.0);
        r4.addConnection(r2, 900.0);
        r4.addConnection(r5, 700.0);
        r5.addConnection(r3, 1100.0);
        r5.addConnection(r4, 700.0);

        List<Region> sites = Arrays.asList(r1, r2, r3, r4, r5);
        Path result = Client.findPath(sites);

        // Expected path determined logically
        List<Region> expectedPath = Arrays.asList(r1, r3, r5, r4, r2);
        assertTrue(result.getRegions().containsAll(expectedPath) && expectedPath.containsAll(result.getRegions()));
    }
}
