package com.algaworks.osworks.domain.model;

import com.algaworks.osworks.domain.exception.BusinessException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class OrderService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    private String description;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private StatusOrderService status;

    private OffsetDateTime openingDate;
    private OffsetDateTime finalDate;

    @OneToMany(mappedBy = "orderService")
    private List<Commentary> commentaries = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
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

    public List<Commentary> getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(List<Commentary> commentaries) {
        this.commentaries = commentaries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderService that = (OrderService) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean canBeFinalized() {
        return StatusOrderService.OPEN.equals(getStatus());
    }

    public boolean cantBeFinalized() {
        return !canBeFinalized();
    }

    public void finalizing() {

        if(cantBeFinalized()) {
            throw new BusinessException("Order service cannot be finalized");
        }

        setStatus(StatusOrderService.FINALIZED);
        setFinalDate(OffsetDateTime.now());
    }
}
