package com.pigatron.cms.setup;

import com.pigatron.admin.settings.Settings;
import com.pigatron.admin.settings.website.LinkPosition;
import com.pigatron.admin.settings.website.LinkType;
import com.pigatron.admin.settings.website.WebSiteSettings;
import com.pigatron.admin.settings.SettingsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import static com.pigatron.admin.settings.website.Link.aLink;
import static com.pigatron.admin.settings.website.WebSiteSettings.aWebSiteSettings;

@Service
@Profile("cms")
public class CmsRepositorySetupService {

    private static final Logger logger = LoggerFactory.getLogger(CmsRepositorySetupService.class);

    @Autowired
    private SettingsRepository settingsRepository;

    @EventListener
    public void setupRepositories(ContextRefreshedEvent event) {
        logger.info("Checking repository setup...");
        addDefaultWebSiteSettings();
    }

    protected void addDefaultWebSiteSettings() {
        Settings settings = settingsRepository.findOne(WebSiteSettings.ID);
        if(settings == null) {
            WebSiteSettings webSiteSettings = aWebSiteSettings()
                    .withTitle("Site Title")
                    .withLink(aLink()
                            .withLinkType(LinkType.ROUTE)
                            .withPosition(LinkPosition.TOP_LEFT)
                            .withTitle("Home")
                            .withAction("/")
                            .build())
                    .withLink(aLink()
                            .withLinkType(LinkType.ROUTE)
                            .withPosition(LinkPosition.TOP_LEFT)
                            .withTitle("Blog")
                            .withAction("/posts")
                            .build())
                    .withLink(aLink()
                            .withLinkType(LinkType.ROUTE)
                            .withPosition(LinkPosition.TOP_LEFT)
                            .withTitle("About")
                            .withAction("page({urlKey:'about'})")
                            .build())
                    .build();
            settingsRepository.save(webSiteSettings);
        }
    }


}
