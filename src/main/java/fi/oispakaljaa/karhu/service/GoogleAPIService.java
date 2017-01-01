package fi.oispakaljaa.karhu.service;

import fi.oispakaljaa.karhu.APItemplates.google.Element;
import fi.oispakaljaa.karhu.domain.Bar;
import fi.oispakaljaa.karhu.repository.BarRepository;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import fi.oispakaljaa.karhu.APItemplates.google.GoogleDistanceMatrixTemplate;

import java.util.*;

/**
 * Created by Hugo on 9.12.2016.
 */

@Service
public class GoogleAPIService {
    private RestTemplate restTemplate;

    public GoogleAPIService() {
        restTemplate = new RestTemplate();
    }

    private String getUrl(String a, String b) {
        return "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + a +"&destinations=" + b + ".Helsinki&key=" + System.getenv("GOOGL_API_KEY");
    }

    public List<Map<Object, Object>> assignDistanceToBars(String a, List<Bar> bars) {
        Object[] addresses = bars.stream().map(bar -> bar.getAddress()).toArray();
        GoogleDistanceMatrixTemplate res = restTemplate.getForObject(getUrl(
            a, String.join("|", Arrays.copyOf(addresses, addresses.length, String[].class))),
            GoogleDistanceMatrixTemplate.class);

        if (res == null || res.rows[0].elements.length != bars.size())
            return null;
        List<Map<Object, Object>> resList = new ArrayList<>();

        // A very bad way of doing this
        for (int i = 0; i < res.rows[0].elements.length; ++i) {
            if (res.rows[0].elements[i].distance == null)
                continue;
            Map<Object, Object> jsonObject = new HashMap<>();
            jsonObject.put("distance", res.rows[0].elements[i].distance.value);
            jsonObject.put("bar", bars.get(i));
            resList.add(jsonObject);
        }
        return resList;
    }
}
