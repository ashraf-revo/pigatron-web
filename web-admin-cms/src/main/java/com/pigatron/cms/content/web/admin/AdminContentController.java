package com.pigatron.cms.content.web.admin;

import com.pigatron.admin.api.AbstractWriteRestController;
import com.pigatron.admin.exception.InvalidParameterException;
import com.pigatron.admin.exception.ResourceNotFoundException;
import com.pigatron.cms.content.entity.Content;
import com.pigatron.cms.content.entity.ContentQuery;
import com.pigatron.cms.content.service.ContentService;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${url.admin}/api/cms/content", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminContentController extends AbstractWriteRestController<Content> {

    private ContentService contentService;

    @Autowired
    public AdminContentController(ContentService contentService) {
        super(contentService);
        this.contentService = contentService;
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET, params = {"urlKey"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get page by URL Key")
    public Content getPageByUrlKey(@RequestParam("urlKey") String urlKey) {
        if(urlKey == null) {
            throw new InvalidParameterException("urlKey cannot be null");
        }
        return contentService.getPageByUrlKey(urlKey).orElseThrow(ResourceNotFoundException::new);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get with query")
    public List<Content> query() {
        ContentQuery contentQuery = new ContentQuery();
        return contentService.find(contentQuery);
    }

}
