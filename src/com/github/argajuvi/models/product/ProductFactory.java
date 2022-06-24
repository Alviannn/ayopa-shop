package com.github.argajuvi.models.product;

import java.sql.Date;

/**
 * <h1>Factory Design Pattern</h1>
 *
 * <h2>Why we use it?</h2>
 * We decided to use Factory Design Pattern because:<br>
 * 1. We think it is one of the easiest pattern to be implemented.<br>
 * 2. Our project contains models that is suitable for <b>Creational Design Pattern.</b><br><br>
 *
 * <h2>Explanation</h2>
 * From several types of Factory Pattern in
 * <a href="https://programhappy.net/2021/10/19/factory-design-pattern/">this link</a>,
 * we use the <b>Factory</b> one considering our products: {@link com.github.argajuvi.models.product.BookProduct},
 * {@link com.github.argajuvi.models.product.ClothingProduct}, and {@link com.github.argajuvi.models.product.FoodProduct}
 * inherit from the same parent class {@link com.github.argajuvi.models.product.Product} but having <b>different constructor parameter</b>.
 * Moreover, we found out similar Factory class that we want to implement from
 * <a href="https://docs.oracle.com/javase/7/docs/api/javax/swing/BorderFactory.html">Java Swing</a>.<br><br>
 *
 * <h2>Purpose</h2>
 * This class main purpose is to build different class of products that inherit from same parent class so that the client
 * would not have to instantiate it themself. Basically we want to make it clean where the product should be produced.
 */
public class ProductFactory {

    public static Product createClothProduct(int id, String name, int price, char size) {
        return new ClothingProduct(id, name, price, size);
    }

    public static Product createFoodProduct(int id, String name, int price, Date expDate) {
        return new FoodProduct(id, name, price, expDate);
    }

    public static Product createBookProduct(int id, String name, int price, int publishYear, String author) {
        return new BookProduct(id, name, price, publishYear, author);
    }

}
