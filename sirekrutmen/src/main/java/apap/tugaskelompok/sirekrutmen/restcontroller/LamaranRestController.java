package apap.tugaskelompok.sirekrutmen.restcontroller;

import apap.tugaskelompok.sirekrutmen.rest.PelatihanDetail;
import apap.tugaskelompok.sirekrutmen.rest.PelatihanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import apap.tugaskelompok.sirekrutmen.service.LamaranRestService;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v1")
public class LamaranRestController {

	@Autowired
	LamaranRestService lamaranRestService;


	@GetMapping(value = "/pelatihan/dari/lamaran")
	private Mono<PelatihanResponse> lamaranPelatihan(PelatihanDetail pelatihanDetail){
		return lamaranRestService.postLamaran(pelatihanDetail);
	}

	
}
