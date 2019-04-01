package boot.controller;

import boot.dto.OfficeRequest;
import boot.entity.Office;
import boot.service.OfficeCreator;
import boot.service.OfficeServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/office")
public class OfficeController {
    private static final Logger LOG = LogManager.getLogger(OfficeController.class);

    @Autowired
    OfficeServiceImpl officeService;
    @Autowired
    OfficeCreator officeCreator;

    @GetMapping("/")
    public @ResponseBody Set<Office> getAllOffices(){
        LOG.debug("start getAllOffices");
        HashSet<Office> offices =  (HashSet)officeService.getAllOffices();
        LOG.debug("end getAllOffices");
        return offices;
    }

    @GetMapping("/{id}")
    public @ResponseBody Office findOfficeById(@PathVariable("id") int id){
        LOG.debug("start findOfficeById, id={}", id);
        Office office = officeService.findOfficeById(new BigDecimal(id));
        LOG.debug("end findOfficeById, id={}", id);
        return office;
    }

    @PostMapping("/")
    public void insertOffice(@Valid @RequestBody OfficeRequest req){
        LOG.debug("start insertOffice, officeRequest={}", req);
        officeService.insertOffice(officeCreator.officeRequestToOffice(req));
        LOG.debug("end insertOffice, officeRequest={}", req);
    }

    @PutMapping("/")
    public void updateOffice(@Valid @RequestBody OfficeRequest req){
        LOG.debug("start updateOffice, officeRequest={}", req);
        officeService.updateOffice(officeCreator.officeRequestToOffice(req));
        LOG.debug("end updateOffice");

    }

    @DeleteMapping("/{id}")
    public void deleteOffice(@PathVariable("id") int id) {
        LOG.debug("start deleteOffice, id={}", id);
        officeService.deleteOffice(new BigDecimal(id));
        LOG.debug("end deleteOffice, id={}", id);

    }


}
