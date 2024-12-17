package codksv.apirfds20242.Service.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import codksv.apirfds20242.Business.BusinessCategory;
import codksv.apirfds20242.Dto.DtoCategory;
import codksv.apirfds20242.Service.Category.RequestObject.RequestInsert;
import codksv.apirfds20242.Service.Category.ResponseObject.ResponseGetAll;
import codksv.apirfds20242.Service.Category.ResponseObject.ResponseGetData;

@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private BusinessCategory businessCategory;

	@GetMapping(path = "getdata")
	public ResponseEntity<ResponseGetData> getData() {
		ResponseGetData responseGetData = new ResponseGetData();
		responseGetData.name = "Tecnologia";
		responseGetData.description = "Encuentra dispositivos de última generación";
		responseGetData.status = true;
		return new ResponseEntity<>(responseGetData, HttpStatus.OK);
	}

	@PostMapping(path = "insert", consumes = "multipart/form-data")
	public ResponseEntity<String> insert(@ModelAttribute RequestInsert request) {
		try {

			DtoCategory dtoCategory = new DtoCategory();

			dtoCategory.setName(request.getName());
			dtoCategory.setDescription(request.getDescription());
			dtoCategory.setStatus(request.isState());

			businessCategory.insert(dtoCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>("Registro realizado correctamente.", HttpStatus.CREATED);
	}

	@GetMapping(path = "getall")
	public ResponseEntity<ResponseGetAll> getAll() {
		ResponseGetAll responseGetAll = new ResponseGetAll();

		List<DtoCategory> listDtoCategory = businessCategory.getAll();

		for (DtoCategory item : listDtoCategory) {
			Map<String, Object> map = new HashMap<>();

			map.put("idcategory", item.getIdcategory());
			map.put("name", item.getName());
			map.put("description", item.getDescription());
			map.put("state ", item.isStatus());
			map.put("createdAt", item.getCreatedAt());
			map.put("updatedAt", item.getUpdatedAt());

			responseGetAll.dto.listCategory.add(map);
		}
		return new ResponseEntity<>(responseGetAll, HttpStatus.OK);
	}

	@DeleteMapping(path = "delete/{idCategory}")
	public ResponseEntity<Boolean> delete(@PathVariable String idCategory) {
		businessCategory.delete(idCategory);

		return new ResponseEntity<>(true, HttpStatus.OK);
	}
}