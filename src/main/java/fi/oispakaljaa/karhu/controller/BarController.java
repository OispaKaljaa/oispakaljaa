package fi.oispakaljaa.karhu.controller;

import fi.oispakaljaa.karhu.domain.Bar;
import fi.oispakaljaa.karhu.repository.BarRepository;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "bars")
public class BarController {

    @Autowired
    BarRepository barRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Bar> list() {
        return barRepository.findAll();
    }

    @RequestMapping(value = "/{barId}", method = RequestMethod.GET)
    public Bar getBar(@PathVariable("barId") Long id) {
        return barRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Bar postBar(@RequestBody Bar bar) {
        return barRepository.save(bar);
    }

    @RequestMapping(value = "/{barId}", method = RequestMethod.DELETE)
    public Bar deleteBar(@PathVariable("barId") Long id) {
        Bar bar = barRepository.findOne(id);
        barRepository.delete(bar);
        return bar;
        
        // se täytyis varmistaa, että jos baari poistetaan niin myös kaikki kyseisen baarin juomat poistetaan
    }

}
