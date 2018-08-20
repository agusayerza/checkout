package com.acs.mercadopaginho.mercadopaginhobackend.sale.persistence.model;

import com.acs.mercadopaginho.mercadopaginhobackend.checkout.persistence.model.CheckOut;
import com.acs.mercadopaginho.mercadopaginhobackend.user.persistence.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Access(AccessType.FIELD)
public class Sale {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(targetEntity = User.class)
    private User user;
    @OneToOne(targetEntity = CheckOut.class)
    private CheckOut checkOut;
    private LocalDateTime localDateTime;

    public Sale() {
    }

    public Sale(User user, CheckOut checkOut) {
        this.user = user;
        this.checkOut = checkOut;
        this.localDateTime = LocalDateTime.now();
    }

    public User getUser() {
        return user;
    }

    public CheckOut getCheckOut() {
        return checkOut;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public Long getId() {
        return id;
    }
}
