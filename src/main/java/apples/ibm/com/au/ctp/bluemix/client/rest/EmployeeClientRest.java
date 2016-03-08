package apples.ibm.com.au.ctp.bluemix.client.rest;

import apples.ibm.com.au.ctp.bluemix.client.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class EmployeeClientRest
{
    private static final Logger log = LoggerFactory.getLogger(EmployeeClientRest.class);

    @RequestMapping("/vcapservices")
    public String showVcapServices()
    {
        return String.format("VCAP_SERVICES : %s", System.getenv("VCAP_SERVICES"));
    }

    @RequestMapping("/json_services_map")
    public Map<String, Object> showVcapServicesAsJsonMap()
    {

        JsonParser parser = JsonParserFactory.getJsonParser();

        String jsonString = System.getenv("VCAP_SERVICES");
        Map<String, Object> jsonMap = parser.parseMap(jsonString);

        return jsonMap;
    }

    @RequestMapping("/viewemps")
    public String viewAllEmployees()
    {
        return Utils.getAllEmps();
    }

    @RequestMapping("/getendpoint")
    public String getAPIMUrlEndpoint()
    {
        return Utils.getEndPoint();
    }

}
