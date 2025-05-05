package com.dsy2201.Semana_9.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dsy2201.Semana_9.clases.Evento;

public interface EventoRepositorio extends JpaRepository<Evento, Integer> {
}
