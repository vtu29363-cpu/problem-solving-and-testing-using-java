import java.util.HashMap;
import java.util.Map;

class UndergroundSystem {

    // Store active check-ins: customer ID -> CheckInInfo
    private Map<Integer, CheckInInfo> checkInMap;
    // Store travel stats: "StartStation->EndStation" -> TravelData
    private Map<String, TravelData> travelMap;

    private static class CheckInInfo {
        String stationName;
        int checkInTime;

        CheckInInfo(String stationName, int checkInTime) {
            this.stationName = stationName;
            this.checkInTime = checkInTime;
        }
    }

    private static class TravelData {
        double totalTime;
        int totalTrips;

        TravelData(double totalTime, int totalTrips) {
            this.totalTime = totalTime;
            this.totalTrips = totalTrips;
        }
    }

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        travelMap = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckInInfo(stationName, t));
    }
    
    public void checkOut(int id, String stationName, int t) {
        CheckInInfo checkIn = checkInMap.remove(id);
        String routeKey = checkIn.stationName + "->" + stationName;
        int travelTime = t - checkIn.checkInTime;

        TravelData data = travelMap.getOrDefault(routeKey, new TravelData(0, 0));
        data.totalTime += travelTime;
        data.totalTrips += 1;
        
        travelMap.put(routeKey, data);
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String routeKey = startStation + "->" + endStation;
        TravelData data = travelMap.get(routeKey);
        return data.totalTime / data.totalTrips;
    }
}