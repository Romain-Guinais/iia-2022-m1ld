package fr.formation.memento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.formation.flyweight.Caractere;
import lombok.Getter;

@Getter
public class MementoTexte implements IMemento {
    private List<Caractere> caracteres = new ArrayList<>();
    private LocalDateTime date = LocalDateTime.now();
}
