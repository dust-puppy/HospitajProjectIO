/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Maciej
 */
public class ATableModel extends AbstractTableModel {

    List wordsList;
    String headerList[] = new String[]{" ", "Aid", "Data", "Nazwa", "Producent", "Ilo��", "Sk�d", "Dok�d"};

    public ATableModel(List list) {
        wordsList = list;
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public int getRowCount() {
        return wordsList.size();
    }

// this method is called to set the value of each cell
    @Override
    public Object getValueAt(int row, int column) {
        ProductMovement entity = null;
        entity = (ProductMovement) wordsList.get(row);

        switch (column) {
            case 0:
                if(entity instanceof ProductOrder)
                {
                    return "[*]";
                }
                else if(entity instanceof ProductOut)
                {
                    return "[^]";
                }
                else return "[v]";
            case 1:
                return entity.id;
            case 2:
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                return sdf.format(entity.date);
            case 3:
                return entity.productQuantity.product.name;
            case 4:
                return entity.productQuantity.product.producer;
            case 5:
                return entity.productQuantity.quantity;
            case 6:
                if(entity instanceof ProductOrder && ((ProductOrder) entity).from != "")
                {
                    return ((ProductOrder) entity).from;
                }
                else if(entity instanceof ProductIn && ((ProductIn) entity).from != "")
                {
                    return ((ProductIn) entity).from;
                }
                return "Magazyn";
            case 7:
                if(entity instanceof ProductOrder && ((ProductOrder) entity).to != "")
                {
                    return ((ProductOrder) entity).to;
                }
                else if(entity instanceof ProductOut && ((ProductOut) entity).to != "")
                {
                    return ((ProductOut) entity).to;
                }
                return "Magazyn";
            default:

                return "";
        }
        //This method will be used to display the name of columns

    }

    @Override
    public String getColumnName(int col) {
        return headerList[col];
    }
    
    void removeRow(int selectedRow) {
        wordsList.remove(selectedRow);
        this.fireTableDataChanged();
    }

}
