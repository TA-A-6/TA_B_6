package apap.tugaskelompok.sirekrutmen.service;

import apap.tugaskelompok.sirekrutmen.rest.GajiBaseResponse;
import apap.tugaskelompok.sirekrutmen.rest.PegawaiBaseResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Transactional
public class UserRestServiceImpl implements UserRestService{

	@Override
	public String aFunction() {
		return "dar";
	}

	@Override
	public PegawaiBaseResponse getDataPegawai(String userName) {
		String endPoint = "https://si-pegawai.herokuapp.com/api/v1/pegawai/"+userName;
		WebClient webClient = WebClient.builder().baseUrl(endPoint).build();
		return webClient.get()
				.uri(uriBuilder -> uriBuilder.build())
				.retrieve()
				.bodyToMono(PegawaiBaseResponse.class).block();
	}

    @Override
    public GajiBaseResponse[] getStatistikGaji() {
        String endPoint = "https://c3d534ab-df50-4c48-9057-1287a34b5821.mock.pstmn.io/api/v1/karyawan-lama";
        WebClient webClient = WebClient.builder().baseUrl(endPoint).build();

        GajiBaseResponse[] bb = webClient.get()
				.uri(uriBuilder -> uriBuilder.build())
				.retrieve()
				.bodyToMono(GajiBaseResponse[].class).block();
//        BaseResponse a = webClient.get()
//                .uri(uriBuilder -> uriBuilder.build())
//                .retrieve()
//                .bodyToMono(BaseResponse.class).block();
//        System.out.println(a);

        return bb;
    }

    //	jangan lupa bikin dulu kerangka functionnya di interfacenya yaa

}
