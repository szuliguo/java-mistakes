package org.geekbang.time.commonmistakes.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private Double price;


    public static List<Product> getData() {
        return Arrays.asList(
                new Product(1L, "苹果", 1.0),
                new Product(2L, "桔子", 2.0),
                new Product(3L, "香蕉", 3.0),
                new Product(4L, "芒果", 4.0),
                new Product(5L, "西瓜", 5.0),
                new Product(6L, "葡萄", 6.0),
                new Product(7L, "桃子", 7.0),
                new Product(8L, "椰子", 8.0),
                new Product(9L, "菠萝", 9.0),
                new Product(10L, "石榴", 10.0),

                new Product(11L, "苹果", 11.0),
                new Product(12L, "桔子", 12.0),
                new Product(13L, "香蕉", 13.0),
                new Product(14L, "芒果", 14.0),
                new Product(15L, "西瓜", 15.0),
                new Product(16L, "葡萄", 16.0),
                new Product(17L, "桃子", 17.0),
                new Product(18L, "椰子", 18.0),
                new Product(19L, "菠萝", 19.0),
                new Product(20L, "石榴", 20.0),


               new Product(21L, "苹果", 1.2),
                new Product(22L, "桔子", 1.2),
                new Product(23L, "香蕉", 1.2),
                new Product(24L, "芒果", 1.2),
                new Product(25L, "西瓜", 1.2),
                new Product(26L, "葡萄", 1.2),
                new Product(27L, "桃子", 1.2),
                new Product(28L, "椰子", 1.2),
                new Product(29L, "菠萝", 1.2),
                new Product(30L, "石榴", 2.2));


    }

    public static void main(String[] args) {
        List<Product> list = getData();
        Map<String, List<Product>> map = list.stream().collect(Collectors.groupingBy(o -> o.getName()));
        map.forEach((k, v) -> {
            Collections.sort(v, new Comparator<Product>() {
                @Override
                public int compare(Product p1, Product p2) {
                    return p1.getPrice().compareTo(p2.getPrice());
                }
            });
        });
        System.out.println(map);

    }
}
