package com.devlhse.pontocerto.utils;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtilsTest {
	private static final String SENHA = "123456";
	private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Test
	public void deveTestarSenhaNula() {
		assertNull(PasswordUtils.gerarBCrypt(null));
	}

	@Test
	public void deveGerarHashParaSenha() {
		String hash = PasswordUtils.gerarBCrypt(SENHA);
		assertTrue(bCryptPasswordEncoder.matches(SENHA, hash));
	}
}
