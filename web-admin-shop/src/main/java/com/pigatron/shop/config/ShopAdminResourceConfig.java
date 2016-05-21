package com.pigatron.shop.config;

import com.pigatron.admin.config.SubModules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShopAdminResourceConfig {

    @Autowired
    public void addSubmodule(SubModules submodules) {
        submodules.addSubmodule("admin.shop.catalogue");
    }

}
