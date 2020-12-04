package apap.tugaskelompok.sirekrutmen.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LamaranRestServiceImpl implements LamaranRestService{

	@Override
	public String aFunction() {
		return "dur";
	}
	
//	jangan lupa bikin dulu kerangka functionnya di interfacenya yaa

}
