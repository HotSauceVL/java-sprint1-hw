import java.util.HashMap;

public class StepTracker {
    HashMap <Integer, MonthData> monthToData = new HashMap<>();
    int target = 10000;
    MonthData monthData = new MonthData();

    public StepTracker(){
        for (int i = 0; i < 12; i++){
            monthToData.put (i, new MonthData());
        }
    }

    void changeTarget(int newTarget){
        target = newTarget;
        System.out.println("Цель изменена, текущая цель: " + target);
    }

    void addSteps(int month, int day, int steps){
        monthData = monthToData.get(month-1);
        monthData.steps[day-1] = steps;
        monthToData.put(month-1, monthData);
        System.out.println("Шаги добавлены");
    }

    int getStepsPerDay(int month, int day){
        monthData = monthToData.get(month-1);
        return monthData.steps[day];
    }

    int getSumStepsPerMonth(int month){
        monthData = monthToData.get(month-1);
        int sumSteps = 0;

        for (int i = 0; i < monthData.steps.length; i++){
            sumSteps += monthData.steps[i];
        }
        return sumSteps;
    }

    int getBestSeriesPerMonth(int month) {
        monthData = monthToData.get(month - 1);
        int currentSeries = 0;
        int maxSeries = 0;

        for (int i = 0; i < monthData.steps.length; i++) {
            if (monthData.steps[i] >= target) {
                currentSeries++;
                if (currentSeries > maxSeries) {
                    maxSeries = currentSeries;
                }
            } else {
                currentSeries = 0;
            }
        }
        return maxSeries;
    }

    int getDayMaxStepsPerMonth(int month){
        monthData = monthToData.get(month-1);
        int maxSteps = 0;
        int day = 0;

        for (int i = 0; i < monthData.steps.length; i++){
            if (monthData.steps[i] > maxSteps){
                maxSteps = monthData.steps[i];
                day = i;
            }
        }
        return day;
    }

    int getMaxStepsPerMonth(int month){
        monthData = monthToData.get(month-1);
        int day = getDayMaxStepsPerMonth(month);
        return monthData.steps[day];
    }

    double getMeanStepsPerMonth(int month){
        int steps = getSumStepsPerMonth(month);
        return (double) steps/30;
    }

    void getStatistics (int month){
        Converter converter = new Converter();

        System.out.println("Количество пройденных шагов по дням:");
        for (int i = 0; i<30; i++){
            if (i!=29) {
                System.out.print((i + 1) + " день: " + getStepsPerDay(month, i) + ", ");
            } else{
                System.out.println((i + 1) + " день: " + getStepsPerDay(month, i));
            }
        }
        System.out.println("Общее количество шагов за месяц: " + getSumStepsPerMonth(month));
        System.out.println("Максимальное количестов шагов пройдено " + (getDayMaxStepsPerMonth(month)+1) + " числа: " + getMaxStepsPerMonth(month));
        System.out.println("Среднее количество шагов за месяц: " + getMeanStepsPerMonth(month));
        System.out.println("Пройденная дистанция (в км): " + converter.convertToDistanseKm(getSumStepsPerMonth(month)));
        System.out.println("Количество сожжённых килокалорий: " + converter.convertToKKallories(getSumStepsPerMonth(month)));
        System.out.println("Лучшая серия: " + getBestSeriesPerMonth(month));
    }
}
