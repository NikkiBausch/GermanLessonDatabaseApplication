package org.launchcode.GermanLessonDatabase;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    private String location = "readings-dir";

    public String getLocation() {
        return location;
    }

    public String setLocation() {
        this.location = location;
    }

}
