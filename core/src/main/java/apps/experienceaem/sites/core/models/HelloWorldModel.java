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

import java.util.Optional;

@Model(adaptables = Resource.class)
public class HelloWorldModel {

    // Resource Type of the Component
    @ValueMapValue(name = PROPERTY_RESOURCE_TYPE, injectionStrategy = InjectionStrategy.OPTIONAL)
    @Default(values = "No resourceType")
    protected String resourceType;

    // Inject the current resource
    @SlingObject
    private Resource currentResource;

    // Inject the resource resolver
    @SlingObject
    private ResourceResolver resourceResolver;

    // Text property
    @ValueMapValue(name = "text", injectionStrategy = InjectionStrategy.OPTIONAL)
    @Default(values = "Default text")
    private String text;

    // Image URL property
    @ValueMapValue(name = "imageUrl", injectionStrategy = InjectionStrategy.OPTIONAL)
    @Default(values = "")
    private String imageUrl;

    // Message to be displayed
    private String message;

    // Method to initialize the model after the injection of dependencies
    @PostConstruct
    protected void init() {
        // Get the PageManager from the resource resolver
        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);

        // Get the path of the current page
        String currentPagePath = Optional.ofNullable(pageManager)
                .map(pm -> pm.getContainingPage(currentResource))
                .map(Page::getPath)
                .orElse("");

        // Construct the message string
        message = "Hello avi!\n"
                + "Resource type is: " + resourceType + "\n"
                + "Current page is:  " + currentPagePath + "\n"
                + "Text: " + text + "\n"
                + "Image URL: " + imageUrl + "\n";
    }

    // Getter for the message
    public String getMessage() {
        return message;
    }

    // Getter for the text property
    public String getText() {
        return text;
    }

    // Getter for the image URL property
    public String getImageUrl() {
        return imageUrl;
    }
}
