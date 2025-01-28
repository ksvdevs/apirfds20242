package codksv.apirfds20242.Service.User;

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

import codksv.apirfds20242.Business.BusinessUser;
import codksv.apirfds20242.Dto.DtoUser;
import codksv.apirfds20242.Service.User.RequestObject.RequestLogin;
import codksv.apirfds20242.Service.User.RequestObject.ResquestInsert;
import codksv.apirfds20242.Service.User.ResponseObject.ResponseGetAll;
import codksv.apirfds20242.Service.User.ResponseObject.ResponseInsert;
import codksv.apirfds20242.Service.User.ResponseObject.ResponseLogin;
import jakarta.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	private BusinessUser businessUser;

	@GetMapping(path = "getall")
	public ResponseEntity<ResponseGetAll> getAll() {
		ResponseGetAll responseGetAll = new ResponseGetAll();

		try {
			List<DtoUser> listDtoUser = businessUser.getAll();

			for (DtoUser item : listDtoUser) {
				Map<String, Object> map = new HashMap<>();

				map.put("idUser", item.getIdUser());
				map.put("nameUser", item.getNameUser());
				map.put("password", item.getPassword());
				map.put("createdAt", item.getCreatedAt());
				map.put("updatedAt", item.getUpdatedAt());

				responseGetAll.dto.listUser.add(map);
				responseGetAll.mo.setSuccess();
			}
		} catch (Exception e) {
			responseGetAll.mo.addResponseMesssage("Ocurrió un error inesperado.");
			responseGetAll.mo.setException();
		}

		return new ResponseEntity<>(responseGetAll, HttpStatus.OK);
	}

	@PostMapping(path = "insert", consumes = "multipart/form-data")
	public ResponseEntity<ResponseInsert> insert(@Valid @ModelAttribute ResquestInsert request,
			BindingResult bindingResult) {
		ResponseInsert response = new ResponseInsert();

		try {
			if (bindingResult.hasErrors()) {
				bindingResult.getAllErrors().forEach(error -> {
					response.mo.addResponseMesssage(error.getDefaultMessage());
				});

				return new ResponseEntity<>(response, HttpStatus.OK);
			}

			DtoUser dtoUser = new DtoUser();

			dtoUser.setNameUser(request.getNameUser());
			dtoUser.setPassword(request.getPassword());
			businessUser.insert(dtoUser);

			response.mo.addResponseMesssage("Registro realizado correctamente");
			response.mo.setSuccess();
		} catch (Exception e) {
			response.mo.addResponseMesssage(
					"Ocurrió un error inesperado, estamos trabajando para resolvero. Gracias por su paciencia.");
			response.mo.setException();
		}

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping(path = "login", consumes = "multipart/form-data")
	public ResponseEntity<ResponseLogin> login(@ModelAttribute RequestLogin request) {
		ResponseLogin response = new ResponseLogin();

		try {
			DtoUser dtoUser = businessUser.login(request.getNameUser(), request.getPassword());

			if (dtoUser != null) {
				Map<String, Object> map = new HashMap<>();

				map.put("idUser", dtoUser.getIdUser());
				map.put("nameUser", dtoUser.getNameUser());
				map.put("createdAt", dtoUser.getCreatedAt());
				map.put("updatedAt", dtoUser.getUpdatedAt());

				response.dto.user = map;

				response.mo.addResponseMesssage("Bienvenido(a) al sistema: " + dtoUser.getNameUser());
				response.mo.setSuccess();
			} else {
				response.mo.addResponseMesssage("Usuario o contraseña incorrecto.");
			}
		} catch (Exception e) {
			response.mo.addResponseMesssage(
					"Ocurrió un error inesperado, estamos trabajando para resolvero. Gracias por su paciencia.");
			response.mo.setException();
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}