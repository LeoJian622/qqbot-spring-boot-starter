package io.github.qqbot.spring.api;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QQBotEndpointsTest {
    @Test
    void catalogContainsEveryDocumentedOperationWithoutDuplicates() {
        assertEquals(48, QQBotEndpoints.ALL.size());
        assertEquals(48, new HashSet<>(QQBotEndpoints.ALL).size());
        assertEquals(48, QQBotEndpoints.ALL.stream().filter(endpoint -> !endpoint.title().isBlank()).count());
    }

    @Test
    void currentFacadeContainsOneTypedMethodPerOperation() {
        long operationMethods = Arrays.stream(QQBotV2Api.class.getDeclaredMethods())
                .filter(method -> method.getParameterCount() == 1)
                .filter(method -> method.getParameterTypes()[0].getSimpleName().equals("Request"))
                .filter(method -> method.getParameterTypes()[0].getEnclosingClass() != null)
                .filter(method -> method.getParameterTypes()[0].getEnclosingClass().getPackageName()
                        .equals("io.github.qqbot.spring.model.v2"))
                .count();

        assertEquals(48, operationMethods);
    }
}
