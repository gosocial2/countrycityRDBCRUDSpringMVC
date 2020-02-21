package net.ozar.gokhan.training.springboot.countrycityperson.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import net.ozar.gokhan.training.springboot.countrycityperson.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import net.ozar.gokhan.training.springboot.countrycityperson.repo.CountryRepository;

/**
 *
 * @author Ozar <gosocial2@ozar.net>
 */
@Controller
@RequestMapping("/country")
public class CountryWebController {

    @Autowired
    CountryRepository repo;

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryWebController.class);

    /*
    @RequestMapping("/countries")
    public String page(Model model) {
        model.addAttribute("attribute", "value");
        return "view.name";
    }
     */
    @ExceptionHandler({Exception.class})
    public String databaseError() {
        return "error";
    }

    @RequestMapping("/list")
    public ModelAndView getCountries() {
        ModelAndView mav = new ModelAndView("countryList");

        List<Country> countries = repo.findAll();

        mav.addObject("countries", countries);
        mav.addObject("pageTitle", new String("Country List"));
        return mav;
    }

    @RequestMapping(path = "/edit/{isoCode}", method = RequestMethod.GET)
    public String editCountry(Model model, @PathVariable(value = "isoCode") String isoCode) {
        LOGGER.info("Looking up country by isoCode (as ID): " + isoCode);
        // Country country = repo.getOne(isoCode);
        Optional<Country> foundCountry = null;
        try {
            foundCountry = repo.findById(isoCode);
        } catch (Exception e) {
            LOGGER.error("Some error occurred while looking up country: \n" + e.getMessage());
        }
        Country country;
        if (foundCountry == null || !foundCountry.isPresent()) {
            System.out.println("No country with isoCode " + isoCode);
            LOGGER.info("No country with ID: " + isoCode);
            country = new Country();
        } else {
            country = foundCountry.get();
        }
        if (country != null) {
            System.out.println("Found country : " + country);
            LOGGER.info("Found country : " + country);
        } else {
            System.out.println("country is null !!!!");
            LOGGER.error("country is null !!!!");
            country = new Country();
        }
        model.addAttribute("country", country);
        return "editCountry";
    }

    @PostMapping("update/{isoCode}")
    public String updateCountry(@PathVariable("isoCode") String isoCode, @Valid Country country, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            country.setIsoCode(isoCode);
            return "editCountry";
        }

        repo.save(country);

        return "redirect:/country/list";
    }

    @GetMapping("delete/{id}")
    public String deleteCountry(@PathVariable("id") String id, Model model) {
        /*
        Country country = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid country Id:" + id));
        repo.delete(country);        
                */
        repo.deleteById(id);
        return "redirect:/country/list";
    }

    @PostMapping("create")
    public String createCountry(@Valid Country country, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "newCountry";
        }
        repo.save(country);
        return "redirect:/country/list";
    }
    
    @GetMapping("new")
    public String newCountry(Model model) {
        model.addAttribute("country", new Country());
        return "newCountry";
    }

}
