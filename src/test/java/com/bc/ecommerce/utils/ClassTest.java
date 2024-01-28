package com.bc.ecommerce.utils;

import org.junit.Assert;
import org.junit.Test;

import javax.validation.constraints.NotNull;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.UUID.randomUUID;
import static org.junit.Assert.assertEquals;

public abstract class ClassTest<T> {

   private static HashMap<Class<?>, Object> defaultObject = new LinkedHashMap<>();
   
   protected boolean equals = false;

   static {
      defaultObject.put(Boolean.class, Boolean.TRUE);
      defaultObject.put(boolean.class, Boolean.TRUE);
      defaultObject.put(int.class, 1);
      defaultObject.put(Long.class, Long.MIN_VALUE);
      defaultObject.put(Integer.class, 1);
      defaultObject.put(BigDecimal.class, new BigDecimal(1));
      defaultObject.put(Float.class, 1.0);
      defaultObject.put(Double.class, 1.0);
      defaultObject.put(String.class, "20200101");
      defaultObject.put(List.class, new ArrayList<String>());
      defaultObject.put(Timestamp.class, new Timestamp(new Date().getTime()));
      defaultObject.put(Date.class, new Date());
      defaultObject.put(Instant.class, Instant.now());
      defaultObject.put(OffsetDateTime.class, OffsetDateTime.now());
      defaultObject.put(InputStream.class, new ByteArrayInputStream("1".getBytes()));
      defaultObject.put(UUID.class, randomUUID());
      defaultObject.put(Map.class, new HashMap<String, Object>());
   }

   protected abstract T getInstance();
   
   @Test
   public void gettersAndSetters() throws Exception {
      T obj = getInstance();
      
      List<Method> getters = new ArrayList<>();
      List<Method> setters = new ArrayList<>();
      List<String> accessors = Arrays.stream(obj.getClass().getDeclaredFields())
              .map(field -> field.getName().toLowerCase())
              .map(field -> List.of("get" + field, "set" + field, "is" + field))
              .flatMap(list -> Stream.of(list.toArray(new String[0])))
              .collect(Collectors.toList());

      for (final Method method : obj.getClass().getMethods()) {
         final String methodName = method.getName().toLowerCase();
         if (!accessors.contains(methodName)) {
            continue;
         }

         if (methodName.startsWith("get") && method.getParameters().length == 0) {
            getters.add(method);

         } else if (methodName.startsWith("set") && method.getParameters().length == 1) {
            setters.add(method);

         } else if (methodName.startsWith("is") && method.getParameters().length == 0) {
            getters.add(method);
         }
      }
      testSetters(obj, setters);
      testGetters(obj, getters);

      if(equals) {
         equalsAndHasCode(obj);
      }
   }

   private void testGetters(Object obj, List<Method> getters) throws Exception {
      for (Method method : getters) {
         Assert.assertNotNull(method.invoke(obj));
      }
   }

   private void testSetters(Object obj, List<Method> setters) throws Exception {
      for (Method method : setters) {
         Object parameter = defaultObject.get(method.getParameterTypes()[0]);

         if (parameter == null) {
            parameter = newInstance(method.getParameterTypes()[0]);
         }

         method.invoke(obj, parameter);
      }
   }

   @NotNull
   private Object newInstance(Class<?> clazz) throws NoSuchMethodException, InstantiationException,
           IllegalAccessException, InvocationTargetException {
      Constructor<?> constructor = clazz.getDeclaredConstructor();
      constructor.setAccessible(true);
      return constructor.newInstance();
   }

   private void equalsAndHasCode(Object obj) throws InstantiationException, IllegalAccessException,
           InvocationTargetException, NoSuchMethodException {
      assertEquals(newInstance(obj.getClass()), newInstance(obj.getClass()));
      assertEquals(newInstance(obj.getClass()).hashCode(), newInstance(obj.getClass()).hashCode());
   }
   
   public void testEquals() {
      equals = true;
   }
}
