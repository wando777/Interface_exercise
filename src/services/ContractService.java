package services;

import java.util.Calendar;
import java.util.Date;

import entities.Contract;
import entities.Installments;

public class ContractService {

	private OnlinePaymentServices onlinePaymentService;

	public ContractService() {
	}

	public ContractService(OnlinePaymentServices onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processContract(Contract contract, int months) {
		double basicQuota = contract.getTotalValue() / months;

		for (int i = 1; i <= months; i++) {
			Date date = addMonths(contract.getDate(), i);
			double updateQuota = basicQuota + onlinePaymentService.interest(basicQuota, i);
			double fullQuota = updateQuota + onlinePaymentService.paymentFee(updateQuota);
			contract.addInstallments(new Installments(date, fullQuota));
		}
	}

	// This method adds months to the current date
	private Date addMonths(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}
}
