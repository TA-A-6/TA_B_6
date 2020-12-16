package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.model.LamaranModel;

import java.util.List;

public interface LamaranRestService {
	
	String aFunction();

	List<LamaranModel> getStatus(int status);

}
