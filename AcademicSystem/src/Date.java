public class Date {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        this.setDay(day);
        this.setMonth(month);
        this.setYear(year);
    }

    public boolean posterior(Date date) {
        int age = date.getYear() - this.getYear();
        int month = date.getMonth() - this.getMonth();
        int day = date.getDay() - this.getDay();

        if (month == 0) {
            if (day < 0) {
                age -= 1;
            }
        } else if (month < 0) {
            age -= 1;
        }

        return age < 0;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Dia inválido");
        }
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Mês inválido");
        }
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year < 1900) {
            throw new IllegalArgumentException("Ano inválido");
        }
        this.year = year;
    }
}
