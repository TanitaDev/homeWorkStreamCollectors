package com.tanita.ocpp;

import com.tanita.ocpp.models.*;
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

        List<CargoStatus> cargoStatuses = List.of(CargoStatus.CANCEL, CargoStatus.PLACED, CargoStatus.LAST_DAY);
        List<CellStatus> cellStatuses = List.of(CellStatus.CELL_WORKING, CellStatus.CELL_BROKEN, CellStatus.CELL_BLOCKED);

        List<Order> orders = Stream.generate(() -> {
                    Order order = new Order();
                    order.setOrderName("order" + new Random().nextInt(6));
                    order.setOrderCargo(List.of(new OrderCargo(cargoStatuses.get(new Random().nextInt(3)), new Cell(cellStatuses.get(new Random().nextInt(3)))),
                            new OrderCargo(cargoStatuses.get(new Random().nextInt(3)), new Cell(cellStatuses.get(new Random().nextInt(3))))));
                    return order;
                })
                .limit(5).toList();

        Map<CellStatus, List<Order>> result2 = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> order.getOrderCargo().stream()
                                .filter(cargo -> cargo.getCargoStatus() == CargoStatus.PLACED)
                                .map(OrderCargo::getCell)
                                .findFirst().map(Cell::getCellStatus)
                                .orElse(CellStatus.CELL_BROKEN)
                ));


        Map<CellStatus, List<CargoStatus>> result = orders.stream()
                .flatMap(order -> order.getOrderCargo().stream())
                .collect(Collectors.groupingBy(
                        cargo -> cargo.getCell().getCellStatus(),
                        Collectors.mapping(OrderCargo::getCargoStatus, Collectors.toList())
                ));


        Map<CellStatus, List<OrderCargo>> groupedByCellStatus = orders.stream()
                .flatMap(order -> order.getOrderCargo().stream())
                .filter(cargo -> cargo.getCargoStatus() == CargoStatus.PLACED)
                .collect(Collectors.groupingBy(cargo -> cargo.getCell().getCellStatus()));


        System.out.println(groupedByCellStatus);

        System.out.println(orders);
        System.out.println("-".repeat(15));
        System.out.println(result2);
        System.out.println("-".repeat(15));
        System.out.println(result);
        System.out.println("-".repeat(15));
        System.out.println(groupedByCellStatus);


        //{cellStatus=[OrderCargo=[cargoStatus=PLACED], OrderCargo=[cargoStatus=PLACED], OrderCargo=[cargoStatus=PLACED]]}
    }

    static class User {

        Integer id;
        List<String> address = new ArrayList<>();

        public void printData() {
            System.out.print(id + " - ");
            System.out.println(address);
        }
    }

    record UserAndAddress(Integer userId, String address) {

    }
}

