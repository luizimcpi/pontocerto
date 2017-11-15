package com.devlhse.pontocerto.repositories;

import static org.junit.Assert.assertNotNull;
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
import com.devlhse.pontocerto.entities.Funcionario;
import com.devlhse.pontocerto.enums.PerfilEnum;
import com.devlhse.pontocerto.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioRepositoryTest {

	@Autowired
	private FuncionarioRepository funcionarioReposirory;

	@Autowired
	private EmpresaRepository empresaRepository;

	private static final String EMAIL_INVALIDO = "devlh@gmail.com";
	private static final String EMAIL_VALIDO = "devlhse@email.com";
	private static final String CPF_VALIDO = "768.236.538-25";
	private static final String CNPJ_VALIDO = "75.507.119/0001-54";

	@Before
	public void setUp() {
		Empresa empresa = this.empresaRepository.save(obterDadosEmpresa());
		this.funcionarioReposirory.save(obterDadosFuncionario(empresa));
	}

	@After
	public final void tearDown() {
		this.empresaRepository.deleteAll();
	}

	@Test
	public void deveBuscarFuncionarioPorEmail() {
		Funcionario funcionario = this.funcionarioReposirory.findByEmail(EMAIL_VALIDO);
		assertTrue(EMAIL_VALIDO.equals(funcionario.getEmail()));
	}

	@Test
	public void deveBuscarFuncionarioPorCpf() {
		Funcionario funcionario = this.funcionarioReposirory.findByCpf(CPF_VALIDO);
		assertTrue(CPF_VALIDO.equals(funcionario.getCpf()));
	}

	@Test
	public void deveBuscarFuncionarioPorEmailECpf() {
		Funcionario funcionario = this.funcionarioReposirory.findByCpfOrEmail(CPF_VALIDO, EMAIL_VALIDO);
		assertTrue(CPF_VALIDO.equals(funcionario.getCpf()));
		assertTrue(EMAIL_VALIDO.equals(funcionario.getEmail()));
		assertNotNull(funcionario);
	}

	@Test
	public void deveBuscarFuncionarioPorCpfComEmailInvalido() {
		Funcionario funcionario = this.funcionarioReposirory.findByCpfOrEmail(CPF_VALIDO, EMAIL_INVALIDO);
		assertTrue(CPF_VALIDO.equals(funcionario.getCpf()));
		assertNotNull(funcionario);
	}

	private Funcionario obterDadosFuncionario(Empresa empresa) {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Luiz Evangelista");
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
		funcionario.setSenha(PasswordUtils.gerarBCrypt("123456"));
		funcionario.setCpf(CPF_VALIDO);
		funcionario.setEmail(EMAIL_VALIDO);
		funcionario.setEmpresa(empresa);
		return funcionario;
	}

	private Empresa obterDadosEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("DEVLHSE LTDA");
		empresa.setCnpj(CNPJ_VALIDO);
		return empresa;
	}

}
