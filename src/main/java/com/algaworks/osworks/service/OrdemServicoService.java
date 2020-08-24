package com.algaworks.osworks.service;

import com.algaworks.osworks.exception.NegocioException;
import com.algaworks.osworks.model.Cliente;
import com.algaworks.osworks.model.OrdemServico;
import com.algaworks.osworks.model.StatusOrdemServico;
import com.algaworks.osworks.repository.ClienteRepository;
import com.algaworks.osworks.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public OrdemServico criar(OrdemServico ordemServico){
        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));

        ordemServico.setStatusOrdemServico(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(LocalDateTime.now());

        return ordemServicoRepository.save(ordemServico);
    }
}
