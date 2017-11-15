package com.devlhse.pontocerto.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devlhse.pontocerto.entities.Empresa;
import com.devlhse.pontocerto.repositories.EmpresaRepository;
import com.devlhse.pontocerto.services.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);

	@Autowired
	private EmpresaRepository empresaRepository;

	@Override
	public Optional<Empresa> buscaPorCnpj(String cnpj) {
		log.info("Buscando uma empresa por CNPJ {}", cnpj);
		return Optional.ofNullable(empresaRepository.findByCnpj(cnpj));
	}

	@Override
	public Empresa persistir(Empresa empresa) {
		log.info("Persistindo uma empresa  {}", empresa);
		return this.empresaRepository.save(empresa);
	}

}
