package boot.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import java.math.BigDecimal;

public interface DaoTemplate {
    static final Logger LOG = LogManager.getLogger(OfficeDaoImpl.class);
    default public void selectTemplate(Session sessionObj){
        try {
            query(sessionObj);
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }

    default public void writeTemplate(Session sessionObj, BigDecimal id){
        try {
            sessionObj.beginTransaction();
            query(sessionObj);
            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            LOG.info("Order With Id={} Is Successfully Updated In The Database!", id);
        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                LOG.info(".......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }

    public void query(Session sessionObj);
}
