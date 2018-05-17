package Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

import static java.lang.Math.sqrt;

public class DumbGeocoder {
    public static Double getDistanceBetween(String address1, String address2) {
        Double distance = Double.parseDouble("99999");
        try {
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey("AIzaSyA6Rc9few_6ZBfXMlC8ofCxYy2TUTKzGFI")
                    .build();

            GeocodingResult[] result1 = GeocodingApi.geocode(context,
                    address1).await();
            GeocodingResult[] result2 = GeocodingApi.geocode(context,
                    address2).await();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Float lat1 = Float.parseFloat(gson.toJson(result1[0].geometry.location.lat));
            Float lng1 = Float.parseFloat(gson.toJson(result1[0].geometry.location.lng));
            Float lat2 = Float.parseFloat(gson.toJson(result2[0].geometry.location.lat));
            Float lng2 = Float.parseFloat(gson.toJson(result2[0].geometry.location.lng));

            //System.out.println(lat1);
            //System.out.println(lat2);
            //System.out.println(lng1);
            //System.out.println(lng2);

            distance = sqrt((lat1 - lat2) * (lat1 - lat2) + (lng1 - lng2) * (lng1 - lng2));
            distance *= 91;
        } catch(Exception e) {
            System.out.println(e);
        }
        return distance;
    }
}
