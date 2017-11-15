package com.devlhse.pontocerto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devlhse.pontocerto.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	
//	@Transactional( readOnly = true )
	Empresa findByCnpj(String cnpj);
}
