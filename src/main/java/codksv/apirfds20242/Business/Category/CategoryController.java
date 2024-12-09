package codksv.apirfds20242.Business.Category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import codksv.apirfds20242.Business.Category.ResponseObject.ResponseGetData;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @GetMapping(path="getdata")
    public ResponseEntity<ResponseGetData> getData() {
        ResponseGetData responseGetData = new ResponseGetData();
        responseGetData.name = "Tecnologia";
        responseGetData.description = "Encuentra dispositivos de última generación";
        responseGetData.status = true;
        return new ResponseEntity<>(responseGetData, HttpStatus.OK);
    } 
}