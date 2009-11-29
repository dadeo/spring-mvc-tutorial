package springapp.service;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import springapp.domain.Product;
import springapp.repository.ProductDao;
import junit.framework.TestCase;

public class DaoProductManagerTest extends TestCase {
    private DaoProductManager productManager;
    private ProductDao productDao;
    private List<Product> products;
    private static int PRODUCT_COUNT = 2;
    private static Double CHAIR_PRICE = new Double(20.50);
    private static String CHAIR_DESCRIPTION = "Chair";
    private static String TABLE_DESCRIPTION = "Table";
    private static Double TABLE_PRICE = new Double(150.10);
    private static int POSITIVE_PRICE_INCREASE = 10;

    protected void setUp() throws Exception {
        products = new ArrayList<Product>();
        Product product = new Product();
        product.setDescription("Chair");
        product.setPrice(CHAIR_PRICE);
        products.add(product);
        product = new Product();
        product.setDescription("Table");
        product.setPrice(TABLE_PRICE);
        products.add(product);

        productDao = mock(ProductDao.class);

        productManager = new DaoProductManager();
        productManager.setProductDao(productDao);
    }

    public void testGetProductsWithNoProducts() {
        when(productDao.getProductList()).thenReturn(null);

        assertNull(productManager.getProducts());
    }

    public void testGetProducts() {
        when(productDao.getProductList()).thenReturn(products);

        List<Product> products = productManager.getProducts();
        assertNotNull(products);

        assertEquals(PRODUCT_COUNT, productManager.getProducts().size());

        Product product = products.get(0);
        assertEquals(CHAIR_DESCRIPTION, product.getDescription());
        assertEquals(CHAIR_PRICE, product.getPrice());

        product = products.get(1);
        assertEquals(TABLE_DESCRIPTION, product.getDescription());
        assertEquals(TABLE_PRICE, product.getPrice());
    }

    public void testIncreasePriceWithNullListOfProducts() {
        when(productDao.getProductList()).thenReturn(null);

        try {
            productManager.increasePrice(POSITIVE_PRICE_INCREASE);
        }
        catch (NullPointerException ex) {
            fail("Products list is null.");
        }

        verify(productDao).getProductList();
        verifyNoMoreInteractions(productDao);
    }

    public void testIncreasePriceWithEmptyListOfProducts() {
        when(productDao.getProductList()).thenReturn(new ArrayList<Product>());

        try {
            productManager.increasePrice(POSITIVE_PRICE_INCREASE);
        }
        catch (Exception ex) {
            fail("Products list is empty.");
        }

        verify(productDao).getProductList();
        verifyNoMoreInteractions(productDao);
    }

    public void testIncreasePriceWithPositivePercentage() {
        when(productDao.getProductList()).thenReturn(products);

        productManager.increasePrice(POSITIVE_PRICE_INCREASE);

        List<Product> products = productManager.getProducts();

        Product product = products.get(0);
        double expectedChairPriceWithIncrease = 22.55;
        assertEquals(expectedChairPriceWithIncrease, product.getPrice());

        product = products.get(1);
        double expectedTablePriceWithIncrease = 165.11;
        assertEquals(expectedTablePriceWithIncrease, product.getPrice());

        verify(productDao, times(2)).getProductList();
        verify(productDao).saveProduct(products.get(0));
        verify(productDao).saveProduct(products.get(1));
    }
}