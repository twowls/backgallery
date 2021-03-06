package org.twowls.backgallery.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a collection.
 *
 * @author Dmitry Chubarov
 */
public class CollectionDescriptor implements Descriptor {
    public static final String CONFIG = "collection.yml";
    private final Map<String, FieldDescriptor> fields = new HashMap<>();
    private final Map<String, Integer> sizes = new HashMap<>();
    private final String description;

    protected CollectionDescriptor(String description, Map<String, FieldDescriptor> fields, Map<String, Integer> sizes) {
        this.description = description;
        this.fields.putAll(fields);
        this.sizes.putAll(sizes);
    }

    public String description() {
        return this.description;
    }

    public Map<String, FieldDescriptor> fields() {
        return this.fields;
    }

    public Map<String, Integer> sizes() {
        return this.sizes;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{ " +
                "description=" + description +
                ", fields=" + fields +
                ", sizes=" + sizes +
                "}";
    }
}
