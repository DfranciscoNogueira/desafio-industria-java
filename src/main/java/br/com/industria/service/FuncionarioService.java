package br.com.industria.service;

import br.com.industria.model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Clock;
import java.time.Period;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class FuncionarioService {

    public void removerPorNome(List<Funcionario> funcionarios, String nome) {
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase(nome));
    }

    public void aplicarAumento(List<Funcionario> funcionarios, BigDecimal percentual) {
        BigDecimal fator = BigDecimal.ONE.add(percentual);
        funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(fator).setScale(2, RoundingMode.HALF_UP)));
    }

    public Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        return funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao, LinkedHashMap::new, Collectors.toList()));
    }

    public List<Funcionario> aniversariantesNosMeses(List<Funcionario> funcionarios, int... meses) {
        Set<Integer> filtro = Arrays.stream(meses).boxed().collect(Collectors.toSet());
        return funcionarios.stream().filter(f -> filtro.contains(f.getDataNascimento().getMonthValue())).toList();
    }

    public Optional<Funcionario> maisVelho(List<Funcionario> funcionarios) {
        return funcionarios.stream().min(Comparator.comparing(Funcionario::getDataNascimento));
    }

    public int idade(Funcionario funcionario, Clock clock) {
        return Period.between(funcionario.getDataNascimento(), java.time.LocalDate.now(clock)).getYears();
    }

    public List<Funcionario> ordenarPorNome(List<Funcionario> funcionarios) {
        return funcionarios.stream().sorted(Comparator.comparing(Funcionario::getNome, String.CASE_INSENSITIVE_ORDER)).toList();
    }

    public BigDecimal totalSalarios(List<Funcionario> funcionarios) {
        return funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal quantidadeSalariosMinimos(Funcionario funcionario, BigDecimal salarioMinimo) {
        return funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
    }

}
