package com.tanita.ocpp.models;

import java.util.List;

public class Order {
    private String orderName;
    private List<OrderCargo> orderCargo;


    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderName='" + orderName + '\'' +
                ", orderCargo=" + orderCargo +
                '}';
    }

    public List<OrderCargo> getOrderCargo() {
        return orderCargo;
    }

    public void setOrderCargo(List<OrderCargo> orderCargo) {
        this.orderCargo = orderCargo;
    }
}
