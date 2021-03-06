package com.pigatron.shop.catalogue.test.endpoint;

import com.pigatron.TestApplication;
import com.pigatron.admin.security.test.AbstractAdminSecurityIntegrationTest;
import com.pigatron.shop.catalogue.entity.Product;
import com.pigatron.shop.catalogue.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.given;
import static com.pigatron.shop.catalogue.entity.Product.ProductBuilder.aProduct;
import static com.pigatron.shop.catalogue.entity.option.SelectProduct.SelectProductBuilder.aSelectProduct;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplication.class)
@WebIntegrationTest
public class ProductEndpointTest extends AbstractAdminSecurityIntegrationTest {

    private static final String API_URL = "/catalogue/product";

    @Autowired
    private ProductRepository productRepository;

    @Before
    @Override
    public void setup() {
        super.setup(API_URL);
    }

    @Test
    public void testPublicGetProductByUrlKey() {
        //TODO
    }

    @Test
    public void testAdminCreateProduct() {
        //TODO
    }

    @Test
    public void testAdminGetProduct_IsOptionFalse() {
        createProductWithOptionAndSave();
        given()
                .auth().basic("admin", "password")
        .when()
                .get(adminApiUrl + "?isOption=false")
        .then()
                .log().all()
                .body("", hasSize(1))
                .body("[0].name", equalTo("Main Product"));
    }

    @Test
    public void testAdminGetProduct_IsOptionTrue() {
        createProductWithOptionAndSave();
        given()
                .auth().basic("admin", "password")
        .when()
                .get(adminApiUrl + "?isOption=true")
        .then()
                .log().all()
                .body("", hasSize(1))
                .body("[0].name", equalTo("Option Product"));
    }

    @Test
    public void testAdminGetProduct_HasOptionsFalse() {
        createProductWithOptionAndSave();
        given()
                .auth().basic("admin", "password")
        .when()
                .get(adminApiUrl + "?hasOptions=false")
        .then()
                .log().all()
                .body("", hasSize(1))
                .body("[0].name", equalTo("Option Product"));
    }

    @Test
    public void testAdminGetProduct_HasOptionsTrue() {
        createProductWithOptionAndSave();
        given()
                .auth().basic(ADMIN_USERNAME, ADMIN_PASSWORD)
        .when()
                .get(adminApiUrl + "?hasOptions=true")
        .then()
                .log().all()
                .body("", hasSize(1))
                .body("[0].name", equalTo("Main Product"));
    }




    private Product buildOptionProduct() {
        return aProduct()
                .name("Option Product")
                .urlKey("option_product")
                .sku("op")
                .isOption(true)
                .build();
    }

    private Product buildMainProduct(Product optionProduct) {
        return aProduct()
                .name("Main Product")
                .urlKey("main_product")
                .sku("mp")
                .option(aSelectProduct()
                        .name("Select Product")
                        .product(optionProduct)
                        .build())
                .build();
    }

    private Product createProductWithOptionAndSave() {
        Product optionProduct = buildOptionProduct();
        Product mainProduct = buildMainProduct(optionProduct);
        productRepository.save(optionProduct);
        productRepository.save(mainProduct);
        return mainProduct;
    }

    private Product createProductWithOption() {
        Product optionProduct = buildOptionProduct();
        Product mainProduct = buildMainProduct(optionProduct);
        return mainProduct;
    }

}
