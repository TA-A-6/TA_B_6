package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.rest.PelatihanDetail;
import apap.tugaskelompok.sirekrutmen.rest.Setting;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.sql.Date;

@Service
@Transactional
public class LamaranRestServiceImpl implements LamaranRestService{
	private final WebClient webClient;

	public LamaranRestServiceImpl(WebClient.Builder webClientBuilder){
		this.webClient = webClientBuilder.baseUrl(Setting.pelatihanUrl).build();
	}

	@Override
	public Mono<String> postLamaran(PelatihanDetail pelatihanDetail){
		return this.webClient.post().uri("/api/v1/pelatihan/tambah")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(pelatihanDetail)
				.retrieve()
				.bodyToMono(String.class);
	}

}
