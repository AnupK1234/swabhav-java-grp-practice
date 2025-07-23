package com.aurionpro.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class MathServiceTest {

    @Test
    public void testDoComplexMath() {
        // Create a mock object
        Calculator mockCalculator = mock(Calculator.class);

        // Define behavior (stubbing)
        when(mockCalculator.add(2, 3)).thenReturn(5);
        when(mockCalculator.multiply(2, 3)).thenReturn(6);

        //  Inject mock into service
        MathService service = new MathService(mockCalculator);

        //  Call method
        int result = service.doComplexMath(2, 3);

        //  Verify result
        assertEquals(30, result);

        //  Verify method calls
        verify(mockCalculator).add(2, 3);
        verify(mockCalculator).multiply(2, 3);
    }
    @Test
    public void testWithSpy() {
        Calculator realCalculator = new Calculator();
        Calculator spyCalculator = spy(realCalculator);

        when(spyCalculator.add(2, 3)).thenReturn(100); // only add() is faked

        assertEquals(100, spyCalculator.add(2, 3));     // mocked
        assertEquals(6, spyCalculator.multiply(2, 3));  // real method
    }

}
