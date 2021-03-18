package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
//        Supplier amazon = new Supplier("Amazon", "Digital content and services");
//        supplierDataStore.add(amazon);
//        Supplier lenovo = new Supplier("Lenovo", "Computers");
//        supplierDataStore.add(lenovo);

        Supplier kernowcraft = new Supplier("Kernowcraft", "Your destination for gemstones and jewellery making");
        supplierDataStore.add(kernowcraft);
        Supplier gemselect = new Supplier("Gemselect", "Explore a world of color");
        supplierDataStore.add(gemselect);

        //setting up a new product category
//        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
//        productCategoryDataStore.add(tablet);
        ProductCategory fluorite = new ProductCategory("Fluorite", "Semi-precious", " is the mineral form of calcium fluoride");
        ProductCategory quartz = new ProductCategory("Quartz", "Semi-precious", "is a hard, crystalline mineral composed of silicon and oxygen atoms");
        ProductCategory citrine = new ProductCategory("Citrine", "Semi-precious", "Citrine is a variety of quartz whose color ranges from a pale yellow to brown due to ferric impurities");
        ProductCategory amethyst = new ProductCategory("Amethyst", "Semi-precious", "is a violet variety of quartz");
        ProductCategory tanzanite = new ProductCategory("Tanzanite", "Precious", "Tanzanite is a blue and violet variety of the mineral ");
        productCategoryDataStore.add(fluorite);
        productCategoryDataStore.add(quartz);
        productCategoryDataStore.add(citrine);
        productCategoryDataStore.add(amethyst);
        productCategoryDataStore.add(tanzanite);


        //setting up products and printing it

        productDataStore.add(new Product("Fluorite Tower", 15.99f, "USD", "Each one of these crystal wands has approximately 4 -5 inch (10 - 12 cm) long (weighs about 3.53-4.59 oz)", fluorite, gemselect));
        productDataStore.add(new Product("Quartz Tower", 39.99f, "USD", "High Quality Natural Clear Quartz Crystal Tower", quartz, kernowcraft));
        productDataStore.add(new Product("Citrine Tower", 34.99f, "USD", "Size(mm):103x36x28 cm", citrine, gemselect));
        productDataStore.add(new Product("Amethyst Cluster", 2194.99f, "USD", "Towering from the floor to accent the decor of any room, be that your living room or office. This is a great quality amethyst piece ", amethyst, gemselect));
        productDataStore.add(new Product("Tanzanite Tower", 118.35f, "USD", "Tanzanite Crystal , Blue Tanzanite , Natural Tanzanite Stone", tanzanite, gemselect));
        productDataStore.add(new Product("Fluorite Sphere", 17.99f, "USD", "Approximate size: 55mm", fluorite, kernowcraft));

    }
}
