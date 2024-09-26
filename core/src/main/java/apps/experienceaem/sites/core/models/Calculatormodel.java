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
public class Calculatormodel {

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

    // Input 1 property
    @ValueMapValue(name = "input1", injectionStrategy = InjectionStrategy.OPTIONAL)
    @Default(values = "0")
    private String input1;

    // Input 2 property
    @ValueMapValue(name = "input2", injectionStrategy = InjectionStrategy.OPTIONAL)
    @Default(values = "0")
    private String input2;

    // Operation property
    @ValueMapValue(name = "operation", injectionStrategy = InjectionStrategy.OPTIONAL)
    @Default(values = "+")
    private String operation;

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
        message = "Calculator Component\n"
                + "Resource type is: " + resourceType + "\n"
                + "Current page is:  " + currentPagePath + "\n"
                + "Input 1: " + input1 + "\n"
                + "Input 2: " + input2 + "\n"
                + "Operation: " + operation + "\n";
    }

    // Getter for the message
    public String getMessage() {
        return message;
    }

    // Getter for input1 property
    public String getInput1() {
        return input1;
    }

    // Getter for input2 property
    public String getInput2() {
        return input2;
    }

    // Getter for operation property
    public String getOperation() {
        return operation;
    }
}
