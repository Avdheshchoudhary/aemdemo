package apps.experienceaem.sites.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Reference;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Dictionary;

@Model(
    adaptables = Resource.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class RamComponentModel {

    @ValueMapValue
    private String text;

    @ValueMapValue
    private String key;

    @Reference
    private ConfigurationAdmin configAdmin;

    private String value;

    @PostConstruct
    protected void init() {
        if (key != null && !key.isEmpty()) {
            value = getConfigValue(key);
        }
    }

    private String getConfigValue(String propertyName) {
        try {
            Configuration config = configAdmin.getConfiguration("ramcomponent");
            if (config != null) {
                Dictionary<String, Object> properties = config.getProperties();
                if (properties != null) {
                    Object value = properties.get(propertyName);
                    return value != null ? value.toString() : "Default Value";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Default Value";
    }

    public String getText() {
        return text != null ? text : "Default Text";
    }

    public String getValue() {
        return value != null ? value : "Default Value";
    }
}
