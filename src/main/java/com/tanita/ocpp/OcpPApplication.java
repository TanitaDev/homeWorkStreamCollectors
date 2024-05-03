package com.tanita.ocpp;

import com.tanita.ocpp.models.Order;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class OcpPApplication {

    public static void main(String[] args) {
        SpringApplication.run(OcpPApplication.class, args);
//
//        Random random = new Random();
//        AtomicInteger i = new AtomicInteger();
//
//        List<User> users = Stream.generate(() -> {
//                    User user = new User();
//                    user.id = i.getAndIncrement();
//                    user.address.add(String.valueOf(random.nextInt(1,5)));
//                    user.address.add(String.valueOf(random.nextInt(5, 10)));
//                    return user;
//                }).limit(10)
//                .toList();
//
//        users.stream().forEach(User::printData);
//
//        List<List<String>> userAddress = users.stream().map(user -> user.address).collect(Collectors.toList());
//        System.out.println("result: ");
//        System.out.println(userAddress);
//        System.out.println("-".repeat(10));
//
//        List<String> address = users.stream().flatMap(user -> user.address.stream()).collect(Collectors.toList());
//
//
//        System.out.println(address);
//        System.out.println("-".repeat(10));
//
//        Map<String, List<Integer>> result = users.stream()
//                .flatMap(user -> user.address
//                        .stream()
//                        .map(adr -> new UserAndAddress(user.id, adr)))
//                .collect(Collectors.groupingBy(UserAndAddress::address,  Collectors.mapping(r -> r.userId, Collectors.toList())));
//
//        System.out.println(result);



        List<Order> orders = Stream.generate(() -> {
            Order order = new Order();
            order.setId(new Random().nextLong(6));
            order.setOrderName("order" + new Random().nextInt(6));
            return order;
        }).limit(5).toList();
        System.out.println(orders);
    }

    static class User {

        Integer id;
        List<String> address = new ArrayList<>();

        public void printData() {
            System.out.print(id + " - ");
            System.out.println(address);
        }
    }

    record UserAndAddress (Integer userId, String address) {

    }
}

