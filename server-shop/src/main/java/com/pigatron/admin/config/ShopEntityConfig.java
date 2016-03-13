package com.pigatron.admin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pigatron.admin.domain.entity.catalogue.option.ProductOption;
import com.pigatron.admin.domain.entity.catalogue.option.ProductOptionMixIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShopEntityConfig {

    @Autowired
    public void objectMapper(ObjectMapper objectMapper) {
        // using mixins allow for external plugins to add their own subtypes
        objectMapper.addMixIn(ProductOption.class, ProductOptionMixIn.class);
    }

}