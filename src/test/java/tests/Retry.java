package tests;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    /*
    Класс Retry реализует интерфейс IRetryAnalyzer из TestNG.
     Позволяет перезапускать упавшие тесты заданное количество раз.
     */
    private int attempt = 1; // Текущее количество попыток выполнения теста
    private static final int MAX_RETRY = 1; // Максимальное количество попыток перезапуска

    @Override
    public boolean retry(ITestResult iTestResult) {
        // Проверяем, что тест упал
        if (!iTestResult.isSuccess()) {
            // Если попыток перезапуска еще осталось
            if (attempt < MAX_RETRY) {
                attempt++; // Увеличиваем счетчик попыток
                iTestResult.setStatus(ITestResult.FAILURE); // Устанавливаем статус FAILURE
                System.out.println("Retrying once again"); // Логируем информацию о перезапуске
                return true; // Возвращаем true - тест будет перезапущен
            } else {
                // Если лимит попыток исчерпан
                iTestResult.setStatus(ITestResult.FAILURE); // Окончательно фиксируем провал теста
            }
        } else {
            // Если тест прошел успешно
            iTestResult.setStatus(ITestResult.SUCCESS); // Устанавливаем статус SUCCESS
        }
        return false; // Возвращаем false - перезапуск не требуется
    }
}
