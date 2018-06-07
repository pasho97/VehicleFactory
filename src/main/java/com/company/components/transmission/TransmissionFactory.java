package com.company.components.transmission;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Factory pattern implementation used for getting transmission instances
 */
@Component
public class TransmissionFactory {
    private Map<String, Transmission> transmissionsById;

    /**
     * @param transmissions list containing supported transmissions
     */
    public TransmissionFactory(List<Transmission> transmissions) {
        if (transmissions.size() == 0) {
            throw new IllegalArgumentException("No transmissions given for the transmission factory");
        }

        transmissionsById = new HashMap<>();
        transmissions.forEach(x->{
            transmissionsById.put(x.getInfo(),x);
            if(!transmissionsById.containsKey(x.getType())||
                    Transmission.compare(x,transmissionsById.get(x.getType()))==-1){
                transmissionsById.put(x.getType(),x);
            }
        });
    }


    /**
     * @param transmissionString representing the requested transmission
     * @return Transmission instance if such a transmission is supported
     * @throws UnsupportedTransmissionException if no such transmission is supported
     */
    public Transmission getTransmission(String transmissionString) {
        if (transmissionString == null) {
            for (TransmissionType type:TransmissionType.values()
                 ) {
                if(transmissionsById.containsKey(type.getId())) {
                    return transmissionsById.get(type.getId());
                }
            }
        }

        if (transmissionsById.containsKey(transmissionString)) {

            return transmissionsById.get(transmissionString);
        }

        throw new UnsupportedTransmissionException();

    }

}
