package ru.shalya.soashopservice.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import ru.shalya.soashopservice.dtos.TotalCountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import ru.shalya.soashopservice.services.ShopService;

@RestController
@RequestMapping("/shop")
public class ShopController {
    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping(value = "/search/by-number-of-wheels/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TotalCountDto> getByWheelsNumber(@PathVariable("from") int from,
                                                           @PathVariable("to") int to){
        TotalCountDto entity = shopService.getByWheelsNumber(from,to);
        return ResponseEntity.ok(entity);
    }

    @PostMapping(value = "/search/fix-distance/{id}")
    public ResponseEntity<String> getByWheelsNumber(@PathVariable("id") Long id){
        try {
            HttpStatus httpStatus = shopService.fixDistance(id);
            return ResponseEntity.ok("OK");
        }catch (HttpClientErrorException e){
            return new ResponseEntity<>("Vehicle с таким ID не существует",HttpStatus.NOT_FOUND);
        }catch (ResourceAccessException e){
            e.printStackTrace();
            return new ResponseEntity<>("Сервис машин недоступен, попробуйте позже!",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/d")
    public String aa(){
        return "AAAA";
    }
}
