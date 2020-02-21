/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ozar.gokhan.training.springboot.countrycityperson.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import javax.servlet.http.HttpServletRequest;
import net.ozar.gokhan.training.springboot.countrycityperson.model.Country;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import net.ozar.gokhan.training.springboot.countrycityperson.repo.CountryRepository;

/**
 *
 * @author Ozar <gosocial2@ozar.net>
 */
@RestController
@RequestMapping("/country-api")
public class CountryRestController {
    
    @Autowired
    CountryRepository repo;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryRestController.class);
    
    // @GetMapping()
    @RequestMapping(value="/countries/", method = RequestMethod.GET)
    public List<Country> list() {
        List<Country> cList = repo.findAll();
        for(Country c : cList) {
            System.out.println("Country : "+c);
        }
        return cList;
    }
    
    // @GetMapping("/{id}")
    @RequestMapping(value="/countries/{isoCode}", method = RequestMethod.GET)
    public Country get(@PathVariable("isoCode") String isoCode) {
        LOGGER.info("Looking up country by isoCode (as ID): "+isoCode);
        Optional<Country> foundCountry = null;
        try {
            foundCountry = repo.findById(isoCode);
        } catch (Exception e) {
            LOGGER.error("Some error occurred while looking up country: \n"+e.getMessage());
        }
        Country result;
        if (foundCountry == null || !foundCountry.isPresent()) {
            System.out.println("No country with isoCode "+isoCode);
            LOGGER.info("No country with ID: "+isoCode);
            result = new Country();
        } else {
            result = foundCountry.get();
        }
        return result;
    }
    /*
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Country input) {
        return null;
    }
    */
    @RequestMapping(value="/countries/", method = RequestMethod.PUT)
    public Country updateCountry(@RequestBody Country country) {
        return repo.save(country);
    }
    
    // @PostMapping
    // public ResponseEntity<?> post(@RequestBody Country input) {
    @RequestMapping(value="/countries/", method = RequestMethod.POST)
    public Country createProduct(@RequestBody Country country) {
        Country savedResult = repo.save(country);
        return savedResult;
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String isoCode) {
        return null;
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public Object handleError(HttpServletRequest req, Exception ex) {
        Object errorObject = new Object();
        return errorObject;
    }
    
}
