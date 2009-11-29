package springapp.web;

import org.springframework.web.servlet.ModelAndView;
import junit.framework.TestCase;
import springapp.service.SimpleProductManager;

import java.util.Map;

public class InventoryControllerTest extends TestCase {

    public void testHandleRequestView() throws Exception {
        InventoryController controller = new InventoryController();
        controller.setProductManager(new SimpleProductManager());

        ModelAndView modelAndView = controller.handleRequest(null, null);

        assertEquals("hello", modelAndView.getViewName());

        assertNotNull(modelAndView.getModel());

        Map modelMap = (Map) modelAndView.getModel().get("model");
        String nowValue = (String) modelMap.get("now");
        assertNotNull(nowValue);
    }

}