package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.model.LamaranModel;
import apap.tugaskelompok.sirekrutmen.repository.LamaranDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LamaranRestServiceImpl implements LamaranRestService{
	@Autowired
	LamaranDb lamaranDb;

	@Override
	public String aFunction() {
		return "dur";
	}

	@Override
	public List<LamaranModel> getStatus(int status){
		return lamaranDb.findAllByStatus(status);
	}

}
