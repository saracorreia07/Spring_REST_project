package com.algaworks.osworks.api.model;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class OrderServiceInput {

    @NotBlank
    private String Description;

    @NotNull
    private BigDecimal price;

    @Valid
    @NotNull
    private ClientIdInput client;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ClientIdInput getClient() {
        return client;
    }

    public void setClient(ClientIdInput client) {
        this.client = client;
    }
}
