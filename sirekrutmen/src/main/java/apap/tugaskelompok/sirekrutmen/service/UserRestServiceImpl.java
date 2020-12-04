package apap.tugaskelompok.sirekrutmen.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserRestServiceImpl implements UserRestService{

	@Override
	public String aFunction() {
		return "dar";
	}

//	jangan lupa bikin dulu kerangka functionnya di interfacenya yaa

}
