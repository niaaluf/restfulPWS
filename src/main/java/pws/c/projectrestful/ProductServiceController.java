/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pws.c.projectrestful;

import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author asus
 */
@RestController
public class ProductServiceController {
    private static Map<String, Product> productRepo = new HashMap<>();
    static {
        Product buah = new Product();
        buah.setId("1");
        buah.setName("Apel");
        productRepo.put(buah.getId(), buah);
        
        Product hewan = new Product();
        hewan.setId("2");
        hewan.setName("Naga");
        productRepo.put(hewan.getId(), hewan);
    }
    
   @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public  ResponseEntity<Object> delete(@PathVariable("id") String id){
        productRepo.remove(id);
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
    }
    
    
    
}
