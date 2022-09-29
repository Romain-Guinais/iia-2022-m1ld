package fr.formation.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.formation.model.Produit;
import fr.formation.request.ProduitRequest;
import fr.formation.service.FournisseurService;
import fr.formation.service.ProduitService;

@WebMvcTest(ProduitController.class)
public class ProduitControllerTest {
    private final static String URL = "/produit%s";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProduitService srvProduit;

    @MockBean
    private FournisseurService srvFournisseur;

    @Test
    public void shouldFindAllOk() throws Exception {
        this.mockMvc
            .perform(MockMvcRequestBuilders.get(URL.formatted("")))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.model().attributeExists("produits"))
            .andExpect(MockMvcResultMatchers.view().name("produit/list"));

        Mockito.verify(this.srvProduit, Mockito.times(1)).findAll();
    }

    @Test
    public void shouldAddOk() throws Exception {
        Mockito.when(this.srvProduit.findById(Mockito.anyInt())).thenReturn(new Produit());

        this.mockMvc
            .perform(MockMvcRequestBuilders.get(URL.formatted("/ajouter")))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("produit"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("fournisseurs"))
            .andExpect(MockMvcResultMatchers.view().name("produit/edit"));

        Mockito.verify(this.srvProduit, Mockito.never()).findById(Mockito.anyInt());
    }

    @Test
    public void shouldPostAddOk() throws Exception {
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.post(URL.formatted("/ajouter"))
                .param("nom", "new nom")
                .param("prix", "50")
                .param("fournisseurId", "1")
            )
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/produit"));

        Mockito.verify(this.srvProduit, Mockito.times(1)).save(Mockito.any(ProduitRequest.class));
    }

    @Test
    public void shouldEditOk() throws Exception {
        Mockito.when(this.srvProduit.findById(Mockito.anyInt())).thenReturn(new Produit());

        this.mockMvc
            .perform(MockMvcRequestBuilders.get(URL.formatted("/modifier?id=1")))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.model().attributeExists("produit"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("fournisseurs"))
            .andExpect(MockMvcResultMatchers.view().name("produit/edit"));

        Mockito.verify(this.srvProduit, Mockito.times(1)).findById(1);
    }

    @Test
    public void shouldPostEditOk() throws Exception {
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.post(URL.formatted("/modifier?id=1"))
                .param("nom", "new nom")
                .param("prix", "50")
                .param("fournisseurId", "1")
            )
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/produit"));

        Mockito.verify(this.srvProduit, Mockito.times(1)).save(Mockito.any(ProduitRequest.class));
    }

    @Test
    public void shouldDeleteStatusRedirection() throws Exception {
        this.mockMvc
            .perform(MockMvcRequestBuilders.get(URL.formatted("/supprimer?id=1")))
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/produit"))
            .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("produit"));

        Mockito.verify(this.srvProduit, Mockito.times(1)).deleteById(1);
    }
}
