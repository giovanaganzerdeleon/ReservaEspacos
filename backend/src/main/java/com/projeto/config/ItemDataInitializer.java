package com.projeto.config;

import com.projeto.model.ItemCampus;
import com.projeto.repository.ItemCampusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemDataInitializer implements CommandLineRunner {

    private final ItemCampusRepository itemRepository;

    public ItemDataInitializer(ItemCampusRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void run(String... args) {
        if (itemRepository.count() == 0) {
            itemRepository.saveAll(List.of(
                    new ItemCampus("Projetor multimídia", "Audiovisual", 2),
                    new ItemCampus("Notebook", "Informática", 5),
                    new ItemCampus("Kit de laboratório", "Laboratório", 3),
                    new ItemCampus("Lousa móvel", "Material de sala", 1),
                    new ItemCampus("Kit de canetões (4 cores)", "Material de sala", 10)
            ));
        }
    }
}
