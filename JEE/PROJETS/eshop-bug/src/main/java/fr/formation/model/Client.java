package fr.formation.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Table(name = "client")
@PrimaryKeyJoinColumn(name = "cli_id")
@Getter @Setter
public class Client extends Personne {
    @Column(name = "cli_prenom")
    private String prenom;

    @Column(name = "cli_date_naissance")
    private LocalDate dateNaissance;

    @OneToMany(mappedBy = "cmd_client_id")
    private List<Commande> commandes;
}
