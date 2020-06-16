package com.acs.mercadopaginho.mercadopaginhobackend;

import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;
import com.acs.mercadopaginho.mercadopaginhobackend.user.persistence.model.User;

public class DummyModelRepository {

    public static User getIronMan() {
        String name = "Anthony";
        String surname = "Stark";
        return new User(name, surname);
    }

    public static User getCaptainAmerica() {
        String name = "Steve";
        String surname = "Rogers";
        return new User(name, surname);
    }

    public static Address getIronManAddress() {
        int height = 10880;
        String streetName = "Malibu Point";
        String cityName = "Point Dume";
        String province = "California";
        int postalCode = 1256;
        return new Address(height, streetName, cityName, province, postalCode);
    }

    public static Address getCaptainAmericaAddress() {
        int height = 569;
        String streetName = "Leaman Place";
        String cityName = "Brooklyn";
        String province = "NY";
        int postalCode = 1064;
        return new Address(height, streetName, cityName, province, postalCode);
    }

    public static User getUser(){
        String name = "Random";
        String surname = "User";
        return new User(name, surname);
    }

    public static Address getAddress() {
        int height = 999;
        String streetName = "Street Name";
        String cityName = "City Name";
        String province = "PR";
        int postalCode = 9999;
        return new Address(height, streetName, cityName, province, postalCode);
    }

}
