package apap.tugaskelompok.sirekrutmen.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;

//import apap.tugaskelompok.sirekrutmen.rest.BaseResponse;
//import apap.tugaskelompok.sirekrutmen.rest.PegawaiDetail;
//import apap.tugaskelompok.sirekrutmen.rest.ResponseStatus;
import apap.tugaskelompok.sirekrutmen.service.UserRestService;
//import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {

	@Autowired
	UserRestService userRestService;
	
	
	
}
