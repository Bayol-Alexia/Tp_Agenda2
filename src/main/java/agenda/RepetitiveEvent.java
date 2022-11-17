package agenda;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * Description : A repetitive Event
 */
public class RepetitiveEvent extends Event {
    /**
     * Constructs a repetitive event
     *
     * @param title the title of this event
     * @param start the start of this event
     * @param duration myDuration in seconds
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     */
    protected ChronoUnit frequency;
    protected ArrayList<LocalDate> exceptionsDates = new ArrayList<LocalDate>();


    public RepetitiveEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency) {
        super(title, start, duration);
        this.frequency = frequency;
    }

    /**
     * Adds an exception to the occurrence of this repetitive event
     *
     * @param date the event will not occur at this date
     */
    public void addException(LocalDate date) {
        exceptionsDates.add(date);
    }

    public ArrayList<LocalDate> getExceptionsDates() {
        return exceptionsDates;
    }



    public boolean isInDay(LocalDate aDay){
        //initialisation de dateSup à la date de début de l'évènement
        LocalDate dateSup = getStart().toLocalDate();
        //vérification que le jour choisi se trouve après le jour de début
        if (aDay.isAfter(dateSup) || aDay.equals(dateSup)){
            while (aDay.isAfter(dateSup) || aDay.equals(dateSup)){
                if (dateSup.equals(aDay) && (!exceptionsDates.contains(aDay))){
                    return true;
                }
                dateSup=dateSup.plus(1, frequency);  
            }
        }
        return false;
    }

    /**
     *
     * @return the type of repetition
     */

    public ChronoUnit getFrequency() {
        return frequency;   
    }

}
