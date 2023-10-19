public class Date {
    // Private member variables to store the day, month, and year.
    private int day;
    private int month;
    private int year;

    // Constructor to initialize the Date object with provided day, month, and year.
    public Date(int day, int month, int year) {
        this.setDay(day);
        this.setMonth(month);
        this.setYear(year);
    }

    // Method to check if the current date is posterior (later) than the provided date.
    public boolean posterior(Date date) {
        // Calculate the difference in years, months, and days.
        int age = date.getYear() - this.getYear();
        int month = date.getMonth() - this.getMonth();
        int day = date.getDay() - this.getDay();

        // Adjust the age based on months and days.
        if (month == 0) {
            if (day < 0) {
                age -= 1;
            }
        } else if (month < 0) {
            age -= 1;
        }

        // If age is non-negative, the date is not posterior.
        if (age >= 0) {
            return false;
        } else {
            return true;
        }
    }

    // Getter and setter methods for day, month, and year.

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
