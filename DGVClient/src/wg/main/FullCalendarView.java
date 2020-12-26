package wg.main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

import javax.swing.GroupLayout.Alignment;

public class FullCalendarView {
	private ArrayList<AnchorPaneNode> allCalendarDays = new ArrayList<>();
    private VBox view;
    private Text calendarTitle;
    private YearMonth currentYearMonth;

    /**
     * Create a calendar view
     * @param yearMonth year month to create the calendar of
     */
    public FullCalendarView(YearMonth yearMonth) {
        currentYearMonth = yearMonth;
        
        // Create the calendar grid pane
        GridPane calendar = new GridPane();
        calendar.setStyle("-fx-border-color:white;");
        calendar.setPrefSize(800, 600);
        calendar.setGridLinesVisible(true);
        
        // Create rows and columns with anchor panes for the calendar
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                AnchorPaneNode ap = new AnchorPaneNode();
                ap.setPrefSize(200,200);
                calendar.add(ap,j,i);
                allCalendarDays.add(ap);
            }
        }
        // Days of the week labels
//        Text[] dayNames = new Text[]{ new Text("일요일"), new Text("월요일"), new Text("화요일"),
//                                        new Text("수요일"), new Text("목요일"), new Text("금요일"),
//                                        new Text("토요일") };
        
        String[] strDayNames = new String[]{
        		"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"
        };
        
        GridPane dayLabels = new GridPane();
        dayLabels.setPrefWidth(600);
        Integer col = 0;
        //for (Text txt : dayNames) {
        for (String str : strDayNames) {
        	Label txt = new Label(str);
        	txt.setPrefWidth(70);
        	txt.setTextAlignment(TextAlignment.CENTER);
        	if(txt.getText().equals("Sun")) {
        		txt.setStyle("-fx-text-fill:red; -fx-text-inner-color: black;");
        	}else if(txt.getText().equals("Sat")) {
        		txt.setStyle("-fx-text-fill:blue; -fx-text-inner-color: black;");        		
        	}else {
        		txt.setStyle("-fx-text-fill:black; -fx-text-inner-color: black;");        		
        	}
        	
            AnchorPane ap = new AnchorPane();
            ap.setPrefSize(210, 20);
            AnchorPane.setBottomAnchor(txt, 5.0);
            ap.getChildren().add(txt);
            ap.setStyle("-fx-background-color:white;-fx-border-color:#ede6e6;"); //SUN~SAT
            dayLabels.add(ap, col++, 0);
        }
        // Create calendarTitle and buttons to change current month
        calendarTitle = new Text();
        
        Button previousMonth = new Button("<");
        previousMonth.setOnAction(e -> previousMonth());
        previousMonth.setPadding(new Insets(5, 10, 5, 10));
        previousMonth.setStyle("-fx-border-color:#ede6e6;");
        
        Button nextMonth = new Button(">");
        nextMonth.setOnAction(e -> nextMonth());
        nextMonth.setPadding(new Insets(5, 10, 5, 10));
        nextMonth.setStyle("-fx-border-color:#ede6e6;");
        
        HBox titleBar = new HBox(previousMonth, calendarTitle, nextMonth);
        titleBar.setAlignment(Pos.BASELINE_CENTER);
        titleBar.setPadding(new Insets(5));
        titleBar.setSpacing(10);
        titleBar.setStyle("-fx-background-color:white;-fx-border-color:#ede6e6;");
        
        // Populate calendar with the appropriate day numbers
        populateCalendar(yearMonth);
        // Create the calendar view
        view = new VBox(titleBar, dayLabels, calendar);
        
    }

    /**
     * Set the days of the calendar to correspond to the appropriate date
     * @param yearMonth year and month of month to render
     */
    public void populateCalendar(YearMonth yearMonth) {
        // Get the date we want to start with on the calendar
        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
        // Dial back the day until it is SUNDAY (unless the month starts on a sunday)
        int days = 0;
        int lastDay = calendarDate.lengthOfMonth();
        
        while (!calendarDate.getDayOfWeek().toString().equals("SUNDAY") ) {
            calendarDate = calendarDate.minusDays(1);
            days--;
        }
        
        // Populate the calendar with day numbers
        for (AnchorPaneNode ap : allCalendarDays) {
            if (ap.getChildren().size() != 0) {
                ap.getChildren().remove(0);
            }
            //Text txt = new Text(String.valueOf(calendarDate.getDayOfMonth()));
            Label txt = new Label(String.valueOf(calendarDate.getDayOfMonth()));
            ap.setDate(calendarDate);
            AnchorPane.setTopAnchor(txt, 5.0);
            AnchorPane.setLeftAnchor(txt, 5.0);
            ap.getChildren().add(txt);
            
            // 요일별로 배경색 설정
            if(calendarDate.getDayOfWeek().toString().equals("SUNDAY")){
            	txt.setStyle("-fx-text-fill:red; -fx-text-base-color: green;");
            	ap.setStyle("-fx-background-color:white; -fx-border-color:#ede6e6;");
            }else if(calendarDate.getDayOfWeek().toString().equals("SATURDAY")){
            	txt.setStyle("-fx-text-fill:blue; -fx-text-base-color: green;");
            	ap.setStyle("-fx-background-color:white; -fx-border-color:#ede6e6;");
            }else {
            	ap.setStyle("-fx-background-color:white; -fx-border-color:#ede6e6;");
            }
            
            // 이전달 날짜와 다음달 날짜 배경색 변경
            if(days<0 || days>=lastDay){
            	ap.setStyle("-fx-background-color:#f2d2b3; -fx-border-color:#ede6e6;");
            }
            
            calendarDate = calendarDate.plusDays(1);
            days++;
        }
        // Change the title of the calendar
        calendarTitle.setText(String.valueOf(yearMonth.getYear() + "년 " + yearMonth.getMonthValue() + "월" ));
    }

    /**
     * Move the month back by one. Repopulate the calendar with the correct dates.
     */
    private void previousMonth() {
        currentYearMonth = currentYearMonth.minusMonths(1);
        populateCalendar(currentYearMonth);
    }

    /**
     * Move the month forward by one. Repopulate the calendar with the correct dates.
     */
    private void nextMonth() {
        currentYearMonth = currentYearMonth.plusMonths(1);
        populateCalendar(currentYearMonth);
    }

    public VBox getView() {
        return view;
    }

    public ArrayList<AnchorPaneNode> getAllCalendarDays() {
        return allCalendarDays;
    }

    public void setAllCalendarDays(ArrayList<AnchorPaneNode> allCalendarDays) {
        this.allCalendarDays = allCalendarDays;
    }

}
