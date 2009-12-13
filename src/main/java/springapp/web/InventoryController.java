package springapp.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springapp.service.ProductManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class InventoryController {
    protected final Log logger = LogFactory.getLog(getClass());
    private ProductManager productManager;

    @RequestMapping("/hello.htm")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String now = (new java.util.Date()).toString();
        logger.info("returning hello view with " + now);

        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("now", now);
        myModel.put("products", this.productManager.getProducts());

        return new ModelAndView("hello", "model", myModel);
    }

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }
}