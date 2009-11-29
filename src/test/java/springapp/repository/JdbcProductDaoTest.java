package springapp.repository;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import springapp.domain.Product;

import java.util.List;

public class JdbcProductDaoTest extends AbstractTransactionalDataSourceSpringContextTests {
    private ProductDao productDao;

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-context.xml"};
    }

    @Override
    protected void onSetUpInTransaction() throws Exception {
        super.deleteFromTables(new String[]{"products"});
        super.executeSqlScript("file:src/main/sql/load_data.sql", true);
    }

    public void testGetProductList() {
        List<Product> products = productDao.getProductList();
        assertEquals("wrong number of products?", 3, products.size());
    }

    public void testSaveProduct() {
        List<Product> products = productDao.getProductList();
        for (Product p : products) {
            p.setPrice(200.12);
            productDao.saveProduct(p);
        }
        List<Product> updatedProducts = productDao.getProductList();
        for (Product p : updatedProducts) {
            assertEquals("wrong price of product?", 200.12, p.getPrice());
        }
    }
}