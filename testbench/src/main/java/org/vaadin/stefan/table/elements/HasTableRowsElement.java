package org.vaadin.stefan.table.elements;

import java.util.List;

import com.vaadin.testbench.HasElementQuery;

/**
 * @author Stefan Uebe
 */
public interface HasTableRowsElement extends HasElementQuery {
    default List<TableRowElement> getRows() {
        return $(TableRowElement.class).all();
    }


}
