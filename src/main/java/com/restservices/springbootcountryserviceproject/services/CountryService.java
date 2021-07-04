package com.restservices.springbootcountryserviceproject.services;

import com.restservices.springbootcountryserviceproject.beans.Country;
import com.restservices.springbootcountryserviceproject.controllers.AddResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CountryService {

    static HashMap<Integer, Country> countryIdMap;

    public CountryService() {
        countryIdMap = new HashMap<Integer, Country>();

        Country canadaCountry = new Country(1, "Canada", "Ottawa");
        Country usaCountry = new Country(2, "USA", "Washington DC");
        Country ukCountry = new Country(3, "UK", "London");

        countryIdMap.put(1, canadaCountry);
        countryIdMap.put(2, usaCountry);
        countryIdMap.put(3, ukCountry);
    }

    // method to get all countries
    public List getAllCountries() {
        List countries = new ArrayList(countryIdMap.values());
        return countries;
    }

    // method to get country id
    public Country getCountryId(int id) {
        Country country = countryIdMap.get(id);
        return country;
    }

    // method to get country by name
    public Country getCountryByName(String countryName) {
        Country country = null;
        for (int i:countryIdMap.keySet()) {
            if (countryIdMap.get(i).getCountryName().equals(countryName)) {
                country = countryIdMap.get(i);
            }
        } return country;
    }

    // method to add a new country
    public Country addCountry(Country country) {
        country.setId(getMaxId());
        countryIdMap.put(country.getId(), country);
        return country;
    }

    // utility method to get max id
    public static int getMaxId() {
        int max = 0;
        for (int id:countryIdMap.keySet()) {
            if (max <= id) {
                max = id;
            }
        } return max + 1;
    }

    // method to update existing country
    public Country updateCountry(Country country) {
        if (country.getId() > 0) {
            countryIdMap.put(country.getId(), country);
        } return country;
    }

    // method to delete existing country
    public AddResponse deleteCountry(int id) {
        countryIdMap.remove(id);
        AddResponse response = new AddResponse();
        response.setMessage("Country deleted");
        response.setId(id);
        return response;
    }
}
