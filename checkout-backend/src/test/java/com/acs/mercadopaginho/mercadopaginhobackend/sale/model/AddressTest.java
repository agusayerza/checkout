package com.acs.mercadopaginho.mercadopaginhobackend.sale.model;

import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AddressTest {


    @Test
    public void test_001_AddressIsCreatedProperly() {
        int height = 50;
        String streetName = "Balcarce";
        String cityName = "Montserrat";
        String province = "Capital Federal";
        int postalCode = 1064;
        Address address = new Address(height, streetName, cityName, province, postalCode);
        assertThat(address.getHeight()).isEqualTo(height);
        assertThat(address.getStreetName()).isEqualTo(streetName);
        assertThat(address.getCityName()).isEqualTo(cityName);
        assertThat(address.getProvince()).isEqualTo(province);
        assertThat(address.getPostalCode()).isEqualTo(postalCode);
    }

    @Test
    public void test_002_HeightMustBeNonNegative() {
        int height = -50;
        String streetName = "Balcarce";
        String cityName = "Montserrat";
        String province = "Capital Federal";
        int postalCode = 1064;
        assertThatThrownBy(() -> {
            Address address = new Address(height, streetName, cityName, province, postalCode);
        }).isInstanceOf(InvalidParameterException.class)
          .hasMessage("Address has to be equal or larger than 0");
    }

    @Test
    public void test_003_PostalCodeLengthMustBe4AndPositive() {
        int height = 50;
        String streetName = "Balcarce";
        String cityName = "Montserrat";
        String province = "Capital Federal";
        int extraLengthPostalCode = 106434;
        int missingLengthPostalCode = 106434;
        int negativePostalCode = -1064;


        assertThatThrownBy(() -> {
            Address address = new Address(height, streetName, cityName, province, extraLengthPostalCode);
        }).isInstanceOf(InvalidParameterException.class)
          .hasMessage("Postal code has to be 4 digits long and positive");

        assertThatThrownBy(() -> {
            Address address = new Address(height, streetName, cityName, province, missingLengthPostalCode);
        }).isInstanceOf(InvalidParameterException.class)
          .hasMessage("Postal code has to be 4 digits long and positive");

        assertThatThrownBy(() -> {
            Address address = new Address(height, streetName, cityName, province, negativePostalCode);
        }).isInstanceOf(InvalidParameterException.class)
          .hasMessage("Postal code has to be 4 digits long and positive");
    }

}