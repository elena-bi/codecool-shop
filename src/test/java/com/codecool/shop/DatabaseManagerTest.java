package com.codecool.shop;

import com.codecool.shop.dao.database.DatabaseManager;
import com.codecool.shop.util.ReadPropertyValues;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DatabaseManagerTest {
    
    private HashMap<String, String> configTestMap;

    @BeforeEach
    void createConfigTestMap() {
        configTestMap = new HashMap<>();
        configTestMap.put("user", "test");
        configTestMap.put("url", "localhost:1234");
        configTestMap.put("password", "password");
        configTestMap.put("dao", "memo");
    }

    @Test
    void setup_IncorrectDbUserName_ThrowsError() throws IOException {
        DatabaseManager dbManagerTest = new DatabaseManager();
        ReadPropertyValues propertyValuesTest = mock(ReadPropertyValues.class);
        when(propertyValuesTest.getPropertyValues()).thenReturn(configTestMap);

        assertThrows(NullPointerException.class, () -> dbManagerTest.setup(propertyValuesTest));
    }
}
