package apap.tugaskelompok.sirekrutmen.service;

import java.sql.Date;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import apap.tugaskelompok.sirekrutmen.rest.BaseResponse;
import apap.tugaskelompok.sirekrutmen.rest.PegawaiDetail;

@Service
@Transactional
public class UserRestServiceImpl implements UserRestService{

	@SuppressWarnings("rawtypes")
	@Override
	public BaseResponse postUserToSipegawai(PegawaiDetail pegawai) {
//		PegawaiDetail data = new PegawaiDetail();
//		
//		data.setUsername("riantesssssssss1231421");
//		data.setNama("rianadr");
//		data.setNoTelepon("09999");		
//		data.setTempatLahir("Bogor");		
//		data.setTanggalLahir(Date.valueOf("2020-1-1"));
//		data.setAlamat("di boror");
//		data.setRole(1);
		
		
		WebClient webClient = WebClient.builder()
				.baseUrl("https://si-pegawai.herokuapp.com")
				.build();
		
		return webClient.post().uri("/api/v1/pegawai")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(pegawai)
				.retrieve()
				.bodyToMono(BaseResponse.class).block();
		
		
	}


}
