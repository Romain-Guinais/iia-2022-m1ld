package fr.formation.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.formation.service.FournisseurService;

@SpringBootTest
@AutoConfigureMockMvc
public class FournisseurControllerIntegrationTest {
    private final static String URL = "/fournisseur%s";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FournisseurService srvFournisseur;

    @Test
    public void testAdd() throws Exception {
        int size = this.srvFournisseur.findAll().size() + 1;

        this.mockMvc
            .perform(
                MockMvcRequestBuilders.post(URL.formatted("/ajouter"))
                .param("nom", "TEST")
            )
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

        Assertions.assertEquals(size, this.srvFournisseur.findAll().size());
    }

    @Test
    public void testEdit() throws Exception {
        Assertions.assertNotEquals("TEST", this.srvFournisseur.findById(1).getNom());
        
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.post(URL.formatted("/modifier?id=1"))
                .param("nom", "TEST")
            )
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

        Assertions.assertEquals("TEST", this.srvFournisseur.findById(1).getNom());
    }
}
