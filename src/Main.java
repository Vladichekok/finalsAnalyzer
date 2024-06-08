import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";// Указываем путь к CSV-файлу с данными о транзакциях.
        TransactionReader csvReader = new CSVTransactionReader();// Создаем экземпляр CSVTransactionReader, который реализует интерфейс TransactionReader.
        List<Transaction> transactions = csvReader.readTransactions(filePath);// Считываем транзакции из CSV-файла, возвращаем список объектов Transaction.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");// Определяем формат даты для последующего анализа.
        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions, formatter);// Создаем экземпляр TransactionAnalyzer, передавая в него список транзакций и форматтер для даты.
        TransactionReportGenerator reportGenerator = new TransactionReportGenerator();// Создаем экземпляр TransactionReportGenerator для генерации отчетов.
        double totalBalance = analyzer.calculateTotalBalance();// Рассчитываем общий баланс по всем транзакциям.
        reportGenerator.printBalanceReport(totalBalance); // Печатаем отчет о балансе.
        String monthYear = "02-2023";// Определяем месяц и год для анализа количества транзакций.
        int transactionsCount = analyzer.countTransactionsByMonth(monthYear, formatter);// Считаем количество транзакций за указанный месяц и год.
        reportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);// Печатаем отчет о количестве транзакций за указанный месяц.
        List<Transaction> topExpenses = analyzer.findTopExpenses();// Находим список транзакций с наибольшими расходами.
        reportGenerator.printTopExpensesReport(topExpenses);// Печатаем отчет о транзакциях с наибольшими расходами.
        reportGenerator.printTotalExpensesReport(transactions);// Печатаем отчет о всех расходах по транзакциям.
    }
}
