/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package various;

/**
 *
 * @author Rajind
 */
import java.awt.Component;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
 
import javax.swing.AbstractCellEditor;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.text.DateFormatter;
 
public class DateCellEditor extends AbstractCellEditor implements
    TableCellEditor
{
  int selectedRow;
  int selectedColumn;
  JTable currentTable;
  Date currentDate;
  JSpinner spinner;
 
  protected static final String EDIT = "edit";
 
  public DateCellEditor()
  {
    Calendar calendar = Calendar.getInstance(); 
        calendar.set(Calendar.HOUR_OF_DAY, 24); // 24 == 12 PM == 00:00:00
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        
        SpinnerDateModel model = new SpinnerDateModel();
        model.setValue(calendar.getTime());

        JSpinner spinner = new JSpinner(model);

        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH : mm : ss : SSSS");
        DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false); // this makes what you want
        formatter.setOverwriteMode(true);

        spinner.setEditor(editor);
  }
 
  public Component getTableCellEditorComponent(JTable table, Object value,
      boolean isSelected, int row, int column)
  {
    spinner.setValue(value);
    currentTable = table;
    selectedRow = row;
    selectedColumn = column;
    return spinner;
  }
 
  public Object getCellEditorValue()
  {
    return spinner.getValue();
  }
 
  // from Sun tutorials:
  //http://java.sun.com/docs/books/tutorial/uiswing/components/table.html#editrender 
  static class DateRenderer extends DefaultTableCellRenderer
  {
    DateFormat formatter;
 
    public DateRenderer()
    {
      super();
    }
 
    public void setValue(Object value)
    {
      if (formatter == null)
      {
        formatter = DateFormat.getDateInstance();
      }
      setText((value == null) ? "" : formatter.format(value));
    }
  }
 
   
 
  private static void createAndShowUI()
  {
    Date[][] dates =
    {
        {new Date(Calendar.getInstance().getTimeInMillis())}
    };
    String[] titles =
    {
      "Date"
    };
    JTable table = new JTable(dates, titles);
    table.setDefaultRenderer(Object.class, new DateRenderer());
    table.setDefaultEditor(Object.class, new DateCellEditor());
     
    JFrame frame = new JFrame("DateCellEditor");
    frame.getContentPane().add(new JScrollPane(table));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
 
  public static void main(String[] args)
  {
    java.awt.EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        createAndShowUI();
      }
    });
  }
 
}