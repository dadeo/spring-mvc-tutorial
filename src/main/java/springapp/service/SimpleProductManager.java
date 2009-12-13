package springapp.service;

import springapp.domain.Product;

import java.util.List;

public class SimpleProductManager implements ProductManager {
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void increasePrice(int percentage) {
        if(products != null && products.size() > 0) {
            for(Product product : products) {
                product.setPrice(product.getPrice() * (1 + (percentage / 100.0)));
            }
        }
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}