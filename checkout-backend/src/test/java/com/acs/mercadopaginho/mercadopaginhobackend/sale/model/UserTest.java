package com.acs.mercadopaginho.mercadopaginhobackend.sale.model;

import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;
import com.acs.mercadopaginho.mercadopaginhobackend.user.persistence.model.User;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.assertj.core.api.Assertions.*;

class UserTest {

    @Test
    public void test_001_UserIsCreatedProperly() {
        String name = "Anthony";
        String surname = "Stark";
        User user = new User(name, surname);
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getSurname()).isEqualTo(surname);
    }

    @Test
    public void test_002_UserNameHasToBeAlphabetic() {
        String name = "Anthony01";
        String surname = "Stark";
        assertThatThrownBy(() -> {
            User user = new User(name, surname);
        }).isInstanceOf(InvalidParameterException.class)
          .hasMessage("User name must be alphabetic");
    }

    @Test
    public void test_003_UserSurnameHasToBeAlphabetic() {
        String name = "Anthony";
        String surname = "Stark01";
        assertThatThrownBy(() -> {
            User user = new User(name, surname);
        }).isInstanceOf(InvalidParameterException.class)
          .hasMessage("User surname must be alphabetic");
    }

    @Test
    public void test_004_WhenYouAddAnAddressToAUserNowTheyHaveIt() {
        String name = "Anthony";
        String surname = "Stark";
        User user = new User(name, surname);

        assertThat(user.getAddresses()).isEmpty();

        int height = 50;
        String streetName = "Balcarce";
        String cityName = "Montserrat";
        String province = "Capital Federal";
        int postalCode = 1064;
        Address address = new Address(height, streetName, cityName, province, postalCode);

        user.addAddress(address);

        assertThat(user.getAddresses()).isNotEmpty().containsOnly(address);
    }


}