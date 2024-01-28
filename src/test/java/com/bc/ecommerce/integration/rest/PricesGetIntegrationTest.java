package com.bc.ecommerce.integration.rest;

import com.bc.ecommerce.boot.spring.config.EcommerceRecorderSpringBootService;
import com.bc.ecommerce.utils.IntegrationTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.TimeZone;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(Parameterized.class)
@SpringBootTest(classes = EcommerceRecorderSpringBootService.class)
@Sql(scripts = "/sql/fill_prices_relation.sql")
@Transactional
public class PricesGetIntegrationTest extends IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private final String productId;

    private final String brandId;

    private final String issueDate;

    private final String output;

    public PricesGetIntegrationTest(String productId, String brandId, String issueDate, String output) throws IOException {
        this.productId = productId;
        this.brandId = brandId;
        this.issueDate = issueDate;
        this.output = Files.readString(Path.of(OUTPUT_FILES_PATH + output));
    }

    @Parameterized.Parameters(name = "Test for: {0} - {1} - {2} - {3}")
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(new Object[][]{
                {"35455", "1", "2020-06-14T10:00:00.000Z", "price_test_1_get.json"},
                {"35455", "1", "2020-06-14T16:00:00.000Z", "price_test_2_get.json"},
                {"35455", "1", "2020-06-14T21:00:00.000Z", "price_test_1_get.json"},
                {"35455", "1", "2020-06-15T10:00:00.000Z", "price_test_3_get.json"},
                {"35455", "1", "2020-06-16T21:00:00.000Z", "price_test_4_get.json"}
        });
    }

    @BeforeClass
    public static void setUp() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Test
    public void priceGetHappyPath() throws Exception {
        MockHttpServletRequestBuilder mockMvcRequestBuilders =
                         get("/price?product_id=" + productId + "&brand_id=" + brandId + "&issue_date=" + issueDate + "")
                        .header("X-B3-TraceId", "123")
                        .header("Authorization", "231")
                        .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(mockMvcRequestBuilders).andExpect(status().isOk()).andReturn();

        if (mvcResult.getResponse().getStatus() == HttpStatus.OK.value()) {
            JSONAssert.assertEquals(output, mvcResult.getResponse().getContentAsString(), JSONCompareMode.LENIENT);
        }

    }


}
