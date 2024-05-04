package com.tanita.ocpp.models;

public class OrderCargo {
    private final CargoStatus cargoStatus;
    private final Cell cell;

    public OrderCargo(CargoStatus cargoStatus, Cell cell) {
        this.cargoStatus = cargoStatus;
        this.cell = cell;
    }

    public CargoStatus getCargoStatus() {
        return cargoStatus;
    }

    public Cell getCell() {
        return cell;
    }

    @Override
    public String toString() {
        return "OrderCargo{" +
                "cargoStatus=" + cargoStatus +
                ", cell=" + cell +
                '}';
    }
}
