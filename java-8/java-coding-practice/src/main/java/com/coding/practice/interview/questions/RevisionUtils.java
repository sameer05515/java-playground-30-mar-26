package com.coding.practice.interview.questions;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RevisionUtils {

  /**
   * Finds revision dates for a given month based on createdDate and revisionAfter days.
   *
   * @param createdDate The date when revision starts.
   * @param revisionAfter Interval in days between each revision.
   * @param targetMonth Target YearMonth (e.g. YearMonth.of(2025, 9)).
   * @return List of LocalDate revisions in the given month.
   */
  public static List<LocalDate> getRevisionDatesForMonth(
      LocalDate createdDate, int revisionAfter, YearMonth targetMonth) {
    List<LocalDate> revisions = new ArrayList<>();
    LocalDate date = createdDate;

    // Keep generating dates until we pass the end of targetMonth
    LocalDate monthStart = targetMonth.atDay(1);
    LocalDate monthEnd = targetMonth.atEndOfMonth();

    while (!date.isAfter(monthEnd)) {
      if (!date.isBefore(monthStart)) {
        revisions.add(date);
      }
      date = date.plusDays(revisionAfter);
    }

    return revisions;
  }

  public static final DateTimeFormatter ddMMMyyyyDateFormatter =
      DateTimeFormatter.ofPattern("yyyy-MM-dd");

  // Example usage
  public static void main(String[] args) {
    //    LocalDate createdDate = LocalDate.of(2025, 8, 18);
    LocalDate createdDate = LocalDate.parse("2025-08-18", ddMMMyyyyDateFormatter);
    int revisionAfter = 3;
    //    YearMonth yearMonth = YearMonth.now();
    YearMonth yearMonth = YearMonth.of(2025, 9);

    List<LocalDate> revisions = getRevisionDatesForMonth(createdDate, revisionAfter, yearMonth);
    List<LocalDate> dupRevisions = new ArrayList<>();
    dupRevisions.addAll(revisions);
    dupRevisions.addAll(getRevisionDatesForMonth(createdDate, revisionAfter, yearMonth));

    System.out.println(dupRevisions.stream().distinct().collect(Collectors.toList()));
    // Output: [2025-09-01, 2025-09-03, 2025-09-05, ... , 2025-09-29]
  }
}
