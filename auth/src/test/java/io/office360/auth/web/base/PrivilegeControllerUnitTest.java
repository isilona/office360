package io.office360.auth.web.base;

import io.office360.auth.web.privilege.PrivilegeController;
import io.office360.auth.web.privilege.PrivilegeDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static io.office360.auth.util.Office360AuthMappings.PRIVILEGES;
import static io.office360.common.web.WebConstants.PATH_SEP;
import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
public class PrivilegeControllerUnitTest {

    private MockMvc mvc;

    @MockBean
    private PrivilegeController privilegeController;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .standaloneSetup(privilegeController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @Test
    public void getPrivileges() throws Exception {

        PrivilegeDto arrival = new PrivilegeDto();
        arrival.setName("PrivilegeName");

        List<PrivilegeDto> allArrivals = singletonList(arrival);

        given(privilegeController.
                findAll(any(HttpServletRequest.class), any(UriComponentsBuilder.class), any(HttpServletResponse.class)))
                .willReturn(allArrivals);


        mvc.perform(get(PATH_SEP + PRIVILEGES)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(arrival.getName())));
    }

}
