package br.com.luciana.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.luciana.converters.NumberConverter;
import br.com.luciana.exceptions.UnsupportedMathOperationException;
import br.com.luciana.math.SimpleMath;

@RestController
public class MathController {

	private SimpleMath math = new SimpleMath();
	
	// ADIÇÃO
	@GetMapping("/sum/{numberOne}/{numberTwo}")
	public Double sum(@PathVariable(value= "numberOne") String numberOne, @PathVariable(value= "numberTwo") String numberTwo) throws Exception {
		
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		return math.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}
	
	
	// SUBTRAÇÃO
	@GetMapping("/sub/{numberOne}/{numberTwo}")
	public Double sub(@PathVariable(value= "numberOne") String numberOne, @PathVariable(value= "numberTwo") String numberTwo) throws Exception {
		
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		return math.sub(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}
	
	// MULTIPLICAÇÃO
	@GetMapping("/mult/{numberOne}/{numberTwo}")
	public Double mult(@PathVariable(value= "numberOne") String numberOne, @PathVariable(value= "numberTwo") String numberTwo) throws Exception {
		
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		return math.mult(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}
	
	// DIVISÃO
	@GetMapping("/div/{numberOne}/{numberTwo}")
	public Double div(@PathVariable(value= "numberOne") String numberOne, @PathVariable(value= "numberTwo") String numberTwo) throws Exception {
		
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		return math.div(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
	}
	
	// MÉDIA
	@PostMapping("/media")
	public Double media(@RequestBody List<Double> numbers) {
		
		if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("The list cannot be empty");
        }
		
		for (Double num : numbers) {
			if(!NumberConverter.isNumeric(num.toString())) {
				throw new UnsupportedMathOperationException("Please set a numeric value!");
			}
        }
		return math.media(numbers);
	}
	
	// RAIZ QUADRADA
	@GetMapping("/squ/{number}")
	public Double squ(@PathVariable(value= "number") String number) throws Exception {
		
		if(!NumberConverter.isNumeric(number)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		return math.squ(NumberConverter.convertToDouble(number));
	}
	
	
	
	
}
