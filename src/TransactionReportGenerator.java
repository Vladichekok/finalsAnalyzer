import java.util.*;

public class TransactionReportGenerator {

    public void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }
    public void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }
    public void printTotalExpensesReport(List<Transaction> transactions) {
        System.out.println("Звіт по сумарним витратам:");
        Set<String> uniqueMonths = new HashSet<>();
        for (Transaction transaction : transactions) {
            String monthYear = transaction.getDate().substring(3);
            uniqueMonths.add(monthYear);
        }
        for (String monthYear : uniqueMonths) {
            System.out.println("Місяць: " + monthYear);
            double totalAmount = 0;
            Map<String, Double> expensesByCategory = new HashMap<>();
            for (Transaction transaction : transactions) {
                String currentMonthYear = transaction.getDate().substring(3);
                if (currentMonthYear.equals(monthYear)) {
                    double amount = transaction.getAmount();
                    totalAmount += amount;

                    String category = transaction.getDescription();
                    expensesByCategory.put(category, expensesByCategory.getOrDefault(category, 0.0) + amount);
                }
            }
            for (Map.Entry<String, Double> entry : expensesByCategory.entrySet()) {
                String category = entry.getKey();
                double amount = entry.getValue();
                int visualizedAmount = (int) Math.abs(amount / 1000);
                String visualization = "*".repeat(visualizedAmount);
                System.out.println(category + ": " + visualization);
            }

            System.out.println("Загальні витрати: " + totalAmount);
            System.out.println();
        }
    }

}

