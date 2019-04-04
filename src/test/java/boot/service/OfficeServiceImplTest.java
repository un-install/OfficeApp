package boot.service;

import boot.entity.Office;
import boot.repository.OfficeDaoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class OfficeServiceImplTest {

    @Spy
    @InjectMocks
    private OfficeService officeService = new OfficeServiceImpl();
    @Mock
    private OfficeDaoImpl officeDao;

    private Office office1 = new Office(new BigDecimal(1));
    private Office office2 = new Office(new BigDecimal(2));

    @Test
    public void testGetAllOffices() {
        Set<Office> offices = new HashSet<>(Arrays.asList( new Office[] {office1, office2}));
        doReturn(offices).when(officeService).getAllOffices();
        Set<Office> result = officeService.getAllOffices();
        assertTrue(offices.containsAll(result) && result.containsAll(offices));
    }

    @Test
    public void testFindOfficeById() {
        doReturn(office1).when(officeDao).findOfficeById(any());
        Office result = officeService.findOfficeById(BigDecimal.valueOf(11));
        assertEquals(office1, result);
    }

    @Test
    public void testInsertOffice() {
        doNothing().when(officeDao).insertOffice(any());
        officeService.insertOffice(office1);
        verify(officeDao, times(1)).insertOffice(any());
    }

    @Test
    public void testUpdateOffice() {
        doNothing().when(officeDao).updateOffice(any());
        officeService.updateOffice(office1);
        verify(officeDao, times(1)).updateOffice(any());
    }

    @Test
    public void testDeleteOffice() {
        doNothing().when(officeDao).deleteOffice(any());
        officeService.deleteOffice(office1.getOffice());
        verify(officeDao, times(1)).deleteOffice(any());
    }
}
