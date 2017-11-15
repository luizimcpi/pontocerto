package com.devlhse.pontocerto.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.devlhse.pontocerto.entities.Empresa;
import com.devlhse.pontocerto.repositories.EmpresaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmpresaServiceTest {
	
	@MockBean
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private EmpresaService empresaService;
	
	private static final String CNPJ_VALIDO = "75.507.119/0001-54";
	
	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.empresaRepository.findByCnpj(Mockito.anyString())).willReturn(new Empresa());
		BDDMockito.given(this.empresaRepository.save(Mockito.any(Empresa.class))).willReturn(new Empresa());
	}
	
	@Test
	public void deveBuscarPorCnpj() {
		Optional<Empresa> empresa = this.empresaService.buscaPorCnpj(CNPJ_VALIDO);
		assertTrue(empresa.isPresent());
	}

	@Test
	public void devePersistirUmaEmpresa() {
		Empresa empresa = this.empresaService.persistir(new Empresa());
		assertNotNull(empresa);
	}
}
