package boot.repository;

import boot.entity.Office;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import javax.persistence.RollbackException;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class OfficeDaoImplTest {

    private final Office EXIST = new Office(new BigDecimal(99));
    private final BigDecimal EXIST_Id = new BigDecimal(99);

    private final Office NOT_EXIST = new Office(new BigDecimal(999));
    private final BigDecimal NOT_EXIST_Id = new BigDecimal(999);


    @Autowired
    private  DataSource dataSource;
    @Autowired
    private OfficeDaoImpl odao;

    @Before
    public void initDb() {
        Resource initSchema = new ClassPathResource("script\\schema.sql");
        Resource data = new ClassPathResource("script\\data.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, data);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
    }

    @Test
    public void testGetAllOffices(){
        Set<Office> aactual = odao.getAllOffices();
        assertTrue(aactual.size() == 2);
    }

    @Test
    public void testFindOfficeByIdExist(){
        Office actual = odao.findOfficeById(EXIST_Id);
        System.out.println(actual);
        assertTrue(actual != null);
    }

    @Test
    public void testFindOfficeByIdNotExist(){
        Office actual = odao.findOfficeById(NOT_EXIST_Id);
        System.out.println(actual);
        assertNull(actual);
    }

    @Test
    public void testInsertOfficeNotExist() {
        odao.insertOffice(NOT_EXIST);
        assertEquals(NOT_EXIST, odao.findOfficeById(NOT_EXIST_Id));
    }

    @Test
    public void testUpdateOfficeExist() {
        EXIST.setCity("abvg");
        EXIST.setSales(new BigDecimal(123));
        odao.updateOffice(EXIST);
        assertEquals(EXIST, odao.findOfficeById(EXIST_Id));
    }

    @Test
    public void testDeleteOfficeExist() {
        odao.deleteOffice(EXIST_Id);
        assertNull(odao.findOfficeById(EXIST_Id));

    }

    @Test
    public void testUpdateOfficeNotExist() {
        odao.updateOffice(NOT_EXIST);
        assertNull(odao.findOfficeById(NOT_EXIST_Id));
    }
}
