import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HashTableClient {
    public static void main(String[] args) {
        // int[] tableSizes = {97, 100, 200};  // Example table sizes
        int[] tableSizes = {200}; // one we actually need
        int[] multiples = {10, 20, 40};  // Multiples of table size

        for (int size : tableSizes) {
            System.out.println("Table size: " + size);

            for (int multiple : multiples) {
                int numItems = size * multiple;
                System.out.println("Number of items: " + numItems);

                HashTable<Integer, String> ht = new HashTable<>();
                Random random = new Random();

                List<Integer> listSizes = new ArrayList<>();

                long startTime = System.currentTimeMillis();

                for (int i = 0; i < numItems; i++) {
                    int key = random.nextInt(numItems);  // Generating random key within the range
                    String value = "Value " + i;
                    ht.put(key, value);
                    listSizes.add(ht.getListSize(key));  // Track the size of the list for each key
                }

                long endTime = System.currentTimeMillis();
                long executionTime = endTime - startTime;
                System.out.println("Execution time: " + executionTime + " ms");

                double averageSize = calculateAverage(listSizes);
                double standardDeviation = calculateStandardDeviation(listSizes, averageSize);

                System.out.println("Average size: " + averageSize);
                System.out.println("Standard deviation: " + standardDeviation);
                System.out.println();
            }
        }
    }

    private static double calculateAverage(List<Integer> list) {
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        return (double) sum / list.size();
    }

    private static double calculateStandardDeviation(List<Integer> list, double average) {
        double sum = 0;
        for (int num : list) {
            sum += Math.pow(num - average, 2);
        }
        double variance = sum / list.size();
        return Math.sqrt(variance);
    }
}