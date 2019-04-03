package boot.service;

import boot.entity.Office;

import java.math.BigDecimal;
import java.util.Set;

public interface OfficeService {
    Set<Office> getAllOffices();

    Office findOfficeById(BigDecimal id);

    void insertOffice(Office offcie);

    void updateOffice(Office offcie);

    void deleteOffice(BigDecimal id);
}
