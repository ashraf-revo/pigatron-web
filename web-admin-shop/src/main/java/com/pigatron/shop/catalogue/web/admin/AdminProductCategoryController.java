package com.pigatron.shop.catalogue.web.admin;

import com.pigatron.shop.catalogue.entity.ProductCategory;
import com.pigatron.shop.catalogue.service.ProductCategoryService;
import com.pigatron.admin.api.AbstractWriteRestController;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "${url.admin}/api/catalogue/category", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminProductCategoryController extends AbstractWriteRestController<ProductCategory> {

    private ProductCategoryService productCategoryService;

    @Autowired
    public AdminProductCategoryController(ProductCategoryService service) {
        super(service);
        this.productCategoryService = service;
    }

    @RequestMapping(value = "/{parentId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create new category and add to given parent category")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "saved successfully"),
            @ApiResponse(code = 400, message = "Validation error")})
    public ProductCategory add(@PathVariable String parentId, @Valid @RequestBody ProductCategory newCategory) {
        return productCategoryService.addSubcategory(parentId, newCategory);
    }

}
