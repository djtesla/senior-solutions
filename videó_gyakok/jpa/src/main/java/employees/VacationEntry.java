package employees;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class VacationEntry {

    private LocalDate dateOfVacation;
    private int days;

    public VacationEntry(LocalDate dateOfVacation, int days) {
        this.dateOfVacation = dateOfVacation;
        this.days = days;
    }

    public VacationEntry() {
    }

    public LocalDate getDateOfVacation() {
        return dateOfVacation;
    }

    public void setDateOfVacation(LocalDate dateOfVacation) {
        this.dateOfVacation = dateOfVacation;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
