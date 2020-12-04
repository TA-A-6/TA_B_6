package apap.tugaskelompok.sirekrutmen.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apap.tugaskelompok.sirekrutmen.service.UserRestService;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {

	@Autowired
	UserRestService userRestService;
	

	/*your code goes here
	
	make sure routing-nya pake /user dulu depannya. e.g. /user/{id}.*/
	
	
}
