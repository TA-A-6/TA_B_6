package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.rest.BaseResponse;
import apap.tugaskelompok.sirekrutmen.rest.GajiBaseResponse;
import apap.tugaskelompok.sirekrutmen.rest.PegawaiBaseResponse;
import apap.tugaskelompok.sirekrutmen.rest.PegawaiDetail;


public interface UserRestService {
	
	PegawaiBaseResponse getDataPegawai(String userName);
	GajiBaseResponse[] getStatistikGaji();
	BaseResponse postUserToSipegawai(PegawaiDetail pegawai) ;
	
}
