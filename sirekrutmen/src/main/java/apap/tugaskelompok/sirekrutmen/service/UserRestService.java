package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.rest.BaseResponse;
//import apap.tugaskelompok.sirekrutmen.rest.PegawaiDetail;
//import apap.tugaskelompok.sirekrutmen.rest.ResponseStatus;
//import reactor.core.publisher.Mono;
import apap.tugaskelompok.sirekrutmen.rest.PegawaiDetail;

public interface UserRestService {
	
	BaseResponse postUserToSipegawai(PegawaiDetail pegawai);
	
}
