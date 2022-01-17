import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StepTracker stepTracker = new StepTracker();

        while (true){
            printMenu();
            int menuCommand = getUserIntInput();

            if (menuCommand == 1){
                int month = printMonthMenu();
                System.out.println("За какое число вы хотите добавить шаги (число от 1 до 30)");
                int day = getUserIntInput();
                day = checkMenu(day, 1, 30);
                System.out.println("Введите количество шагов:");
                int steps = getUserIntInput();
                steps = checkSteps(steps);
                stepTracker.addSteps(month, day, steps);
            } else if (menuCommand == 2){
                int month = printMonthMenu();
                stepTracker.getStatistics(month);
            } else if (menuCommand == 3){
                System.out.println("Введите новую цель:");
                int target = getUserIntInput();
                target = checkSteps(target);
                stepTracker.changeTarget(target);
            } else if (menuCommand == 0){
                break;
            } else {
                System.out.println("Извините, данная команда еще не реализована");
            }
        }
    }

    public static int getUserIntInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static int checkSteps(int steps){
        while (true){
            if (steps >= 0) {
                break;
            } else {
                System.out.println("Некорректный ввод. Количество шагов не может отрицательным. Введите количество шагов:");
                steps = getUserIntInput();
            }
        }
        return steps;
    }

    public static int checkMenu(int userInput, int minItem, int maxItem){
        while (true){
            if ((userInput >= minItem) && (userInput <= maxItem)){
                break;
            } else {
                System.out.println("Некорректный ввод. Выберите пункт меню от " + minItem + " до " + maxItem);
                userInput = getUserIntInput();
            }
        }
        return userInput;
    }

    public static void printMenu(){
        System.out.println("Выберите действие:");
        System.out.println("1 - Ввести количество шагов за день");
        System.out.println("2 - Напечатать статистику за месяц");
        System.out.println("3 - Изменить цель");
        System.out.println("0 - Выход");
    }

    public static int printMonthMenu(){
        System.out.println("Выберите месяц:");
        for (int i = 0; i < 12; i++){
            System.out.println((i + 1) + " - " + getMonth(i));
        }
        int month = getUserIntInput();
        month = checkMenu(month, 1, 12);
        return month;
    }

    public static String getMonth(int monthNumber){
        String [] monthName = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Октябрь", "Декабрь"};
        return monthName[monthNumber];
    }
}
