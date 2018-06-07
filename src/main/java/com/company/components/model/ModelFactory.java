package com.company.components.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class representing factory pattern for VehicleModel instances
 */
@Component
public class ModelFactory {
    private final Map<String, VehicleModel> models;

    /**
     * @param modelList List of VehicleModel instances
     */
    @Autowired
    public ModelFactory(List<VehicleModel> modelList) {
        models = new HashMap<>();
        modelList.forEach(x -> models.put(x.getModel(), x));
    }

    /**
     * @param modelString String representing a {@link VehicleModel} instance
     * @return VehicleModel instance representing the requested {@code #modelString}
     * @throws IllegalArgumentException if such a model is not found
     */
    public VehicleModel getModel(String modelString) {
        if (models.containsKey(modelString)) {
            return models.get(modelString);
        } else {
            BodyType[] types = BodyType.values();
            for (BodyType type : types) {
                if (models.containsKey(modelString + type.toString())) {
                    return models.get(modelString + type.toString());
                }
            }
        }

        throw new IllegalArgumentException("required model is unsupported");
    }
}
