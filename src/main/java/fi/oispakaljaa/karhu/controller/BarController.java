package fi.oispakaljaa.karhu.controller;

import fi.oispakaljaa.karhu.APItemplates.google.OispakaljaaTemplate;
import fi.oispakaljaa.karhu.domain.Bar;
import fi.oispakaljaa.karhu.repository.BarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/bars")
public class BarController {

    @Autowired
    BarRepository barRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<OispakaljaaTemplate> list() {
        return new ResponseEntity<>(new OispakaljaaTemplate("OK", "", barRepository.findAll()), HttpStatus.OK);
    }

    @RequestMapping(value = "/{barId}", method = RequestMethod.GET)
    public ResponseEntity<OispakaljaaTemplate> getBar(@PathVariable("barId") Long id) {
        Bar b = barRepository.findOne(id);
        if (b == null)
            return new ResponseEntity<>(new OispakaljaaTemplate("Error", "Bar not found", null), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new OispakaljaaTemplate("OK", "", b), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<OispakaljaaTemplate> postBar(@RequestBody Bar bar) {
        return new ResponseEntity<OispakaljaaTemplate>(new OispakaljaaTemplate("OK", "", barRepository.save(bar)), HttpStatus.OK);
    }

    @RequestMapping(value = "/{barId}", method = RequestMethod.DELETE)
    public ResponseEntity<OispakaljaaTemplate> deleteBar(@PathVariable("barId") Long id) {
        Bar bar = barRepository.findOne(id);
        if (bar == null)
            return new ResponseEntity<>(new OispakaljaaTemplate("Error", "Bar not found", null), HttpStatus.NOT_FOUND);

        barRepository.delete(bar);
        return new ResponseEntity<OispakaljaaTemplate>(new OispakaljaaTemplate("OK", "", bar), HttpStatus.OK);

        // se täytyis varmistaa, että jos baari poistetaan niin myös kaikki kyseisen baarin juomat poistetaan
    }

}
