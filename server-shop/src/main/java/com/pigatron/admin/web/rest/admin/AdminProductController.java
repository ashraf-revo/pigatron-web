package com.pigatron.admin.web.rest.admin;

import com.pigatron.admin.domain.entity.catalogue.Product;
import com.pigatron.admin.service.catalogue.ProductService;
import com.pigatron.server.web.rest.AbstractWriteRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${url.admin}/api/catalogue/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminProductController extends AbstractWriteRestController<Product> {

    @Autowired
    public AdminProductController(ProductService service) {
        super(service, "name");
    }

}