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
import net.ozar.gokhan.training.springboot.countrycityperson.model.City;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import net.ozar.gokhan.training.springboot.countrycityperson.repo.CityRepository;

/**
 *
 * @author Ozar <gosocial2@ozar.net>
 */
@RestController
@RequestMapping("/city-api")
public class CityRestController {
    
    @Autowired
    CityRepository repo;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CityRestController.class);
    
   
    @RequestMapping(value="/cities/", method = RequestMethod.GET)
    public List<City> list() {
        List<City> cList = repo.findAll();
        cList.forEach((c) -> {
            System.out.println("City : "+c);
        });
        return cList;
    }
    
    // @GetMapping("/{id}")
    @RequestMapping(value="/cities/{id}", method = RequestMethod.GET)
    public City get(@PathVariable("id") Integer id) {
        LOGGER.info("Looking up city by id (as ID): "+id);
        Optional<City> foundCity = null;
        try {
            foundCity = repo.findById(id);
        } catch (Exception e) {
            LOGGER.error("Some error occurred while looking up city: \n"+e.getMessage());
        }
        City result;
        if (foundCity == null || !foundCity.isPresent()) {
            System.out.println("No city with id "+id);
            LOGGER.info("No city with ID: "+id);
            result = new City();
        } else {
            result = foundCity.get();
        }
        return result;
    }
    /*
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody City input) {
        return null;
    }
    */
    @RequestMapping(value="/cities/", method = RequestMethod.PUT)
    public City updateCity(@RequestBody City city) {
        return repo.save(city);
    }
    
    // @PostMapping
    // public ResponseEntity<?> post(@RequestBody City input) {
    @RequestMapping(value="/cities/", method = RequestMethod.POST)
    public City createProduct(@RequestBody City city) {
        City savedResult = repo.save(city);
        return savedResult;
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public Object handleError(HttpServletRequest req, Exception ex) {
        Object errorObject = new Object();
        return errorObject;
    }
    
}
