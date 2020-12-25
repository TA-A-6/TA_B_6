package apap.tugaskelompok.sirekrutmen.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import apap.tugaskelompok.sirekrutmen.rest.BaseResponse;
import apap.tugaskelompok.sirekrutmen.rest.PegawaiDetail;
import apap.tugaskelompok.sirekrutmen.rest.GajiBaseResponse;
import apap.tugaskelompok.sirekrutmen.rest.PegawaiBaseResponse;

@Service
@Transactional
public class UserRestServiceImpl implements UserRestService{

	@Override
	public BaseResponse postUserToSipegawai(PegawaiDetail pegawai) {

		WebClient webClient = WebClient.builder()
				.baseUrl("https://si-pegawai.herokuapp.com")
				.build();
		
		return webClient.post().uri("/api/v1/pegawai")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(pegawai)
				.retrieve()
				.bodyToMono(BaseResponse.class).block();
		
		
	}

	@Override
	public PegawaiBaseResponse getDataPegawai(String userName) {
		String endPoint = "https://si-pegawai.herokuapp.com/api/v1/pegawai/" + userName;
		WebClient webClient = WebClient.builder().baseUrl(endPoint).build();
		return webClient.get()
				.uri(uriBuilder -> uriBuilder.build())
				.retrieve()
				.bodyToMono(PegawaiBaseResponse.class).block();
	}

    @Override
    public GajiBaseResponse[] getStatistikGaji() {
        String endPoint = "https://sipayroll-b5.herokuapp.com/api/v1/karyawan-lama";
        WebClient webClient = WebClient.builder().baseUrl(endPoint).build();

        return webClient.get()
				.uri(uriBuilder -> uriBuilder.build())
				.retrieve()
				.bodyToMono(GajiBaseResponse[].class).block();
    }

}
