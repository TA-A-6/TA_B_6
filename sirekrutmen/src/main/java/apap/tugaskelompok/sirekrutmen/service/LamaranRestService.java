package apap.tugaskelompok.sirekrutmen.service;
import apap.tugaskelompok.sirekrutmen.model.LamaranModel;

import java.util.List;

import apap.tugaskelompok.sirekrutmen.rest.PelatihanDetail;
import apap.tugaskelompok.sirekrutmen.rest.PelatihanResponse;
import reactor.core.publisher.Mono;


public interface LamaranRestService {

	Mono<String> postLamaran(PelatihanDetail pelatihanDetail);

	List<LamaranModel> getStatus(int status);

}
