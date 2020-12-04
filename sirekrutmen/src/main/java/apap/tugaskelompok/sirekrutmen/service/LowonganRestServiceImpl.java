package apap.tugaskelompok.sirekrutmen.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LowonganRestServiceImpl implements LowonganRestService{

	@Override
	public String aFunction() {
		return "der";
	}
	

//	jangan lupa bikin dulu kerangka functionnya di interfacenya yaa
	
}
