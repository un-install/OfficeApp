package boot.repository;

import boot.entity.Office;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Repository
public class OfficeDaoImpl implements OfficeDao {
    private Session sessionObj;

    private SessionFactory sessionFactoryObj;

    private static final Logger LOG = LogManager.getLogger(OfficeDaoImpl.class);

    @Autowired
    public OfficeDaoImpl(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactoryObj = factory.unwrap(SessionFactory.class);
    }

    @Override
    public Set<Office> getAllOffices() {
        Set<Office> offices = new HashSet<>();
        DaoTemplate daoTemplate = (session) -> offices.addAll(session.createQuery("FROM Office", Office.class).list());
        daoTemplate.selectTemplate(sessionFactoryObj.openSession());
        return offices;
    }

    @Override
    public Office findOfficeById(BigDecimal id) {
        Office office = new Office();
        DaoTemplate daoTemplate = (session) -> office.setAll(session.get(Office.class, id));
        daoTemplate.selectTemplate(sessionFactoryObj.openSession());
        return office;
    }

    @Override
    public void insertOffice(Office office) {
        DaoTemplate daoTemplate = (session) -> session.save(office);
        daoTemplate.writeTemplate(sessionFactoryObj.openSession(), office.getOffice());
    }

    @Override
    public void updateOffice(Office office) {
        DaoTemplate daoTemplate = (session) -> session.update(office);
        daoTemplate.writeTemplate(sessionFactoryObj.openSession(), office.getOffice());
    }

    @Override
    public void deleteOffice(BigDecimal id) {
        DaoTemplate daoTemplate = (session) -> session.delete(id);
        daoTemplate.writeTemplate(sessionFactoryObj.openSession(), id);
    }
}
