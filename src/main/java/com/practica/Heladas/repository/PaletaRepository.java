package com.practica.Heladas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.practica.Heladas.documentos.Paleta;

public interface PaletaRepository extends MongoRepository<Paleta, String> {
}
