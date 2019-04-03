package boot.service;

import boot.dto.OfficeRequest;
import boot.entity.Office;
import boot.entity.Salesrep;
import org.springframework.stereotype.Component;

@Component
public class OfficeCreator {
    public Office officeRequestToOffice(OfficeRequest req){
        return new Office(req.getOffice(), req.getCity(), req.getRegion(), new Salesrep(req.getMgr()), req.getTarget(), req.getSales(), null);
    }
}
