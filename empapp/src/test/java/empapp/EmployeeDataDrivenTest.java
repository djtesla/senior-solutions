package empapp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

public class EmployeeDataDrivenTest {

    private int[][] agesAndYears =  {{2000,0}, {2010,10}, {2030,30}};

    @Test
    @RepeatedTest(
            value = 3, name = "Get age test {currentRepetition}")
    void testGetAge(RepetitionInfo repetitionInfo) {
        assertEquals(agesAndYears[repetitionInfo.getCurrentRepetition()-1][1],
                new Employee("John Doe", 2000).getAge(agesAndYears[repetitionInfo.getCurrentRepetition()-1][0]));




    }
}
