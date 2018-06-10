package com.company.components.engine.emission.standards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class EmissionStandardFactory {
    private final List<EmissionStandard> emissionStandardList;

    @Autowired
    public EmissionStandardFactory(List<EmissionStandard> emissionStandards) {
        emissionStandardList = new ArrayList<>(emissionStandards);
        emissionStandardList.sort(Comparator.comparing(EmissionStandard::getName));
    }

    /**
     * @param emissionStandardName the name of the requested emission standard
     * @return emission standard with the same name if one exists , if emissionStandardName is empty string a default
     * emission standard is returned
     * @throws IllegalArgumentException if emission standard with the given name is unsupported
     */
    public EmissionStandard getStandardInstance(String emissionStandardName) {
        if (emissionStandardName.equals("")) {
            return emissionStandardList.get(0);
        }
        for (EmissionStandard standard : emissionStandardList
                ) {
            if (emissionStandardName.equals(standard.getName())) {
                return standard;
            }
        }

        throw new UnsupportedEmissionStandardException();
    }

    public EmissionStandard getHighestStandard() {
        return emissionStandardList.get(emissionStandardList.size() - 1);
    }
}
