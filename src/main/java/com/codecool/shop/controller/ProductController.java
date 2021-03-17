package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryIdAsString;
        try {
            categoryIdAsString = req.getParameter("category");
        } catch (NullPointerException e) {
            categoryIdAsString = "";
        }

        int categoryId = processIdInput(categoryIdAsString);
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        String supplierIdAsString;

        try {
            supplierIdAsString = req.getParameter("supplier");
        } catch (NullPointerException e) {
            supplierIdAsString = "";
        }
        int supplierId = processIdInput(supplierIdAsString);
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        ProductCategory selectedCategory = productCategoryDataStore.find(categoryId);
        Supplier selectedSupplier = supplierDataStore.find(supplierId);

        List<Product> productsByCategory = displayFilteredByCategory(categoryId, productDataStore, selectedCategory);
        List<Product> productBySupplier = displayFilteredBySupplier(supplierId, productDataStore, selectedSupplier);

        productsByCategory.retainAll(productBySupplier);

        List<Product> finalList = productsByCategory;

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());

        context.setVariable("products", finalList);

        engine.process("product/index.html", context, resp.getWriter());
    }

    private int processIdInput(String categoryIdAsString) {
        int categoryId = -1;
        try {
            categoryId = Integer.parseInt(categoryIdAsString);
        } catch (Exception ignored){}

        return categoryId;
    }

    private List<Product> displayFilteredByCategory(int idProduct, ProductDao productDataStore, ProductCategory category) {
        if(idProduct == -1) {
            return productDataStore.getAll();
        } else {
            return productDataStore.getBy(category);
        }
    }

    private List<Product> displayFilteredBySupplier(int idProduct, ProductDao productDataStore, Supplier supplier) {
        if(idProduct == -1) {
            return productDataStore.getAll();
        } else {
            return productDataStore.getBy(supplier);
        }
    }

}
