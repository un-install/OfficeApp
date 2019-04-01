package boot.service;

import boot.entity.Office;
import boot.repository.OfficeDao;
import boot.repository.OfficeDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class OfficeServiceImpl implements OfficeService {
    private static final Logger LOG = LogManager.getLogger(OfficeServiceImpl.class);

    @Autowired
    private OfficeDaoImpl odao;

    @Override
    public Set<Office> getAllOffices() {
        LOG.debug("start getAllOffices");
        HashSet<Office> offices =  (HashSet)odao.getAllOffices();
        LOG.debug("end getAllOffices");
        return offices;
    }

    @Override
    public Office findOfficeById(BigDecimal id) {
        LOG.debug("start findOfficeById, id={}", id);
        Office office = odao.findOfficeById(id);
        LOG.debug("end findOfficeById, id={}", id);
        return office;
    }

    @Override
    public void insertOffice(Office office) {
        LOG.debug("start insertOffice, office={}", office);
        odao.insertOffice(office);
        LOG.debug("end insertOffice, office={}", office);
    }

    @Override
    public void updateOffice(Office office) {
        LOG.debug("start updateOffice office={}", office);
        odao.updateOffice(office);
        LOG.debug("end updateOffice");
    }

    @Override
    public void deleteOffice(BigDecimal id) {
        LOG.debug("start deleteOffice, id={}", id);
        odao.deleteOffice(id);
        LOG.debug("end deleteOffice, id={}", id);
    }
}
