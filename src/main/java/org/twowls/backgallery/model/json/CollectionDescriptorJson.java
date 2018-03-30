package org.twowls.backgallery.model.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.twowls.backgallery.model.CollectionDescriptor;

import java.util.Map;

/**
 * <p>TODO add documentation...</p>
 *
 * @author Dmitry Chubarov
 */
public class CollectionDescriptorJson extends CollectionDescriptor {
    private static final String DESCRIPTION_JSON = "description";
    private static final String SIZES_JSON = "sizes";

    @JsonCreator
    @SuppressWarnings("unused")
    CollectionDescriptorJson(@JsonProperty(DESCRIPTION_JSON) String description,
            @JsonProperty(SIZES_JSON) Map<String, Integer> sizes) {
        super(description, sizes);
    }

    @Override
    @JsonProperty(DESCRIPTION_JSON)
    public String description() {
        return super.description();
    }

    @Override
    @JsonProperty(SIZES_JSON)
    public Map<String, Integer> sizes() {
        return super.sizes();
    }
}
