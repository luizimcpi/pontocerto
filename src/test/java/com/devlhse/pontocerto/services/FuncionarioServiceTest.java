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

import com.devlhse.pontocerto.entities.Funcionario;
import com.devlhse.pontocerto.repositories.FuncionarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioServiceTest {
	
	@MockBean
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	private static final String CPF_VALIDO = "768.236.538-25";
	private static final String EMAIL_VALIDO = "devlhse@email.com";
	
	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.funcionarioRepository.findByCpf(Mockito.anyString())).willReturn(new Funcionario());
		BDDMockito.given(this.funcionarioRepository.findByEmail(Mockito.anyString())).willReturn(new Funcionario());
		BDDMockito.given(this.funcionarioRepository.findOne(Mockito.anyLong())).willReturn(new Funcionario());
		BDDMockito.given(this.funcionarioRepository.save(Mockito.any(Funcionario.class))).willReturn(new Funcionario());
	}
	
	@Test
	public void deveBuscarPorCpf() {
		Optional<Funcionario> funcionario = this.funcionarioService.buscarPorCpf(CPF_VALIDO);
		assertTrue(funcionario.isPresent());
	}
	
	@Test
	public void deveBuscarPorEmail() {
		Optional<Funcionario> funcionario = this.funcionarioService.buscarPorEmail(EMAIL_VALIDO);
		assertTrue(funcionario.isPresent());
	}
	
	@Test
	public void deveBuscarPorId() {
		Optional<Funcionario> funcionario = this.funcionarioService.buscarPorId(1L);
		assertTrue(funcionario.isPresent());
	}

	@Test
	public void devePersistirUmaFuncionario() {
		Funcionario funcionario =  this.funcionarioService.persistir(new Funcionario());
		assertNotNull(funcionario);
	}
}
