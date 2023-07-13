package br.com.usam.objects.shop.controller;

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
import br.com.usam.objects.shop.model.Shop;
import br.com.usam.objects.shop.service.IServiceShop;

@RestController
@RequestMapping(path = "/api/v1/app/shop")
public class ShopController {

    // Shop Mappings
    @Autowired
    private IServiceShop serviceShop;

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateProduct(@RequestBody Map<String, Object> updates,
            @RequestHeader(value = "UserId", required = false) String userId) {

        try {
            return new ResponseEntity<>(ObjectUtils.updateOrSaveObject(updates, serviceShop), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Shop shop = new Shop();
        shop.setId(id);

        try {
            serviceShop.delete(shop);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> salvarProduct(@RequestBody Shop shop,
            @RequestHeader(value = "UserId", required = false) String userId) {
        try {
            return new ResponseEntity<>(serviceShop.save(shop), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Message " + e.getLocalizedMessage());
            CustomException ce = new CustomException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
            return new ResponseEntity<>(ce, ce.getHttpStatus());
        }
    }

    @RequestMapping(path = "/all")
    public ResponseEntity<?> getProducts(@RequestHeader(value = "UserId", required = false) String userId) {
        try {
            return new ResponseEntity<>(serviceShop.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/lookup")
    public ResponseEntity<?> getLookup(@RequestParam String term) {
        try {
            return new ResponseEntity<>(serviceShop.getLookup(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(serviceShop.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }
}
