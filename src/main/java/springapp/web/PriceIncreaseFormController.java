package springapp.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import springapp.web.PriceIncrease;
import springapp.service.ProductManager;

@Controller
@RequestMapping("/priceincrease.htm")
@SessionAttributes("priceIncrease")
public class PriceIncreaseFormController {
    protected final Log logger = LogFactory.getLog(getClass());
    private ProductManager productManager;
    private Validator priceIncreaseValidator;

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(ModelMap model) {
        PriceIncrease priceIncrease = new PriceIncrease();
        priceIncrease.setPercentage(20);
        model.addAttribute("priceIncrease", priceIncrease);
        return "priceincrease";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("priceIncrease") PriceIncrease priceIncrease, BindingResult result, SessionStatus status) {
        priceIncreaseValidator.validate(priceIncrease, result);
        if (result.hasErrors()) {
            return "priceincrease";
        } else {
            int increase = priceIncrease.getPercentage();
            logger.info("Increasing prices by " + increase + "%.");
            productManager.increasePrice(increase);
            logger.info("returning from PriceIncreaseForm view to hello");
            status.setComplete();
            return "redirect:/hello.htm";
        }
    }

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }

    public ProductManager getProductManager() {
        return productManager;
    }

    public void setPriceIncreaseValidator(Validator priceIncreaseValidator) {
        this.priceIncreaseValidator = priceIncreaseValidator;
    }
}