package com.tanita.ocpp.models;

public class Cell {
    private CellStatus cellStatus;

    public Cell(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    public CellStatus getCellStatus() {
        return cellStatus;
    }

    @Override
    public String toString() {
        return "Cell: " +
                "cellStatus=" + cellStatus;
    }
}
