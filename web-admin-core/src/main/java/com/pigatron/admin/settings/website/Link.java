package com.pigatron.admin.settings.website;

public class Link {

    private LinkType type;
    private LinkPosition position;
    private String title;
    private String action;
    private String cssClass;

    public Link() {
    }

    public Link(LinkType type, LinkPosition position, String title, String action, String cssClass) {
        this.type = type;
        this.position = position;
        this.title = title;
        this.action = action;
        this.cssClass = cssClass;
    }

    private Link(Builder builder) {
        setType(builder.linkType);
        setPosition(builder.position);
        setTitle(builder.title);
        setAction(builder.action);
        setCssClass(builder.cssClass);
    }

    public static Builder aLink() {
        return new Builder();
    }

    public LinkType getType() {
        return type;
    }

    public void setType(LinkType type) {
        this.type = type;
    }

    public LinkPosition getPosition() {
        return position;
    }

    public void setPosition(LinkPosition position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


    public static final class Builder {
        private LinkType linkType;
        private LinkPosition position;
        private String title;
        private String action;
        private String cssClass;

        private Builder() {
        }

        public Builder withLinkType(LinkType val) {
            linkType = val;
            return this;
        }

        public Builder withPosition(LinkPosition val) {
            position = val;
            return this;
        }

        public Builder withTitle(String val) {
            title = val;
            return this;
        }

        public Builder withAction(String val) {
            action = val;
            return this;
        }

        public Builder withCssClass(String val) {
            cssClass = val;
            return this;
        }

        public Link build() {
            return new Link(this);
        }
    }
}
