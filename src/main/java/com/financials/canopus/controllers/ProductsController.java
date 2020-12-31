package com.financials.canopus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.financials.canopus.dao.CustomerRepository;
import com.financials.canopus.dao.ProductRepository;
import com.financials.canopus.domain.Customer;
import com.financials.canopus.domain.Product;
import com.financials.canopus.domain.views.CreateCustomerRequest;
import com.financials.canopus.domain.views.CreateProductRequest;
import com.financials.canopus.domain.views.UpdateCustomerRequest;
import com.financials.canopus.domain.views.UpdateProductRequest;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping (path = "/v1")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @ApiOperation (value = "Create a product")
    @PostMapping ("/products")
    public @ResponseBody
    Product createProduct(@RequestBody CreateProductRequest request) {
        return productRepository.save(Product.fromRequest(request));
    }

    @ApiOperation(value = "List all products")
    @GetMapping ("/products")
    public @ResponseBody Iterable<Product> listProducts() {
        return productRepository.findAll();
    }

    @ApiOperation(value = "Retrieve the details of a product")
    @GetMapping ("/products/{id}")
    public @ResponseBody Product getProduct(@PathVariable String id) {
        return productRepository.findByExternalId(id);
    }

    @ApiOperation(value = "Update a product")
    @PutMapping ("/products/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody UpdateProductRequest request) {
        Product product = productRepository.findByExternalId(id);
        product.setActive(request.getActive());
        product.setDescription(request.getDescription());
        product.setName(request.getName());
        product.setCaption(request.getCaption());
        product.setShippable(request.getShippable());
        return productRepository.save(product);
    }

    @ApiOperation(value = "Delete a product")
    @DeleteMapping ("/products/{id}")
    public void deleteProduct(@PathVariable String id) {
        Product product = productRepository.findByExternalId(id);
        productRepository.delete(product);
    }
}
