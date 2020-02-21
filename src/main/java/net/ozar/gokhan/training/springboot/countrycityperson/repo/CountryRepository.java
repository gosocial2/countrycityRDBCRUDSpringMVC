/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ozar.gokhan.training.springboot.countrycityperson.repo;

import java.util.List;
import net.ozar.gokhan.training.springboot.countrycityperson.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ozar <gosocial2@ozar.net>
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    
    //Override CrudRepository or PagingAndSortingRepository's query method:
    @Override
    @Query("select e from #{#entityName} e where e.deleted=false")
    public List<Country> findAll();

    //Look up deleted entities
    @Query("select e from #{#entityName} e where e.deleted=true")
    public List<Country> recycleBin(); 
    
    //Soft delete.
    @Override
    @Query("update #{#entityName} e set e.deleted=true where e.isoCode=?1")
    @Modifying
    public void deleteById(String isoCode); 
}
