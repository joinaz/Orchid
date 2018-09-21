package com.eden.orchid.impl.themes.components;

import com.eden.orchid.api.OrchidContext;
import com.eden.orchid.api.options.annotations.Description;
import com.eden.orchid.api.resources.resource.OrchidResource;
import com.eden.orchid.api.theme.components.OrchidComponent;

import javax.inject.Inject;

@Description(value = "Locate and display your project's Readme file.", name = "Readme")
public final class ReadmeComponent extends OrchidComponent {

    @Inject
    public ReadmeComponent(OrchidContext context) {
        super(context, "readme", 50);
    }

    public String getContent() {
        OrchidResource readmeResource = context.findClosestFile("readme");
        if (readmeResource != null) {
            return readmeResource.compileContent(this);
        }
        return null;
    }
}
