package fr.formation.queryservice.messaging;

import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.formation.queryservice.model.Commentaire;
import fr.formation.queryservice.model.Produit;
import fr.formation.queryservice.repo.ICommentaireRepository;
import fr.formation.queryservice.repo.IProduitRepository;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class CommentaireEventHandler {
    private final ICommentaireRepository repoCommentaire;
    private final IProduitRepository repoProduit;
    
    @Bean
    public Consumer<CommentaireCreatedEvent> commentaireCreated() {
        return evt -> {
            Commentaire commentaire = Commentaire.builder()
                .id(evt.getCommentaireId())
                .texte(evt.getTexte())
                .produitId(evt.getProduitId())
                .noteQualite(evt.getNoteQualite())
                .noteRapport(evt.getNoteRapport())
                .noteFacilite(evt.getNoteFacilite())
                .build();

            this.repoCommentaire.save(commentaire);

            Optional<Produit> optProduit = this.repoProduit.findById(evt.getProduitId());

            if (optProduit.isPresent()) {
                optProduit.get().setNote(commentaire.getNote());
                this.repoProduit.save(optProduit.get());
            }
        };
    }
}
