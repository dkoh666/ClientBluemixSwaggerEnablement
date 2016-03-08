package apples.ibm.com.au.ctp.bluemix.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Utils
{
    private static final Logger log = LoggerFactory.getLogger(Utils.class);
    private static final JsonParser parser = JsonParserFactory.getJsonParser();

    public static String getAllEmps()
    {
        String employeesResponse = "";
        RestTemplate restTemplate = new RestTemplate();


        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<String>(headers);
        HttpEntity<String> response = null;

        response = restTemplate.exchange(getEndPoint() + Constants.viewemps, HttpMethod.GET, entity, String.class);
        employeesResponse = response.getBody();

        return employeesResponse;
    }

    public static String getEndPoint()
    {
        String url = "";
        JsonParser parser = JsonParserFactory.getJsonParser();

        String jsonString = System.getenv("VCAP_SERVICES");

        Map<String, Object> jsonMap = parser.parseMap(jsonString);

        System.out.println(jsonMap.size());
        String firstKey = null;

        for ( String key : jsonMap.keySet() )
        {
            firstKey = key;
            // should only be one key in the MAP
            break;
        }

        List credentialsList = (List) jsonMap.get(firstKey);

        Map valueMap = (Map) credentialsList.get(0);

        System.out.println(valueMap.get("credentials"));

        Map<String, String> credentialsMap = (Map) valueMap.get("credentials");

        url = credentialsMap.get("url");

        return url;
    }

}
