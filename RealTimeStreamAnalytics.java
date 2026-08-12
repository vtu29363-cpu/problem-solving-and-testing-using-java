import java.util.*;
import java.util.stream.*;

public class RealTimeStreamAnalytics {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        List<Map.Entry<String, Integer>> readings = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String sensor = sc.next();
            int temperature = sc.nextInt();

            readings.add(
                new AbstractMap.SimpleEntry<>(sensor, temperature)
            );
        }

        readings.stream()
                .filter(entry -> entry.getValue() > 50)
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.averagingDouble(
                                Map.Entry::getValue
                        )
                ))
                .entrySet()
                .stream()
                .sorted((a, b) ->
                        Double.compare(b.getValue(), a.getValue())
                )
                .forEach(entry ->
                        System.out.println(
                                entry.getKey() + " " + entry.getValue()
                        )
                );
    }
}
    

