package com.devlhse.pontocerto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devlhse.pontocerto.entities.Funcionario;

//@Transactional(readOnly = true)
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	Funcionario findByCpf(String cpf);
	
	Funcionario findByEmail(String email);
	
	Funcionario findByCpfOrEmail(String cpf, String email);

}
