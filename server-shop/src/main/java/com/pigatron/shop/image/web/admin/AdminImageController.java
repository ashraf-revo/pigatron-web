package com.pigatron.shop.image.web.admin;

import com.pigatron.shop.image.entity.Image;
import com.pigatron.shop.image.service.ImageService;
import com.pigatron.shop.image.web.customer.CustomerImageController;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "${url.admin}/api/catalogue/image")
public class AdminImageController extends CustomerImageController {

    @Autowired
    public AdminImageController(ImageService imageService) {
        super(imageService);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Insert image file")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "saved successfully"),
            @ApiResponse(code = 400, message = "Validation error")})
    public Image save(@Valid @RequestBody byte[] fileData, @RequestHeader("Content-Type") String mimeType) {
        return imageService.save(fileData, mimeType);
    }

    @RequestMapping(value = "/clearCache", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void clearCache() {
        imageService.clearCache();
    }

}
