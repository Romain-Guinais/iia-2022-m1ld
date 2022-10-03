package fr.formation.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.formation.service.ProduitService;

@SpringBootTest
@AutoConfigureMockMvc
public class ProduitControllerIntegrationTest {
    private final static String URL = "/produit%s";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProduitService srvProduit;

    @Test
    public void testAdd() throws Exception {
        int size = this.srvProduit.findAll().size() + 1;

        this.mockMvc
            .perform(
                MockMvcRequestBuilders.post(URL.formatted("/ajouter"))
                .param("nom", "TEST")
                .param("prix", "azerty")
                .param("fournisseurId", "3")
            )
            .andExpect(MockMvcResultMatchers.status().isBadRequest());

        this.mockMvc
            .perform(
                MockMvcRequestBuilders.post(URL.formatted("/ajouter"))
                .param("nom", "TEST")
                .param("prix", "50")
            )
            .andExpect(MockMvcResultMatchers.status().isBadRequest());

        this.mockMvc
            .perform(
                MockMvcRequestBuilders.post(URL.formatted("/ajouter"))
                .param("nom", "TEST")
                .param("prix", "50")
                .param("fournisseurId", "3")
            )
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

        Assertions.assertEquals(size, this.srvProduit.findAll().size());
    }

    @Test
    public void testEdit() throws Exception {
        Assertions.assertNotEquals("TEST", this.srvProduit.findById(2).getNom());
        
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.post(URL.formatted("/modifier?id=2"))
                .param("nom", "TEST")
            )
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

        Assertions.assertEquals("TEST", this.srvProduit.findById(2).getNom());
    }
}
