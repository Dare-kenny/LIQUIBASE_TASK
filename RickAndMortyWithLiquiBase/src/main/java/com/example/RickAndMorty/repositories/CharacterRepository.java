package com.example.RickAndMorty.repositories;

import com.example.RickAndMorty.schemas.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<CharacterEntity,Integer> {
}
