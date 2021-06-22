package locations;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SoftAssertionsExtension.class)
class LocationTest {

    LocationParser locationParser;

    @BeforeEach
    void init () {
        locationParser = new LocationParser();
    }

/*
Budapest,47.497912,19.040235
Aberdeen,57.9,2.9
Bremen,535.5,8.49
Dakar,14.40,17.28
Naples,40.50,14.15
Greenwich,51.477928,0
Null Island,0,0
 */

    @Test
    @DisplayName("Test method parseWithAssertEquals()")
    void testParseWithAssertEquals() {
        Location location = locationParser.parse("Aberdeen,57.9,2.9");

        assertAll(
                () -> assertEquals("Aberdeen", location.getName()),
                () -> assertEquals(57.9, location.getLat()),
                () -> assertEquals(2.9, location.getLon())
        );

    }

    @Test
    void testParseWithAssertJ() {
        Location location = locationParser.parse("Bremen,535.5,8.49");
        assertThat(location)
                .extracting(Location::getName)
                .isEqualTo("Bremen");
    }

    @Test
    void testParseThrowsException() {
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, ()-> locationParser.parse("Bremen,53x5.5,8.49"));
        assertEquals("Invalid coordinate", iae.getMessage());
    }

    @Test
    void testIsOnEquator() {
        Location location = locationParser.parse("Null Island,0,0");
        assertTrue(location.isOnEquator());
    }

    @Test
    void testIsOnPrimeMeridian() {
        Location location = locationParser.parse("Greenwich,51.477928,0");
        assertTrue(location.isOnPrimeMeridian());
    }

    @Test
    void testDifferentReferencesWithSameValues() {
        Location location1 = locationParser.parse("Greenwich,51.477928,0");
        Location location2 = locationParser.parse("Greenwich,51.477928,0");
        assertNotSame(location1, location2);
    }

    @Test
    void testAllAttributesWithSoft(SoftAssertions softly) {
        Location location = locationParser.parse("Naples,40.50,14.15");
        softly.assertThat(location.getName()).isEqualTo("Naples");
        softly.assertThat(location.getLat()).isEqualTo(40.50);
        softly.assertThat(location.getLon()).isEqualTo(14.15);

    }
}