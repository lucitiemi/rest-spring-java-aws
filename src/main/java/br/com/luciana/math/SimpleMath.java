package br.com.luciana.math;

import java.util.List;

public class SimpleMath {

	
	// ADIÇÃO
	public Double sum(Double numberOne,Double numberTwo) {
		return numberOne + numberTwo;
	}
	
	
	// SUBTRAÇÃO
	public Double sub(Double numberOne,Double numberTwo) {
		return numberOne - numberTwo;
	}
	
	// MULTIPLICAÇÃO
	public Double mult(Double numberOne,Double numberTwo) {
		return numberOne * numberTwo;
	}
	
	// DIVISÃO
	public Double div(Double numberOne,Double numberTwo) {
		return numberOne / numberTwo;
	}
	
	// MÉDIA
	public Double media(List<Double> numbers) {
		double sum = 0;
        for (Double num : numbers) {
            sum += num;
        }
        return sum / numbers.size();
	}
	
	// RAIZ QUADRADA
	public Double squ(Double number) {
		return Math.sqrt(number);
	}
}
