package com.devlhse.pontocerto.repositories;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.devlhse.pontocerto.entities.Empresa;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmpresaRepositoryTest {

	@Autowired
	private EmpresaRepository empresaRepository;

	private static final String CNPJ_VALIDO = "75.507.119/0001-54";

	@Before
	public void setUp() throws Exception {
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("Empresa exemplo");
		empresa.setCnpj(CNPJ_VALIDO);
		this.empresaRepository.save(empresa);
	}

	@After
	public final void tearDown() {
		this.empresaRepository.deleteAll();
	}

	@Test
	public void deveBuscarEmpresaPorCnpj() {
		Empresa empresa = this.empresaRepository.findByCnpj(CNPJ_VALIDO);
		assertTrue(CNPJ_VALIDO.equals(empresa.getCnpj()));
	}
}
