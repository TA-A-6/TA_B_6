package apap.tugaskelompok.sirekrutmen.service;


import apap.tugaskelompok.sirekrutmen.model.LamaranModel;
import apap.tugaskelompok.sirekrutmen.repository.LamaranDb;
import apap.tugaskelompok.sirekrutmen.rest.PelatihanResponse;
import org.springframework.beans.factory.annotation.Autowired;

import apap.tugaskelompok.sirekrutmen.rest.PelatihanDetail;
import apap.tugaskelompok.sirekrutmen.rest.Setting;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.sql.Date;

import java.util.List;

@Service
@Transactional
public class LamaranRestServiceImpl implements LamaranRestService{

	@Autowired
	LamaranDb lamaranDb;

	private final WebClient webClient;

	public LamaranRestServiceImpl(WebClient.Builder webClientBuilder){
		this.webClient = webClientBuilder.baseUrl(Setting.pelatihanUrl).build();
	}

	@Override
	public Mono<PelatihanResponse> postLamaran(PelatihanDetail pelatihanDetail){
		return this.webClient.post().uri("/api/v1/pelatihan/tambah")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(pelatihanDetail)
				.retrieve()
				.bodyToMono(PelatihanResponse.class);
	}

	@Override
	public List<LamaranModel> getStatus(int status){
		return lamaranDb.findAllByStatus(status);
	}


}
