package springapp.domain;

import junit.framework.TestCase;

public class ProductTest extends TestCase {
    private Product product;

    protected void setUp() throws Exception {
        product = new Product();
    }

    public void test_setAndGetDescription() {
        String testDescription = "aDescription";
        assertNull(product.getDescription());
        product.setDescription(testDescription);
        assertEquals(testDescription, product.getDescription());
    }

    public void test_setAndGetPrice() {
        double testPrice = 100.00;
        assertEquals(0, 0, 0);
        product.setPrice(testPrice);
        assertEquals(testPrice, product.getPrice(), 0);
    }
}