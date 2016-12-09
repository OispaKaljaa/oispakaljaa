package fi.oispakaljaa.karhu.service;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import fi.oispakaljaa.karhu.APItemplates.GoogleDistanceMatrixTemplate;

/**
 * Created by Hugo on 9.12.2016.
 */

@Service
public class GoogleAPIService {
    RestTemplate restTemplate;


    private String getUrl(String a, String b) {
        return "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + a +"&destinations=" + b + ".Helsinki&key=AIzaSyDSbvTAIB6YD0hE26dIbzvBhM7-tRpapGI";
    }
    public GoogleAPIService() {
        restTemplate = new RestTemplate();
    }
    public int getDistance(String a, String b) {
        GoogleDistanceMatrixTemplate res = restTemplate.getForObject(getUrl(a, b), GoogleDistanceMatrixTemplate.class);
        return res.rows[0].elements[0].distance.value;
    }
}
