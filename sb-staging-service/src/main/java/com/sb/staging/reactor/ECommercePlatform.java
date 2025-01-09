package com.sb.staging.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

public class ECommercePlatform {

    // 模拟从数据库中获取产品列表
    public static Flux<Product> getProductsFromDatabase() {
        List<Product> products = Arrays.asList(
                new Product(1, "Laptop", 1000),
                new Product(2, "Smartphone", 800),
                new Product(3, "Headphones", 200),
                new Product(4, "Tablet", 600)
        );
        return Flux.fromIterable(products);
    }

    // 模拟从外部API获取特价产品信息
    public static Flux<Product> getSpecialOffers() {
        List<Product> specialOffers = Arrays.asList(
                new Product(5, "Smartwatch", 300),
                new Product(6, "Camera", 700)
        );
        return Flux.fromIterable(specialOffers);
    }

    // 模拟从消息队列获取新产品通知
    public static Mono<Product> getNewProductNotification() {
        // 假设我们每次只收到一条新产品通知
        return Mono.just(new Product(7, "Speaker", 150));
    }

    public static void main(String[] args) {
        // 从各个数据源获取产品信息，并合并成一个Flux
        Flux<Product> allProducts = Flux.concat(
                getProductsFromDatabase(),
                getSpecialOffers(),
                getNewProductNotification()
        );

        // 过滤出价格低于500的产品
        Flux<Product> filteredProducts = allProducts.filter(product -> product.getPrice() < 500);

        // 按价格升序排序产品
        Flux<Product> sortedProducts = filteredProducts.sort((p1, p2) -> p1.getPrice() - p2.getPrice());

        // 订阅最终的产品流并处理每个产品
        sortedProducts.subscribe(product -> System.out.println("Product: " + product));
    }

    static class Product {
        private int id;
        private String name;
        private int price;

        public Product(int id, String name, int price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }
    }
}