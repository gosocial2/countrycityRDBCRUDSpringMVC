/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ozar.gokhan.training.springboot.countrycityperson.repo;

import net.ozar.gokhan.training.springboot.countrycityperson.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ozar <gosocial2@ozar.net>
 */
@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    
    int deleteByCityName(String cityName);
    
}
