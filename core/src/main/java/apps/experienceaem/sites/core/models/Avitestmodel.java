package apps.experienceaem.sites.core.models;

import static org.apache.sling.api.resource.ResourceResolver.PROPERTY_RESOURCE_TYPE;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Model(adaptables = Resource.class)
public class Avitestmodel {

    @ValueMapValue(name = PROPERTY_RESOURCE_TYPE, injectionStrategy = InjectionStrategy.OPTIONAL)
    @Default(values = "No resourceType")
    protected String resourceType;

    @SlingObject
    private Resource currentResource;

    @SlingObject
    private ResourceResolver resourceResolver;

    @ValueMapValue(name = "text", injectionStrategy = InjectionStrategy.OPTIONAL)
    @Default(values = "Default text")
    private String text;

    // Inject multiple image URLs
    @ValueMapValue(name = "imageUrls", injectionStrategy = InjectionStrategy.OPTIONAL)
    @Default(values = {})
    private List<String> imageUrls;

    private String message;

    @PostConstruct
    protected void init() {
        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);

        String currentPagePath = Optional.ofNullable(pageManager)
                .map(pm -> pm.getContainingPage(currentResource))
                .map(Page::getPath)
                .orElse("");

        message = "Hello avi!\n"
                + "Resource type is: " + resourceType + "\n"
                + "Current page is:  " + currentPagePath + "\n"
                + "Text: " + text + "\n"
                + "Image URLs: " + String.join(", ", imageUrls) + "\n";
    }

    public String getMessage() {
        return message;
    }

    public String getText() {
        return text;
    }

    public List<String> getImageUrls() {
        return imageUrls != null ? imageUrls : new ArrayList<>();
    }
}
