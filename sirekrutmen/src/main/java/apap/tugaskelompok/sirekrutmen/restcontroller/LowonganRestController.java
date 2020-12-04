package apap.tugaskelompok.sirekrutmen.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apap.tugaskelompok.sirekrutmen.service.LowonganRestService;

@RestController
@RequestMapping("/api/v1")
public class LowonganRestController {
	
	@Autowired
	LowonganRestService lowonganRestService;
	
	/*your code goes here
	
	make sure routing-nya pake /lowongan dulu depannya. e.g. /lowongan/{id}.*/
	
	
}
