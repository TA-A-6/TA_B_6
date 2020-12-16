package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.rest.PelatihanDetail;
import reactor.core.publisher.Mono;

public interface LamaranRestService {

	Mono<String> postLamaran(PelatihanDetail pelatihanDetail);

}
