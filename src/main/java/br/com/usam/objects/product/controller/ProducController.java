package br.com.usam.objects.product.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.usam.infrastructure.support.CustomException;
import br.com.usam.infrastructure.support.ObjectUtils;
import br.com.usam.objects.product.model.Product;
import br.com.usam.objects.product.service.IServiceProduct;

@RestController
@RequestMapping(path = "/api/v1/app/product")
public class ProducController {

    // Product Mappings
    @Autowired
    private IServiceProduct serviceProduct;

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateProduct(@RequestBody Map<String, Object> updates,
            @RequestHeader(value = "UserId", required = false) String userId) {

        try {
            return new ResponseEntity<>(ObjectUtils.updateOrSaveObject(updates, serviceProduct), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Product product = new Product();
        product.setId(id);

        try {
            serviceProduct.delete(product);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> salvarProduct(@RequestBody Product product,
            @RequestHeader(value = "UserId", required = false) String userId) {
        try {
            return new ResponseEntity<>(serviceProduct.save(product), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Message " + e.getLocalizedMessage());
            CustomException ce = new CustomException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
            return new ResponseEntity<>(ce, ce.getHttpStatus());
        }
    }

    @RequestMapping(path = "/all")
    public ResponseEntity<?> getProducts(@RequestHeader(value = "UserId", required = false) String userId) {
        try {
            return new ResponseEntity<>(serviceProduct.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/lookup")
    public ResponseEntity<?> getLookup(@RequestParam String term) {
        try {
            return new ResponseEntity<>(serviceProduct.getLookup(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        try {
            Product product = serviceProduct.findById(id);
            if (product == null) {
                throw new Exception("Product not found");
            }

            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }
}
