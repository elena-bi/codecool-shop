package com.codecool.shop;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductServiceTest {
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDataStore;
    private ProductCategory testProductCategory;
    private Supplier testSupplier;

    @BeforeEach
    void generateProducts() {

        this.productDao = ProductDaoMem.getInstance();
        this.productCategoryDao = ProductCategoryDaoMem.getInstance();
        this.supplierDataStore = SupplierDaoMem.getInstance();

        this.testProductCategory = new ProductCategory("test",
                "dept", "a description");
        this.testSupplier = new Supplier("Supplier", "a description");
        this.supplierDataStore.add(testSupplier);
        this.productCategoryDao.add(testProductCategory);
        this.productDao.add(new Product("Fluorite Tower",
                15.99f, "USD",
                "text", testProductCategory, testSupplier));
    }

    @Test
    void getProductCategory_correctID_returnsID() {
        ProductService testProductService = new ProductService(
                this.productDao,
                this.productCategoryDao);

        assertEquals(
                this.testProductCategory,
                testProductService.getProductCategory(1));
    }

    @Test
    void getProductCategory_incorrectID_returnsNull() {
        ProductService testProductService = new ProductService(
                this.productDao,
                this.productCategoryDao);

        assertEquals(
                null,
                testProductService.getProductCategory(0));
    }

    @Test
    void getProductsForCategory_existingCategory_returnsProducts() {
        ProductService testProductService = new ProductService(
                this.productDao,
                this.productCategoryDao);
        List<Product> testProductList = new ArrayList<>();
        testProductList.add(this.productDao.find(1));

        assertEquals(
                testProductList,
                testProductService.getProductsForCategory(1));
    }

    @Test
    void getProductsForCategory_incorrectCategory_throwsError() {
        ProductService testProductService = new ProductService(
                this.productDao,
                this.productCategoryDao);
        List<Product> testProductList = new ArrayList<>();

        assertEquals(
                testProductList,
                testProductService.getProductsForCategory(0));
    }
}
