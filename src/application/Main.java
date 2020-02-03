package application;

import java.text.ParseException;
import java.util.Locale;

import br.edu.unifacisa.si.p2.input.Input;
import entities.Contract;
import entities.Installments;
import services.ContractService;
import services.PaypalService;

public class Main {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);

		System.out.println("Enter contract data:");
		Contract contract = new Contract(Input.leInt("Number: "), Input.leDate("Date (dd/MM/yyyy): "),
				Input.leDouble("Total value: "));
		
		ContractService contractService = new ContractService(new PaypalService());
		
		contractService.processContract(contract, Input.leInt("Enter number of Installments: "));
		
		//Printing installments
		System.out.println("Installments:");
		for (Installments i : contract.getInstallments()) {
			System.out.println(i);
		}
	}

}
