package com.devlhse.pontocerto.services;

import java.util.Optional;

import com.devlhse.pontocerto.entities.Empresa;

public interface EmpresaService {

	Optional<Empresa> buscaPorCnpj(String cnpj);

	Empresa persistir(Empresa empresa);

}
