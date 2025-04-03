package com.loanmanagement.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.loanmanagement.model.*;
import com.loanmanagement.repo.LoanApplicationsRepository;
import com.loanmanagement.repo.LoanTypesRepository;
import com.loanmanagement.repo.StatusesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

class LoanServiceTest {

    @Mock
    private LoanApplicationsRepository loanRepository;

    @Mock
    private StatusesRepository statusRepository;

    @Mock
    private LoanTypesRepository loanTypesRepository;

    @InjectMocks
    private LoanService loanService;

    private LoanApplicationsModel loanApplication;
    private StatusesModel status;
    private LoanTypes loanType;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Common data
        status = new StatusesModel();
        status.setStatus("pending");

        loanType = new LoanTypes();
        loanType.setId(1L);
        loanType.setLoanType("Personal Loan");

        loanApplication = new LoanApplicationsModel();
        loanApplication.setId(1L);
        loanApplication.setTitle("Car Loan");
        loanApplication.setDescription("Loan for purchasing a car");
        loanApplication.setAmount(new BigDecimal(10000));
        loanApplication.setStatus(status);
        loanApplication.setLoanType(loanType);
        loanApplication.setApplicationDate(LocalDate.now());
    }

    @Test
    void testCreateLoanWithoutTitle() {
        loanApplication.setTitle(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            loanService.createLoan(loanApplication);
        });

        assertEquals("Loan title is required", exception.getMessage());
    }

    @Test
    void testDeleteLoan() {
        // Simulates that the loan exists
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loanApplication));

        // Eliminate the loan
        boolean result = loanService.deleteLoan(1L);

        assertTrue(result);
        verify(loanRepository).delete(loanApplication); // Verify that the loan was eliminated
    }

    @Test
    void testCreateLoanWithoutDescription() {
        loanApplication.setDescription(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            loanService.createLoan(loanApplication);
        });

        assertEquals("Loan description is required", exception.getMessage());
    }

    @Test
    void testCreateLoanWithInvalidStatus() {
        loanApplication.setStatus(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            loanService.createLoan(loanApplication);
        });

        assertEquals("Pending status not found", exception.getMessage());
    }

    @Test
    void testFindLoanByIdNotFound() {
        // Pretends the loan does not exist
        when(loanRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<LoanApplicationsModel> loan = loanService.findLoanById(999L);

        assertFalse(loan.isPresent()); // Verify that the loan was not found
    }
}
