package com.bc.ecommerce.domain.operational;

import com.bc.ecommerce.utils.ClassTest;

public class PricesTest extends ClassTest<Prices> {

   @Override
   protected Prices getInstance() {
      testEquals();
      return new Prices();
   }

}
