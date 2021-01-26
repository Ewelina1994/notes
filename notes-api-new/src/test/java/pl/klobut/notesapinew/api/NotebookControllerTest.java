package pl.klobut.notesapinew.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;
import pl.klobut.notesapinew.manager.NotebookMenager;
import pl.klobut.notesapinew.model.Note;
import pl.klobut.notesapinew.model.Notebook;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"pl.klobut.notesapinew"})
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "jan", password = "1234")
public class NotebookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private NotebookMenager notebookMenager;


    @Test
    public void testListNotebook() throws Exception {
        List<Notebook> listNotebook = new ArrayList<>();
        listNotebook.add(new Notebook((long) 1, "Monday"));
        listNotebook.add(new Notebook((long) 2, "Tuesday"));
        listNotebook.add(new Notebook((long) 3, "Wednesday"));
        listNotebook.add(new Notebook((long) 4, "Thursday"));
        Mockito.when(notebookMenager.findAll()).thenReturn(listNotebook);

        String url = "http://localhost:8080/api/notebooks/all";

        MvcResult mvcResult = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        System.out.println("Actually: " + actualJsonResponse);

        String expectedJsonResponse = objectMapper.writeValueAsString(listNotebook);
        System.out.println("Expected: " + expectedJsonResponse);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);
    }

    @Test
    public void testCreateNewNotebook() throws Exception {
        Notebook newNotebook = new Notebook("Weednesday");
        Mockito.when(notebookMenager.save(newNotebook)).thenReturn(newNotebook);
        String json = objectMapper.writeValueAsString(newNotebook);
        String url="/api/notebooks";
        MvcResult mvcResult = mockMvc.perform(
                post(url)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        System.out.println("Result: " + mvcResult.getResponse().getContentAsString());
        String response = mvcResult.getResponse().getContentAsString();
//        JSONObject obj=(JSONObject) JSONValue.parse(response);
//        Integer idNotebook= (Integer) obj.get("id");
//
//        Optional<Notebook> notebook = notebookMenager.findById(Long.valueOf(idNotebook));
//
//        assertThat(notebook.get().getName()).isEqualTo(newNotebook.getName());
    }

    @Test
    public void testDeleteNotebook() throws Exception {

        Long idNotbook = 1L;
        Mockito.doNothing().when(notebookMenager).deleteById(idNotbook);
       String url = "/api/notebooks/"+idNotbook;
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(notebookMenager, Mockito.times(1)).deleteById(idNotbook);
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
        .andDo(MockMvcResultHandlers.print());

        Mockito.verify(notebookMenager, Mockito.times(0)).save(notebook);

    }

    @Test
    public void testUpdateNotebook() throws Exception {
        Notebook existNotebook = new Notebook((long)1, "Friday");
        Notebook savedNotebook = new Notebook(1L, "Sunday");

        Mockito.when(notebookMenager.save(existNotebook)).thenReturn(savedNotebook);

        String url = "/api/notebooks";
        mockMvc.perform(post(url)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(existNotebook))
        ).andExpect(status().isOk())
        .andDo(MockMvcResultHandlers.print());
    }
}
