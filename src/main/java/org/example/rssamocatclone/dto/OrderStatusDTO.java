package org.example.rssamocatclone.dto;


import java.time.LocalDateTime;

public class OrderStatusDTO {

    private int id;

    private String statusName;

    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String logMethod(){
        return "[" + LocalDateTime.now().toString() + "]" +  " -> " + this.statusName + " " + this.description;
    }
}
