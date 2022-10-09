package pl.niepracuj.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import pl.niepracuj.model.dto.advertisement.AdvertisementSearchCriteriaDto;
import pl.niepracuj.model.enums.TechnologyEnum;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.niepracuj.util.TestUtils.toJson;

@SpringBootTest
@AutoConfigureMockMvc
public class AdvertisementControllerIT {

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    @Sql("/sql/controller/advertisement.sql")
//    public void whenGetAllAdvertisements_thenReturnAdvertisements() throws Exception {
//        // when && then
//        mockMvc.perform(get("/adv/all")).andExpect(status().isOk())
//                .andExpect(jsonPath("$.size()", Matchers.equalTo(1)));
//    }

    @Test
    @Sql("/sql/controller/advertisement.sql")
    public void whenGetAdvertisementsByCriteria_thenOkResponse() throws Exception {
        // given
        var criteria = AdvertisementSearchCriteriaDto.builder()
                .technologyName(TechnologyEnum.JAVA).build();
        var criteriaJson = toJson(criteria);

        // when && then
        mockMvc.perform(post("/adv/search?page=0&size=10&sort=id,DESC")
                        .content(criteriaJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.equalTo(1)));
    }

}
