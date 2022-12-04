/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pws.c.projectrestful;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pws.projectcontroller.Product;

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
    
    //delete
   @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public  ResponseEntity<Object> delete(@PathVariable("id") String id){
        productRepo.remove(id);
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
    }
    
    //put
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product){
        
        // Menambahkan kondisi if and else
        
        //Jika product yang ingin di edit tidak memiliki id maka data tidak dapat di update
        if(!productRepo.containsKey(id)){
            return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
        }
        
        //Jika product memiliki id maka data tersebut dapat di update
        else{
            productRepo.remove(id);
            product.setId(id);
            productRepo.put(id, product);
            return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
        }
    }
    
    //post
    @RequestMapping(value = "/products", method = RequestMethod.POST) 
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
       
        // Menambahkan kondisi if and else
        
        // Jika menambahkan product dengan id yang sama  
        if(productRepo.containsKey(product.getId())){
            return new ResponseEntity<>("Id product is already exist, please check again", HttpStatus.OK);
        }
        
        // Jika id belum pernah terbuat maka dapat membuat data baru
        else{
            productRepo.put(product.getId(), product);
            return new ResponseEntity<>("Product os created successfully", HttpStatus.CREATED);
        }
    }
    
    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct(){
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }
    
}
