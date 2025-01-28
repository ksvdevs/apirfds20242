package codksv.apirfds20242.Service.Client;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import codksv.apirfds20242.Business.BusinessClient;
import codksv.apirfds20242.Dto.DtoClient;

import codksv.apirfds20242.Service.Client.RequestObject.RequestInsert;
import codksv.apirfds20242.Service.Client.ResponseObject.ResponseGetAll;
import codksv.apirfds20242.Service.Client.ResponseObject.ResponseInsert;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private BusinessClient businessClient;

	@GetMapping(path = "getall")
	public ResponseEntity<ResponseGetAll> getAll() {
		ResponseGetAll responseGetAll = new ResponseGetAll();

		try {
			List<DtoClient> listDtoClient = businessClient.getAll();

			for (DtoClient item : listDtoClient) {
				Map<String, Object> map = new HashMap<>();

				map.put("idClient", item.getIdClient());
				map.put("firstName", item.getFirstName());
				map.put("surName", item.getSurName());
				map.put("dni", item.getDni());
				map.put("birthDate ", item.getBirthDate());
				map.put("createdAt", item.getCreatedAt());
				map.put("updatedAt", item.getUpdatedAt());

				responseGetAll.dto.listClient.add(map);
				responseGetAll.mo.setSuccess();
			}
		} catch (Exception e) {
			responseGetAll.mo.addResponseMesssage("Ocurrió un error inesperado.");
			responseGetAll.mo.setException();
		}

		return new ResponseEntity<>(responseGetAll, HttpStatus.OK);
	}

	@PostMapping(path = "insert", consumes = "multipart/form-data")
	public ResponseEntity<ResponseInsert> insert(@Valid @ModelAttribute RequestInsert request,
			BindingResult bindingResult) {
		ResponseInsert response = new ResponseInsert();

		System.out.println(request.getSurName());
		System.out.println(request.getBirthDate());
		System.out.println(request.getPhone());
		System.out.println(request.isGender());

		try {
			if (bindingResult.hasErrors()) {
				bindingResult.getAllErrors().forEach(error -> {
					response.mo.addResponseMesssage(error.getDefaultMessage());
				});

				return new ResponseEntity<>(response, HttpStatus.OK);
			}

			DtoClient dtoClient = new DtoClient();
			dtoClient.setFirstName(request.getFirstName());
			dtoClient.setSurName(request.getSurName());
			dtoClient.setDni(request.getDni());
			dtoClient.setGender(request.isGender());
			dtoClient.setPhone(Integer.valueOf(request.getPhone()));
			dtoClient.setAddress(request.getAddress());
			dtoClient.setBirthDate(new SimpleDateFormat("yyyy-mm-dd").parse(request.getBirthDate()));
			dtoClient.setEmail(request.getEmail());
			System.out.println("sdsd");
			System.out.println(request.isGender());
			businessClient.insert(dtoClient);

			response.mo.addResponseMesssage("Registro realizado correctamente");
			response.mo.setSuccess();
		} catch (Exception e) {
			response.mo.addResponseMesssage(
					"Ocurrió un error inesperado, estamos trabajando para resolvero. Gracias por su paciencia.");
			response.mo.setException();
		}

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
