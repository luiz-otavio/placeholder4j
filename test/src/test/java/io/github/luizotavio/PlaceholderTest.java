package io.github.luizotavio;

import io.github.luizotavio.cache.CommonPlaceholderCache;
import io.github.luizotavio.exception.InternalPlaceholderException;
import io.github.luizotavio.placeholder.CommonPlaceholder;
import io.github.luizotavio.replacer.CommonPlaceholderReplacer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlaceholderTest {

    private static CommonPlaceholderReplacer replacer;

    @BeforeAll
    public static void setupAll() {
        replacer = new CommonPlaceholderReplacer('%');

        CommonPlaceholderCache commonPlaceholderCache = replacer.getCache();

        commonPlaceholderCache.register(new CommonPlaceholder("%test-unit-case-1%", 1));
        commonPlaceholderCache.register(new CommonPlaceholder("%test-unit-case-2%", "Jonh"));
        commonPlaceholderCache.register(new CommonPlaceholder("%test-unit-case-3%", null));
    }

    @Test
    @DisplayName("Test unit case 1")
    public void testUnitCase1() throws InternalPlaceholderException {
        Assertions.assertEquals(
                "1",
                replacer.replace("%test-unit-case-1%", "")
        );
    }

    @Test
    @DisplayName("Test unit case 2")
    public void testUnitCase2() throws InternalPlaceholderException {
        Assertions.assertEquals(
                "Jonh",
                replacer.replace("%test-unit-case-2%", "")
        );
    }

    @Test
    @DisplayName("Test unit case 3")
    public void testUnitCase3() throws InternalPlaceholderException {
        Assertions.assertEquals(
                "null",
                replacer.replace("%test-unit-case-3%", "")
        );
    }

}
