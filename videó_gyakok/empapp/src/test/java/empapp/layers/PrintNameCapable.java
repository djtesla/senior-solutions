package empapp.layers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public interface PrintNameCapable {

    @BeforeEach
    default void init(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName());

    }
}
