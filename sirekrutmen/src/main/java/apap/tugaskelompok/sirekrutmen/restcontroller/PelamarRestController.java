package apap.tugaskelompok.sirekrutmen.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apap.tugaskelompok.sirekrutmen.service.PelamarRestService;

@RestController
@RequestMapping("/api/v1")
public class PelamarRestController {

	@Autowired
	PelamarRestService pelamarRestService;
	
	/*your code goes here
	
	make sure routing-nya pake /pelamar dulu depannya. e.g. /pelamar/{id}.*/
	
}
