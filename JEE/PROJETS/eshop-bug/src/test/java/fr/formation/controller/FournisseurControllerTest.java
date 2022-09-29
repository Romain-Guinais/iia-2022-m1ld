package fr.formation.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.formation.model.Fournisseur;
import fr.formation.request.FournisseurRequest;
import fr.formation.service.FournisseurService;

@WebMvcTest(FournisseurController.class)
public class FournisseurControllerTest {
    private final static String URL = "/fournisseur%s";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FournisseurService srvFournisseur;

    @Test
    public void shouldFindAllOk() throws Exception {
        this.mockMvc
            .perform(MockMvcRequestBuilders.get(URL.formatted("")))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.model().attributeExists("fournisseurs"))
            .andExpect(MockMvcResultMatchers.view().name("fournisseur/list"));

        Mockito.verify(this.srvFournisseur, Mockito.times(1)).findAll();
    }

    @Test
    public void shouldFindByIdOk() throws Exception {
        Mockito.when(this.srvFournisseur.findDetailedById(1)).thenReturn(new Fournisseur());
        
        this.mockMvc
            .perform(MockMvcRequestBuilders.get(URL.formatted("/details?id=1")))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.model().attributeExists("fournisseur"))
            .andExpect(MockMvcResultMatchers.view().name("fournisseur/details"));

        Mockito.verify(this.srvFournisseur, Mockito.never()).findById(1);
        Mockito.verify(this.srvFournisseur, Mockito.times(1)).findDetailedById(1);
    }

    @Test
    public void shouldAddOk() throws Exception {
        Mockito.when(this.srvFournisseur.findById(Mockito.anyInt())).thenReturn(new Fournisseur());

        this.mockMvc
            .perform(MockMvcRequestBuilders.get(URL.formatted("/ajouter")))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("fournisseur"))
            .andExpect(MockMvcResultMatchers.view().name("fournisseur/edit"));

        Mockito.verify(this.srvFournisseur, Mockito.never()).findById(Mockito.anyInt());
    }

    @Test
    public void shouldPostAddOk() throws Exception {
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.post(URL.formatted("/ajouter"))
                .param("nom", "new nom")
            )
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/fournisseur"));

        Mockito.verify(this.srvFournisseur, Mockito.times(1)).save(Mockito.any(FournisseurRequest.class));
    }

    @Test
    public void shouldEditOk() throws Exception {
        Mockito.when(this.srvFournisseur.findById(Mockito.anyInt())).thenReturn(new Fournisseur());

        this.mockMvc
            .perform(MockMvcRequestBuilders.get(URL.formatted("/modifier?id=1")))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.model().attributeExists("fournisseur"))
            .andExpect(MockMvcResultMatchers.view().name("fournisseur/edit"));

        Mockito.verify(this.srvFournisseur, Mockito.times(1)).findById(1);
    }

    @Test
    public void shouldPostEditOk() throws Exception {
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.post(URL.formatted("/modifier?id=1"))
                .param("nom", "new nom")
            )
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/fournisseur"));

        Mockito.verify(this.srvFournisseur, Mockito.times(1)).save(Mockito.any(FournisseurRequest.class));
    }

    @Test
    public void shouldDeleteStatusRedirection() throws Exception {
        this.mockMvc
            .perform(MockMvcRequestBuilders.get(URL.formatted("/supprimer?id=1")))
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/fournisseur"))
            .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("fournisseur"));

        Mockito.verify(this.srvFournisseur, Mockito.times(1)).deleteById(1);
    }
}
