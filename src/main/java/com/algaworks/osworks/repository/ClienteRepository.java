package com.algaworks.osworks.repository;

import com.algaworks.osworks.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public List<Cliente> findByNome(String nome);
    public List<Cliente> findByNomeContaining(String nome);

}
