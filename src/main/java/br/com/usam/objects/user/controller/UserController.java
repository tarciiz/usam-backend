package br.com.usam.objects.user.controller;

import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.usam.infrastructure.support.StringUtil;
import br.com.usam.infrastructure.support.CustomException;
import br.com.usam.infrastructure.support.ErrorResponse;
import br.com.usam.infrastructure.support.ObjectUtils;
import br.com.usam.objects.product.model.Product;
import br.com.usam.objects.product.service.IServiceProduct;
import br.com.usam.objects.user.model.User;
import br.com.usam.objects.user.service.IServiceUser;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "/api/v1/app/user")
public class UserController {

    // User Mappings
    @Autowired
    private IServiceUser serviceUser;

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateProduct(@RequestBody Map<String, Object> updates,
            @RequestHeader(value = "UserId", required = false) String userId) {

        try {
            return new ResponseEntity<>(ObjectUtils.updateOrSaveObject(updates, serviceUser), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        User user = new User();
        user.setId(id);

        try {
            serviceUser.delete(user);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> salvarProduct(@RequestBody User user,
            @RequestHeader(value = "UserId", required = false) String userId) {
        try {
            return new ResponseEntity<>(serviceUser.save(user), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Message " + e.getLocalizedMessage());
            CustomException ce = new CustomException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
            return new ResponseEntity<>(ce, ce.getHttpStatus());
        }
    }

    @RequestMapping(path = "/all")
    public ResponseEntity<?> getProducts(@RequestHeader(value = "UserId", required = false) String userId) {
        try {
            return new ResponseEntity<>(serviceUser.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/lookup")
    public ResponseEntity<?> getLookup(@RequestParam String term) {
        try {
            return new ResponseEntity<>(serviceUser.getLookup(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(serviceUser.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/login")
    public ResponseEntity<?> login(String login, String password) {
        try {
            return new ResponseEntity<>(serviceUser.findByLoginAndPassword(login, StringUtil.toMD5(password)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.OK);
        }
    }
}
