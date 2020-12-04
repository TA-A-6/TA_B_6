package apap.tugaskelompok.sirekrutmen.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apap.tugaskelompok.sirekrutmen.service.LamaranRestService;

@RestController
@RequestMapping("/api/v1")
public class LamaranRestController {

	@Autowired
	LamaranRestService lamaranRestService;

	/*your code goes here
	
	make sure routing-nya pake /lamaran dulu depannya. e.g. /lamaran/{id}.*/
	
}
