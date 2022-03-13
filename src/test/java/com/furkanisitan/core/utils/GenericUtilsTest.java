package com.furkanisitan.core.utils;

import com.furkanisitan.core.exceptions.NoSuchDeclaredFieldException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GenericUtilsTest {

    @Test
    void getFields_FieldCountIs1_GivenBaseClass() {
        var fields = GenericUtils.getFields(BaseClass.class);

        assertEquals(1, fields.length);
    }

    @Test
    void getFields_FieldCountIs2_GivenChildClass() {
        var fields = GenericUtils.getFields(ChildClass.class);

        assertEquals(2, fields.length);
    }

    @Test
    void getField_FieldFound_GivenChildClass() {
        var field = GenericUtils.getField(ChildClass.class, "name");

        assertEquals("name", field.getName());
    }

    @Test
    void getField_InheritedFieldFound_GivenChildClass() {
        var field = GenericUtils.getField(ChildClass.class, "id");

        assertEquals("id", field.getName());
    }

    @Test
    void getField_ThrowsNoSuchDeclaredFieldException_FieldNotFound() {

        assertThrows(NoSuchDeclaredFieldException.class, () -> GenericUtils.getField(ChildClass.class, "__"));
    }

    static class BaseClass {
        private int id;
    }


    static class ChildClass extends BaseClass {
        private String name;
    }

}