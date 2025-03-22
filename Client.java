import java.util.*;

public class Client {
    private static final Random RAND = new Random();

    public static void main(String[] args) throws Exception {
        // List<Region> scenario = createRandomScenario(10, 10, 100, 1000, 100000);
        List<Region> scenario = createRandomScenario(5, 100, 1000, 100, 1000);
        System.out.println(scenario);
        
        Path allocation = findPath(scenario);
        if (allocation != null) {
            printResult(allocation);
        } else {
            System.out.println("No valid path found. :-(");
        }
    }

    // Behavior: This method finds the optimal path through a list of regions starting from the
    // first region in the list. The most optimal path is the path that helps the most people. If 
    // there is a tie amongst paths that help the most people, then the cheapest among those paths
    // is the most optimal one.
    // Exceptions: if the list of regions is null, then an IllegalArgumentException is thrown
    // Returns: the most optimal Path to take, or null if the list of regions is empty
    // Parameters: 
    // - sites: the list of regions to go through
    public static Path findPath(List<Region> sites) {
        if(sites == null){
            throw new IllegalArgumentException();
        }
        if(sites.isEmpty()){
            return null;
        }
        if(sites.size() == 1){
            return new Path().extend(sites.get(0));
        }
        return findPath(sites, sites.get(0), new Path().extend(sites.get(0)), new Path());
    }

    // Behavior: This private pair method recursively finds the most optimal path through the given
    // regions starting from the current region. It makes sure that each region is only visited
    // once.
    // Returns: The most optimal path
    // Parameters:
    // - regions: the list of regions
    // - curr: the current region being looked at
    // - currPath: the path constructed so far
    // - bestPath: the most optimal path so far
    private static Path findPath(List<Region> regions, Region curr, Path currPath, Path bestPath){
        if(currPath.size() == regions.size()){
            return findBestPath(currPath, bestPath);
        }
        boolean extended = false;
        if(!regions.isEmpty()){
            for(Region region : regions){
                if(!currPath.getRegions().contains(region) && curr.canReach(region)){
                    Path updatedPath = currPath.extend(region);
                    Path potentialBest = findPath(regions, region, updatedPath, bestPath);
                    bestPath = findBestPath(potentialBest, bestPath);
                    extended = true;
                }
            }
        }
        if(!extended){
            bestPath = findBestPath(currPath, bestPath);
        }
        return bestPath;
    }

    // Behavior: Compares two paths and returns the better one based on the given criteria. 
    // A path is considered to be better if it includes more people, or in the case of a tie, the
    // cheaper one.
    // Returns: the best path (most people or in the case of a tie the cheaper one)
    // Parameters:
    // - newPath: the new path being compared
    // - prevPath: the best path so far
    private static Path findBestPath(Path newPath, Path prevPath){
        if(prevPath == null || newPath.totalPeople() > prevPath.totalPeople()
                || newPath.totalCost() < prevPath.totalCost()){
            prevPath = newPath;
        }
        return prevPath;
    }
    ///////////////////////////////////////////////////////////////////////////
    // PROVIDED HELPER METHODS - **DO NOT MODIFY ANYTHING BELOW THIS LINE!** //
    ///////////////////////////////////////////////////////////////////////////
    
    /**
    * Prints each path in the provided set. Useful for getting a quick overview
    * of all path currently in the system.
    * @param paths Set of paths to print
    */
    public static void printPaths(Set<Path> paths) {
        System.out.println("All Allocations:");
        for (Path a : paths) {
            System.out.println("  " + a);
        }
    }

    /**
    * Prints details about a specific path result, including the total people
    * helped and total cost.
    * @param path The path to print
    */
    public static void printResult(Path path) {
        System.out.println("Result: ");
        List<Region> regions = path.getRegions();
        System.out.print(regions.get(0).getName());
        for (int i = 1; i < regions.size(); i++) {
            System.out.print(" --($" + regions.get(i - 1).getCostTo(regions.get(i)) + ")-> " + regions.get(i).getName());
        }
        System.out.println();
        System.out.println("  People helped: " + path.totalPeople());
        System.out.printf("  Cost: $%.2f\n", path.totalCost());
    }

    /**
    * Creates a scenario with numRegions regions by randomly choosing the population 
    * and travel costs for each region.
    * @param numRegions Number of regions to create
    * @param minPop Minimum population per region
    * @param maxPop Maximum population per region
    * @param minCost Minimum cost of travel between regions
    * @param maxCost Maximum cost of travel between regions
    * @return A list of randomly generated regions
    */
    public static List<Region> createRandomScenario(int numRegions, int minPop, int maxPop,
                                                    double minCost, double maxCost) {
        List<Region> result = new ArrayList<>();

        // ranomly create regions
        for (int i = 0; i < numRegions; i++) {
            // int pop = RAND.nextInt(minPop, maxPop + 1);
            int pop = RAND.nextInt(maxPop - minPop + 1) + minPop;
            result.add(new Region("Region #" + i, pop));
        }

        // randomly create connections between regions
        for (int i = 0; i < numRegions; i++) {
            // int numConnections = RAND.nextInt(1, numLocs - i);
            Region site = result.get(i);
            for (int j = i + 1; j < numRegions; j++) {
                // flip a coin to decide whether or not to add each connection
                if (RAND.nextBoolean()) {
                    Region other = result.get(j);
                    // double cost = round2(RAND.nextDouble(minCostPer, maxCostPer));
                    double cost = round2(RAND.nextDouble() * (maxCost - minCost) + maxCost);
                    site.addConnection(other, cost);
                    other.addConnection(site, cost);
                }
            }
        }

        return result;
    }

    /**
    * Manually creates a simple list of regions to represent a known scenario.
    * @return A simple list of regions
    */
    public static List<Region> createSimpleScenario() {
        List<Region> result = new ArrayList<>();
        Region regionOne = new Region("Region #1", 100);
        Region regionTwo = new Region("Region #2", 50);
        Region regionThree = new Region("Region #3", 100);

        regionOne.addConnection(regionTwo, 200);
        regionOne.addConnection(regionThree, 300);

        regionTwo.addConnection(regionOne, 200);

        regionThree.addConnection(regionOne, 300);
        
        result.add(regionOne);   // Region #1: pop. 100 - [Region #2 (200.0), Region #3 (300.0)]
        result.add(regionTwo);   // Region #2: pop. 50 - [Region #1 (200.0)]
        result.add(regionThree); // Region #3: pop. 100 - [Region #1 (300.0)]

        return result;
    }   

    /**
    * Rounds a number to two decimal places.
    * @param num The number to round
    * @return The number rounded to two decimal places
    */
    private static double round2(double num) {
        return Math.round(num * 100) / 100.0;
    }
}
