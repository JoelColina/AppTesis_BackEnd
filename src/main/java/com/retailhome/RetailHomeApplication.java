package com.retailhome;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RetailHomeApplication {

//	@Autowired
//	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {

		SpringApplication.run(RetailHomeApplication.class, args);
	}

//	@Bean
//	public RetailHomeApplication(PasswordEncoder passwordEncoder) {
//		this.passwordEncoder = passwordEncoder
//	}
//	@Bean
//	public Utils utils;

//	@Bean
//	public CommandLineRunner iniData(ClientRepository clientRepository
////							, AccountRepository accountRepository,
////									 TransactionRepository transactionRepository, LoanRepository loanRepository,
////									 ClientLoanRepository clientLoanRepository, CardRepository cardRepository
//	){
//		return (
//				args -> {
//
//					  Client client1 = new Client("Joel", "Colina", "Vargas", "melba@mindhub.com", this.passwordEncoder.encode("11111111"));
//					  Client client2 = new Client("Jose", "Salazar", "jonasalazarp@gmail.com",this.passwordEncoder.encode("22222222"));
//				 	  Client client3 = new Client("Danitza", "Alvarado", "danitza.alvaradobernales@gmail.com",this.passwordEncoder.encode("33333333"));

//				    clientRepository.save(client1);
//					clientRepository.save(client2);
//					clientRepository.save(client3);

//					  LocalDate fetchHow = LocalDate.now().plusDays(1);
//					  Account account1 = new Account("VIN001", fetchHow, 5000,  client1);
//					  Account account2 = new Account("VIN002", fetchHow, 15000,  client1);
//					  Account account3 = new Account("VIN003", fetchHow, 50000,  client2);
//					  Account account4 = new Account("VIN004", fetchHow, 150000,  client3);
//
//					accountRepository.save(account1);
//					accountRepository.save(account2);
//					accountRepository.save(account3);
//					accountRepository.save(account4);
//
//					  String tipTarjeta ="Transferencia recibida";
//
//					  Transaction transaction1 = new Transaction(TransactionType.CREDIT, 2000,tipTarjeta,fetchHow,account1);
//					  Transaction transaction2 = new Transaction(TransactionType.DEBIT,-1000, "Compra Tienda YX",fetchHow,account1);
//					  Transaction transaction3 = new Transaction(TransactionType.CREDIT,10000, tipTarjeta,fetchHow,account2);
//					  Transaction transaction4 = new Transaction(TransactionType.DEBIT,-50000, "Compra Tienda YA",fetchHow,account2);
//					  Transaction transaction5 = new Transaction(TransactionType.CREDIT,20000, tipTarjeta,fetchHow, account3);
//					  Transaction transaction6 = new Transaction(TransactionType.DEBIT,-1500, "Compra Tienda YY",fetchHow, account3);
//					  Transaction transaction7 = new Transaction(TransactionType.CREDIT,15000, tipTarjeta,fetchHow,account4);
//					  Transaction transaction8 = new Transaction(TransactionType.DEBIT,-10000, "Compra Tienda YZ",fetchHow,account4);
//
//					transactionRepository.save(transaction1);
//					transactionRepository.save(transaction2);
//					transactionRepository.save(transaction3);
//					transactionRepository.save(transaction4);
//					transactionRepository.save(transaction5);
//					transactionRepository.save(transaction6);
//					transactionRepository.save(transaction7);
//					transactionRepository.save(transaction8);
//
//						List<Integer> quotaH = List.of(12,24,36,48,60);
//						List<Integer> quotaP = List.of(6,12,24);
//						List<Integer> quotaA = List.of(6,12,24,36);
//
//						Loan loan1 = new Loan("Hipotecario",500000, quotaH);
//						Loan loan2 = new Loan("Personal",100000, quotaP);
//						Loan loan3 = new Loan("Automotriz",300000, quotaA);
//
//					loanRepository.save(loan1);
//					loanRepository.save(loan2);
//					loanRepository.save(loan3);
//
//						ClientLoan clientLoan1 = new ClientLoan(400000, 60, client1,loan1);
//						ClientLoan clientLoan2 = new ClientLoan(50000, 12, client1,loan2);
//
//						ClientLoan clientLoan3 = new ClientLoan(100000, 24, client2,loan2);
//						ClientLoan clientLoan4 = new ClientLoan(200000, 36, client2,loan3);
//
//					clientLoanRepository.save(clientLoan1);
//					clientLoanRepository.save(clientLoan2);
//					clientLoanRepository.save(clientLoan3);
//
//					clientLoanRepository.save(clientLoan4);
//
//					    LocalDate fetchEnd = fetchHow.plusYears(5);
//						int years = fetchEnd.getYear();
//						Month mes = fetchEnd.getMonth();
//
//						String fechaString = (Utils.getConvierteMes(mes) + "/" + years % 100);
//
//						SimpleDateFormat format = new SimpleDateFormat("M/yy");
//						Date thruDate = format.parse(fechaString);
//
//					Card card1 = new Card(CardType.DEBIT, "6666-9999-7777-8888", 369, fetchHow, thruDate, CardColor.GOLD, client1);
//					Card card2 = new Card(CardType.CREDIT, "9999-8888-7777-6666", 963, fetchHow, thruDate, CardColor.TITANIUM, client1);
//					Card card3 = new Card(CardType.CREDIT, "9999-5555-4444-3333", 258, fetchHow, thruDate, CardColor.SILVER, client2);
//
//						cardRepository.save(card1);
//						cardRepository.save(card2);
//						cardRepository.save(card3);
//
//				});
//	}
}
