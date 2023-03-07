
package util;

import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import model.Task;
/**
 *
 * @author Natanael
 */
public class DeadlineColumnCellRender extends DefaultTableCellRenderer{
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelect, boolean hasFocus, int row, int col){
        //devolve um component que vai ser mostrado na celula
        
        JLabel label = (JLabel) super.getTableCellRendererComponent(table,
                value, isSelect, hasFocus, row, col);
        
        label.setHorizontalAlignment(CENTER);      
        
        TaskTableModel taskModel =(TaskTableModel) table.getModel();
        Task task = taskModel.getTasks().get(row);
        
        if (task.getDeadline().after(new Date())){
            label.setBackground(Color.GREEN);
        }
        else{
            label.setBackground(Color.RED);
        }
            
        return label;
    }
    
}
