package com.pigatron.shop.catalogue.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pigatron.admin.api.View;
import com.pigatron.cms.image.entity.Image;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class ProductImage {

    @DBRef(lazy = true)
    @JsonIgnoreProperties({"mimeType"})
    @JsonView(View.Public.class)
    private Image image;

    @JsonView(View.Public.class)
    private boolean thumbnail;


    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(boolean thumbnail) {
        this.thumbnail = thumbnail;
    }
}
