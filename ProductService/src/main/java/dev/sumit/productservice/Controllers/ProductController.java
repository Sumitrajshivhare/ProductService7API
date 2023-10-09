package dev.sumit.productservice.Controllers;

import dev.sumit.productservice.Services.ProductService;
import dev.sumit.productservice.ThirdPartyClient.ProductServiceClient.FakeStoreClient.FakeStoreProductDTO;
import dev.sumit.productservice.dtos.ExceptionDto;
import dev.sumit.productservice.dtos.GenericProductDTO;
import dev.sumit.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {


    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<GenericProductDTO> getAllProducts(){

        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public GenericProductDTO getProductById(@PathVariable("id") Long id) throws Exception {

        return productService.getProductyId((id));
    }

    @PostMapping
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO product){
            return productService.createProduct(product);

    }

    @PutMapping("{id}")
    public GenericProductDTO updateProductById( @PathVariable("id") Long id,@RequestBody GenericProductDTO product){
        return productService.updateProductById(id,product);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDTO> deleteProductById(@PathVariable("id") Long id){
        return new ResponseEntity<>(

                productService.deteleProduct(id),
                //Manually we can set status code
                HttpStatus.ACCEPTED
        );

    }

    @GetMapping("/categories")
    public List<String> getAllCategories(){
        return productService.getAllCategories();
    }

    @GetMapping("/category/{category}")
    public  List<GenericProductDTO> getByCategory(@PathVariable("category") String category){

        return  productService.getByCategory(category);


    }

}
