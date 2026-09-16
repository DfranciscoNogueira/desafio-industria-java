package br.com.industria;

import br.com.industria.model.Funcionario;
import br.com.industria.service.FuncionarioService;
import br.com.industria.util.Formatador;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Principal {

    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");
    private static final FuncionarioService SERVICE = new FuncionarioService();

    public static void main(String[] args) {
        // 3.1
        List<Funcionario> funcionarios = criarFuncionarios();
        // 3.2
        SERVICE.removerPorNome(funcionarios, "João");
        // 3.3
        titulo("3.3 - Funcionários");
        funcionarios.forEach(Principal::imprimirFuncionario);
        // 3.4
        SERVICE.aplicarAumento(funcionarios, new BigDecimal("0.10"));
        // 3.5
        Map<String, List<Funcionario>> porFuncao = SERVICE.agruparPorFuncao(funcionarios);
        // 3.6
        titulo("3.6 - Funcionários agrupados por função");
        porFuncao.forEach((funcao, lista) -> {
            System.out.println("\n" + funcao + ":");
            lista.forEach(Principal::imprimirFuncionario);
        });
        // 3.8
        titulo("3.8 - Aniversariantes dos meses 10 e 12");
        SERVICE.aniversariantesNosMeses(funcionarios, 10, 12).forEach(Principal::imprimirFuncionario);
        // 3.9
        titulo("3.9 - Funcionário com maior idade");
        SERVICE.maisVelho(funcionarios).ifPresent(f -> System.out.printf("Nome: %s | Idade: %d anos%n", f.getNome(), SERVICE.idade(f, Clock.systemDefaultZone())));
        // 3.10
        titulo("3.10 - Funcionários em ordem alfabética");
        SERVICE.ordenarPorNome(funcionarios).forEach(f -> System.out.println(f.getNome()));
        // 3.11
        titulo("3.11 - Total dos salários");
        System.out.println("R$ " + Formatador.numero(SERVICE.totalSalarios(funcionarios)));
        // 3.12
        titulo("3.12 - Quantidade de salários mínimos");
        funcionarios.forEach(f -> System.out.printf("%s: %s salários mínimos%n", f.getNome(), Formatador.numero(SERVICE.quantidadeSalariosMinimos(f, SALARIO_MINIMO))));
    }

    public static List<Funcionario> criarFuncionarios() {
        return new ArrayList<>(List.of(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
                new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
                new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));
    }

    private static void imprimirFuncionario(Funcionario f) {
        System.out.printf("Nome: %-8s | Nascimento: %s | Salário: %12s | Função: %s%n", f.getNome(), Formatador.data(f.getDataNascimento()), Formatador.numero(f.getSalario()), f.getFuncao());
    }

    private static void titulo(String texto) {
        System.out.println("\n============================================================\n" + texto + "\n============================================================");
    }

}
