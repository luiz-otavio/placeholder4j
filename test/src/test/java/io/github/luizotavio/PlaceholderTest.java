package io.github.luizotavio;

import io.github.luizotavio.cache.DefaultPlaceholderCache;
import io.github.luizotavio.exception.InternalPlaceholderException;
import io.github.luizotavio.impl.ConsumerPlaceholder;
import io.github.luizotavio.impl.VariablePlaceholder;
import io.github.luizotavio.replacer.DefaultPlaceholderReplacer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlaceholderTest {

    private static DefaultPlaceholderReplacer replacer;

    @BeforeAll
    public static void setupAll() {
        replacer = new DefaultPlaceholderReplacer('%');

        DefaultPlaceholderCache commonPlaceholderCache = replacer.getCache();

        commonPlaceholderCache.register(new VariablePlaceholder("%test-unit-case-1%"));
        commonPlaceholderCache.register(new VariablePlaceholder("%test-unit-case-2%"));
        commonPlaceholderCache.register(new ConsumerPlaceholder("%test-unit-case-3%", () -> "Luiz Otávio"));
    }

    @Test
    @DisplayName("Test unit case 1")
    public void testUnitCase1() throws InternalPlaceholderException {
        Assertions.assertEquals(
          "1",
          replacer.replace("%test-unit-case-1%", "1")
        );
    }

    @Test
    @DisplayName("Test unit case 2")
    public void testUnitCase2() throws InternalPlaceholderException {
        Assertions.assertEquals(
          "Jonh",
          replacer.replace("%test-unit-case-2%", "Jonh")
        );
    }

    @Test
    @DisplayName("Test unit case 3")
    public void testUnitCase3() throws InternalPlaceholderException {
        Assertions.assertEquals(
          "Luiz Otávio",
          replacer.replace("%test-unit-case-3%")
        );
    }

    @Test
    @DisplayName("Performance test")
    public void performanceTest() throws InternalPlaceholderException {
        String message = "Hello %test-unit-case-1%";

        long start = System.currentTimeMillis();

        for (int i = 0; i < 1000000; i++) {
            replacer.replace(message);
        }

        long end = System.currentTimeMillis();

        System.out.println("Performance test: " + (end - start) + "ms");
    }

}
