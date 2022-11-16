package fr.formation.queryservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("produit")
@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class Produit {
    @Id
    private int id;
    private String nom;
    private double prix;
    private int note;
}