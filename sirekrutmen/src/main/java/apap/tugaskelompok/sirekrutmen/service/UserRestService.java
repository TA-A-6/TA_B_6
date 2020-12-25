package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.rest.BaseResponse;
import apap.tugaskelompok.sirekrutmen.rest.PegawaiDetail;

public interface UserRestService {
	
BaseResponse postUserToSipegawai(PegawaiDetail pegawai);
import apap.tugaskelompok.sirekrutmen.rest.GajiBaseResponse;
import apap.tugaskelompok.sirekrutmen.rest.PegawaiBaseResponse;

public interface UserRestService {
	
	String aFunction();
	PegawaiBaseResponse getDataPegawai(String userName);
	GajiBaseResponse[] getStatistikGaji();
	
}
