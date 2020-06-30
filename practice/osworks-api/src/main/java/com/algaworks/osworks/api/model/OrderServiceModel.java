package com.algaworks.osworks.api.model;

import com.algaworks.osworks.domain.model.StatusOrderService;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class OrderServiceModel {

    private Long id;
    private ClientResumeModel client;
    private String description;
    private BigDecimal price;
    private StatusOrderService status;
    private OffsetDateTime openingDate;
    private OffsetDateTime finalDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientResumeModel getClient() {
        return client;
    }

    public void setClient(ClientResumeModel client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public StatusOrderService getStatus() {
        return status;
    }

    public void setStatus(StatusOrderService status) {
        this.status = status;
    }

    public OffsetDateTime getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(OffsetDateTime openingDate) {
        this.openingDate = openingDate;
    }

    public OffsetDateTime getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(OffsetDateTime finalDate) {
        this.finalDate = finalDate;
    }
}
