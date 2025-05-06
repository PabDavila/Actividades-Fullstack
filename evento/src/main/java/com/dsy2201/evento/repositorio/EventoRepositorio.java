package com.dsy2201.evento.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dsy2201.evento.clases.Evento;

public interface EventoRepositorio extends JpaRepository<Evento, Integer> {
}
