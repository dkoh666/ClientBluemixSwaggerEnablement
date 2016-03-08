package apples.ibm.com.au.ctp.bluemix.client.controller;

import apples.ibm.com.au.ctp.bluemix.client.Utils;
import apples.ibm.com.au.ctp.bluemix.client.beans.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class WelcomeController
{
    private static final Logger log = LoggerFactory.getLogger(WelcomeController.class);
    private static final JsonParser parser = JsonParserFactory.getJsonParser();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showSearchPage(Model model)
    {
        List<Employee> emps = new ArrayList<Employee>();

        String empJson = Utils.getAllEmps();

        log.info(empJson);

        List<Object> jsonList = parser.parseList(empJson);

        for (Object item: jsonList)
        {
            Map m = (Map) item;
            emps.add(new Employee(m.get("id") + "", (String)m.get("name"), m.get("age") + ""));
        }

        log.info("Employees found = " + emps.size());
        log.info("emps = " + emps);

        model.addAttribute("employees", emps);
        model.addAttribute("employeescount", emps.size());

        return "welcome";
    }
}
