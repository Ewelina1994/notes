package pl.klobut.notesapinew.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import pl.klobut.notesapinew.manager.NotebookMenager;
import pl.klobut.notesapinew.model.Notebook;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.assertj.core.api.Assertions.assertThat;



@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser(username = "jan", password = "1234", roles = "USER")
@Sql("/test.sql")
public class NotebookControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    NotebookMenager notebookMenager;

    @Test
    public void getNotebookById() {
        ResponseEntity<Notebook> response = testRestTemplate.getForEntity("/api/notebooks/101", Notebook.class);
        Optional<Notebook> serchNotebook = notebookMenager.findById(101L);
        Assertions.assertEquals(101, serchNotebook.get().getId());
        Assertions.assertEquals("poniedziałek", serchNotebook.get().getName());
    }

    @Test
    public void testNotebookNameMustNotBlanck() throws Exception {
        Notebook notebook = new Notebook("");
        String url = "/api/notebooks";

        mockMvc.perform(
                post(url)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(notebook))
        ).andExpect(status().isBadRequest())
        .andDo(print());
    }

    @Test
    public void testCreateNewNotebook() throws Exception {
        Notebook newNotebook = new Notebook("Weednesday");

        String json = objectMapper.writeValueAsString(newNotebook);
        String url="/api/notebooks";
        MvcResult mvcResult = mockMvc.perform(
                post(url)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        JSONObject obj=(JSONObject) JSONValue.parse(response);
        Integer idNotebook= (Integer) obj.get("id");

        Optional<Notebook> notebook = notebookMenager.findById(Long.valueOf(idNotebook));

        assertThat(notebook.get().getName()).isEqualTo(newNotebook.getName());
    }

    @Test
    public void testUpdateNotebook() throws Exception {
        Notebook updateNotebook = new Notebook((long)102, "Friday");


        String url = "/api/notebooks";
        MvcResult mvcResult = mockMvc.perform(post(url)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(updateNotebook))
        ).andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        JSONObject obj=(JSONObject) JSONValue.parse(response);
        Integer idNotebook= (Integer) obj.get("id");

        Optional<Notebook> notebook = notebookMenager.findById(Long.valueOf(idNotebook));

        assertThat(notebook.get().getName()).isEqualTo(updateNotebook.getName());
    }

    @Test
    public void testDeleteNotebook() throws Exception {
        Long idNotebook = 101L;
        String url = "/api/notebooks/" + idNotebook;

        mockMvc.perform(delete(url)).andExpect(status().isOk());

        Optional<Notebook> result = notebookMenager.findById(idNotebook);


        assertThat(result).isNotPresent();
    }
}
