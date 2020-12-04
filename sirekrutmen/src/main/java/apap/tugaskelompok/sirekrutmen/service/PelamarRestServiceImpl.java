package apap.tugaskelompok.sirekrutmen.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PelamarRestServiceImpl implements PelamarRestService{

	@Override
	public String aFunction() {
		return "dor";
	}


//	jangan lupa bikin dulu kerangka functionnya di interfacenya yaa
	
}
