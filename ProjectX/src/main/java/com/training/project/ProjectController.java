package com.training.project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.project.model.Comment;
import com.training.project.model.Product;
import com.training.project.model.ProductForm;
import com.training.project.repository.ProjectService;
import com.training.project.util.FileUploadUtil;


@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/")
public class ProjectController {
	
	private static String API_URL = "https://random-message.herokuapp.com/random-message";
	
	
	@Autowired
	private ProjectService service;
	@Autowired
	private RestTemplate restService;
	
	@GetMapping("products")
	public List<Product> getProducts() {
	return service.getProducts();
	}
	
	@GetMapping("valid-product-id/{id}")
	public boolean isValidProduct(@PathVariable int id) {
		return service.containsProduct(id);
	}
	
	@PostMapping("products")
	public Product addProduct(@RequestParam String productJson,
			@RequestParam MultipartFile productImage) throws IOException {
	   
		ObjectMapper toObj = new ObjectMapper();
		ProductForm productForm = toObj.readValue(productJson, ProductForm.class);
		System.out.println(productForm + " " + productImage);
		
	   
		  String fileName = StringUtils.cleanPath(productImage.getOriginalFilename());
		  Product product = new Product(productForm.getId(), productForm.getName());
		  product.setImages(fileName);
		  
		  Product pro = service.createProduct(product);
		  service.createFeatures(productForm.getFeatures(), pro);
		  String uploadDir = "product-images/" + pro.getId();
		  
		  FileUploadUtil.saveFile(uploadDir, fileName, productImage);
		 
       
       return pro;
	}
	

	
	@GetMapping(value = "/products/{id}/image",
            produces = {MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_JPEG_VALUE })
	 public ResponseEntity<Resource> getImage(@PathVariable int id) throws IOException {
        Product p = service.getProduct(id);
		final ByteArrayResource inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get(
                "product-images/" + p.getId() +"/"+ p.getImages()
        )));
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentLength(inputStream.contentLength())
                .body(inputStream);

    }
	
	@GetMapping("products/{id}")
	public Product getProduct(@PathVariable int id) {
		return service.getProduct(id);
	}
	@GetMapping("/products/{id}/comments")
	public List<Comment> getCommentsForProductId(@PathVariable int id) {
		return service.getCommentsForProductId(id);
	}
	
	@PostMapping("/products/{id}/comments")
	public Comment createComment(@RequestBody Comment c, @PathVariable int id) {
		Product p = service.getProduct(id);
		return service.createComment(c, p);
	}
	
	@GetMapping("/random-welcome-message")
	public String getMessage() {
		String message = restService.getForObject(API_URL, String.class);
		return message;
	}
}
