package br.com.industria.service;

import br.com.industria.Principal;
import br.com.industria.model.Funcionario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FuncionarioServiceTest {

    private FuncionarioService service;
    private List<Funcionario> funcionarios;

    @BeforeEach
    void setUp() {
        service = new FuncionarioService();
        funcionarios = Principal.criarFuncionarios();
    }

    @Test
    void deveCriarFuncionariosNaOrdemDaTabela() {
        assertEquals(10, funcionarios.size());
        assertEquals("Maria", funcionarios.get(0).getNome());
        assertEquals("Helena", funcionarios.get(9).getNome());
    }

    @Test
    void deveRemoverJoao() {
        service.removerPorNome(funcionarios, "João");
        assertEquals(9, funcionarios.size());
        assertTrue(funcionarios.stream().noneMatch(f -> f.getNome().equals("João")));
    }

    @Test
    void deveAplicarAumentoDeDezPorCento() {
        service.aplicarAumento(funcionarios, new BigDecimal("0.10"));
        assertEquals(new BigDecimal("2210.38"), funcionarios.get(0).getSalario());
    }

    @Test
    void deveAgruparPorFuncao() {
        Map<String, List<Funcionario>> map = service.agruparPorFuncao(funcionarios);
        assertEquals(3, map.get("Operador").size());
        assertEquals(2, map.get("Gerente").size());
    }

    @Test
    void deveEncontrarAniversariantesDeOutubroEDezembro() {
        List<Funcionario> r = service.aniversariantesNosMeses(funcionarios, 10, 12);
        assertEquals(List.of("Maria", "Miguel"), r.stream().map(Funcionario::getNome).toList());
    }

    @Test
    void deveEncontrarFuncionarioMaisVelho() {
        assertEquals("Caio", service.maisVelho(funcionarios).orElseThrow().getNome());
    }

    @Test
    void deveCalcularIdadeComDataControlada() {
        Funcionario caio = funcionarios.get(2);
        Clock clock = Clock.fixed(Instant.parse("2026-09-15T12:00:00Z"), ZoneOffset.UTC);
        assertEquals(65, service.idade(caio, clock));
    }

    @Test
    void deveOrdenarPorNome() {
        assertEquals(List.of("Alice", "Arthur", "Caio", "Heitor", "Helena", "Heloísa", "João", "Laura", "Maria", "Miguel"), service.ordenarPorNome(funcionarios).stream().map(Funcionario::getNome).toList());
    }

    @Test
    void deveSomarSalariosAposRemocaoEAumento() {
        service.removerPorNome(funcionarios, "João");
        service.aplicarAumento(funcionarios, new BigDecimal("0.10"));
        assertEquals(new BigDecimal("48509.25"), service.totalSalarios(funcionarios));
    }

    @Test
    void deveCalcularQuantidadeDeSalariosMinimos() {
        Funcionario maria = funcionarios.get(0);
        assertEquals(new BigDecimal("1.66"), service.quantidadeSalariosMinimos(maria, new BigDecimal("1212.00")));
    }

}
