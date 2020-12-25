package apap.tugaskelompok.sirekrutmen.restcontroller;

import apap.tugaskelompok.sirekrutmen.model.JenisLowonganModel;
import apap.tugaskelompok.sirekrutmen.model.LowonganModel;
import apap.tugaskelompok.sirekrutmen.model.UserModel;
import apap.tugaskelompok.sirekrutmen.rest.BaseResponse;
import apap.tugaskelompok.sirekrutmen.rest.LowonganDto;
import apap.tugaskelompok.sirekrutmen.service.JenisLowonganService;
import apap.tugaskelompok.sirekrutmen.service.LowonganService;
import apap.tugaskelompok.sirekrutmen.service.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.modelmapper.ModelMapper;
import org.springframework.expression.ParseException;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.beans.BeanProperty;
import java.util.List;
import java.util.NoSuchElementException;
import apap.tugaskelompok.sirekrutmen.service.LowonganRestService;

@RestController
@RequestMapping("/api/v1")
public class LowonganRestController {

	@Autowired
	private LowonganRestService lowonganRestService;

	@Autowired
	private LowonganService lowonganService;

	@Autowired
	private UserService userService;

	@Autowired
	private JenisLowonganService jenisLowonganService;

	@Autowired
	private ModelMapper modelMapper;

	private LowonganModel convertToEntity(LowonganDto lowonganDto) throws ParseException {
		LowonganModel lowonganModel = modelMapper.map(lowonganDto, LowonganModel.class);
		return lowonganModel;
	}

	private Long convertToId(LowonganDto lowonganDto) throws ParseException {
		return lowonganDto.getJenisLowongan();
	}

	@PostMapping(value = "/lowongan/add")
	private BaseResponse<LowonganModel> createLowongan(@Valid @RequestBody LowonganDto lowongan, BindingResult bindingResult){
		BaseResponse<LowonganModel> response;
		response = new BaseResponse<>();

		if(bindingResult.hasFieldErrors()){
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
			);
		}
		else {

			LowonganModel lowonganModel = convertToEntity(lowongan);

			lowonganModel.setKodeLowongan(lowonganService.getKodeRest(lowonganModel, convertToId(lowongan)));

			lowonganModel.setUser(userService.getUserByUsername("payroll"));

			lowonganModel.setJenisLowongan(jenisLowonganService.getJenisLowonganById(convertToId(lowongan)));

			/**List<UserModel> listUser = userService.findAll();
			 *
			lowonganModel.setUser(listUser.get(0));*/

			lowonganModel = lowonganRestService.createLowongan(lowonganModel);
			response.setStatus(200);
			response.setMessage("success");
			response.setResult(lowonganModel);
			return response;
		}
	}
	
	
}
