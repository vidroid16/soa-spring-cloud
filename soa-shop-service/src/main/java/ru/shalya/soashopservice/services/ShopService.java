package ru.shalya.soashopservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import ru.shalya.soashopservice.config.RestTemplateConfig;
import ru.shalya.soashopservice.dtos.TotalCountDto;
import ru.shalya.soashopservice.dtos.VehicleDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShopService {

    @Value("${vehicles-url}")
    private String vehiclesUrl;
    private final RestTemplate restTemplate;

    @Autowired
    public ShopService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    public TotalCountDto getByWheelsNumber(int from, int to){
        ResponseEntity<TotalCountDto> responseEntity =
                restTemplate.exchange(
                        vehiclesUrl+"/vehicles",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<TotalCountDto>() {}
                );

        List<VehicleDto> allVehicles = responseEntity.getBody().getVehicles();
        List<VehicleDto> result = new ArrayList<>();

        allVehicles.forEach(v->{
            if((v.getWheelsNumber()<=to) && (v.getWheelsNumber()>=from))
                result.add(v);
        });
        TotalCountDto resultTotalCount = new TotalCountDto();
        resultTotalCount.setVehicles(result);
        resultTotalCount.setTotalCount(responseEntity.getBody().getTotalCount());
        return resultTotalCount;
    }

    public HttpStatus fixDistance(Long id){
        ResponseEntity<VehicleDto> responseEntity =
                restTemplate.exchange(
                        vehiclesUrl+"/vehicles/"+id,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<VehicleDto>() {}
                );
        VehicleDto fixedVehicle = responseEntity.getBody();
        fixedVehicle.setMileage(0);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<VehicleDto> request = new HttpEntity<VehicleDto>(fixedVehicle, headers);

        ResponseEntity<VehicleDto> responseEntityResult =
                restTemplate.exchange(
                        vehiclesUrl+"/vehicles/"+id,
                        HttpMethod.PUT,
                        request,
                        new ParameterizedTypeReference<VehicleDto>() {}
                );
        return responseEntityResult.getStatusCode();
    }
}
