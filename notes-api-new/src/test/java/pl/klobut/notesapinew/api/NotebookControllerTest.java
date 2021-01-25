package pl.klobut.notesapinew.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.agent.VirtualMachine;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.klobut.notesapinew.config.WebSecurityConfig;
import pl.klobut.notesapinew.manager.NotebookMenager;
import pl.klobut.notesapinew.model.Notebook;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"pl.klobut.notesapinew"})
@SpringBootTest
@AutoConfigureMockMvc
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
    @WithMockUser(username = "jan", password = "1234", roles = "USER")
    public void testListNotebook() throws Exception {
        System.out.println("it is ok");
        List<Notebook> listNotebook = new ArrayList<>();
        listNotebook.add(new Notebook((long) 1, "Monday"));
        listNotebook.add(new Notebook((long) 2, "Tuesday"));
        listNotebook.add(new Notebook((long) 3, "Wednesday"));
        listNotebook.add(new Notebook((long) 4, "Thursday"));
        Mockito.when(notebookMenager.findAll()).thenReturn(listNotebook);

        String url = "http://localhost:8080/api/notebooks/all";

        MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        System.out.println("Actually: " + actualJsonResponse);

        String expectedJsonResponse = objectMapper.writeValueAsString(listNotebook);
        System.out.println("Expected: " + expectedJsonResponse);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);
    }

    @Test
    @WithMockUser(username = "jan", password = "1234", roles = "USER")
    public void testCreateNewNotebook() throws Exception {
        Notebook newNotebook = new Notebook("Weednesday");
        Notebook saveNotebook = new Notebook((long)1, "Weednesday");
        Mockito.when(notebookMenager.save(newNotebook)).thenReturn(saveNotebook);

        String url="/api/notebooks";
        mockMvc.perform(
                post(url).content("application/json")
        .content(objectMapper.writeValueAsString(newNotebook))
        ).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "jan", password = "1234")
    public void testDeleteNotebook() throws Exception {
        Long notebookId = (long) 1;
        Mockito.doNothing().when(notebookMenager).deleteById(notebookId);

        String url = "/api/notebooks/"+notebookId;

        mockMvc.perform(get(url)).andExpect(status().isOk());
        Mockito.verify(notebookMenager, Mockito.times(1)).deleteById(notebookId);
//        Mockito.when(notebookMenager.deleteById(1L)).thenReturn("SUCCESS");
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/notebooks", 1L))
//                .andExpect(status().isOk());
    }
}
